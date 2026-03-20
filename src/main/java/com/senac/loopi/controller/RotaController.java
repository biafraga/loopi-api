package com.senac.loopi.controller;

import com.senac.loopi.entity.Rota;
import com.senac.loopi.service.RotaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rotas")
public class RotaController {
    private final RotaService rotaService;

    public RotaController(RotaService rotaService){
        this.rotaService = rotaService;
    }

    // GET /api/rotas
    @GetMapping
    public List<Rota> listarRotas(){
        return rotaService.listarRotas();
    }

    // GET /api/rotas/1
    @GetMapping("/{id}")
    public Rota obterRotaPeloId(@PathVariable Integer id){
        return rotaService.obterRotaPeloId(id);
    }

    //POST /api/rotas
    @PostMapping
    public Rota adicionarRota(@RequestBody Rota rota){
        return rotaService.salvarRota(rota);
    }

    // PUT /api/rotas/1
    @PutMapping("/{id}")
    public Rota atualizarRota(@PathVariable Integer id, @RequestBody Rota rota){
        rota.setId(id);
        return rotaService.salvarRota(rota);
    }

    // DELETE /api/rotas/1
    @DeleteMapping("/{id}")
    public void deletarRota(@PathVariable Integer id){
        rotaService.deletarRota(id);
    }
}
