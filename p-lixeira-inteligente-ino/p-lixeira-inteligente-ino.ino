#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>
#include <ESP8266HTTPUpdateServer.h>
#include <Adafruit_NeoPixel.h>
#include <Servo.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>

Servo servo;

#define PIN_LED D4
#define QTD_LEDS 8

#define TRIGGER_PIN D6
#define ECHO_PIN D5

#define TRIGGER_PIN_MOTOR D7
#define ECHO_PIN_MOTOR D8

long duration;
int distance;

long duration_MOTOR;
int distance_MOTOR;

Adafruit_NeoPixel pixels(QTD_LEDS, PIN_LED, NEO_GRB + NEO_KHZ800);

ESP8266WebServer httpServer(80);
ESP8266HTTPUpdateServer httpUpdater;

String RGB = "0,0,0,255,0";

unsigned long lastSensorRead = 0;
unsigned long sensorReadDelay = 1000;
// float distance = 0.0;

const float TRASH_BIN_HEIGHT = 6.0; // Altura da lixeira em cm
const float TRASH_BIN_WIDTH = 10.0;  // Largura da lixeira em cm
const float TRASH_BIN_DEPTH = 6.0;  // Profundidade da lixeira em cm

const char* serverUrl = "http://url:porta/";

unsigned long lastTransmissionTime = 0;

void setup() {
  Serial.begin(115200);
  pixels.begin();

  pinMode(TRIGGER_PIN, OUTPUT);
  pinMode(ECHO_PIN, INPUT);

  pinMode(TRIGGER_PIN_MOTOR, OUTPUT);
  pinMode(ECHO_PIN_MOTOR, INPUT);

  connectToWifi();

  acendeLed(255, 0, 0, 255); 
  updateRGBColors();

  // servo motor
  servo.attach(5); //D1
  servo.write(0);

  delay(2000);
}



void loop() {
  httpServer.handleClient();

  distance = calculateDistance();
  distance_MOTOR = calculateDistanceMotor();

  // Serial.println("Distancia Motor: " + distance_MOTOR);
  String* colors = new String[QTD_LEDS];
  colors = updateLEDs(distance);

  float volume = calculateVolume(distance);
  Serial.println("Volume calculado: " + String(volume, 2) + " cm³");

  String motorState = (distance_MOTOR <= 40) ? "open" : "close";

  if (millis() - lastTransmissionTime >= 20000) {
    String get_led_colors = getLastNonZeroColor(colors);
    sendDataToServer(volume, motorState, get_led_colors);
    delete[] colors;
    // Atualiza o tempo da última transmissão
    lastTransmissionTime = millis();
  }

  // if (millis() - lastSensorRead >= sensorReadDelay) {
  //   lastSensorRead = millis();
  //   distance = readDistance();
  //   Serial.println("Distância medida: " + String(distance, 2) + " cm");

  //   float volume = calculateVolume(distance);
  //   Serial.println("Volume calculado: " + String(volume, 2) + " cm³");

  //   updateLEDs(distance);
  // }

  // Servo motor
  if (distance_MOTOR <= 40) {
    servo.write(180);
  } else {
    servo.write(0);
  }
  delay(1000);
}

float calculateDistance() {
  digitalWrite(TRIGGER_PIN, LOW);
  delayMicroseconds(2);

  digitalWrite(TRIGGER_PIN, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIGGER_PIN, LOW);
  
  duration = pulseIn(ECHO_PIN, HIGH);

  // Calcula e retorna a distância
  int distance_calc = duration * 0.034 / 2;
  Serial.println("Distancia: ");
  Serial.println(distance_calc);
  return distance_calc;
}

float calculateDistanceMotor() {
  digitalWrite(TRIGGER_PIN_MOTOR, LOW);
  delayMicroseconds(2);

  digitalWrite(TRIGGER_PIN_MOTOR, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIGGER_PIN_MOTOR, LOW);
  
  duration_MOTOR = pulseIn(ECHO_PIN_MOTOR, HIGH);

  // Calcula e retorna a distância
  int distance_MOTOR_calc = duration_MOTOR * 0.034 / 2;
  Serial.println("Distancia Motor:");
  Serial.println(distance_MOTOR_calc);
  return distance_MOTOR_calc;
}

void connectToWifi() {
  Serial.print("Conectando ao WiFi");

  const char *ssid = "nome_da_rede_wifi";
  const char *password = "senha_da_rede_wifi";

  WiFi.begin(ssid, password);

  int counter = 0;
  while (WiFi.status() != WL_CONNECTED && counter++ < 20) {
    delay(500);
    Serial.print(".");
  }
  if (WiFi.status() != WL_CONNECTED) {
    Serial.println("Não é possível conectar-se ao Wi-Fi. Reiniciando...");
    ESP.restart();
  }

  Serial.println("");
  Serial.print("Conectado ao WiFi. Endereço IP: ");
  Serial.println(WiFi.localIP());

  httpUpdater.setup(&httpServer);
  httpServer.on("/updateRGBColors", updateRGBColors);
  httpServer.on("/getRGBColors", getRGBColors);
  httpServer.on("/get_device_type", get_device_type);
  httpServer.on("/getDistance", getDistance);
  httpServer.on("/getVolume", getVolume); // Nova rota para obter o volume
  httpServer.begin();

  Serial.println("Servidor HTTP iniciado.");
}

