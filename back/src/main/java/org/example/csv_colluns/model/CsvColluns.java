package org.example.csv_colluns.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "csv_colluns")
public class CsvColluns {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String DRE;
    private Float CODESC;
    private String TIPOESC;
    private String NOMES;
    private String NOMESCOFI;
    private String CEU;
    private String DIRETORIA;
    private String SUBPREF;
    private String ENDERECO;
    private Float NUMERO;
    private String BAIRRO;
    private Float CEP;
    private Float TEL1;
    private String TEL2;
    private String FAX;
    private String SITUACAO;
    private Float CODDIST;
    private String DISTRITO;
    private Float SETOR;
    private Float CODINEP;
    private Float CD_CIE;
    private String EH;
    private String FX_ETARIA;
    private String DT_CRIACAO;
    private Float ATO_CRIACAO;
    private String DOM_CRIACAO;
    private String DT_INI_CONV;
    private String DT_AUTORIZA;
    private String DT_EXTINCAO;
    private String NOME_ANT;
    private String REDE;
    private Float LATITUDE;
    private Float LONGITUDE;
    private String DATA_BASE;


    // Getter Methods

    public String getDRE() {
        return DRE;
    }

    public Float getCODESC() {
        return CODESC;
    }

    public String getTIPOESC() {
        return TIPOESC;
    }

    public String getNOMES() {
        return NOMES;
    }

    public String getNOMESCOFI() {
        return NOMESCOFI;
    }

    public String getCEU() {
        return CEU;
    }

    public String getDIRETORIA() {
        return DIRETORIA;
    }

    public String getSUBPREF() {
        return SUBPREF;
    }

    public String getENDERECO() {
        return ENDERECO;
    }

    public Float getNUMERO() {
        return NUMERO;
    }

    public String getBAIRRO() {
        return BAIRRO;
    }

    public Float getCEP() {
        return CEP;
    }

    public Float getTEL1() {
        return TEL1;
    }

    public String getTEL2() {
        return TEL2;
    }

    public String getFAX() {
        return FAX;
    }

    public String getSITUACAO() {
        return SITUACAO;
    }

    public Float getCODDIST() {
        return CODDIST;
    }

    public String getDISTRITO() {
        return DISTRITO;
    }

    public Float getSETOR() {
        return SETOR;
    }

    public Float getCODINEP() {
        return CODINEP;
    }

    public Float getCD_CIE() {
        return CD_CIE;
    }

    public String getEH() {
        return EH;
    }

    public String getFX_ETARIA() {
        return FX_ETARIA;
    }

    public String getDT_CRIACAO() {
        return DT_CRIACAO;
    }

    public Float getATO_CRIACAO() {
        return ATO_CRIACAO;
    }

    public String getDOM_CRIACAO() {
        return DOM_CRIACAO;
    }

    public String getDT_INI_CONV() {
        return DT_INI_CONV;
    }

    public String getDT_AUTORIZA() {
        return DT_AUTORIZA;
    }

    public String getDT_EXTINCAO() {
        return DT_EXTINCAO;
    }

    public String getNOME_ANT() {
        return NOME_ANT;
    }

    public String getREDE() {
        return REDE;
    }

    public Float getLATITUDE() {
        return LATITUDE;
    }

    public Float getLONGITUDE() {
        return LONGITUDE;
    }

    public String getDATA_BASE() {
        return DATA_BASE;
    }

    // Setter Methods

    public void setDRE(String DRE) {
        this.DRE = DRE;
    }

    public void setCODESC(Float CODESC) {
        this.CODESC = CODESC;
    }

    public void setTIPOESC(String TIPOESC) {
        this.TIPOESC = TIPOESC;
    }

    public void setNOMES(String NOMES) {
        this.NOMES = NOMES;
    }

    public void setNOMESCOFI(String NOMESCOFI) {
        this.NOMESCOFI = NOMESCOFI;
    }

    public void setCEU(String CEU) {
        this.CEU = CEU;
    }

    public void setDIRETORIA(String DIRETORIA) {
        this.DIRETORIA = DIRETORIA;
    }

    public void setSUBPREF(String SUBPREF) {
        this.SUBPREF = SUBPREF;
    }

    public void setENDERECO(String ENDERECO) {
        this.ENDERECO = ENDERECO;
    }

    public void setNUMERO(Float NUMERO) {
        this.NUMERO = NUMERO;
    }

    public void setBAIRRO(String BAIRRO) {
        this.BAIRRO = BAIRRO;
    }

    public void setCEP(Float CEP) {
        this.CEP = CEP;
    }

    public void setTEL1(Float TEL1) {
        this.TEL1 = TEL1;
    }

    public void setTEL2(String TEL2) {
        this.TEL2 = TEL2;
    }

    public void setFAX(String FAX) {
        this.FAX = FAX;
    }

    public void setSITUACAO(String SITUACAO) {
        this.SITUACAO = SITUACAO;
    }

    public void setCODDIST(Float CODDIST) {
        this.CODDIST = CODDIST;
    }

    public void setDISTRITO(String DISTRITO) {
        this.DISTRITO = DISTRITO;
    }

    public void setSETOR(Float SETOR) {
        this.SETOR = SETOR;
    }

    public void setCODINEP(Float CODINEP) {
        this.CODINEP = CODINEP;
    }

    public void setCD_CIE(Float CD_CIE) {
        this.CD_CIE = CD_CIE;
    }

    public void setEH(String EH) {
        this.EH = EH;
    }

    public void setFX_ETARIA(String FX_ETARIA) {
        this.FX_ETARIA = FX_ETARIA;
    }

    public void setDT_CRIACAO(String DT_CRIACAO) {
        this.DT_CRIACAO = DT_CRIACAO;
    }

    public void setATO_CRIACAO(Float ATO_CRIACAO) {
        this.ATO_CRIACAO = ATO_CRIACAO;
    }

    public void setDOM_CRIACAO(String DOM_CRIACAO) {
        this.DOM_CRIACAO = DOM_CRIACAO;
    }

    public void setDT_INI_CONV(String DT_INI_CONV) {
        this.DT_INI_CONV = DT_INI_CONV;
    }

    public void setDT_AUTORIZA(String DT_AUTORIZA) {
        this.DT_AUTORIZA = DT_AUTORIZA;
    }

    public void setDT_EXTINCAO(String DT_EXTINCAO) {
        this.DT_EXTINCAO = DT_EXTINCAO;
    }

    public void setNOME_ANT(String NOME_ANT) {
        this.NOME_ANT = NOME_ANT;
    }

    public void setREDE(String REDE) {
        this.REDE = REDE;
    }

    public void setLATITUDE(Float LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public void setLONGITUDE(Float LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public void setDATA_BASE(String DATA_BASE) {
        this.DATA_BASE = DATA_BASE;
    }
}