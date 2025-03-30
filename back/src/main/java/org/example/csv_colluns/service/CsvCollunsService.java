package org.example.csv_colluns.service;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.csv_colluns.dto.CsvData;
import org.example.csv_colluns.model.CsvColluns;
import org.example.csv_colluns.repository.CsvCollunsRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CsvCollunsService {


    @Autowired
    private CsvCollunsRepository repository;

    public void processCsv(@NotNull MultipartFile file) throws IOException {

        InputStreamReader reader = new InputStreamReader(file.getInputStream());
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withHeader()
                .withDelimiter(';'));

         String[] header = csvParser.getHeaderMap().keySet().toArray(new String[0]);
        System.out.println("Cabeçalhos do CSV: " + Arrays.toString(header));

         List<CsvColluns> dataList = new ArrayList<>();
        for (CSVRecord record : csvParser) {
            CsvColluns data = new CsvColluns();

             if (record.isMapped("DRE")) {
                data.setDRE(record.get("DRE"));
            } else {
                System.out.println("Campo DRE não encontrado no registro");
                 data.setDRE("Campo DRE não encontrado");
            }

             try {
                data.setCODESC(Float.parseFloat(record.get("CODESC")));
            } catch (Exception e) {
                System.out.println("Erro ao processar CODESC: " + e.getMessage());
                data.setCODESC(0f);
            }

             data.setTIPOESC(record.get("TIPOESC"));
            data.setNOMES(record.get("NOMES"));
            data.setNOMESCOFI(record.get("NOMESCOFI"));
            data.setCEU(record.get("CEU"));
            data.setDIRETORIA(record.get("DIRETORIA"));
            data.setSUBPREF(record.get("SUBPREF"));
            data.setENDERECO(record.get("ENDERECO"));
            try {
                data.setNUMERO(Float.parseFloat(record.get("NUMERO")));
            } catch (Exception e) {
                System.out.println("Erro ao processar NUMERO: " + e.getMessage());
                data.setNUMERO(0f);
            }

            data.setBAIRRO(record.get("BAIRRO"));
            try {
                data.setCEP(Float.parseFloat(record.get("CEP")));
            } catch (Exception e) {
                System.out.println("Erro ao processar CEP: " + e.getMessage());
                data.setCEP(0f);
            }

            try {
                data.setTEL1(Float.parseFloat(record.get("TEL1")));
            } catch (Exception e) {
                System.out.println("Erro ao processar TEL1: " + e.getMessage());
                data.setTEL1(0f);
            }

            data.setTEL2(String.valueOf(record.get("TEL2")));
            data.setFAX(record.get("FAX"));
            data.setSITUACAO(record.get("SITUACAO"));
            try {
                data.setCODDIST(Float.parseFloat(record.get("CODDIST")));
            } catch (Exception e) {
                System.out.println("Erro ao processar CODDIST: " + e.getMessage());
                data.setCODDIST(0f);
            }

            data.setDISTRITO(record.get("DISTRITO"));
            try {
                data.setSETOR(Float.parseFloat(record.get("SETOR")));
            } catch (Exception e) {
                System.out.println("Erro ao processar SETOR: " + e.getMessage());
                data.setSETOR(0f);
            }

            try {
                data.setCODINEP(Float.parseFloat(record.get("CODINEP")));
            } catch (Exception e) {
                System.out.println("Erro ao processar CODINEP: " + e.getMessage());
                data.setCODINEP(0f);
            }

            try {
                data.setCD_CIE(Float.parseFloat(record.get("CD_CIE")));
            } catch (Exception e) {
                System.out.println("Erro ao processar CD_CIE: " + e.getMessage());
                data.setCD_CIE(0f);
            }

            data.setEH(record.get("EH"));
            data.setFX_ETARIA(record.get("FX_ETARIA"));
            data.setDT_CRIACAO(record.get("DT_CRIACAO"));
            try {
                data.setATO_CRIACAO(Float.parseFloat(record.get("ATO_CRIACAO")));
            } catch (Exception e) {
                System.out.println("Erro ao processar ATO_CRIACAO: " + e.getMessage());
                data.setATO_CRIACAO(0f);
            }

            data.setDOM_CRIACAO(record.get("DOM_CRIACAO"));
            data.setDT_INI_CONV(record.get("DT_INI_CONV"));
            data.setDT_AUTORIZA(record.get("DT_AUTORIZA"));
            data.setDT_EXTINCAO(record.get("DT_EXTINCAO"));
            data.setNOME_ANT(record.get("NOME_ANT"));
            data.setREDE(record.get("REDE"));
            try {
                data.setLATITUDE(Float.parseFloat(record.get("LATITUDE")));
            } catch (Exception e) {
                System.out.println("Erro ao processar LATITUDE: " + e.getMessage());
                data.setLATITUDE(0f);
            }

            try {
                data.setLONGITUDE(Float.parseFloat(record.get("LONGITUDE")));
            } catch (Exception e) {
                System.out.println("Erro ao processar LONGITUDE: " + e.getMessage());
                data.setLONGITUDE(0f);
            }

            data.setDATA_BASE(record.get("DATABASE"));

             dataList.add(data);
        }

        System.out.println(dataList);


        repository.saveAll(dataList);
    }


}