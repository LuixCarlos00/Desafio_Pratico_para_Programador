package org.example.csv_colluns.controller;


import org.example.csv_colluns.service.CsvCollunsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/csvColluns")
public class CsvCollunsController {

    @Autowired
    private CsvCollunsService csvCollunsService;



    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            csvCollunsService.processCsv(file);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Arquivo CSV processado com sucesso!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Erro ao processar o arquivo: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }




}
