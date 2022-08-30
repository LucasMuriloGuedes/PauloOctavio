package com.join.boletoiptupauloocatvio.services;
import com.join.boletoiptupauloocatvio.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuscaDadosService {

    @Autowired
    private DadosRetornoIPTUService retornoService;

    @Autowired
    private ImovelEmissaoIptuService imovelEmissaoIptuService;

    @Autowired
    private ControleProcessamentoService controleProcessamentoService;

    public int buscaLinhaDigitavel(String mes, String ano) {

        List<DadosRetornoIptu> listRetornoIptu = new ArrayList<>();
        Integer qtdeIPTU = 0;
        String mesReferencia = mes;
        String anoReferencia = ano;
        Integer qtdeBoletoSalvo = 0;
        Integer qtdeItensSemDebito = 0;
        Integer qtdeImovelNaoEncotrado = 0;

        int anoConvertido = Integer.parseInt(ano);
        LocalDate dataAtual = LocalDate.now();
        int anoAtual = dataAtual.getYear();

        if (anoAtual == anoConvertido) {

            List<ImovelEmissaoIptu> listImovelIptu = imovelEmissaoIptuService.findAll();
            List<String> imoveisSemDebitos = new ArrayList<>();
            List<String> imoveisNaoEncotrado = new ArrayList<>();

            for (ImovelEmissaoIptu x : listImovelIptu) {
                DebitoIPTU debitoIPTU = new DebitoIPTU();
                debitoIPTU.setAnoExercicio(anoReferencia);
                debitoIPTU.setImovel(x.getNumeroImovel());
                debitoIPTU.getDarCodigoReceita().add("1252");
                debitoIPTU.getDarCotaReferencia().add(mesReferencia);
                debitoIPTU.setDesricaoReceita("IPTU / TLP");
                debitoIPTU.setTipoDebito("1");

                EmitirDAR emitirDAR = new EmitirDAR(null, null, null, debitoIPTU, null, null);
                RestTemplate restTemplate = new RestTemplate();
                try {
                    String retornoBody = restTemplate.postForObject("https://ww1.receita.fazenda.df.gov.br/WPI/api/Debitos/EmitirDAR", emitirDAR, String.class);
                    String[] recorteBody = retornoBody.split("LinhaCodigoBarras");
                    String[] dataVencimentoSplit = retornoBody.split("DarDataVencimento");
                    String dataVencimento = dataVencimentoSplit[1].substring(4, 14).trim();
                    System.out.println("Data de vencimento: " + dataVencimento);
                    String recorteLinhaDigitavel = recorteBody[1];
                    String linhaDigitavel = recorteLinhaDigitavel.substring(4, 55);
                    String valorTotal = recorteLinhaDigitavel.substring(83, 94).trim();
                    DadosRetornoIptu retornoIptu = new DadosRetornoIptu(null, x.getNumeroImovel(), anoReferencia, mesReferencia, valorTotal, linhaDigitavel, dataVencimento);
                    listRetornoIptu.add(retornoIptu);
                    qtdeBoletoSalvo = baixarBoleto(x.getNumeroImovel(), x.getEmpreendimento(), mesReferencia, anoReferencia);
                    qtdeIPTU += 1;
                    System.out.println("Id imovel: " + x.getId() + " -- Numero Imovel: " + x.getNumeroImovel() + " -- Valor total: " + valorTotal);
                } catch (HttpServerErrorException e) {
                    System.out.println("Não há débitos para o imovel: " + x.getNumeroImovel());
                    imoveisSemDebitos.add(x.getNumeroImovel());
                    qtdeItensSemDebito += 1;
                } catch (HttpClientErrorException e) {
                    System.out.println("Imovel não encotrado: " + x.getNumeroImovel());
                    imoveisNaoEncotrado.add(x.getNumeroImovel());
                    qtdeImovelNaoEncotrado += 1;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Quantidade de itens sem débitos do mês de referência " + mesReferencia + ": " + qtdeItensSemDebito);
            for (String x : imoveisSemDebitos) {
                System.out.println(" Items sem débitos: " + x);
            }

            System.out.println("Quantidade de imoveis não encontrado: " + qtdeImovelNaoEncotrado);
            for (String x : imoveisNaoEncotrado) {
                System.out.println("Imovel não localizado: " + x);
            }
            ControleProcessamento controleProcessamento = new ControleProcessamento(null, mes, ano);
            controleProcessamentoService.insert(controleProcessamento);
            retornoService.insert(listRetornoIptu);
            return qtdeIPTU;
        } else {
            return qtdeIPTU;
        }

    }

    public Integer baixarBoleto(String numeroImovel, String empreendimento, String mesReferencia, String ano) throws IOException {
        String urlBoleto = "https://ww1.receita.fazenda.df.gov.br/WPI/api/Report/Publica/RelatorioDAR/pdf?P_TIPODAR=iptu&P_PARAMDAR=%7B%27AnoExercicio%27%3A%272022%27%2C%27Inscricao%27%3A%27" + numeroImovel + "%27%2C%27DarCotaReferencia%27%3A%27" + mesReferencia + "%27%2C%27CodigoReceita%27%3A%271252%27%2C%27CDA%27%3A%27%27%2C%27TipoDebito%27%3A3%7D";
        Integer qtdeBoletoSalvo = 1;
        URL url = new URL(urlBoleto); //conecta com localhost e busca o arquivo a ser baixado
        File file = new File("C:\\Users\\Lucas Murilo\\Desktop\\Temp\\Boletos_" + ano + "\\" + mesReferencia + "\\" + empreendimento + "\\" + numeroImovel + "_" + mesReferencia + "_" + ano + ".pdf");
        InputStream is = url.openStream();
        FileOutputStream fos = new FileOutputStream(file);
        int bytes = 0;
        while ((bytes = is.read()) != -1) {
            fos.write(bytes);
        }
        is.close();
        fos.close();
        return qtdeBoletoSalvo;
    }
}