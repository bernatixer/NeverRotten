

# NeverRotten

### Organize your food and never let it get rotten! 

## Inspiration

We are students, we forget things, and having some products getting expired on the fridge is somehow usual. We also share a flat with other friends, but... what is your food and what your collegue's one?

We wanted to solve this problems, an application that helps you to not have your food expired and also that tracks the food you currently have.

## What it does

The application takes care of what food you actually have and it's expiration date. Also to make this process easy, you don't need to enter the name of the objects, you only let a camera scan it and let the app register it. So when a product is expiring the app sends you a message informing you. Since you can share a house with other people, you can see what food is yours and what isn't, so you only eat/take what is yours ;)

## How we built it

We decided to analyze objects by a photo, so you don't need to enter the names of your products manually, so we have a Raspberry Pi Zero W with a camera integrated making photos and sending them to Microsoft Vision recognition API. Then we have the android application which let's you enter the expiration day and keep track of your products.

## Challenges we ran into

We wanted to use the DragonBoard 820c for the hardware, but we ran into problems working with it so we moved to RaspberryPi and Arduino. We also had a lot of trouble installing some modules to RaspberryPi since it's a small computer. Also, with the Arduino WiFi module, which we couldn't install it because some driver problems.

## Accomplishments that we're proud of

We made on time to get the raspberry take a camera, analyze it and send it to the application. This application also keeps track of the products. So we're happy to have a 'final' product :D

## What we learned

We learned Python! We knew how to do some simple coding but since we had to work more with libraries, we learned more. Also, configuring the raspberry to work headless, making it connect directly to the wifi was also a great challenge that we accomplished.

## What's next for NeverRotten

We have a lot of future ideas, firstly we would love to implement facial recognition so the application knows who is submitting new food to the fridge. Also with the data we have it would be nice to recommend the users some receipts!! So you know what you can have for dinner if you're not inspirated ;D

As a very future idea, we think this could be hooked with let's say, your local supermarket. They could know what food you are missing so when you go to buy the supermarket could prepare your order.

## Team Members

Bernat Torres 

> I've worked with the raspberry pi, I made the camera take the photo and upload it to FireBase, then use the FireBase URL and submit it to the Microsoft Vision API to detect the object.  

Dreaqdp

> I've focused on hardware part. I have tried making the Dragonboard 820c become the main hardware part, but we had a lot of problems and I started with Arduino; I made all the circuits for LCD, it's code, tried to configure Wifi's modul and, as last option, configure an SPI connection Arduino-Raspberry Pi Zero with python.

LordSantAnna

> I worked on the Android App including the UI, the backend and the interaction with the Firebase Database. 