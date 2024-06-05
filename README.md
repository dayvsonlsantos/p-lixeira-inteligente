![LineLumos](https://user-images.githubusercontent.com/102249811/184330542-24bd9f7f-b369-4460-ae37-d84455a3589c.png)


<p align="center">
 Lixeira Inteligente
</p>

<p align="center">
 <a href="#Sobre">Sobre</a> •
 <a href="#Objetivo">Objetivo</a> •
 <a href="#Funcionamento">Funcionamento</a> •
 <a href="#Itens-Necessários">Itens Necessários</a> •
 <a href="#Circuito-IOT">Circuito IOT</a> •
 <a href="#Resumo-do-Fluxo">Resumo do Fluxo</a> •
 <a href="#Diagrama">Diagrama</a> •
 <a href="#Técnico">Técnico</a> •
 <a href="#Equipe">Equipe</a> •
 <a href="#Licença">Licença</a> •
</p>

## ![link](https://github.com/dayvsonlsantos/p-mostra-dados/assets/102249811/6487f089-0953-418f-b661-4ba593e08c4c)  Sobre

<p align=justify>O <b>Lixo Inteligente</b> é um projeto IOT desenvolvido como projeto final para a cadeira de IOT na faculdade Senac. O desenvolvemos para facilitar a gestão de resíduos. Nosso projeto utiliza sensores ultrassônicos, um motor servo e uma barra de LED RGB, todos controlados por um Arduino Node MCU. Além disso, os dados de uso do lixo são armazenados na nuvem, permitindo monitoramento remoto.</p>

<br/>

## ![link](https://github.com/dayvsonlsantos/p-mostra-dados/assets/102249811/6487f089-0953-418f-b661-4ba593e08c4c)  Objetivo

<p align=justify>Automatizar a operação do lixo e fornecer dados em tempo real sobre o volume de resíduos, visando melhorar a eficiência e a gestão dos lixos em ambientes urbanos.</p>
<br/>

## ![link](https://github.com/dayvsonlsantos/p-mostra-dados/assets/102249811/6487f089-0953-418f-b661-4ba593e08c4c)  Funcionamento

- ### Detecção de Aproximação
<p align=justify>Sensor ultrassônico detecta quando uma pessoa se aproxima do lixo.</p>
<p align=justify>O motor servo abre a tampa automaticamente.</p>
<br/>

- ### Monitoramento de Volume
<p align=justify>Outro sensor ultrassônico dentro do lixo mede o volume de resíduos.</p>
<p align=justify>A barra de LED RGB externa indica o nível de preenchimento do lixo.</p>
<br/>

- ### Registro na Nuvem
<p align=justify>Cada movimento é registrado e salvo em um banco de dados na nuvem via API.</p>
<p align=justify>Dados salvos: Cor RGB, status da tampa (aberta ou fechada), momento de abertura, volume do lixo.</p>
<br/>

## ![link](https://github.com/dayvsonlsantos/p-mostra-dados/assets/102249811/6487f089-0953-418f-b661-4ba593e08c4c) Itens Necessários:

- **Placa Arduino Node MCU**
- **Protoboard**
- **Barra de LED RGB**
- **2 Sensores Ultrassônicos**
- **Servo Motor**
- **API e Banco de Dados na Nuvem**

<br/>


## ![link](https://github.com/dayvsonlsantos/p-mostra-dados/assets/102249811/6487f089-0953-418f-b661-4ba593e08c4c) Circuito IOT

<img src='https://github.com/dayvsonlsantos/p-lixeira-inteligente/assets/102249811/c675f833-d482-40bb-8a73-5467e24a5975' width="500px;">

##### Obs: Circuito feito através do [Cirkit Designer](https://www.cirkitstudio.com/) 

<br/>

### Detalhes de Fiação

- **NodeMCU V3 ESP8266**
  - `3V` está conectado a:
    - `VCC` da matriz de LEDs RGB
    - `+5V` de ambos os servos Tower Pro SG90
  
  - `GND` é compartilhado com:
    - `GND` da matriz de LEDs RGB
    - `GND` de ambos os servos motor

  - `D4` está conectado ao `DIN` da matriz de LEDs RGB
    
  - `D6` está conectado ao `pino de Sinal` de um servo motor

  - `D7` está conectado ao `pino de Sinal` do outro servo motor
    
  - `Matriz de LEDs RGB`
    - `VCC` conectado a `3V` do NodeMCU V3 ESP8266
    - `GND` conectado a `GND` do NodeMCU V3 ESP8266
    - `DIN` conectado a `D4` do NodeMCU V3 ESP8266

  - Servo Motor
    - `Sinal de um servo` conectado a `D6` do NodeMCU V3 ESP8266
    - `Sinal do outro servo` conectado a `D7` do NodeMCU V3 ESP8266
    - `+5V` de ambos os servos conectado a `3V` do NodeMCU V3 ESP8266
    - `GND` de ambos os servos conectado a `GND` do NodeMCU V3 ESP8266

<br/>

## ![link](https://github.com/dayvsonlsantos/p-mostra-dados/assets/102249811/6487f089-0953-418f-b661-4ba593e08c4c) Resumo do Fluxo

- ### Arduino:
  Envia dados periodicamente via POST usando a biblioteca httpClient.

- ### Backend:
  - <b>Controller</b>: Recebe a requisição POST.
  - <b>JPA Repository</b>: Salva os dados no banco de dados utilizando o método save.
  - <b>Consultas SQL</b>: Cria consultas específicas na interface repository para análise de dados.
  - <b>Rota HTTP GET</b>: Fornece acesso aos dados processados via rota HTTP GET.
 
- ### Frontend:
  - <b>app/page.tsx</b>: Faz a requisição GET ao backend, usando o fetch.
  - <b>const lastItem em app/page.tsx</b>: Armazena o último item retorno dentre os objetos.
  - <b>mappedData em app/page.tsx</b>: Cria um novo objeto com os dados organizados.
  - <b>data em app/page.tsx</b>: Recebe o valor de mappedData, e permite o uso dos mesmos no HTML.

- ### Banco de Dados MySQL:
  - <b>Configuração de Credenciais</b>: Assegura o acesso seguro ao banco de dados através das configurações no arquivo application.properties.
  - <b>Geração do JAR</b>:
    - Build do Projeto: Gera o arquivo JAR da aplicação usando Maven ou Gradle.
  - <b>Deploy na Instância EC2</b>:
    - Acesso AWS: Inicializa e conecta-se à instância EC2.
    - Transferência do JAR: Usa scp para copiar o JAR para a instância EC2.
    - Execução do JAR: Executa o JAR na instância EC2 usando java -jar.'

<br/>

## ![link](https://github.com/dayvsonlsantos/p-mostra-dados/assets/102249811/6487f089-0953-418f-b661-4ba593e08c4c) Diagrama

<img src='https://github.com/dayvsonlsantos/p-lixeira-inteligente/assets/102249811/e6ba52bd-5f9e-490f-beaf-f408b35805d0' width="500px;">

<br/>


## ![link](https://github.com/dayvsonlsantos/p-mostra-dados/assets/102249811/6487f089-0953-418f-b661-4ba593e08c4c) Técnico

### Frontend

- ##### 1° Acesse p-lixeira-inteligente-front
- ##### 2° Renomear o arquivo .env.example para .env
- ##### 3° Adicione a rota do backend, seguindo o seguinte modelo:
  ```bash
  http://url:8080/rota/:path*
  ```
  ###### Obs: Suponto que sua rota seja assim: http://url:8080/rota/aqui -> deverá colocá-la da seguinte forma: http://url:8080/rota/:path*
- ##### 3.1 (Em caso de erro de requisição): Acesse o arquivo next.config.mjs, e altere:
  ###### Antes:
  ```bash
  destination: url
  ```
  ###### Depois:
  ```bash
  destination: 'http://url:8080/rota/:path*'
  ```
- ##### 4° Execute

  ```bash
  npm run dev
  # ou
  yarn dev
  # ou
  pnpm dev
  # ou
  bun dev
  ```

- ##### 5° Abra [http://localhost:3000](http://localhost:3000) com seu navegador para ver o resultado

<br />

### Backend

#### Funcionamento do Sistema IoT com Arduino e Integração de Backend

<p align="justify">Nódigo do Arduino, utilizamos a biblioteca httpClient, com isso, o sistema faz uma requisição do tipo POST em intervalos de tempo determinados. A requisição contém as propriedades definidas no arquivo src/main/java/com/example/j_api_iot/model/entity/Principal.java.</p>

<br/>

#### Processo de Recepção e Salvamento de Dados

- ##### Requisição POST:
  A cada intervalo de tempo (15seg), o Arduino envia uma requisição POST com os dados coletados.

- ##### Recepção pelo Controller:
  A requisição é recebida pelo Controller no backend.

- ##### Salvamento no Banco de Dados:
  O Controller utiliza o método save do JPA, contido na interface repository, para salvar os dados no banco de dados.

<br/>


#### Consulta e Análise dos Dados

- ##### Criação de Consultas SQL:
  Baseado nos dados salvos, criamos consultas SQL na interface repository para mostrar os dados de acordo com sua relevância e possibilitar análises detalhadas.

- ##### Rota HTTP GET:
  As informações resultantes das consultas são disponibilizadas por meio de uma rota HTTP GET, permitindo o acesso aos dados processados.

<br/>

#### Configuração da Conexão com o Banco de Dados MySQL

- ##### Configuração de Credenciais:
  Definimos as credenciais de acesso ao banco de dados MySQL no arquivo de configuração do Spring Boot (application.properties ou application.yml):

```bash
properties

# application.properties
spring.datasource.username=root
spring.datasource.password=123
spring.datasource.url=jdbc:mysql://srv-meumysql.mysql.database.azure.com/nome_do_Database
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

<br/>

#### Geração do Arquivo JAR

- ##### Geração do JAR:
  Para criar o arquivo JAR da aplicação Spring Boot, siga os seguintes passos:

  - <b>Build com Maven</b>:
    Execute o comando abaixo na raiz do projeto para compilar o código e gerar o arquivo JAR:

    ```bash
      ./mvnw clean package
    ```

  - <b>Build com Gradle</b>:
    Caso esteja utilizando Gradle, execute o comando abaixo na raiz do projeto:

    ```bash
      ./gradlew clean build
    ```

  - <b>Localização do JAR</b>:
    Após a conclusão do build, o arquivo JAR estará disponível no diretório target (para Maven) ou build/libs (para Gradle).

<br/>

#### Deploy na Instância EC2 da AWS

- ##### Acessar Conta AWS e Instância EC2:
  ###### 1° Faça login na sua conta AWS.
  ###### 2° Inicialize a sua instância EC2.

- ##### Conectar-se à Instância EC2:
  ###### 3° Conecte-se à sua instância EC2 usando SSH.

- ##### Copiar o JAR para a Instância EC2:
  ###### 4° No terminal local, execute o seguinte comando dentro da pasta do projeto para copiar o JAR para a instância EC2:

  ```bash
    scp ./target/j-api_iot-0.0.1-SNAPSHOT.jar ec2-user@(seu-DNS):/home/ec2-user
  ```

<br/>

#### Rodar o Projeto na Instância EC2:

- ##### Conectado à sua instância EC2, navegue até o diretório onde o JAR foi copiado e execute o comando:

```bash
    java -jar j-api_iot-0.0.1-SNAPSHOT.jar
```

<br />

### IOT

#### Resumo do Código

Este código configura um sensor de distância baseado em ESP8266 para monitorar o nível de preenchimento de uma lixeira, controlar LEDs RGB e um motor servo, e enviar dados para um servidor via API.

#### Configurar ambiente e compilar o código

- ##### 1° Abra o arquivo p-lixeira-inteligente-ino
- ##### 2° Em Arquivo/Preferencias/URLs do Gerenciador de Placas Adicionais, colocque:
  ```bash
      https://arduino.esp8266.com/stable/package_esp8266com_index.json
  ```
- ##### 3° Em Ferramentas/Placa/Gerenciador de Placas, busque por ESP8266, e instale esse pacote
- ##### 4° Em Rascunho/Incluir Biblioteca/Gerenciar bibliotecas, busque por ESP8266, e instale esse pacotes: ArduinoJson, Adafruit NeoPixel
- ##### 5° Na linha 41, em: const char* serverUrl, adicione a url da sua api, pela qual a placa ESP8266 irá enviar os dados. Exemplo:
  ```bash
      const char* serverUrl = "http:url:porta/";
  ```
- ##### 5° Na linha 41, em: const char* serverUrl, adicione a url da sua api, pela qual a placa ESP8266 irá enviar os dados
- ##### 6° Na linha 149, em: const char *ssid, adicione o nome da sua rede wifi, cuja a palca ESP8266 irá se conectar
- ##### 7° Na linha 150, em: const char *password, adicione a senha da sua rede wifi, cuja a palca ESP8266 irá se conectar
  ```bash
    const char *ssid = "nome_da_rede_wifi";
    const char *password = "senha_da_rede_wifi";
  ```
- ##### 8° Conecte sua placa ao computador
- ##### 9° Em Ferramentas/Placa/esp8266, selecione o modelo: NodeMCU 1.0 (ESP-12E Module)
- ##### 10° Em Ferramentas/Portal, selecione a porta USB que você conectou a ESP8266

<br />

#### Componentes Principais
- **Bibliotecas Importadas**: Inclui bibliotecas para WiFi, servidor web, atualização HTTP, controle de LEDs (Adafruit_NeoPixel), servo motor, HTTP e manipulação de JSON.
- **Pinos Configurados**:
  - **LED**: D4
  - **Sensor de Distância**: TRIGGER_PIN (D6), ECHO_PIN (D5)
  - **Motor**: TRIGGER_PIN_MOTOR (D7), ECHO_PIN_MOTOR (D8)
- **Constantes de Dimensões**: Dimensões da lixeira -> altura (TRASH_BIN_HEIGHT), largura (TRASH_BIN_WIDTH), profundidade (TRASH_BIN_DEPTH).
- **URL do Servidor**: `serverUrl` para enviar os dados.
- **Atraso de Leitura de Sensor**: `sensorReadDelay` definido como 1000 ms.

<br />

#### Função `setup`
- Inicializa a serial, LEDs, pinos do sensor de distância e motor.
- Conecta ao WiFi e configura o servidor HTTP.

<br />

#### Função `loop`
- Lida com requisições HTTP.
- Calcula a distância medida pelos sensores e controla o motor com base na distância.
- Atualiza os LEDs conforme a distância.
- Calcula e envia o volume de preenchimento da lixeira para o servidor a cada 20 segundos.

<br />

#### Detalhamento Adicional
  - Define o estado do motor (`motorState`) como "open" se a distância medida pelo sensor do motor for menor ou igual a 40 cm, caso contrário, define como "close".
  - Verifica se se passaram 20 segundos desde a última transmissão utilizando `millis()` e `lastTransmissionTime`.
  - Se o intervalo de tempo for maior ou igual a 20 segundos, executa os seguintes passos:
    - Obtém a última cor diferente de zero dos LEDs utilizando a função `getLastNonZeroColor`.
    - Chama a função `sendDataToServer` para enviar o volume, estado do motor e a cor dos LEDs para o servidor.
    - Libera a memória alocada para o array `colors` com `delete[] colors`.
    - Atualiza `lastTransmissionTime` para o tempo atual, marcando o momento da última transmissão.

<br />

#### Controle do Motor Servo
- Verifica a distância medida pelo sensor do motor (`distance_MOTOR`).
- Se a distância for menor ou igual a 40 cm, ajusta o servo motor para a posição de 180 graus, indicando que a tampa deve estar aberta.
- Se a distância for maior que 40 cm, ajusta o servo motor para a posição de 0 graus, indicando que a tampa deve estar fechada.

<br />

#### Atualização dos LEDs
- **Função `updateLEDs`**:
  - Calcula o número de LEDs que devem ser acesos com base na altura preenchida da lixeira.
  - Percorre todos os LEDs (`QTD_LEDS`):
    - LEDs são coloridos de acordo com a proporção preenchida:
      - Verde para os primeiros 2 LEDs.
      - Amarelo para os próximos 2 LEDs.
      - Laranja para os próximos 2 LEDs.
      - Vermelho para os últimos 2 LEDs.
    - LEDs além do nível de preenchimento são desligados.
  - Retorna um array com as cores dos LEDs.
- **Função `getLastNonZeroColor`**:
  - Percorre o array de cores dos LEDs de trás para frente.
  - Retorna a última cor diferente de "0,0,0" (desligado).

<br />

#### Envio de Dados para a API

A função **`sendDataToServer`** é responsável por enviar dados para um servidor via API utilizando uma conexão WiFi. O funcionamento é detalhado a seguir:

- ##### Verificação da Conexão WiFi:
  - A função verifica se o dispositivo está conectado à rede WiFi usando WiFi.status() == WL_CONNECTED.

- ##### Configuração da Requisição HTTP:
  - Se conectado, cria um cliente WiFi (WiFiClient client) e um objeto HTTP (HTTPClient http).
  - Inicializa a conexão HTTP com a URL do servidor (http.begin(client, serverUrl)).
  - Define o cabeçalho da requisição como JSON (http.addHeader("Content-Type", "application/json")).

- ##### Criação do Objeto JSON:
  - Um objeto JSON é criado usando StaticJsonDocument.
  - Os dados (volume, motorState, led_colors) são adicionados ao objeto JSON.

- ##### Serialização do JSON:
  - O objeto JSON é serializado em uma string (serializeJson(doc, jsonPayload)).

- ##### Envio da Requisição POST:
  - A requisição POST é enviada com o payload JSON (http.POST(jsonPayload)).
  - O código de resposta HTTP é verificado.

- ##### Tratamento da Resposta:
  - Se a resposta do servidor for positiva (httpResponseCode > 0), a resposta do servidor é impressa.
  - Caso contrário, um erro é impresso.

- ##### Finalização da Requisição:
  - A conexão HTTP é encerrada (http.end()).

- ##### Falha na Conexão WiFi:
  - Se não estiver conectado ao WiFi, uma mensagem de erro é impressa.
  - Este código assegura que os dados sejam enviados ao servidor de forma segura e trata possíveis erros de conexão ou requisição.

<br />

#### Funções Auxiliares
- **`calculateDistance` e `calculateDistanceMotor`**: Calculam a distância medida pelos sensores.
- **`connectToWifi`**: Conecta ao WiFi e inicializa o servidor HTTP.
- **`acendeLed` e `updateRGBColors`**: Controlam as cores dos LEDs.
- **`getRGBColors`, `get_device_type`, `getDistance`, `getVolume`**: Lida com requisições HTTP para obter informações do dispositivo.
- **`calculateVolume`**: Calcula o volume preenchido da lixeira.
- **`updateLEDs`**: Atualiza as cores dos LEDs conforme o nível de preenchimento da lixeira.
- **`getLastNonZeroColor`**: Obtém a última cor diferente de zero dos LEDs para envio ao servidor.

Este código monitora continuamente o nível de preenchimento da lixeira, controla visualmente esse nível através dos LEDs e aciona o motor conforme a necessidade, enquanto periodicamente envia dados para um servidor remoto para monitoramento e análise.

<br />


### Banco de Dados

<p align='justify'>Após adicionar o nome do banco de  automáticamente </p>

#### 1° Crie um banco de dados mysql
#### 2° Adicione o nome do banco de dados ao código Java, em: p-lixeira-inteligente-back/src/main/resources/application.properties
##### Obs: Adicione ao final de spring.datasource.url, ficando:
```bash
spring.datasource.url= jdbc:mysql://localhost:3306/hostlocal_
```

<br />

## ![link](https://github.com/dayvsonlsantos/p-mostra-dados/assets/102249811/6487f089-0953-418f-b661-4ba593e08c4c) Equipe

### <p align=center>Dayvson Lima<p/>

<img align='right' src='https://avatars.githubusercontent.com/u/102249811?s=400&u=2843e9ff654eb5587f9e6ad6b873fed0b1c0df77&v=4' width="150px;">

<p align=justify>Olá, sou Dayvson. Tenho como foco aperfeiçoar cada vez mais as minhas habilidades, tanto as técnicas (hard skills), como as comportamentais (soft skills), me tornando um profisional cada vez melhor. Tenho como foco o desenvolvimento de aplicações web, buscando fornecer uma ótima experiência ao usuário.</p>

<br />

[![Github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://www.linkedin.com/in/dayvsonlimasantos)[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white)](https://github.com/dayvsonlsantos)

<br/>

### <p align=center>Reginaldo Alves<p/>

<img align='left' src='https://github.com/dayvsonlsantos/p-near-hospital/assets/102249811/9a9cf70a-7e7b-49b3-9ce1-2cde7671b488)' width="150px;">

<p align=justify>Olá, me chamo Reginaldo. Desde que me entendo por gente tenho tido contato com a tecnologia, e hoje um dos meus sonhos que estão se realizando é o de virar desenvolvedor. Meu foco é o desenvolvimento Back End e pretendo me aprimorar pra muitas outras tecnologias.</p>

<br />

<br/>

### <p align=center>Natan Gonçalvez<p/>

<img align='right' src='https://github.com/dayvsonlsantos/p-near-hospital/assets/102249811/25c7072b-0deb-4f4e-aec4-5253cba93dd4' width="150px;">

<p align=justify>Me chamo Natan. Sempre estive envolvido com a tecnologia, e atualmente estou me formando em Análise e Desenvolvimento de Sistemas na Faculdade Senac.</p>

<br />

<br/>

### <p align=center>Luis Henrique<p/>

<img align='left' src='https://github.com/dayvsonlsantos/p-lixeira-inteligente/assets/102249811/78388306-7cae-41e6-b410-320ec5dd8af7' width="150px;">

<p align=justify>Me chamo Luis, sou um desenvolvedor de software apaixonado por tecnologia e inovação. Especializo-me em desenvolvimento web, C# (.NET), SQL e cibersegurança, e adoro trabalhar em projetos desafiadores.</p>

<br />

<br/>

### <p align=center>Daniel Oliveira<p/>

<img align='right' src='https://github.com/dayvsonlsantos/p-near-hospital/assets/102249811/a01154cd-50fb-4cad-96e9-c74a1276586b' width="150px;">

<p align=justify>Me chamo Daniel, sou novo no mercado da tecnologia, e tenho me interessado bastante, atualmente já faço diversos cursos de TI, principalmente voltado para área backend em Java. Além disso, estou me formando em ADS(análise e desenvolvimento de sistema) pela faculdade Senac.</p>

<br />

<br/>

## ![link](https://user-images.githubusercontent.com/102249811/184334676-ed902c74-e1fc-44a8-828b-4c3eb1490767.png) Licença

O projeto se encontra sob a licença [GPLv3](https://github.com/dayvsonlsantos/p-lixeira-inteligente/blob/main/LICENSE).
