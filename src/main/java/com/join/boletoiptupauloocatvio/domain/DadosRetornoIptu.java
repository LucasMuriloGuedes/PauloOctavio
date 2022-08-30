package com.join.boletoiptupauloocatvio.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DadosRetornoIptu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imovel;
    private String anoExercicio;
    private String cotaReferencia;
    private String valor;
    private String linhaDigitavel;
    private String dataVencimento;

}
