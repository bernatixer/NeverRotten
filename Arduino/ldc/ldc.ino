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

  if (state) { //passar al server que sí repetició
     digitalWrite (LED, HIGH);
  }
}

void loop() {
  // set the cursor to col0, line 1
  
  // print de number of seconds since reset

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
      
      
      delay (100);
      stage--;
      break;
    case 3 :
      lcd.setCursor (0, 0);
      lcd.print ("Repeat photo");
    case 4 :
    case 5 :
    case 6 :
    case 7 :*/
  }
  
  stage++;
  stage %= 2;
}