void acendeLed(int r, int g, int b, int a) {
  for(int i = 0; i < QTD_LEDS; i++) {
    pixels.setPixelColor(i, pixels.Color(r, g, b));
  }
  pixels.show();
}

void updateRGBColors() {
  RGB = httpServer.arg("rgb_color");

  int R, G, B, A, toggle;
  sscanf(RGB.c_str(), "%d,%d,%d,%d,%d", &R, &G, &B, &A, &toggle);

  if (toggle == 0) {
    acendeLed(0, 0, 0, 0);
  } else {
    acendeLed(R, G, B, 255);
  }

  httpServer.send(200, "text/plain", RGB);
}

void getRGBColors() {
  httpServer.send(200, "text/plain", RGB);
}

void get_device_type() {
  httpServer.send(200, "text/plain", "Trash Bin Volume Sensor");
}

void getDistance() {
  String distanceStr = String(distance, 2);
  httpServer.send(200, "text/plain", distanceStr);
}

void getVolume() {
  float volume = calculateVolume(distance);
  String volumeStr = String(volume, 2) + " cm³";
  httpServer.send(200, "text/plain", volumeStr);
}

// float readDistance() {
//   digitalWrite(TRIGGER_PIN, LOW);
//   delayMicroseconds(5);
//   digitalWrite(TRIGGER_PIN, HIGH);
//   delayMicroseconds(15);
//   digitalWrite(TRIGGER_PIN, LOW);
  
//   int duration = pulseIn(ECHO_PIN, HIGH);
//   float distance = duration * 0.0343 / 2;
//   return distance;
// }

float calculateVolume(float measuredDistance) {
  if (measuredDistance > TRASH_BIN_HEIGHT) {
    measuredDistance = TRASH_BIN_HEIGHT;
  }
  float emptySpaceHeight = TRASH_BIN_HEIGHT - measuredDistance;
  float volume = TRASH_BIN_WIDTH * TRASH_BIN_DEPTH * measuredDistance;
  return volume;
}

String* updateLEDs(float measuredDistance) {
  String* led_colors = new String[QTD_LEDS];
  float filledHeight = TRASH_BIN_HEIGHT + 1 - measuredDistance;
  int numLedsToLight = (filledHeight / TRASH_BIN_HEIGHT) * QTD_LEDS;

  for (int i = 0; i < QTD_LEDS; i++) {
    if (i < numLedsToLight) {
      if (i < 2) {
        pixels.setPixelColor(i, pixels.Color(0, 255, 0)); // Verde
        led_colors[i] = "0,255,0"; // Verde
      } else if (i < 4) {
        pixels.setPixelColor(i, pixels.Color(255, 255, 0)); // Amarelo
        led_colors[i] = "255,255,0"; // Amarelo
      } else if (i < 6) {
        pixels.setPixelColor(i, pixels.Color(255, 120, 0)); // Laranja
        led_colors[i] = "255,120,0"; // Laranja
      } else {
        pixels.setPixelColor(i, pixels.Color(255, 0, 0)); // Vermelho
        led_colors[i] = "255,0,0"; // Vermelho
      }
    } else {
      pixels.setPixelColor(i, pixels.Color(0, 0, 0)); // Desliga o LED
      led_colors[i] = "0,0,0"; // Desliga o LED
    }
  }
  pixels.show();
  getLastNonZeroColor(led_colors);
  return led_colors;
}

String getLastNonZeroColor(String* colors) {
  String lastNonZeroColor = "0,0,0"; // Valor padrão se todos os LEDs estiverem desligados

  // Começa a análise do último elemento do array
  for (int i = QTD_LEDS - 1; i >= 0; i--) {
    // Verifica se a cor do LED atual é diferente de "0,0,0"
    if (colors[i] != "0,0,0") {
      lastNonZeroColor = colors[i]; // Se for diferente, atualiza a cor não nula
      break; // Sai do loop após encontrar a primeira cor não nula
    }
  }

  Serial.print("RGB:");
  Serial.print(lastNonZeroColor);
  return lastNonZeroColor;
}

void sendDataToServer(float volume, String motorState, String led_colors) {
  if (WiFi.status() == WL_CONNECTED) {
    WiFiClient client;
    HTTPClient http;
    http.begin(client, serverUrl);
    http.addHeader("Content-Type", "application/json");

    // Create JSON object
    StaticJsonDocument<200> doc;
    doc["volume"] = volume;
    doc["trash_state"] = motorState;
    doc["rgb"] = led_colors;

    // Serialize JSON object to string
    String jsonPayload;
    serializeJson(doc, jsonPayload);

    int httpResponseCode = http.POST(jsonPayload);
    if (httpResponseCode > 0) {
      String response = http.getString();
      Serial.println(httpResponseCode);
      Serial.println(response);
    } else {
      Serial.print("Erro na requisição: ");
      Serial.println(httpResponseCode);
    }
    http.end();
  } else {
    Serial.println("Falha na conexão WiFi");
  }
}
