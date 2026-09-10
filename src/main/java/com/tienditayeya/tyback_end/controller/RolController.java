package com.tienditayeya.tyback_end.controller;

import com.tienditayeya.tyback_end.model.Rol;
import com.tienditayeya.tyback_end.service.RolService;
import com.tienditayeya.tyback_end.dto.RolDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController // con el restcontroller vamos a poder utilizar metodos get,post,put y delete
@RequestMapping("/api/roles") //dirección url donde vamos a poder mandar peticiones http

public class RolController {

    // el controlador necesita una instancia de la capa de servicios
    // en esete caso el RolService se necesita inyectar al RolController,por eso se agrega al constructor
    // esto permite trabajar localmente con los meteodos del servicio
    public final RolService rolService;
    private RolController(RolService rolService){
        this.rolService = rolService;
    }

    /**
     * Creamos el metodo listar para consultar la entidad completa
     */
    @GetMapping
    /*la anotacion significa que ya vamos a poder usar una peticion tipo get
    a la url y regresara la informacion de la tabla */

    public ResponseEntity<List<RolDTO>> listar(){
        List<Rol> roles = rolService.listar();
        List<RolDTO> rolesDTO = roles.stream()
                .map(rol -> new RolDTO(rol.getIdRol(), rol.getRolUsuario()))
                .toList();

        return ResponseEntity.ok(rolesDTO);
    }

    /**
     * GET localhost:8080/api/roles/{id}
     */
     @GetMapping("/{id}")
    public ResponseEntity<RolDTO> obtenerPorId(@PathVariable Long id) {
         return rolService.obtenerPorId(id)
                 .map(rol -> new RolDTO(rol.getIdRol(), rol.getRolUsuario()))
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
     }

    /**
     *POST MAPPING para crear un nuevo rol
     */

    @PostMapping //es para crear o insertar datos
    public ResponseEntity<RolDTO> crear(@Valid @RequestBody RolDTO rolDTO){
         Rol nuevoRol = new Rol();
         nuevoRol.setRolUsuario(rolDTO.getRolUsuario());
         Rol rolGuardado = rolService.crear(nuevoRol);
         RolDTO respuestaDTO = new RolDTO(rolGuardado.getIdRol(), rolGuardado.getRolUsuario());

         return ResponseEntity.created(URI.create("/api/roles/" + respuestaDTO.getIdRol())).body(respuestaDTO);

    }

    /**
     * PUT MAPPING para updates
     */

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,@Valid @RequestBody RolDTO rolDTO){
        try{
            Rol rolParaActualizar = new Rol();
            rolParaActualizar.setRolUsuario(rolDTO.getRolUsuario());
            Rol rolActualizado = rolService.actualizar(id, rolParaActualizar);
            RolDTO respuestaDTO = new RolDTO(rolActualizado.getIdRol(), rolActualizado.getRolUsuario());
            return ResponseEntity.ok(respuestaDTO);
        }
        catch (IllegalArgumentException ex) {

            // Si el error es porque me equivoqué de ID -> 404 Not Found
            if (ex.getMessage().equals("Rol no encontrado")) {
                return ResponseEntity.notFound().build();
            }

            // Si el error es porque repetí el nombre -> 400 Bad Request
            return ResponseEntity.badRequest().body(ex.getMessage());
        }


    }

    /**
     * solo lo hacemos por id para eliminar un registro
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Rol> eliminar(@PathVariable Long id){
        try{
            rolService.eliminar(id);
            return ResponseEntity.noContent().build(); // estado 204 porque se eliminó el registro
        }
        catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build(); // estado 404
        }
    }



}
