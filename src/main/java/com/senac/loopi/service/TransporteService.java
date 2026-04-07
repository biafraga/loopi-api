package com.senac.loopi.service;

import com.senac.loopi.model.transporte.Transporte;
import com.senac.loopi.repository.TransporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransporteService {
    private final TransporteRepository transporteRepository;

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

    //soft delete
    public void deletarTransporte(Integer id){
        Transporte transporte = obterTransportePeloId(id);
        if (transporte != null) {
            transporte.setStatus(0); // inativo
            transporteRepository.save(transporte);
        }
    }
}
