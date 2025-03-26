package org.example.csv.service;


import org.example.csv.model.ArquivoCsv;
import org.example.csv.repository.ArquivoCsvRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;


@Service
public class ArquivoCsvService {


    private static final Logger log = LoggerFactory.getLogger(ArquivoCsvService.class);
    @Autowired
    private ArquivoCsvRepository arquivoCsvRepository;

    public ArquivoCsv salvarArquivo(MultipartFile file) throws IOException {;
        System.out.println(file);
        ArquivoCsv arquivo = new ArquivoCsv();
        arquivo.setNomeDoArquivo(file.getOriginalFilename());
        arquivo.setTipoDeArquivo(file.getContentType());
        arquivo.setTamanhoDoArquivo(file.getSize());
        arquivo.setDataDeImportacao(new Date());
        arquivo.setConteudoArquivo(file.getBytes()); // Armazena como bytes

        return arquivoCsvRepository.save(arquivo);
    }
}
