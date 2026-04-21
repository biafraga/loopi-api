package com.senac.loopi.controller;

import com.senac.loopi.model.rota.DadosAtualizacaoRota;
import com.senac.loopi.model.rota.DadosCadastroRota;
import com.senac.loopi.model.rota.DadosDetalhamentoRota;
import com.senac.loopi.model.rota.Rota;
import com.senac.loopi.model.usuario.Usuario;
import com.senac.loopi.service.RotaService;
import com.senac.loopi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/rotas")
@RequiredArgsConstructor
public class RotaController {
    private final RotaService rotaService;
    private final UsuarioService usuarioService;

    // GET /api/rotas
    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoRota>> listarRotas(
            @PageableDefault(size = 10, sort = {"apelido"}) Pageable paginacao){
        Page<DadosDetalhamentoRota> page = rotaService.listarRotas(paginacao)
                .map(DadosDetalhamentoRota::new);

        return ResponseEntity.ok(page);
    }


    // GET /api/rotas/1
    @GetMapping("/{id}")
    public DadosDetalhamentoRota obterRotaPeloId(@PathVariable Integer id){
        Rota rota = rotaService.obterRotaPeloId(id);
        return new DadosDetalhamentoRota(rota);
    }

    //POST /api/rotas
    @PostMapping
    public DadosDetalhamentoRota adicionarRota(@RequestBody @Valid DadosCadastroRota dados){
        Rota novaRota = new Rota();
        novaRota.setApelido(dados.apelido());
        novaRota.setOrigem(dados.origem());
        novaRota.setDestino(dados.destino());
        novaRota.setLatitudeOrigem(dados.latitudeOrigem());
        novaRota.setLongitudeOrigem(dados.longitudeOrigem());
        novaRota.setLatitudeDestino(dados.latitudeDestino());
        novaRota.setLongitudeDestino(dados.longitudeDestino());
        novaRota.setStatus(1); //Rota vai nascer ativa

        // Aqui buscamos o usuário real no banco antes de salvar
        Usuario dono = usuarioService.obterUsuarioPeloId(dados.usuarioId());
        novaRota.setUsuario(dono);

        Rota rotaSalva = rotaService.salvarRota(novaRota);
        return new DadosDetalhamentoRota(rotaSalva);
    }

    // PUT /api/rotas/1
    @PutMapping("/{id}")
    public DadosDetalhamentoRota atualizarRota(@PathVariable Integer id, @RequestBody @Valid DadosAtualizacaoRota dados){
        Rota rotaExistente = rotaService.obterRotaPeloId(id);

        rotaExistente.setApelido(dados.apelido());
        rotaExistente.setOrigem(dados.origem());
        rotaExistente.setDestino(dados.destino());
        rotaExistente.setLatitudeOrigem(dados.latitudeOrigem());
        rotaExistente.setLongitudeOrigem(dados.longitudeOrigem());
        rotaExistente.setLatitudeDestino(dados.latitudeDestino());
        rotaExistente.setLongitudeDestino(dados.longitudeDestino());

        Rota rotaAtualizada = rotaService.salvarRota(rotaExistente);
        return new DadosDetalhamentoRota(rotaAtualizada);
    }

    // DELETE /api/rotas/1
    @DeleteMapping("/{id}")
    public void deletarRota(@PathVariable Integer id){
        rotaService.deletarRota(id);
    }
}
