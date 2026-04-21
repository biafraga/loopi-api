package com.senac.loopi.controller;

import com.senac.loopi.model.usuario.DadosAtualizacaoUsuario;
import com.senac.loopi.model.usuario.DadosCadastroUsuario;
import com.senac.loopi.model.usuario.DadosDetalhamentoUsuario;
import com.senac.loopi.model.usuario.Usuario;
import com.senac.loopi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    // GET /api/usuarios
    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoUsuario>> listarUsuarios(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        Page<DadosDetalhamentoUsuario> page = usuarioService.listarUsuarios(paginacao)
                .map(DadosDetalhamentoUsuario::new);

        return ResponseEntity.ok(page);
    }

    // GET /api/usuarios/1
    @GetMapping("/{id}")
    public DadosDetalhamentoUsuario obterUsuarioPeloId(@PathVariable Integer id){
        Usuario usuario = usuarioService.obterUsuarioPeloId(id);

        return new DadosDetalhamentoUsuario(usuario);
    }

    //POST /api/usuarios
    @PostMapping
    public DadosDetalhamentoUsuario adicionarUsuario(@RequestBody @Valid DadosCadastroUsuario dados){
        // Entidade 'Usuario' vazia para receber os dados
        Usuario novoUsuario = new Usuario();

        // Copiamos os dados autorizados do DTO para a Entidade
        // Lembrando: no Record, não usamos "get", chamamos o atributo direto com parênteses
        novoUsuario.setNome(dados.nome());
        novoUsuario.setEmail(dados.email());
        novoUsuario.setSenha(dados.senha());

        novoUsuario.setStatus(1); // Todo usuário novo nasce com status 1 (Ativo)

        Usuario usuarioSalvo = usuarioService.salvarUsuario(novoUsuario);

        return new DadosDetalhamentoUsuario(usuarioSalvo);
    }

    // PUT /api/usuarios/1
    @PutMapping("/{id}")
    public DadosDetalhamentoUsuario atualizarUsuario
    (@PathVariable Integer id, @RequestBody DadosAtualizacaoUsuario dados){
        // Primeiro vamos buscar no banco o usuário que já existe pelo ID
        Usuario usuarioExistente = usuarioService.obterUsuarioPeloId(id);

        // dados novos que vieram do DTO
        usuarioExistente.setNome(dados.nome());
        usuarioExistente.setEmail(dados.email());
        usuarioExistente.setSenha(dados.senha());
        // Obs: a gente NÃO mexe no status nem no ID. Eles continuam intactos no banco!

        //Mandamos salvar essa atualização
        Usuario usuarioAtualizado = usuarioService.salvarUsuario(usuarioExistente);

        //Devolvemos os dados para a tela usando nosso filtro que esconde a senha
        return new DadosDetalhamentoUsuario(usuarioAtualizado);

    }

    // DELETE /api/usuarios/1
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Integer id){
        usuarioService.deletarUsuario(id);
    }
}
