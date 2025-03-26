package org.example.csv.controller;


import org.example.csv.model.ArquivoCsv;
import org.example.csv.service.ArquivoCsvService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/arquivos")
public class CsvController {

    @Autowired
    private ArquivoCsvService arquivoCsvService;




    @PostMapping("/upload")
    public ResponseEntity<?> uploadArquivo(@RequestParam("file") @NotNull MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhum arquivo enviado!");
        }

        try {

            // Salva o arquivo no banco
            ArquivoCsv arquivoSalvo = arquivoCsvService.salvarArquivo(file);

            // Retorna resposta com informações do arquivo salvo
            Map<String, Object> response = new HashMap<>();
            response.put("nomeDoArquivo", arquivoSalvo.getNomeDoArquivo());
            response.put("tamanhoDoArquivo", arquivoSalvo.getTamanhoDoArquivo());
            response.put("tipoDeArquivo", arquivoSalvo.getTipoDeArquivo());
            response.put("dataDeImportacao", arquivoSalvo.getDataDeImportacao());

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar arquivo");
        }
    }
}