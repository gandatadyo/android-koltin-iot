#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>
const char* ssid     = "@gandatadyo_plus";      // SSID
const char* password = "Dataku123";      // Password
//const char* host = "localhost";  // IP serveur - Server IP
//const int   port = 8080;            // Port serveur - Server Port
//const int   watchdog = 5000;        // Fréquence du watchdog - Watchdog frequency
//unsigned long previousMillis = millis(); 


int pin = D0;
int pin1 = D5;
int pin2 = D6;
int pin3 = D7;

ESP8266WebServer server(80);

void setup() {
  pinMode(pin, OUTPUT);
  pinMode(pin1, OUTPUT);
  pinMode(pin2, OUTPUT);
  pinMode(pin3, OUTPUT);
  
  Serial.begin(115200);
  Serial.print("Connecting to ");
  Serial.println(ssid);
  
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());


  server.on("/", localhost);
  server.begin();
}

void localhost() {
  if(server.arg("relay")=="on"){
    digitalWrite(pin, LOW);
    digitalWrite(pin1, LOW);
    digitalWrite(pin2, LOW);
    digitalWrite(pin3, LOW);
    server.send(200, "text/html", "<a href='?relay=off'><button>OFF</button></a>");
  }
  if(server.arg("relay")=="off"){
    digitalWrite(pin, HIGH);
    digitalWrite(pin1, HIGH);
    digitalWrite(pin2, HIGH);
    digitalWrite(pin3, HIGH);
    server.send(200, "text/html", "<a href='?relay=on'><button>ON</button></a>");
  }



  if(server.arg("switch1")=="on"){
    digitalWrite(pin3, HIGH);
    server.send(200, "text/html", "true");
  }
  if(server.arg("switch1")=="off"){
    digitalWrite(pin3, LOW);
    server.send(200, "text/html", "false");
  }
  if(server.arg("switch2")=="on"){
    digitalWrite(pin2, HIGH);
    server.send(200, "text/html", "true");
  }
  if(server.arg("switch2")=="off"){
    digitalWrite(pin2, LOW);
    server.send(200, "text/html", "false");
  }
  server.send(200, "text/html", "<a href='?relay=off'><button>OFF</button></a>");
}


void loop() {
   server.handleClient();
}
