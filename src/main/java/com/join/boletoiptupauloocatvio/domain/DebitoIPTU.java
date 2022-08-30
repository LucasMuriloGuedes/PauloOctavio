package com.join.boletoiptupauloocatvio.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebitoIPTU implements Serializable {

    private String AnoExercicio;
    private String Tributo;
    private String Imovel;
    private String Pgto;
    private List<String> DarCodigoReceita = new ArrayList<>();
    private List<String> DarCotaReferencia = new ArrayList<>();
    private String DarDataVencimento;
    private String DarPlacaChassi;
    private String DarNumProcesso;
    private List<String> DarNomeRazaoSocial = new ArrayList<>();
    private String DarCpfCnpj;
    private List<String> DarEndereco = new ArrayList<>();
    private String DarUnidadeAdministrativa;
    private String DarEspecificaoReceita;
    private String DarReservadoSef;
    private String DarValorUPDF;
    private String DarInformacoesPrevistas;
    private List<String> DarValorPrincipal = new ArrayList<>();
    private List<String> DarValorMulta = new ArrayList<>();
    private List<String> DarJurosMora = new ArrayList<>();
    private List<String> DarValorOutros = new ArrayList<>();
    private String LinhaCodigoBarras;
    private List<String> DarValorTotal = new ArrayList<>();
    private String DarMensagem ;
    private String Mensagem;
    private String EnderecoCorrespondencia;
    private String CepCorrespondencia;
    private String BairroCorrespondencia;
    private String MunicipioCorrespondencia;
    private String UFCorrespondencia;
    private String DarCidade;
    private String DarTelefone;
    private String SiglaTributo;
    private String Situacao;
    private String Asterisco;
    private String CodMensagem;
    private String CDA;
    private String DesricaoReceita;
    private String TipoDebito;


    @Override
    public String toString() {
        return "DebitoIPTU{" +
                "AnoExercicio='" + AnoExercicio + '\'' +
                ", Tributo='" + Tributo + '\'' +
                ", Imovel='" + Imovel + '\'' +
                ", Pgto='" + Pgto + '\'' +
                ", DarCodigoReceita=" + DarCodigoReceita +
                ", DarCotaReferencia=" + DarCotaReferencia +
                ", DarDataVencimento='" + DarDataVencimento + '\'' +
                ", DarPlacaChassi='" + DarPlacaChassi + '\'' +
                ", DarNumProcesso='" + DarNumProcesso + '\'' +
                ", DarNomeRazaoSocial=" + DarNomeRazaoSocial +
                ", DarCpfCnpj='" + DarCpfCnpj + '\'' +
                ", DarEndereco=" + DarEndereco +
                ", DarUnidadeAdministrativa='" + DarUnidadeAdministrativa + '\'' +
                ", DarEspecificaoReceita='" + DarEspecificaoReceita + '\'' +
                ", DarReservadoSef='" + DarReservadoSef + '\'' +
                ", DarValorUPDF='" + DarValorUPDF + '\'' +
                ", DarInformacoesPrevistas='" + DarInformacoesPrevistas + '\'' +
                ", DarValorPrincipal=" + DarValorPrincipal +
                ", DarValorMulta=" + DarValorMulta +
                ", DarJurosMora=" + DarJurosMora +
                ", DarValorOutros=" + DarValorOutros +
                ", LinhaCodigoBarras='" + LinhaCodigoBarras + '\'' +
                ", DarValorTotal=" + DarValorTotal +
                ", DarMensagem='" + DarMensagem + '\'' +
                ", Mensagem='" + Mensagem + '\'' +
                ", EnderecoCorrespondencia='" + EnderecoCorrespondencia + '\'' +
                ", CepCorrespondencia='" + CepCorrespondencia + '\'' +
                ", BairroCorrespondencia='" + BairroCorrespondencia + '\'' +
                ", MunicipioCorrespondencia='" + MunicipioCorrespondencia + '\'' +
                ", UFCorrespondencia='" + UFCorrespondencia + '\'' +
                ", DarCidade='" + DarCidade + '\'' +
                ", DarTelefone='" + DarTelefone + '\'' +
                ", SiglaTributo='" + SiglaTributo + '\'' +
                ", Situacao='" + Situacao + '\'' +
                ", Asterisco='" + Asterisco + '\'' +
                ", CodMensagem='" + CodMensagem + '\'' +
                ", CDA='" + CDA + '\'' +
                ", DesricaoReceita='" + DesricaoReceita + '\'' +
                ", TipoDebito='" + TipoDebito + '\'' +
                '}';
    }
}
