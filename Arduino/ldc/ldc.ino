/*
   liquidCrystal Library - Hello World
   16x2 LCD

*/

#include <LiquidCrystal.h>
// initialize library, assotiations LCD pins with ardu's pins
const int rs = 50, en = 51, d4 = 26, d5 = 27, d6 = 28, d7 = 29;
LiquidCrystal lcd (rs, en, d4, d5, d6, d7);

int stage = 0;
int state = 0, old_val = 0;
const int BUTTON = 42, LED = 13;
int old_millis;

void setup() {
  lcd.begin (16, 2); //LDC's number of cols and rows
  lcd.write ("Wellcome");
  pinMode (BUTTON, INPUT);
  pinMode (LED, OUTPUT);
  Serial.begin (9600);

}

int repeat_button () {
  int val = digitalRead(BUTTON);
  if (val == HIGH && old_val == LOW) {
    state = 1;
    delay (25);
  }
  old_val = val;

}

void printing (string text, int ms) {
  lcd.print (text);
  delay (ms);
}

void loop() {

  switch (stage) {
    case 0 :
      lcd.setCursor(0, 1);
      lcd.write ("Push button star");
      delay (1500);
      lcd.clear();
      //repeat_button();
      break;
      
    case 1 :
      lcd.setCursor (2, 0);
      lcd.write("Waiting for");
      lcd.setCursor (3, 1);
      lcd.write ("a product");
      delay (150);
      Serial.print("photo prod");
      delay (1000);
      lcd.clear();
      stage++;
      break;
      
    case 2 :
      lcd.setCursor (0, 4);
      lcd.print("Is it..?");
      lcd.setCursor (1, 0);
      lcd.print ("...");
      if (Serial.available) {
        String code  = Serial.read();
        lcd.setCursor (1, 15-code.lenght());
        lcd.print(code);
        stage++;
      }
      delay (1500);
      old_millis = millis();
      break;
      
    case 3 :
      int dif = millis() - old_millis();
      if (dif < 3000 && repeat_button) {
        lcd.setCursor (0, 0);
        printing ("Repeat photo in", 1500);
        printing ("1", 500);
        printing ("2", 500);
        printing ("3", 250);
        Serial.print("rept_photo"); 
        stage = 2;
      }
      else if (dif > 3000) stage = 4;
      break;
     
    case 4 :
      lcd.setCursor (0, 0);
      printing ("Prepare your next product", 2500);
      stage = 1;
      break;
  }
  stage %= 5;
}
