package com.senac.loopi.service;

import com.senac.loopi.model.usuario.Usuario;
import com.senac.loopi.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        // 1. Arrange (Preparar os dados e treinar o dublê)
        Usuario usuarioParaSalvar = new Usuario();
        usuarioParaSalvar.setNome("Bia Fraga");
        usuarioParaSalvar.setEmail("bia@teste.com");

        Usuario usuarioSalvoMock = new Usuario();
        usuarioSalvoMock.setId(1);
        usuarioSalvoMock.setNome("Bia Fraga");
        usuarioSalvoMock.setEmail("bia@teste.com");

        //Mockito!
        when(usuarioRepository.save(usuarioParaSalvar)).thenReturn(usuarioSalvoMock);

        // 2. Act
        Usuario resultado = usuarioService.salvarUsuario(usuarioParaSalvar);

        // 3. Assert
        assertNotNull(resultado); // Garante que a resposta não veio vazia
        assertEquals(1, resultado.getId()); // Garante que pegou o ID falso que criamos
        assertEquals("Bia Fraga", resultado.getNome()); // Confere o nome

        // Verificação extra de nível sênior:
        // Confirma se o service realmente chamou o 'save' do repository exatamente 1 vez
        verify(usuarioRepository, times(1)).save(usuarioParaSalvar);
    }

    @Test
    void deveObterUsuarioPorIdComSucesso() {
        // 1. Arrange
        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(1);
        usuarioMock.setNome("Bia Fraga");

        // Pode ter um usuário dentro ou estar vazia.
        when(usuarioRepository.findById(1)).thenReturn(java.util.Optional.of(usuarioMock));

        // 2. Act
        Usuario resultado = usuarioService.obterUsuarioPeloId(1);

        // 3. Assert
        assertNotNull(resultado); // Garante que não veio vazio
        assertEquals(1, resultado.getId());
        assertEquals("Bia Fraga", resultado.getNome());

        // Verifica se o Service realmente mandou o Repository procurar o ID 1
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void deveRetornarNuloQuandoUsuarioNaoExistir() {
        // 1. Arrange
        // Ensinando o dublê: Quando procurarem o ID 99, devolva uma caixa vazia (Optional.empty)
        when(usuarioRepository.findById(99)).thenReturn(java.util.Optional.empty());

        // 2. Act
        Usuario resultado = usuarioService.obterUsuarioPeloId(99);

        // 3. Assert
        assertNull(resultado); // Garante que o resultado é nulo, já que o usuário não existe

        verify(usuarioRepository, times(1)).findById(99);
    }

    @Test
    void deveListarTodosOsUsuarios() {
        // 1. Arrange
        Usuario u1 = new Usuario();
        u1.setId(1);
        u1.setNome("Bia");

        Usuario u2 = new Usuario();
        u2.setId(2);
        u2.setNome("Vitor Hugo");

        // Ensinando o dublê: quando chamarem o findAll, devolva essa lista com 2 usuários
        when(usuarioRepository.findAll()).thenReturn(java.util.List.of(u1, u2));

        // 2. Act
        java.util.List<Usuario> listaResultado = usuarioService.listarUsuarios();

        // 3. Assert
        assertNotNull(listaResultado);
        assertEquals(2, listaResultado.size()); // Garante que vieram exatamente 2 na lista
        assertEquals("Bia", listaResultado.get(0).getNome()); // Confere o primeiro

        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void deveDeletarUsuarioComSucesso() {
        // 2. Act
        usuarioService.deletarUsuario(1); // Mandamos deletar o ID 1

        // 3. Assert
        // A nossa única verificação é: o Service chamou o deleteById do Repository para o ID 1?
        verify(usuarioRepository, times(1)).deleteById(1);
    }

}