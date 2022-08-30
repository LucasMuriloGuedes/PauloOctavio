package com.join.boletoiptupauloocatvio.services;

import com.join.boletoiptupauloocatvio.domain.ImovelEmissaoIptu;
import com.join.boletoiptupauloocatvio.repositories.ImovelEmissaoIptuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ImovelEmissaoIptuService {

    @Autowired
    private ImovelEmissaoIptuRepository repository;

    public List<ImovelEmissaoIptu> findAll(){
        return repository.findAll();
    }

    public ImovelEmissaoIptu findById(Integer id){
        Optional<ImovelEmissaoIptu> obj =  repository.findById(id);
        return obj.orElse(null);
    }

    public ImovelEmissaoIptu findByImovel(String imovel){
        return repository.findByNumeroImovelEquals(imovel);
    }

    public void insert(ImovelEmissaoIptu imovelEmissaoIptu){
        if(!imovelEmissaoIptu.getEmpreendimento().equals("") || !imovelEmissaoIptu.getNumeroImovel().equals("")){
            imovelEmissaoIptu.setId(null);
            repository.save(imovelEmissaoIptu);
        }
    }
    public void update(ImovelEmissaoIptu obj) {
        ImovelEmissaoIptu newObj = findById(obj.getId());
        newObj.setNumeroImovel(obj.getNumeroImovel());
        newObj.setEmpreendimento(obj.getEmpreendimento());
        repository.save(newObj);
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }

    public Page<ImovelEmissaoIptu> findPaginatedAll(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ImovelEmissaoIptu> list;

        if (findAll().size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, findAll().size());
            list = findAll().subList(startItem, toIndex);
        }

        Page<ImovelEmissaoIptu> imovelList
                = new PageImpl<ImovelEmissaoIptu>(list, PageRequest.of(currentPage, pageSize), findAll().size());

        return imovelList;
    }

    public Page<ImovelEmissaoIptu> findPaginatedEmprendimento(Pageable pageable, String tipoEmpreendimento) {

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ImovelEmissaoIptu> list;
        System.out.println("Tipo de Empreendimento: " + tipoEmpreendimento);
        List<ImovelEmissaoIptu> listImovelEmpreendimento = repository.findByEmpreendimentoEquals(tipoEmpreendimento);
        if (listImovelEmpreendimento.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listImovelEmpreendimento.size());
            list = listImovelEmpreendimento.subList(startItem, toIndex);
        }

        Page<ImovelEmissaoIptu> imovelList
                = new PageImpl<ImovelEmissaoIptu>(list, PageRequest.of(currentPage, pageSize), listImovelEmpreendimento.size());

        return imovelList;
    }
}
