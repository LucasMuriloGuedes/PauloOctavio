package com.join.boletoiptupauloocatvio.services;

import com.join.boletoiptupauloocatvio.domain.Empreendimento;
import com.join.boletoiptupauloocatvio.repositories.EmpreendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpreendimentoService {

    @Autowired
    private EmpreendimentoRepository repository;

    public Empreendimento findById(Integer id){
        Optional<Empreendimento> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public List<Empreendimento> findAll(){
        return repository.findAll();
    }

    public void insert(Empreendimento obj){
        repository.save(obj);
    }

    public void delete(Integer id) {
        Empreendimento obj = findById(id);
        repository.delete(obj);
    }

    public void update(Empreendimento obj) {
        Empreendimento newObj = findById(obj.getId());
        newObj.setNome(obj.getNome());
        newObj.setCnpj(obj.getCnpj());
        newObj.setEmpreed(obj.getEmpreed());
        newObj.setRua(obj.getRua());
        newObj.setNumero(obj.getNumero());
        newObj.setCidade(obj.getCidade());
        newObj.setEstado(obj.getEstado());
        newObj.setBanco(obj.getBanco());
        newObj.setAgencia(obj.getAgencia());
        newObj.setAgencia(obj.getConta());
        repository.save(newObj);
    }
}
