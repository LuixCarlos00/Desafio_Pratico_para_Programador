import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';
import Swal from 'sweetalert2';
import type { Arquivo } from '../Arquivo';
import { log } from 'console';

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

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.uploadForm = new FormGroup({
      file: new FormControl(null)
    });
  }



onFileSelected(event: Event) {
  const target = event.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    // Atualize a variável selectedFile com o arquivo selecionado
    this.selectedFile = target.files[0];

    const reader = new FileReader();
    reader.onload = () => {
      console.log(' result', reader.result);
      const base64String = reader.result?.toString().split(',')[1]; // Extrai a string Base64
      console.log('Base64 do CSV:', base64String);
    };

    reader.readAsDataURL(this.selectedFile); // Lê o arquivo como DataURL (que inclui o Base64)
  }
}


  onSubmit() {
    if (!this.selectedFile) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Selecione um arquivo primeiro!',
      });
      return;
    }

    const reader = new FileReader();
    reader.onload = () => {
      const base64String = reader.result?.toString().split(',')[1]; // Extrai o Base64
      if (!base64String) {
        Swal.fire({
          icon: 'error',
          title: 'Erro',
          text: 'Falha ao processar o arquivo!',
        });
        return;
      }
      console.log('Base64 do CSV:', base64String);
      const tamanhoEmKB = this.selectedFile!.size / 1024;
      const tamanhoEmMB = tamanhoEmKB / 1024;

      const arquivo: Arquivo = {
        nomeDoArquivo: this.selectedFile!.name,
        tamanhoDoArquivo: tamanhoEmMB,
        dataDeImportacao: new Date().toISOString(),
        tipoDeArquivo: this.selectedFile!.type,
        caminhoArquivo: 'C:\\Users\\Downloads',  // Corrigido para caminho válido (em Windows, utilizar \\)
        conteudoArquivo: base64String
      };

      console.log('Objeto Arquivo:', arquivo);
      this.enviarParaBackend(arquivo);
    };

    reader.readAsDataURL(this.selectedFile);
  }



  enviarParaBackend(arquivo: Arquivo) {
    if (!this.selectedFile) {
      Swal.fire({
        icon: 'error',
        title: 'Erro!',
        text: 'Nenhum arquivo selecionado.',
      });
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile, this.selectedFile.name);
    this.http.post('http://localhost:8080/arquivos/upload', formData)
      .subscribe({
        next: (res) => {
          Swal.fire({
            icon: 'success',
            title: 'Sucesso!',
            text: 'Arquivo enviado com sucesso!',
          });
          console.log('Resposta do backend:', res);
        },
        error: (err) => {
          Swal.fire({
            icon: 'error',
            title: 'Erro!',
            text: 'Falha ao enviar o arquivo.',
          });
          console.error('Erro:', err);
        }
      });
  }





}
