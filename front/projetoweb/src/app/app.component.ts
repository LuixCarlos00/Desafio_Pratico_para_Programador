import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';
import Swal from 'sweetalert2';
import type { Arquivo } from '../Arquivo';

 
const MAX_FILE_SIZE_MB = 10; // Tamanho máximo permitido em MB
const ALLOWED_FILE_TYPES = ['text/csv', 'application/vnd.ms-excel']; // Tipos de arquivo permitidos

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, HttpClientModule, ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  uploadForm!: FormGroup;
  title = 'projetoweb';
  selectedFile: File | null = null;
  isUploading = false; // Flag para controlar estado de upload

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  private initializeForm(): void {
    this.uploadForm = new FormGroup({
      file: new FormControl(null)
    });
  }

  selecionarArquivo(event: Event): void {
    const target = event.target as HTMLInputElement;
    
    if (!target.files || target.files.length === 0) {
      this.mostraErro('Nenhum arquivo selecionado!');
      return;
    }

    const file = target.files[0];
    
    // Validações do arquivo
    if (!this.arquivoValido(file)) {
      return;
    }

    this.selectedFile = file;
    this.infomacoesLogs(file);
  }

  private arquivoValido(file: File): boolean {
    // Verifica tipo do arquivo
    if (!ALLOWED_FILE_TYPES.includes(file.type)) {
      this.mostraErro('Tipo de arquivo não permitido. Por favor, envie um arquivo CSV.');
      return false;
    }

    // Verifica tamanho do arquivo
    const fileSizeMB = file.size / (1024 * 1024);
    if (fileSizeMB > MAX_FILE_SIZE_MB) {
      this.mostraErro(`O arquivo é muito grande. Tamanho máximo permitido: ${MAX_FILE_SIZE_MB}MB`);
      return false;
    }

    return true;
  }

  private infomacoesLogs(file: File): void {
    const reader = new FileReader();
    reader.onload = () => {
      const base64String = reader.result?.toString().split(',')[1];
      console.log('Informações do arquivo:', {
        nome: file.name,
        tipo: file.type,
        tamanho: `${(file.size / (1024 * 1024)).toFixed(2)}MB`,
        base64: base64String?.substring(0, 30) + '...' // Log parcial do Base64
      });
    };
    reader.readAsDataURL(file);
  }



  onSubmit(): void {
    if (!this.selectedFile) {
      this.mostraErro('Selecione um arquivo primeiro!');
      return;
    }

    this.isUploading = true;
    this.loading('Enviando arquivo...');

    const reader = new FileReader();
    
    reader.onload = () => {
      try {
        const base64String = reader.result?.toString().split(',')[1];
        if (!base64String) {
          throw new Error('Falha ao converter arquivo para Base64');
        }

        const arquivo = this.criarObjeto(this.selectedFile!, base64String);
        this.enviarParaBackend();
      } catch (error) {
        this.Error(error);
      }
    };

    reader.onerror = () => {
      this.Error(new Error('Erro ao ler o arquivo'));
    };

    reader.readAsDataURL(this.selectedFile);
  }





  private criarObjeto(file: File, base64String: string): Arquivo {
    const tamanhoEmMB = file.size / (1024 * 1024);
    
    return {
      nomeDoArquivo: file.name,
      tamanhoDoArquivo: tamanhoEmMB,
      dataDeImportacao: new Date().toISOString(),
      tipoDeArquivo: file.type,
      caminhoArquivo: 'C:\\Users\\Downloads',
      conteudoArquivo: base64String
    };
  }




  enviarParaBackend( ): void {
    if (!this.selectedFile) {
      this.mostraErro('Nenhum arquivo selecionado.');
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile, this.selectedFile.name);

    this.http.post('http://localhost:8080/arquivos/upload', formData)
      .subscribe({
        next: (res) => this.mensagemSucesso(res),
        error: (err) => this.tratarErro(err)
      });
  }




  private mensagemSucesso(response: any): void {
    this.isUploading = false;
    Swal.fire({
      icon: 'success',
      title: 'Sucesso!',
      text: 'Arquivo enviado com sucesso!',
      timer: 2000
    });
    console.log('Resposta do backend:', response);
    
    // Resetar o formulário após sucesso
    this.resetForm();
  }



  private tratarErro(error: HttpErrorResponse): void {
    this.isUploading = false;
    console.error('Erro no upload:', error);

    let errorMessage = 'Falha ao enviar o arquivo.';
    
    if (error.status === 413) {
      errorMessage = 'O arquivo é muito grande para ser enviado.';
    } else if (error.status === 415) {
      errorMessage = 'Tipo de arquivo não suportado.';
    } else if (error.error?.message) {
      errorMessage = error.error.message;
    }

    this.mostraErro(errorMessage);
  }



  private Error(error: unknown): void {
    this.isUploading = false;
    console.error('Erro:', error);
    
    const errorMessage = error instanceof Error ? error.message : 'Ocorreu um erro inesperado';
    this.mostraErro(errorMessage);
  }

  private mostraErro(message: string): void {
    Swal.fire({
      icon: 'error',
      title: 'Erro',
      text: message,
      confirmButtonText: 'Entendi'
    });
  }



  private loading(message: string): void {
    Swal.fire({
      title: message,
      allowOutsideClick: false,
      didOpen: () => {
        Swal.showLoading();
      }
    });
  }

  private resetForm(): void {
    this.selectedFile = null;
    this.uploadForm.reset();
    const fileInput = document.getElementById('fileInput') as HTMLInputElement;
    if (fileInput) {
      fileInput.value = '';
    }
  }
}