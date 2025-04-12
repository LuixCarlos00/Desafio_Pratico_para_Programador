import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import Swal from 'sweetalert2';


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
  selectedFile: File | null = null;
  isUploading = false;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.inicializarFormulario();
  }

  private inicializarFormulario(): void {
    this.uploadForm = new FormGroup({
      file: new FormControl(null, [Validators.required]),
      gravarTipo: new FormControl('inteiro', [Validators.required])
    });
  }

  selecionarArquivo(event: Event): void {
    const target = event.target as HTMLInputElement;

    if (!target.files || target.files.length === 0) {
      this.mostraErro('Nenhum arquivo selecionado!');
      return;
    }

    const file = target.files[0];

    if (!this.arquivoValido(file)) {
      return;
    }

    this.selectedFile = file;
  }

  private arquivoValido(file: File): boolean {
    const ALLOWED_FILE_TYPES = ['text/csv', 'application/vnd.ms-excel'];
    const MAX_FILE_SIZE_MB = 10;

    if (!ALLOWED_FILE_TYPES.includes(file.type)) {
      this.mostraErro('Tipo de arquivo não permitido. Por favor, envie um arquivo CSV.');
      return false;
    }

    const fileSizeMB = file.size / (1024 * 1024);
    if (fileSizeMB > MAX_FILE_SIZE_MB) {
      this.mostraErro(`O arquivo é muito grande. Tamanho máximo permitido: ${MAX_FILE_SIZE_MB}MB`);
      return false;
    }

    return true;
  }

  onSubmit(): void {
    if (!this.selectedFile) {
      this.mostraErro('Selecione um arquivo primeiro!');
      return;
    }

    this.isUploading = true;
    this.loading('Enviando arquivo...');

    const formData = new FormData();
    formData.append('file', this.selectedFile, this.selectedFile.name);
    formData.append('gravarTipo', this.uploadForm.value.gravarTipo);

    const tipo = this.uploadForm.value.gravarTipo;

    let url = '';

    switch (tipo) {
      case 'inteiro':
        url = 'http://localhost:8080/arquivos/upload';
        break;
      case 'colunas':
        url = 'http://localhost:8080/csvColluns/upload';
        break;
      case 'dinamico':
        url = 'http://localhost:8080/csv_Dinamico/upload';
        break;
      default:
        this.mostraErro('Tipo de gravação inválido.');
        return;
    }

    this.http.post(url, formData).subscribe({
      next: (res) => this.mensagemSucesso(res),
      error: (err) => this.tratarErro(err)
    });
  }

  private mensagemSucesso(response: any): void {
    this.isUploading = false;
    Swal.fire({
      icon: 'success',
      title: 'Sucesso!',
      text: response.message || 'Arquivo processado com sucesso!',
      timer: 2000
    });
    this.resetForm();
  }

  private tratarErro(error: any): void {
    this.isUploading = false;
    let errorMessage = 'Falha ao enviar o arquivo.';
    if (error.status === 413) {
      errorMessage = 'O arquivo é muito grande para ser enviado.';
    } else if (error.status === 415) {
      errorMessage = 'Tipo de arquivo não suportado.';
    } else if (error.error?.message) {
      errorMessage = error.error.message;
    } else {
      errorMessage = error.message || errorMessage;
    }
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
