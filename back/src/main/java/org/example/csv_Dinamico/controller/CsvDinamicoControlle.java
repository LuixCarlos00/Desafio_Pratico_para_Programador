package org.example.csv_Dinamico.controller;

import org.example.csv_Dinamico.service.CsvDinamicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/csv_Dinamico")
public class CsvDinamicoControlle {

    @Autowired
    private CsvDinamicoService csvDinamicoService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadCsv(
            @RequestParam("file") MultipartFile file,
            @RequestParam("gravarTipo") String gravarTipo
    ) {
        Map<String, String> response = new HashMap<>();

        try {
            // Chama o service (agora ele cuida do nome da tabela internamente)
            String nomeTabela = csvDinamicoService.processCsvDinamico(file);

            response.put("message", "CSV processado e armazenado com sucesso.");
            response.put("tabela", nomeTabela);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "Erro ao processar o CSV: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
