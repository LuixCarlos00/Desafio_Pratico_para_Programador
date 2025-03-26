export interface Arquivo {
    idArquivo?: number;
    nomeDoArquivo: string;
    tamanhoDoArquivo: number;
    dataDeImportacao: string;
    tipoDeArquivo: string;
    caminhoArquivo?: string;
    conteudoArquivo: string; 
  }
  