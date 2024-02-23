package com.codigo.semana10.infraestructure.repository;

import com.codigo.semana10.domain.model.Usuario;
import com.codigo.semana10.domain.ports.out.UsuarioRepositoryPort;
import com.codigo.semana10.infraestructure.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioJpaRepositoryAdapater implements UsuarioRepositoryPort {
    private final UsuarioJpaRepository usuarioJpaRepository;
    public UsuarioJpaRepositoryAdapater(UsuarioJpaRepository usuarioJpaRepository){
        this.usuarioJpaRepository=usuarioJpaRepository;
    }
    @Override
    public Usuario save(Usuario usuario){
        UsuarioEntity usuarioEntity=UsuarioEntity.fromDomainModel(usuario);
        UsuarioEntity saveUsuarioEntity=usuarioJpaRepository.save(usuarioEntity);
        return saveUsuarioEntity.toDomainModel();
    }
    @Override
    public Optional<Usuario> findById(Long id){
        return usuarioJpaRepository.findById(id)
                .map(UsuarioEntity::toDomainModel);
    }
    @Override
    public Optional<Usuario> update(Long id, Usuario usuario){
        if (usuarioJpaRepository.existsById(id)) {
            UsuarioEntity usuarioEntity=UsuarioEntity.fromDomainModel(usuario);
            UsuarioEntity updateUsuarioEntity=usuarioJpaRepository.save(usuarioEntity);
            return Optional.of(updateUsuarioEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id){
        if(usuarioJpaRepository.existsById(id)){
            usuarioJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
