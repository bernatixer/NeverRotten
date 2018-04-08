import requests
import pyrebase

config = {
  "apiKey": "AIzaSyAvkeB92bhNY7_WitpQrtefQwVnZX8lMs0",
  "authDomain": "neverrotten-d2127.firebaseapp.com",
  "databaseURL": "https://neverrotten-d2127.firebaseio.com",
  "storageBucket": "neverrotten-d2127.appspot.com"
}

firebase = pyrebase.initialize_app(config)

db = firebase.database()
storage = firebase.storage()

def stream_handler(message):
    print(message["event"]) # put
    print(message["path"]) # /-K7yGTTEp7O549EzTYtI
    print(message["data"]) # {'title': 'Pyrebase', "body": "etc..."}

    data = {"scan": 0}
    db.child("Info").set(data)

my_stream = db.child("Info").stream(stream_handler)
