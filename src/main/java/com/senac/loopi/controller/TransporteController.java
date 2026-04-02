package com.senac.loopi.controller;

import com.senac.loopi.model.transporte.Transporte;
import com.senac.loopi.service.TransporteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transportes")
public class TransporteController {
    private final TransporteService transporteService;

    public TransporteController(TransporteService transporteService){
        this.transporteService = transporteService;
    }

    // GET /api/transportes
    @GetMapping
    public List<Transporte> listarTransportes(){
        return transporteService.listarTransportes();
    }

    // GET /api/transportes/1
    @GetMapping("/{id}")
    public Transporte obterTransportePeloId(@PathVariable Integer id){
        return transporteService.obterTransportePeloId(id);
    }

    //POST /api/transportes
    @PostMapping
    public Transporte adicionarTransporte(@RequestBody Transporte transporte){
        return transporteService.salvarTransporte(transporte);
    }

    // PUT /api/transportes/
    @PutMapping("/{id}")
    public Transporte atualizarTransporte(@PathVariable Integer id, @RequestBody Transporte transporte){
        transporte.setId(id);
        return transporteService.salvarTransporte(transporte);
    }

    // DELETE /api/transportes/1
    @DeleteMapping("/{id}")
    public void deletarTransporte(@PathVariable Integer id){
        transporteService.deletarTransporte(id);
    }
}
