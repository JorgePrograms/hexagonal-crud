package com.codigo.semana10.infraestructure.entity;


import com.codigo.semana10.domain.model.Persona;
import com.codigo.semana10.domain.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name="nombre_usuario")
    private String nombreUsuario;
    private String contrasenia;
    @Column (name = "correo_electronico")
    private String correoElectronico;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id")
    private PersonaEntity personaEntity;


    public static UsuarioEntity fromDomainModel(Usuario usuario){
        if(usuario==null){
            return null;
        }
        return new UsuarioEntity(usuario.getId(),usuario.getNombreUsuario(),usuario.getContrasenia(),usuario.getCorreoElectronico(),PersonaEntity.fromDomainModel(usuario.getPersona()));
    }

    public Usuario toDomainModel(){
       Persona persona = (personaEntity != null) ? personaEntity.toDomainModel() : null;

        return new Usuario(id,nombreUsuario,contrasenia,correoElectronico,persona);
    }




}
