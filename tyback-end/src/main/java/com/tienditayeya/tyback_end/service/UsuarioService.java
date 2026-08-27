package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.model.Rol;
import com.tienditayeya.tyback_end.model.Usuario;
import com.tienditayeya.tyback_end.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    //Métodos

    /**
     *Listar los valores de la tabla usuario
     */
    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }
    /**
     *Busca y muestra el rol por id
     */
    public Optional<Usuario> obtenerPorId(Long id){
        if(!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Id no encontrado");
        }
        return usuarioRepository.findById(id);
    }
    /**
     * Agregar un registro a la tabla
     */
    public Usuario crear(Usuario usuario){
        usuarioRepository.findByEmail(usuario.getEmail()).ifPresent(emailEncontrado -> {
            throw new IllegalArgumentException("Ya existe un usario con ese email");
        });
        return usuarioRepository.save(usuario);
    }
    /**
     *Actualiza al usuario
     */
    public Usuario actualizar(Long id, Usuario usuario){
        Usuario actual = usuarioRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Usuario no encontrado"));
        usuarioRepository.findByEmail(usuario.getEmail()).ifPresent(emailEncontrado -> {
            if(!emailEncontrado.getIdUsuario().equals(id)){
                throw new IllegalArgumentException("Ya existe un usuario con ese email");
            }
        });

        actual.setNombreCompleto(usuario.getNombreCompleto());
        actual.setEmail(usuario.getEmail());
        actual.setTelefono(usuario.getTelefono());
        actual.setPassword(usuario.getPassword());
        actual.setFechaRegistro(usuario.getFechaRegistro());
        actual.setRol(usuario.getRol());
        return usuarioRepository.save(actual);
    }
    /**
     *Elimina un registro de la tabla
     */
    public void eliminar(Long id){
        if(!usuarioRepository.existsById(id)){
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);

    }




}
