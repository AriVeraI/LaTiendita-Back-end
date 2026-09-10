package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.dto.LoginRequest;
import com.tienditayeya.tyback_end.dto.AdminCreateRequest;
import com.tienditayeya.tyback_end.dto.RegistroUsuarioRequest;
import com.tienditayeya.tyback_end.dto.UsuarioResponse;
import com.tienditayeya.tyback_end.model.Rol;
import com.tienditayeya.tyback_end.model.Usuario;
import com.tienditayeya.tyback_end.repository.RolRepository;
import com.tienditayeya.tyback_end.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthSessionService authSessionService;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          RolRepository rolRepository,
                          PasswordEncoder passwordEncoder,
                          AuthSessionService authSessionService) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.authSessionService = authSessionService;
    }

    @Transactional
    public UsuarioResponse registrar(RegistroUsuarioRequest request) {
        String correo = request.correo().trim().toLowerCase();
        String telefono = request.telefono().trim();

        if (usuarioRepository.existsByEmailIgnoreCase(correo)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo ya está registrado");
        }
        if (usuarioRepository.existsByTelefono(telefono)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El teléfono ya está registrado");
        }

        Rol rolUsuario = rolRepository.findByRolUsuarioIgnoreCase("user")
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "No existe el rol 'user' en la base de datos"
                ));

        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(request.nombre().trim());
        usuario.setEmail(correo);
        usuario.setTelefono(telefono);
        usuario.setPassword(passwordEncoder.encode(request.clave()));
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setRol(rolUsuario);

        return toResponse(usuarioRepository.save(usuario), null);
    }


    @Transactional
    public UsuarioResponse registrarAdmin(AdminCreateRequest request) {
        String correo = request.correo().trim().toLowerCase();
        String telefono = request.telefono().trim();
        if (usuarioRepository.existsByEmailIgnoreCase(correo)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo ya está registrado");
        }
        if (usuarioRepository.existsByTelefono(telefono)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El teléfono ya está registrado");
        }
        Rol rolAdmin = rolRepository.findByRolUsuarioIgnoreCase("admin")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No existe el rol admin"));
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(request.nombre().trim());
        usuario.setEmail(correo);
        usuario.setTelefono(telefono);
        usuario.setPassword(passwordEncoder.encode(request.clave()));
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setRol(rolAdmin);
        return toResponse(usuarioRepository.save(usuario), null);
    }


    @Transactional
    public UsuarioResponse cambiarRol(Long idUsuario, String nombreRol) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        Rol rol = rolRepository.findByRolUsuarioIgnoreCase(nombreRol)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol no válido"));
        usuario.setRol(rol);
        return toResponse(usuarioRepository.save(usuario), null);
    }

    @Transactional(readOnly = true)
    public UsuarioResponse login(LoginRequest request) {
        Usuario usuario = validarCredenciales(request);
        String token = authSessionService.crearSesion(usuario);
        return toResponse(usuario, token);
    }

    @Transactional(readOnly = true)
    public UsuarioResponse loginAdmin(LoginRequest request) {
        Usuario usuario = validarCredenciales(request);
        if (usuario.getRol() == null || !"admin".equalsIgnoreCase(usuario.getRol().getRolUsuario())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El usuario no tiene permisos de administrador");
        }
        String token = authSessionService.crearSesion(usuario);
        return toResponse(usuario, token);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll().stream().map(usuario -> toResponse(usuario, null)).toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponse obtenerUsuarioSesion(AuthSessionService.SesionUsuario sesion) {
        Usuario usuario = usuarioRepository.findById(sesion.idUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "El usuario de la sesión ya no existe"));
        return toResponse(usuario, null);
    }

    private Usuario validarCredenciales(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(request.correo().trim())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));

        if (!passwordEncoder.matches(request.clave(), usuario.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }
        return usuario;
    }

    private UsuarioResponse toResponse(Usuario usuario, String sessionToken) {
        return new UsuarioResponse(
                usuario.getIdUsuario(),
                usuario.getNombreCompleto(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getFechaRegistro(),
                usuario.getRol() != null ? usuario.getRol().getRolUsuario() : null,
                sessionToken
        );
    }
}
