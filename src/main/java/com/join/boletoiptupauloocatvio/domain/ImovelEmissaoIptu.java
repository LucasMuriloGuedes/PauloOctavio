package com.join.boletoiptupauloocatvio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ImovelEmissaoIptu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numeroImovel;
    private String empreendimento;

    public ImovelEmissaoIptu(){

    }

    public ImovelEmissaoIptu(Integer id, String numeroImovel, String empreendimento) {
        this.id = id;
        this.numeroImovel = numeroImovel;
        this.empreendimento = empreendimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroImovel() {
        return numeroImovel;
    }

    public void setNumeroImovel(String numeroImovel) {
        this.numeroImovel = numeroImovel;
    }

    public String getEmpreendimento() {
        return empreendimento;
    }

    public void setEmpreendimento(String empreendimento) {
        this.empreendimento = empreendimento;
    }

    @Override
    public String toString() {
        return "ImovelEmissaoIptu{" +
                "id=" + id +
                ", numeroImovel='" + numeroImovel + '\'' +
                ", empreendimento='" + empreendimento + '\'' +
                '}';
    }
}
