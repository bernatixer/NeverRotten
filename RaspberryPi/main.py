from picamera import PiCamera
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
    print(message["data"])

    if message["data"] == 1:
        camera = PiCamera()
        camera.start_preview()
        camera.capture('image.png')
        camera.stop_preview()

        print("DONE")

        storage.child("images/image.png").put("image.png")
        image_url = storage.child("images/image.png").get_url(1)
        print image_url

        subscription_key = "9923a43e1d22471da3450888fc6ad5db"
        assert subscription_key

        vision_base_url = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/"
        vision_analyze_url = vision_base_url + "analyze"

        headers  = {'Ocp-Apim-Subscription-Key': subscription_key }
        params   = {'visualFeatures': 'Categories,Description'}
        data     = {'url': image_url}
        response = requests.post(vision_analyze_url, headers=headers, params=params, json=data)
        response.raise_for_status()
        analysis = response.json()

        banneds = ["indoor","sitting","small","table","black","old","mirror","white","holding","case","man","woman","young","clock","desk","desktop","open","laptop","top","water","red","refrigerator","sink","top","office","wooden","holding","room","standing","monitor","keyboard","mouse","computer"]
        tags = analysis["description"]["tags"]
        print(tags)
        good = tags[0]
        ban = False
        for tag in tags:
            good = tag
            for banned in banneds:
                if tag == banned:
                    ban = True
                    break
            if ban == False:
                break
            else:
                ban = False

        user = db.child("Food").get()

        data = {"data": 1, "foto": image_url, "id":len(user.val()) , "name": good, "propietari": "Adria"}
        db.child("Food").child(len(user.val())).set(data)

        data = {"scan": 0}
        db.child("Info").set(data)

my_stream = db.child("Info").stream(stream_handler)
