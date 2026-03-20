package com.senac.loopi.controller;

import com.senac.loopi.entity.Alerta;
import com.senac.loopi.service.AlertaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/alertas")
public class AlertaController {
    private final AlertaService alertaService;

    public AlertaController(AlertaService alertaService){
        this.alertaService = alertaService;
    }

    // GET /api/alertas
    @GetMapping
    public List<Alerta> listarAlertas(){
        return alertaService.listarAlertas();
    }

    // GET /api/alertas/1
    @GetMapping("/{id}")
    public Alerta obterAlertaPeloId(@PathVariable Integer id){
        return alertaService.obterAlertaPeloId(id);
    }

    //POST /api/alertas
    @PostMapping
    public Alerta adicionarAlerta(@RequestBody Alerta alerta){
        return alertaService.salvarAlerta(alerta);
    }

    // PUT /api/alertas/1
    @PutMapping("/{id}")
    public Alerta atualizarAlerta(@PathVariable Integer id, @RequestBody Alerta alerta){
        alerta.setId(id);
        return alertaService.salvarAlerta(alerta);
    }

    // DELETE /api/alertas/1
    @DeleteMapping("/{id}")
    public void deletarAlerta(@PathVariable Integer id){
        alertaService.deletarAlerta(id);
    }
}
