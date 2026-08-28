package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.model.Usuario;
import com.tienditayeya.tyback_end.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthSessionService {
    private static final Duration DURACION_SESION = Duration.ofHours(8);
    private final Map<String, SesionUsuario> sesiones = new ConcurrentHashMap<>();
    private final UsuarioRepository usuarioRepository;

    public AuthSessionService(UsuarioRepository usuarioRepository){this.usuarioRepository=usuarioRepository;}

    public String crearSesion(Usuario usuario) {
        String token = UUID.randomUUID().toString();
        sesiones.put(token, desdeUsuario(usuario, Instant.now().plus(DURACION_SESION)));
        return token;
    }

    public SesionUsuario requerirSesion(String token) {
        if (token == null || token.isBlank()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Debes iniciar sesión");
        String limpio=token.trim();
        SesionUsuario sesion = sesiones.get(limpio);
        if (sesion == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sesión inválida o cerrada");
        if (Instant.now().isAfter(sesion.expiraEn())) {
            sesiones.remove(limpio);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "La sesión expiró. Inicia sesión nuevamente");
        }

        // Relee el rol desde MySQL: una revocación de admin surte efecto de inmediato, incluso con token ya abierto.
        Usuario usuario = usuarioRepository.findById(sesion.idUsuario()).orElseThrow(() -> {
            sesiones.remove(limpio);
            return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "El usuario de esta sesión ya no existe");
        });
        SesionUsuario actualizada = desdeUsuario(usuario, sesion.expiraEn());
        sesiones.put(limpio, actualizada);
        return actualizada;
    }

    public SesionUsuario requerirAdmin(String token) {
        SesionUsuario sesion = requerirSesion(token);
        if (!"admin".equalsIgnoreCase(sesion.rol())) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Se requieren permisos de administrador");
        return sesion;
    }

    public void cerrarSesion(String token) { if (token != null && !token.isBlank()) sesiones.remove(token.trim()); }
    public void cerrarSesionesDeUsuario(Long idUsuario){sesiones.entrySet().removeIf(e->e.getValue().idUsuario().equals(idUsuario));}

    private SesionUsuario desdeUsuario(Usuario usuario, Instant expiraEn){
        String rol=usuario.getRol()!=null?usuario.getRol().getRolUsuario():"user";
        return new SesionUsuario(usuario.getIdUsuario(),usuario.getNombreCompleto(),usuario.getEmail(),rol,expiraEn);
    }

    public record SesionUsuario(Long idUsuario,String nombreCompleto,String email,String rol,Instant expiraEn){}
}
