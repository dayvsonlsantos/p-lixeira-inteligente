
<p align="center">
 Lixo Inteligente (SmartTrash)
</p>

<p align="center">
 <a href="#Sobre">Sobre</a> •
 <a href="#Dayvson-Lima">Dayvson Lima</a> •
 <a href="#Daniel-Oliveira">Daniel Oliveira</a> • 
 <a href="#-Licença">Licença</a>
</p>

## ![link](https://github.com/dayvsonlsantos/p-mostra-dados/assets/102249811/6487f089-0953-418f-b661-4ba593e08c4c)  Sobre

<p align=justify>O <b>Lixo Inteligente (SmartTrash)</b> é um projeto IOT desenvolvido como projeto final para a cadeira de IOT na faculdade Senac. O desenvolvemos para facilitar a gestão de resíduos. Nosso projeto utiliza sensores ultrassônicos, um motor servo e uma barra de LED RGB, todos controlados por um Arduino Node MCU. Além disso, os dados de uso do lixo são armazenados na nuvem, permitindo monitoramento remoto.</p>

## Itens:

- **Placa Arduino Node MCU**
- **Protoboard**
- **Barra de LED RGB**
- **2 Sensores Ultrassônicos**
- **Servo Motor**
- **API e Banco de Dados na Nuvem**

<br/>

## ![link](https://github.com/dayvsonlsantos/p-mostra-dados/assets/102249811/6487f089-0953-418f-b661-4ba593e08c4c)  Objetivo

<p align=justify>Automatizar a operação do lixo e fornecer dados em tempo real sobre o volume de resíduos, visando melhorar a eficiência e a gestão dos lixos em ambientes urbanos.</p>
<br/>

## ![link](https://github.com/dayvsonlsantos/p-mostra-dados/assets/102249811/6487f089-0953-418f-b661-4ba593e08c4c)  Funcionamento

### DETECÇÃO DE APROXIMAÇÃO
<p align=justify>Sensor ultrassônico detecta quando uma pessoa se aproxima do lixo.</p>
<p align=justify>O motor servo abre a tampa automaticamente.</p>
<br/>

### MONITORAMENTO DE VOLUME
<p align=justify>Outro sensor ultrassônico dentro do lixo mede o volume de resíduos.</p>
<p align=justify>A barra de LED RGB externa indica o nível de preenchimento do lixo.</p>
<br/>

### REGISTRO NA NUVEM
<p align=justify>Cada movimento é registrado e salvo em um banco de dados na nuvem via API.</p>
<p align=justify>Dados salvos: Cor RGB, status da tampa (aberta ou fechada), momento de abertura, volume do lixo.</p>
<br/>

## Antes de iniciar

#### 1° Renomear o arquivo .env.example para .env
#### 2° Adicione a rota do backend, seguindo o seguinte modelo:
```bash
http://url:8080/rota/:path*
```
##### Obs: Suponto que sua rota seja assim: http://url:8080/rota/aqui -> deverá colocá-la da seguinte forma: http://url:8080/rota/:path*

<br />

## Iniciar

Primeiro, execute o servidor de desenvolvimento:

```bash
npm run dev
# or
yarn dev
# or
pnpm dev
# or
bun dev
```

Abra [http://localhost:3000](http://localhost:3000) com seu navegador para ver o resultado

<br />


### <p align=center>Dayvson Lima<p/>

<img align='right' src='https://avatars.githubusercontent.com/u/102249811?s=400&u=2843e9ff654eb5587f9e6ad6b873fed0b1c0df77&v=4' width="150px;">

<p align=justify>Olá, sou Dayvson. Tenho 22 anos, graduando em Análise e Desenvolvimento de Sistemas. Tenho como foco aperfeiçoar meus conhecimentos em TI e estou sempre aberto a aprender novas tecnologias para atender às necessidades dos clientes.</p>

<br />

[![Github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://www.linkedin.com/in/dayvsonlimasantos)[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white)](https://github.com/dayvsonlsantos)

<br/>

### <p align=center>Daniel Oliveira<p/>

<img align='left' src='https://github.com/dayvsonlsantos/p-near-hospital/assets/102249811/a01154cd-50fb-4cad-96e9-c74a1276586b' width="150px;">

<p align=justify>Me chamo Daniel, sou novo no mercado da tecnologia, e tenho me interessado bastante, atualmente já faço diversos cursos de TI, principalmente voltado para área backend em Java. Além disso, estou me formando em ADS(análise e desenvolvimento de sistema) pela faculdade Senac.</p>

<br/>
<br/>
<br/>
<br/>
