package org.example.csv.model;

import jakarta.persistence.*;
import lombok.*;

 import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "arquivos_csv")
public class ArquivoCsv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idArquivo")
    private Long idArquivo;

    @Column(name = "nome_Do_Arquivo", nullable = false)
    private String nomeDoArquivo;

    @Column(name = "tamanho_Do_Arquivo", nullable = false)
    private Long tamanhoDoArquivo;

    @Column(name = "data_De_Importacao")
    private Date dataDeImportacao;

    @Column(name = "tipo_De_Arquivo")
    private String tipoDeArquivo;

    @Column(name = "caminho_Arquivo")
    private String caminhoArquivo;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] conteudoArquivo;


    public Long getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(Long idArquivo) {
        this.idArquivo = idArquivo;
    }

    public String getNomeDoArquivo() {
        return nomeDoArquivo;
    }

    public void setNomeDoArquivo(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
    }

    public Long getTamanhoDoArquivo() {
        return tamanhoDoArquivo;
    }

    public void setTamanhoDoArquivo(Long tamanhoDoArquivo) {
        this.tamanhoDoArquivo = tamanhoDoArquivo;
    }

    public Date getDataDeImportacao() {
        return dataDeImportacao;
    }

    public void setDataDeImportacao(Date dataDeImportacao) {
        this.dataDeImportacao = dataDeImportacao;
    }

    public String getTipoDeArquivo() {
        return tipoDeArquivo;
    }

    public void setTipoDeArquivo(String tipoDeArquivo) {
        this.tipoDeArquivo = tipoDeArquivo;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public byte[] getConteudoArquivo() {
        return conteudoArquivo;
    }

    public void setConteudoArquivo(byte[] conteudoArquivo) {
        this.conteudoArquivo = conteudoArquivo;
    }
}
