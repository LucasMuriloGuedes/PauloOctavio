package com.join.boletoiptupauloocatvio.controllers;

import com.join.boletoiptupauloocatvio.services.BuscaDadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/debitos")
public class DebitoIPTUController {

    @Autowired
    private BuscaDadosService service;

    @PostMapping(value = "/{mes}/{ano}")
    public ResponseEntity<String> findDebito(@PathVariable String mes, @PathVariable String ano) throws InterruptedException, IOException {
        Integer emitirDAR = 0;
        if (mes.equals("") || mes.equals(null) || ano.equals("") || ano.equals(null)){
            System.out.println("As informações não são válidas para o processamento!");
        }else{

            emitirDAR = service.buscaLinhaDigitavel(mes, ano);
        }
        return ResponseEntity.ok().body("Quantidade de IPTUS salvos = " + emitirDAR);
    }
}
