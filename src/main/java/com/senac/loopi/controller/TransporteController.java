package com.senac.loopi.controller;

import com.senac.loopi.model.rota.Rota;
import com.senac.loopi.model.transporte.DadosAtualizacaoTransporte;
import com.senac.loopi.model.transporte.DadosCadastroTransporte;
import com.senac.loopi.model.transporte.DadosDetalhamentoTransporte;
import com.senac.loopi.model.transporte.Transporte;
import com.senac.loopi.service.RotaService;
import com.senac.loopi.service.TransporteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transportes")
@RequiredArgsConstructor
public class TransporteController {
    private final TransporteService transporteService;
    private final RotaService rotaService;

    // GET /api/transportes
    @GetMapping
    public List<DadosDetalhamentoTransporte> listarTransportes(){
        return transporteService.listarTransportes().stream()
                .map(DadosDetalhamentoTransporte::new)
                .toList();
    }

    // GET /api/transportes/1
    @GetMapping("/{id}")
    public DadosDetalhamentoTransporte obterTransportePeloId(@PathVariable Integer id){
        Transporte transporte = transporteService.obterTransportePeloId(id);
        return new DadosDetalhamentoTransporte(transporte);
    }

    //POST /api/transportes
    @PostMapping
    public DadosDetalhamentoTransporte adicionarTransporte(@RequestBody @Valid DadosCadastroTransporte dados){
        Transporte novoTransporte = new Transporte();
        novoTransporte.setTipo(dados.tipo());
        novoTransporte.setStatus(1); // Nasce ativo

        // Busca a rota no banco
        Rota rota = rotaService.obterRotaPeloId(dados.rotaId());
        if (rota == null) {
            throw new RuntimeException("Erro: Essa rota não existe no banco de dados!");
        }

        // Amarra ao transporte
        novoTransporte.setRota(rota);

        Transporte transporteSalvo = transporteService.salvarTransporte(novoTransporte);
        return new DadosDetalhamentoTransporte(transporteSalvo);
    }

    // PUT /api/transportes/
    @PutMapping("/{id}")
    public DadosDetalhamentoTransporte atualizarTransporte(@PathVariable Integer id, @RequestBody @Valid DadosAtualizacaoTransporte dados){
        Transporte transporteExistente = transporteService.obterTransportePeloId(id);

        transporteExistente.setTipo(dados.tipo());

        Transporte transporteAtualizado = transporteService.salvarTransporte(transporteExistente);
        return new DadosDetalhamentoTransporte(transporteAtualizado);
    }

    // DELETE /api/transportes/1
    @DeleteMapping("/{id}")
    public void deletarTransporte(@PathVariable Integer id){
        transporteService.deletarTransporte(id);
    }
}
