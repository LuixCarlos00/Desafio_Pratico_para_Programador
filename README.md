# Guia de Instalação e Configuração do Projeto

## Requisitos
Antes de iniciar, certifique-se de ter as seguintes versões instaladas:
- **Angular CLI**: 17.3.13
- **Node.js**: 18.13.0
- **NPM**: 8.19.3

## Passos para iniciar o projeto

### 1. Instalar o Angular CLI e Node.js
Se você ainda não tem o Angular CLI e o Node.js instalados, siga os passos abaixo:
```sh
# Instalar Node.js (se necessário, baixe do site oficial: https://nodejs.org/)
nvm install 18.13.0 # Se estiver usando nvm

# Instalar Angular CLI
npm install -g @angular/cli@17.3.13
```

### 2. Clonar o repositório e acessar o diretório do projeto
```sh
git clone https://github.com/LuixCarlos00/Desafio_Pratico_para_Programador
```

### 3. Instalar as dependências do projeto
Entre no diretório do projeto web 
```sh
cd Desafio_Pratico_para_Programador/front/projetoweb
```

### 3.1 Execute o comando para baixar dependências 
```sh
npm install
```

### 4. Iniciar o Front
```sh
ng serve
```
O projeto estará disponível em `http://localhost:4200/`.

## Observações
- Certifique-se de ter todas as dependências instaladas corretamente.
- Caso ocorra algum erro relacionado a permissões, tente rodar `npm install` com `sudo` (Linux/macOS).
- Para verificar se a instalação foi bem-sucedida, rode:
```sh
ng version
```
O output deve ser semelhante à imagem abaixo:

![image](https://github.com/user-attachments/assets/d335db07-f6f9-4f3f-9d4f-5d1565f508b8)

---

### 5. Inicializando o projeto Back 
Abra o IntelliJ e carregue o projeto no diretório `back`. Em seguida, execute o projeto.

No arquivo `application.properties` contém a configuração de conexão com o banco de dados MySQL. 
As credenciais padrão são:
- **Usuário**: root
- **Senha**: root
- **Nome do banco**: projetoteste

Crie o banco de dados manualmente antes de iniciar o projeto:

![image](https://github.com/user-attachments/assets/306ff03d-7c39-4064-ba1b-f3252d0f9fee)

Ao iniciar o projeto pela primeira vez, as tabelas serão criadas automaticamente no banco de dados:

![image](https://github.com/user-attachments/assets/49d989e5-dc01-411d-836a-a6e517044033)

Você deve subir o backend e o frontend para que o sistema funcione corretamente.

Quando o backend estiver rodando corretamente, aparecerá a seguinte mensagem no console:
```sh
Started Main in 2.67 seconds (process running for 2.955)
```

E no frontend, a seguinte saída será exibida:
```sh
Application bundle generation complete. [5.677 seconds]     

Watch mode enabled. Watching for file changes...
  ➜  Local:   http://localhost:4200/
  ➜  press h + enter to show help
```

### Acesse o sistema no navegador:
[http://localhost:4200/](http://localhost:4200/)

Video: https://drive.google.com/file/d/1TC9w1byAChFU3cxuz9wNpdzC_ECeWyz4/view?usp=sharing



