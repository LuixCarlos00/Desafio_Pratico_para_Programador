package org.example.csv.controller;

import org.example.csv.exception.FileStorageException;
import org.example.csv.exception.InvalidFileException;
import org.example.csv.model.ArquivoCsv;
import org.example.csv.service.ArquivoCsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/arquivos")
public class CsvController {


    private static final Logger logger = LoggerFactory.getLogger(CsvController.class);

    // Tamanhos em bytes
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final String[] ALLOWED_CONTENT_TYPES = {"text/csv", "application/vnd.ms-excel","application/csv","text/x-csv"};

    private final ArquivoCsvService arquivoCsvService;

    @Autowired
    public CsvController(ArquivoCsvService arquivoCsvService) {
        this.arquivoCsvService = arquivoCsvService;
    }




    @PostMapping("/upload")
    public ResponseEntity<?> uploadArquivo(@RequestParam("file") MultipartFile file) {
        try {
             validarArquivo(file);
            ArquivoCsv arquivoSalvo = arquivoCsvService.salvarArquivo(file);

            Map<String, Object> response = new HashMap<>();
            response.put("nomeDoArquivo", arquivoSalvo.getNomeDoArquivo());
            response.put("tamanhoDoArquivo", arquivoSalvo.getTamanhoDoArquivo());
            response.put("tipoDeArquivo", arquivoSalvo.getTipoDeArquivo());
            response.put("dataDeImportacao", arquivoSalvo.getDataDeImportacao());
            response.put("mensagem", "Arquivo processado com sucesso");

            return ResponseEntity.ok(response);

        } catch (InvalidFileException e) {
             return ResponseEntity.badRequest().body(Map.of(//Arquivo inválido
                    "erro", "Arquivo inválido",
                    "mensagem", e.getMessage()
            ));
        } catch (FileStorageException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(//Erro ao armazenar arquivo
                    "erro", "Falha no armazenamento",
                    "mensagem", e.getMessage()
            ));
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(//Erro inesperado ao processar arquivo
                    "erro", "Erro no processamento",
                    "mensagem", "Ocorreu um erro inesperado ao processar o arquivo"

            ));
        }
    }





    private void validarArquivo(MultipartFile file) throws InvalidFileException {
        if (file.isEmpty()) {
            throw new InvalidFileException("O arquivo está vazio");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new InvalidFileException(
                    String.format("Tamanho do arquivo excede o limite permitido de %dMB",
                            MAX_FILE_SIZE / (1024 * 1024))
            );
        }

        boolean isValidType = false;
        for (String allowedType : ALLOWED_CONTENT_TYPES) {
            if (allowedType.equalsIgnoreCase(file.getContentType())) {
                isValidType = true;
                break;
            }
        }

        if (!isValidType) {
            throw new InvalidFileException(
                    String.format("Tipo de arquivo não suportado. Tipos permitidos: %s",
                            String.join(", ", ALLOWED_CONTENT_TYPES))
            );
        }
    }
}