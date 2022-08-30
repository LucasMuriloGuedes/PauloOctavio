package com.join.boletoiptupauloocatvio.controllers;

import com.join.boletoiptupauloocatvio.domain.ControleProcessamento;
import com.join.boletoiptupauloocatvio.domain.DadosRetornoIptu;
import com.join.boletoiptupauloocatvio.domain.Empreendimento;
import com.join.boletoiptupauloocatvio.domain.ImovelEmissaoIptu;
import com.join.boletoiptupauloocatvio.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ViewController {

    private String ultimoEmpreendimento = "";

    private String valorImovel;

    @Autowired
    private BuscaDadosService service;

    @Autowired
    private ControleProcessamentoService controleProcessamentoService;

    @Autowired
    private ImovelEmissaoIptuService imovelEmissaoIptuService;

    @Autowired
    private DadosRetornoIPTUService serviceRetornoIptu;

    @Autowired
    private EmpreendimentoService empreendimentoService;

    //Páginas
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/infoatualizacaodebitos")
    public String infoatualizacaodebitos() {
        return "infoatualizacaodebitos";
    }

    @GetMapping("/cadastrarimoveis")
    public String paginaCadastrarImovel() {
        return "cadastrarimoveis";
    }

    @GetMapping("/atualizacaodebitos")
    public String atualizacaoDebitos() {
        return "atualizacaodebitos";
    }

    @GetMapping("/cadastrandoempreendimento")
    public String cadastroEmpreendimento() {
        return "cadastroempreendimento";
    }

    @PostMapping("/home")
    public String findByImovel(String imovel) {
        valorImovel = imovel;
        return "redirect:/list";
    }

    //Serviços

    @PostMapping(value = "/cadastrarimovel")
    public String cadastrarImovel(ImovelEmissaoIptu obj) {
        imovelEmissaoIptuService.insert(obj);
        return "redirect:/cadastrandoimovel";
    }

    @GetMapping(value = "/excluirimovel/{id}")
    public String  excluir(@PathVariable Integer id) {
        imovelEmissaoIptuService.delete(id);
        return "redirect:/cadastrandoimovel";
    }

    @GetMapping(value = "/list")
    public ModelAndView listPrestacaoImovel() {
        ModelAndView mv = new ModelAndView("listimovel");
        List<DadosRetornoIptu> obj = serviceRetornoIptu.findByImovel(valorImovel);
        mv.addObject("obj", obj);
        return mv;
    }


    @GetMapping(value = "/cadastrandoimovel")
    public String listImoveis(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(40);

        Page<ImovelEmissaoIptu> obj = imovelEmissaoIptuService.findPaginatedAll(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("obj", obj);

        int totalPages = obj.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "cadastrarimoveis.html";
    }

    @GetMapping(value = "/findByNumeroImovel")
    public ModelAndView findByValorImovel(String imovel){
        ModelAndView mv = new ModelAndView("cadastrarimoveis");
        ImovelEmissaoIptu obj = imovelEmissaoIptuService.findByImovel(imovel);
        mv.addObject("obj", obj);
        return mv;
    }

    @GetMapping(value = "/findByEmpreendimento")
    public String findByEmpreendimento(
            String tipoEmpreendimento,
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(40);

        ultimoEmpreendimento = tipoEmpreendimento;

        Page<ImovelEmissaoIptu> obj = imovelEmissaoIptuService.findPaginatedEmprendimento(PageRequest.of(currentPage - 1, pageSize), tipoEmpreendimento);
        model.addAttribute("obj", obj);
        int totalPages = obj.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "findbyempreendimento.html";
    }

    @GetMapping(value = "/findByEmpreendimentoPage")
    public String findByEmpreendimentoPage(
            Model model,
            @RequestParam("tipoEmpreendimento") String tipoEmpreendimento,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(40);

        tipoEmpreendimento = ultimoEmpreendimento;

        Page<ImovelEmissaoIptu> obj = imovelEmissaoIptuService.findPaginatedEmprendimento(PageRequest.of(currentPage - 1, pageSize), tipoEmpreendimento);
        model.addAttribute("obj", obj);
        int totalPages = obj.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "findbyempreendimento.html";
    }

    @PostMapping(value = "/updateImovel")
    public String updateImovel(ImovelEmissaoIptu obj) {
        imovelEmissaoIptuService.update(obj);
        return "redirect:/cadastrandoimovel";
    }

    @GetMapping(value = "/atualizabase")
    public ModelAndView listBaseProcessada() {
        ModelAndView mv = new ModelAndView("cadastroempreendimento");
        List<ControleProcessamento> obj = controleProcessamentoService.findAll();
        mv.addObject("obj", obj);
        return mv;
    }

    //Empreendimento
    @PostMapping(value = "/cadastrarempreendimento")
    public String cadastrarEmpreendimento(Empreendimento obj) {
        System.out.println(obj);
        empreendimentoService.insert(obj);
        return "redirect:/listEmpreendimento";
    }

    @GetMapping(value = "/listEmpreendimento")
    public ModelAndView listEmpreendimento() {
        ModelAndView mv = new ModelAndView("cadastroempreendimento");
        List<Empreendimento> obj = empreendimentoService.findAll();
        mv.addObject("obj", obj);
        return mv;
    }

    @GetMapping(value = "/excluirempreendimento/{id}")
    public String  excluirEmpreendimento(@PathVariable Integer id) {
        empreendimentoService.delete(id);
        return "redirect:/listEmpreendimento";
    }

    @PostMapping(value = "/updateempreendimento")
    public String updateEmpreendimento(Empreendimento obj) {
        empreendimentoService.update(obj);
        return "redirect:/listEmpreendimento";
    }

    @PostMapping(value = "/atualizarbase")
    public String atualizarBase(String mes, String ano) throws InterruptedException, IOException {
        Integer emitirDAR = 0;
        if (mes.equals("Selecione o mês de referência") || ano.equals("Selecione o ano de referência")){
            System.out.println("As informações não são válidas para o processamento!");
        }else{
            emitirDAR = service.buscaLinhaDigitavel(mes, ano);
            System.out.println("Quantidade de IPTUS salvos = " + emitirDAR);
        }
       return "redirect:/listatualizacaobase";
    }

    @GetMapping(value = "/listatualizacaobase")
    public ModelAndView listAtualizacaoBase() {
        ModelAndView mv = new ModelAndView("atualizacaodebitos");
        List<ControleProcessamento> obj = controleProcessamentoService.findByOrderByMes();
        mv.addObject("obj", obj);
        return mv;
    }
}