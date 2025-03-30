# Guia de Instalação e Configuração do Projeto

Este guia irá orientá-lo sobre como configurar o ambiente de desenvolvimento e executar o projeto corretamente, tanto para o **frontend** (Angular) quanto para o **backend** (Java).

## Requisitos

Antes de iniciar, certifique-se de ter as seguintes versões e ferramentas instaladas:

### Ferramentas Necessárias

- **Angular CLI**: 17.3.13
- **Node.js**: 18.13.0
- **NPM**: 8.19.3
- **Java**: 17
- **IntelliJ IDEA** (ou outra IDE de sua preferência para o backend)
- **MySQL** (ou outro banco de dados compatível configurado)

### Instalação do Node.js e Angular CLI

Se você ainda não tem o Angular CLI e o Node.js instalados, siga os passos abaixo:

```bash
# Instalar Node.js (se necessário, baixe do site oficial: https://nodejs.org/)
nvm install 18.13.0  # Caso esteja usando o NVM

# Instalar Angular CLI
npm install -g @angular/cli@17.3.13
```














### 1.Passos para iniciar o Frontend
1. Clonar o Repositório
Clone o repositório do projeto para o seu diretório local:
```sh
git clone https://github.com/LuixCarlos00/Desafio_Pratico_para_Programador
```

### 2. . Acessar o Diretório do Projeto
Entre na pasta do frontend do projeto:
```sh
cd Desafio_Pratico_para_Programador/front/projetoweb
```

### 3 Instalar Dependências
Baixe as dependências necessárias para o projeto:
```sh
npm install
```

### 3.1 Execute o comando para baixar dependências 
```sh
npm install
```

### 4.Iniciar o Servidor de Desenvolvimento
Inicie o servidor de desenvolvimento para o frontend:
```sh
ng serve
```
O projeto estará disponível em `http://localhost:4200/`.

5. Verificar se a Instalação foi Bem-Sucedida
Para verificar se tudo foi instalado corretamente, execute:
```sh
ng version
```
O output deve ser semelhante à imagem abaixo:

![image](https://github.com/user-attachments/assets/d335db07-f6f9-4f3f-9d4f-5d1565f508b8)

---

### Passos para Iniciar o Backend
1. Requisitos do Backend
Java: 17
IntelliJ IDEA (ou outra IDE de sua preferência)

2. Configuração do Banco de Dados
No backend, as configurações de conexão com o banco de dados MySQL estão no arquivo application.properties. As credenciais padrão são:

Usuário: root
Senha: root
Nome do banco: projeto


3. Criar o Banco de Dados
Antes de iniciar o projeto, crie o banco de dados manualmente com o nome projeto.

![image](https://github.com/user-attachments/assets/4cc00aa0-aeb1-4ad7-9d93-c6938b3368eb)



5. Iniciar o Backend
Abra o projeto no IntelliJ IDEA e execute o arquivo principal do projeto. Ao iniciar o projeto pela primeira vez, as tabelas serão criadas automaticamente no banco de dados.

A mensagem no console indicando que o backend está rodando corretamente será:

![image](https://github.com/user-attachments/assets/49d989e5-dc01-411d-836a-a6e517044033)





Acesso ao Sistema
Quando o frontend e backend estiverem rodando corretamente, você pode acessar o sistema no navegador através do seguinte endereço:

http://localhost:4200/

Vídeo Tutorial
Para um guia visual sobre como configurar e executar o projeto, assista ao vídeo disponível no link abaixo:

Video: https://drive.google.com/file/d/1TC9w1byAChFU3cxuz9wNpdzC_ECeWyz4/view?usp=sharing



