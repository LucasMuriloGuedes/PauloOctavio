package com.join.boletoiptupauloocatvio.repositories;

import com.join.boletoiptupauloocatvio.domain.ImovelEmissaoIptu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImovelEmissaoIptuRepository extends JpaRepository<ImovelEmissaoIptu, Integer> {

    List<ImovelEmissaoIptu> findByEmpreendimentoEquals(String tipoEmpreendiemento);
    ImovelEmissaoIptu findByNumeroImovelEquals(String imovel);
}
