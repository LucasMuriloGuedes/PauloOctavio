package com.join.boletoiptupauloocatvio.repositories;

import com.join.boletoiptupauloocatvio.domain.ControleProcessamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ControleProcessamentoRepository extends JpaRepository<ControleProcessamento, Integer> {
   ControleProcessamento findByMesAndAnoEquals(String ano, String mes);

   List<ControleProcessamento> findByOrderByMes();
}
