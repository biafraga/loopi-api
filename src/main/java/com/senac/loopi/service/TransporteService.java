package com.senac.loopi.service;

import com.senac.loopi.entity.Transporte;
import com.senac.loopi.repository.TransporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransporteService {
    private final TransporteRepository transporteRepository;

    public TransporteService(TransporteRepository transporteRepository){
        this.transporteRepository = transporteRepository;
    }

    //criar ou alterar transporte
    public Transporte salvarTransporte(Transporte transporte){
        return transporteRepository.save(transporte);
    }

    // listar transportes
    public List<Transporte> listarTransportes(){
        return transporteRepository.findAll();
    }

    //procurar transporte pelo id
    public Transporte obterTransportePeloId(Integer id){
        return transporteRepository.findById(id).orElse(null);
    }

    //deletar transporte
    public void deletarTransporte(int id){
        transporteRepository.deleteById(id);
    }
}
