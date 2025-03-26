package org.example.csv.service;

import org.example.csv.exception.FileProcessingException;
import org.example.csv.exception.FileStorageException;
import org.example.csv.model.ArquivoCsv;
import org.example.csv.repository.ArquivoCsvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class ArquivoCsvService {

    private static final Logger logger = LoggerFactory.getLogger(ArquivoCsvService.class);

    private final ArquivoCsvRepository arquivoCsvRepository;

    @Autowired
    public ArquivoCsvService(ArquivoCsvRepository arquivoCsvRepository) {
        this.arquivoCsvRepository = arquivoCsvRepository;
    }

    public ArquivoCsv salvarArquivo(MultipartFile file) throws FileStorageException, FileProcessingException {

        ArquivoCsv arquivo = new ArquivoCsv();
        try {
            arquivo.setNomeDoArquivo(file.getOriginalFilename());
            arquivo.setTipoDeArquivo(file.getContentType());
            arquivo.setTamanhoDoArquivo(file.getSize());
            arquivo.setDataDeImportacao(new Date());
            arquivo.setConteudoArquivo(file.getBytes());

            return arquivoCsvRepository.save(arquivo);

        } catch (IOException e) {
            throw new FileProcessingException("Falha ao processar o conteúdo do arquivo", e);
        } catch (DataAccessException e) {
            throw new FileStorageException("Falha ao armazenar os metadados do arquivo", e);
        } catch (Exception e) {
            throw new FileStorageException("Erro inesperado ao processar o arquivo", e);
        }
    }
}