package com.tienditayeya.tyback_end.service;

import com.tienditayeya.tyback_end.model.Rol;
import com.tienditayeya.tyback_end.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.*; // se usa (List & Optional)

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    //Métodos

    /**
     *Listar los valores de la tabla Rol
     */
    public List<Rol> listar(){
        return rolRepository.findAll();
    }
    /**
     *Busca y muestra el rol por id
     */
    public Optional<Rol> obtenerPorId(Long id){
        if(!rolRepository.existsById(id)) {
            throw new IllegalArgumentException("Id no encontrado");
        }
        return rolRepository.findById(id);
    }
    /**
     *Actualiza el tipo de rol
     */
    public Rol actualizar(Long id, Rol rol){
        Rol actual = rolRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Rol no encontrado"));

        if(!actual.getRolUsuario().equals(rol.getRolUsuario()) &&
        rolRepository.existsByRolUsuario(rol.getRolUsuario())){
            throw new IllegalArgumentException("El rol ya existe");
        }

        actual.setRolUsuario(rol.getRolUsuario());
        return rolRepository.save(actual);
    }
    /**
     *Elimina un registro de la tabla
     */
    public void eliminar(Long id){
        if(!rolRepository.existsById(id)){
            throw new IllegalArgumentException("Rol no encontrado");
        }
        rolRepository.deleteById(id);

    }
    /**
     * Agregar un registro a la tabla
     */
    public Rol crear(Rol rol){
        if(rolRepository.existsByRolUsuario(rol.getRolUsuario())){
            throw new IllegalArgumentException("El rol ya existe");
        }
        return rolRepository.save(rol);


    }


}
