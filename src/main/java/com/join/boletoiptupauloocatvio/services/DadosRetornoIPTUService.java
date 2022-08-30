package com.join.boletoiptupauloocatvio.services;

import com.join.boletoiptupauloocatvio.domain.DadosRetornoIptu;
import com.join.boletoiptupauloocatvio.repositories.DadosRetornoIPTURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DadosRetornoIPTUService {

    @Autowired
    private DadosRetornoIPTURepository repository;

    public List<DadosRetornoIptu> findByImovel(String imovel){
        List<DadosRetornoIptu> obj = repository.findByImovelEquals(imovel);
        return obj;
    }

    public void insert(List<DadosRetornoIptu> listRetorno){
        repository.saveAll(listRetorno);
    }
}
