package com.join.boletoiptupauloocatvio.services;

import com.join.boletoiptupauloocatvio.domain.ControleProcessamento;
import com.join.boletoiptupauloocatvio.repositories.ControleProcessamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControleProcessamentoService {

    @Autowired
    private ControleProcessamentoRepository repository;

    public List<ControleProcessamento> findAll(){
        return repository.findAll();
    }

    public List<ControleProcessamento> findByOrderByMes(){
        return repository.findByOrderByMes();
    }

    public void insert(ControleProcessamento obj){
       repository.save(obj);
    }

    public ControleProcessamento findByAnoMes(String ano, String mes){
        ControleProcessamento obj = repository.findByMesAndAnoEquals(ano, mes);
        return obj;
    }
}
