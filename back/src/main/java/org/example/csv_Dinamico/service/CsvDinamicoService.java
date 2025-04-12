package org.example.csv_Dinamico.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.*;

@Service
public class CsvDinamicoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;



    // Lista de palavras reservadas que devem ser evitadas
    private static final Set<String> PALAVRAS_RESERVADAS = Set.of(
            "SELECT", "INSERT", "DELETE", "UPDATE", "WHERE", "JOIN", "FROM", "TABLE",
            "GROUP", "ORDER", "BY", "CREATE", "DROP", "ALTER", "AND", "OR", "NOT", "AS"
    );




    public String processCsvDinamico(@NotNull MultipartFile file) throws IOException {
        String nomeArquivo = file.getOriginalFilename();
        String tableName = tratarNomeTabela(nomeArquivo);


        // le o conteudo do CSV
        InputStreamReader leitor = new InputStreamReader(file.getInputStream());
        CSVParser parser = new CSVParser(leitor, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withDelimiter(';'));


        List<String> colunasOriginais = new ArrayList<>(parser.getHeaderMap().keySet());


        // trata os nomes das colunas
        List<String> colunasTratadas = new ArrayList<>();
        for (String coluna : colunasOriginais) {
            colunasTratadas.add(tratarNomeColuna(coluna));
        }



        // criacao da tabela no banco
        StringBuilder sqlCriacao = new StringBuilder("CREATE TABLE IF NOT EXISTS `" + tableName + "` (");
        for (String coluna : colunasTratadas) {
            sqlCriacao.append("`").append(coluna).append("` VARCHAR(255), ");
        }
        sqlCriacao.setLength(sqlCriacao.length() - 2);
        sqlCriacao.append(")");
        jdbcTemplate.execute(sqlCriacao.toString());



        for (CSVRecord linha : parser) {
            StringBuilder sqlInsert = new StringBuilder("INSERT INTO `" + tableName + "` (");
            StringBuilder sqlValores = new StringBuilder("VALUES (");
            List<Object> valores = new ArrayList<>();

            for (int i = 0; i < colunasOriginais.size(); i++) {
                String colunaOriginal = colunasOriginais.get(i);
                String colunaTratada = colunasTratadas.get(i);

                sqlInsert.append("`").append(colunaTratada).append("`, ");
                sqlValores.append("?, ");

                //;tratamos os dados aqui
                String valorBruto = linha.get(colunaOriginal);
                String valorTratado = tratarValorCelula(valorBruto);
                valores.add(valorTratado);
            }

            sqlInsert.setLength(sqlInsert.length() - 2);
            sqlValores.setLength(sqlValores.length() - 2);
            sqlInsert.append(") ").append(sqlValores).append(")");

            jdbcTemplate.update(sqlInsert.toString(), valores.toArray());
        }




        return tableName;
    }




    // trata nomes das colunas para evitar palavras reservadas e caracteres invalidos
    private @NotNull String tratarNomeColuna(String nomeOriginal) {
        if (nomeOriginal == null) return "coluna_sem_nome";

        String nomeLimpo = removerAcentos(nomeOriginal).toUpperCase();

        if (PALAVRAS_RESERVADAS.contains(nomeLimpo)) {
            nomeLimpo = nomeLimpo + "_COL";
        }

        return nomeLimpo.toLowerCase();
    }





    // trata o nome do arquivo para virar nome de tabela
    private @NotNull String tratarNomeTabela(String nomeArquivo) {
        if (nomeArquivo == null) return "tabela_sem_nome";

        String nomeSemExtensao = nomeArquivo.replaceAll("\\.[^.]*$", "");
        String nomeTratado = removerAcentos(nomeSemExtensao);

        if (nomeTratado.matches("^[0-9].*")) {
            nomeTratado = "tabela_" + nomeTratado;
        }

        return nomeTratado.toLowerCase();
    }




    // remove acentos e caracteres especiais do conteudo das celulas
    private String tratarValorCelula(String valor) {
        if (valor == null) return null;

        String normalizado = Normalizer.normalize(valor, Normalizer.Form.NFD);
        String semAcento = normalizado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        // remove qualquer caractere especial mas mantém espaços, pontos e vírgulas se quiser
        return semAcento.replaceAll("[^\\p{L}\\p{N} \\.,;:-]", "").trim();
    }





    // remove acentos e caracteres especiais
    private String removerAcentos(String texto) {
        if (texto == null) return null;

        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);

        String semAcento = normalizado.replaceAll("\\p{M}", "");

        return semAcento.replaceAll("[^a-zA-Z0-9]", "");
    }




}
