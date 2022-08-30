package com.join.boletoiptupauloocatvio.repositories;

import com.join.boletoiptupauloocatvio.domain.DadosRetornoIptu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DadosRetornoIPTURepository extends JpaRepository<DadosRetornoIptu, Integer> {

    List<DadosRetornoIptu> findByImovelEquals(String imovel);
}
