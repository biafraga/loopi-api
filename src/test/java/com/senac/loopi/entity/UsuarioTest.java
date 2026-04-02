package com.senac.loopi.entity;

import com.senac.loopi.model.usuario.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    @Test
    void deveCriarUsuarioComSucesso(){
        //Arrange
        Usuario usuario = new Usuario();

        //Act
        usuario.setNome("Bia Fraga");
        usuario.setEmail("bia@teste.com");
        usuario.setStatus(1);

        //Assert
        assertEquals("Bia Fraga", usuario.getNome());
        assertEquals("bia@teste.com", usuario.getEmail());
        assertEquals(1, usuario.getStatus());
    }

}