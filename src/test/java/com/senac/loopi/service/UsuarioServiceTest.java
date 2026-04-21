package com.senac.loopi.service;

import com.senac.loopi.model.usuario.Usuario;
import com.senac.loopi.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveSalvarUsuarioComSucesso() {
        Usuario usuarioParaSalvar = new Usuario();
        usuarioParaSalvar.setNome("Bia Fraga");

        Usuario usuarioSalvoMock = new Usuario();
        usuarioSalvoMock.setId(1);
        usuarioSalvoMock.setNome("Bia Fraga");

        when(usuarioRepository.save(usuarioParaSalvar)).thenReturn(usuarioSalvoMock);

        Usuario resultado = usuarioService.salvarUsuario(usuarioParaSalvar);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(usuarioRepository, times(1)).save(usuarioParaSalvar);
    }

    @Test
    void deveObterUsuarioPorIdComSucesso() {
        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(1);
        usuarioMock.setNome("Bia Fraga");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioMock));

        Usuario resultado = usuarioService.obterUsuarioPeloId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void deveRetornarNuloQuandoUsuarioNaoExistir() {
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        Usuario resultado = usuarioService.obterUsuarioPeloId(99);

        assertNull(resultado);
        verify(usuarioRepository, times(1)).findById(99);
    }

    // --- TESTE ATUALIZADO PARA PAGINAÇÃO ---
    @Test
    void deveListarUsuariosAtivosComPaginacao() {
        // 1. Arrange
        Usuario u1 = new Usuario();
        u1.setId(1);
        u1.setNome("Bia");

        Usuario u2 = new Usuario();
        u2.setId(2);
        u2.setNome("Vitor Hugo");

        // Cria uma paginação falsa (Página 0, tamanho 10)
        Pageable paginacaoMock = PageRequest.of(0, 10);

        // Cria uma "Página Falsa" (PageImpl) com a nossa lista
        Page<Usuario> paginaFalsa = new PageImpl<>(List.of(u1, u2));

        // Ensina o dublê: quando chamarem findByStatus(1), devolva a página falsa
        when(usuarioRepository.findByStatus(1, paginacaoMock)).thenReturn(paginaFalsa);

        // 2. Act
        Page<Usuario> resultado = usuarioService.listarUsuarios(paginacaoMock);

        // 3. Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.getTotalElements()); // Verifica o total de elementos na página
        assertEquals("Bia", resultado.getContent().get(0).getNome()); //getContent() abre a página para lermos a lista

        verify(usuarioRepository, times(1)).findByStatus(1, paginacaoMock);
    }

    // --- TESTE ATUALIZADO PARA SOFT DELETE ---
    @Test
    void deveDeletarUsuarioComSucesso() {
        // 1. Arrange
        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(1);
        usuarioMock.setStatus(1); // Começa ativo

        // Ensina o dublê a devolver o usuário quando o Service for buscar o ID 1
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioMock));

        // 2. Act
        usuarioService.deletarUsuario(1);

        // 3. Assert
        assertEquals(0, usuarioMock.getStatus()); // Garante que a regra de negócio mudou para zero

        // Verifica se ele buscou e depois se ele SALVOU a alteração
        verify(usuarioRepository, times(1)).findById(1);
        verify(usuarioRepository, times(1)).save(usuarioMock);
    }
}