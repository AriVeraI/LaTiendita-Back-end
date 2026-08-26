package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.dto.UsuarioRequestDTO;
import com.tienditayeya.tyback_end.dto.UsuarioResponseDTO;
import com.tienditayeya.tyback_end.model.Usuario;
import com.tienditayeya.tyback_end.service.UsuarioService;
import com.tienditayeya.tyback_end.service.RolService;
import com.tienditayeya.tyback_end.model.Rol;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController // con el restcontroller vamos a poder utilizar metodos get,post,put y delete
@RequestMapping("/api/usuarios") //dirección url donde vamos a poder mandar peticiones http

public class UsuarioController {

    // el controlador necesita una instancia de la capa de servicios
    // en esete caso el UsuarioService se necesita inyectar al UsuarioController,por eso se agrega al constructor
    // esto permite trabajar localmente con los meteodos del servicio
    public final UsuarioService usuarioService;
    public final RolService rolService;
    private UsuarioController(UsuarioService usuarioService,RolService rolService){
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    /**
     * Creamos el metodo listar para consultar la entidad completa
     */
    @GetMapping
    /*la anotacion significa que ya vamos a poder usar una peticion tipo get
    a la url y regresara la informacion de la tabla */
    public ResponseEntity<List<UsuarioResponseDTO>> listar(){
        List<Usuario> usuarios = usuarioService.listar();

        List<UsuarioResponseDTO> usuarioDTO = usuarios.stream()
                .map(u-> new UsuarioResponseDTO(
                        u.getIdUsuario(),
                        u.getNombreCompleto(),
                        u.getEmail(),
                        u.getTelefono(),
                        u.getFechaRegistro(),
                        u.getRol().getIdRol()
                ))
                .toList();
        return ResponseEntity.ok(usuarioDTO);
    }

    /**
     * GET localhost:8080/api/usuarios{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id)
                .map(u-> new UsuarioResponseDTO(
                        u.getIdUsuario(),
                        u.getNombreCompleto(),
                        u.getEmail(),
                        u.getTelefono(),
                        u.getFechaRegistro(),
                        u.getRol().getIdRol()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     *POST MAPPING para crear un nuevo rol
     */

    @PostMapping //es para crear o insertar datos
    public ResponseEntity<?> crear(@Valid @RequestBody UsuarioRequestDTO requestDTO){
        try{
        //Buscar el objeto Rol real con el Id del DTO
        Rol rolAsignado = rolService.obtenerPorId(requestDTO.getIdRol())
                .orElseThrow(()-> new IllegalArgumentException("Rol no encontrado"));

        // Transformar el DTO en entidad
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreCompleto(requestDTO.getNombreCompleto());
        nuevoUsuario.setEmail(requestDTO.getEmail());;
        nuevoUsuario.setTelefono(requestDTO.getTelefono());
        nuevoUsuario.setPassword(requestDTO.getPassword());
        nuevoUsuario.setFechaRegistro(requestDTO.getFechaRegistro());
        nuevoUsuario.setRol(rolAsignado);

        Usuario creado = usuarioService.crear(nuevoUsuario);

            UsuarioResponseDTO respuestaDTO = new UsuarioResponseDTO(
                creado.getIdUsuario(),
                creado.getNombreCompleto(),
                creado.getEmail(),
                creado.getTelefono(),
                creado.getFechaRegistro(),
                creado.getRol().getIdRol()
        );
        return ResponseEntity.created(URI.create("/api/usuarios" + creado.getIdUsuario())).body(respuestaDTO);
        }
        catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * PUT MAPPING para updates
     */

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,@Valid @RequestBody UsuarioRequestDTO requestDTO){
        try{
            Rol rolAsignado = rolService.obtenerPorId(requestDTO.getIdRol())
                    .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));
            Usuario usuarioParaActualizar = new Usuario();
            usuarioParaActualizar.setNombreCompleto(requestDTO.getNombreCompleto());
            usuarioParaActualizar.setEmail(requestDTO.getEmail());
            usuarioParaActualizar.setTelefono(requestDTO.getTelefono());
            usuarioParaActualizar.setPassword(requestDTO.getPassword());
            usuarioParaActualizar.setFechaRegistro(requestDTO.getFechaRegistro());
            usuarioParaActualizar.setRol(rolAsignado);

            Usuario usuarioActualizado = usuarioService.actualizar(id, usuarioParaActualizar);

            UsuarioResponseDTO respuestaDTO = new UsuarioResponseDTO(
                    usuarioActualizado.getIdUsuario(),
                    usuarioActualizado.getNombreCompleto(),
                    usuarioActualizado.getEmail(),
                    usuarioActualizado.getTelefono(),
                    usuarioActualizado.getFechaRegistro(),
                    usuarioActualizado.getRol().getIdRol()
            );
            return ResponseEntity.ok(respuestaDTO);
        }
        catch (IllegalArgumentException ex) {
            if (ex.getMessage().equals("Usuario no encontrado") || ex.getMessage().equals("Rol no encontrado")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * solo lo hacemos por id para eliminar un registro
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        try{
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build(); // estado 204 porque se eliminó el registro
        }
        catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build(); // estado 404
        }
    }

}
