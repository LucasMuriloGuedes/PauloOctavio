package com.join.boletoiptupauloocatvio.domain;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmitirDAR implements Serializable {


    private String DarSislanca;
    private String DarDividaAtiva;
    private String DebitoIPVA;
    private DebitoIPTU DebitoIPTU;
    private String DebitoISS;
    private String DebitoParcelamento;

}
