import serial, time

arduino = serial.Serial('COM3', 9600, timeout = .1)
time.sleep(1)
while True: 
	data = arduino.readline()[:-2]
	if data == "photo_prod" or data == "rept_photo":
		#send a "take a photo" order
		# send a string with the name of the product
		arduino.write ();

