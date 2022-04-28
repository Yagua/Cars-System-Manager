import urllib3
import json

url = "http://localhost:8080/api/v1/vehicles/"
url_img = "http://localhost:8080/api/v1/images/vh/%d"
url_client = "http://localhost:8080/api/v1/clients/"
http = urllib3.PoolManager()

if not url.startswith("http") and not url_img.startswith("http"):
    raise RuntimeError("Incorrect and possibly insecure protocol in url")

def toBinaryData(file):
    data = ""
    with open(file, "rb") as img:
        data = img.read()
    return data

users = [
    {
        "clientId": "1234352345",
        "firstName": "Harold",
        "secondName": "Andres",
        "paternalLastName": "Rodriguez",
        "maternalLastName": "Rodriguez",
        "address": "kra200",
        "telephoneNumber": "1123123412",
        "userName": "harodriguez",
        "password": "harodriguez",
        "invoices": []
    },
    {
        "clientId": "2345234234",
        "firstName": "Miguel",
        "secondName": "Angel",
        "paternalLastName": "Mejia",
        "maternalLastName": "Galvis",
        "address": "Cll 200 A",
        "telephoneNumber": "2352345234",
        "userName": "mamejiag",
        "password": "mamejiag",
        "invoices": []
    }
]

data = [
    {
        "vehicleName": "Acura MDX",
        "model": "2022",
        "vehicleWeight": 1250,
        "available": True,
        "placeOfManufacture": "Alemania",
        "numberOfDoors": 4,
        "deliveryDate": None,
        "manufacturer": "Pilot",
        "sellerName": "Hernesto Ardila",
        "image": {
            "imageName": "1.jpg",
            "imageType": "image/jpg",
            "imageContent": "./friendly_cars/src/main/resources/static/vehicles/1.jpg"
        }
    },
    {
        "vehicleName": "Acura RDX",
        "model": "Rt2-lx2",
        "vehicleWeight": 1240,
        "available": True,
        "placeOfManufacture": "Alemania",
        "numberOfDoors": 4,
        "deliveryDate": None,
        "manufacturer": "Pilot",
        "sellerName": "Laura Mora",
        "image": {
            "imageName": "2.jpg",
            "imageType": "image/jpg",
            "imageContent": "./friendly_cars/src/main/resources/static/vehicles/2.jpg"
        }
    },
    {
        "vehicleName": "Alfa Romeo Giulia",
        "model": "wrtx23-23u",
        "vehicleWeight": 1350,
        "available": True,
        "placeOfManufacture": "Italia",
        "numberOfDoors": 4,
        "deliveryDate": None,
        "manufacturer": "Alfa Romeo",
        "sellerName": "Lung Zua",
        "image": {
            "imageName": "3.jpg",
            "imageType": "image/jpg",
            "imageContent": "./friendly_cars/src/main/resources/static/vehicles/3.jpg"
        }
    },
    {
        "vehicleName": "Alfa Romeo Stelvio",
        "model": "z2r2-3ku",
        "vehicleWeight": 1420,
        "available": True,
        "placeOfManufacture": "Italia",
        "numberOfDoors": 4,
        "deliveryDate": None,
        "manufacturer": "Alfa Romeo",
        "sellerName": "Emiliano Nara",
        "image": {
            "imageName": "4.jpg",
            "imageType": "image/jpg",
            "imageContent": "./friendly_cars/src/main/resources/static/vehicles/4.jpg"
        }
    },
    {
        "vehicleName": "Aston Martin DB11",
        "model": "DB11",
        "vehicleWeight": 1640,
        "available": True,
        "placeOfManufacture": "Francia",
        "numberOfDoors": 4,
        "deliveryDate": None,
        "manufacturer": "AMR Coupé",
        "sellerName": "Rosa Huge",
        "image": {
            "imageName": "5.jpg",
            "imageType": "image/jpg",
            "imageContent": "./friendly_cars/src/main/resources/static/vehicles/5.jpg"
        }
    },
    {
        "vehicleName": "Aston Martin DBS",
        "model": "DBS",
        "vehicleWeight": 1440,
        "available": True,
        "placeOfManufacture": "Francia",
        "numberOfDoors": 4,
        "deliveryDate": None,
        "manufacturer": "AMR Coupé",
        "sellerName": "Hermes Soto",
        "image": {
            "imageName": "6.jpg",
            "imageType": "image/jpg",
            "imageContent": "./friendly_cars/src/main/resources/static/vehicles/6.jpg"
        }
    },
    {
        "vehicleName": "Aston Martin Vantage",
        "model": "Roadster",
        "vehicleWeight": 1560,
        "available": True,
        "placeOfManufacture": "Francia",
        "numberOfDoors": 4,
        "deliveryDate": None,
        "manufacturer": "AMR Coupé",
        "sellerName": "Harold Monte",
        "image": {
            "imageName": "7.jpg",
            "imageType": "image/jpg",
            "imageContent": "./friendly_cars/src/main/resources/static/vehicles/7.jpg"
        }
    },
    {
        "vehicleName": "Audi A1",
        "model": "A1",
        "vehicleWeight": 1320,
        "available": True,
        "placeOfManufacture": "Inglaterra",
        "numberOfDoors": 4,
        "deliveryDate": None,
        "manufacturer": "Audi",
        "sellerName": "Gaston Vaca",
        "image": {
            "imageName": "8.jpg",
            "imageType": "image/jpg",
            "imageContent": "./friendly_cars/src/main/resources/static/vehicles/8.jpg"
        }
    },
    {
        "vehicleName": "Audi A3 Sedán",
        "model": "A3 Sedán",
        "vehicleWeight": 1450,
        "available": True,
        "placeOfManufacture": "Inglaterra",
        "numberOfDoors": 4,
        "deliveryDate": None,
        "manufacturer": "Audi",
        "sellerName": "Sofia Rangel",
        "image": {
            "imageName": "9.jpg",
            "imageType": "image/jpg",
            "imageContent": "./friendly_cars/src/main/resources/static/vehicles/9.jpg"
        }
    },
    {
        "vehicleName": "Audi A4",
        "model": "A4",
        "vehicleWeight": 1460,
        "available": True,
        "placeOfManufacture": "Inglaterra",
        "numberOfDoors": 4,
        "deliveryDate": None,
        "manufacturer": "Audi",
        "sellerName": "Hugo Coz",
        "image": {
            "imageName": "10.jpg",
            "imageType": "image/jpg",
            "imageContent": "./friendly_cars/src/main/resources/static/vehicles/10.jpg"
        }
    },
    {
        "vehicleName": "Bentley Flying Spur",
        "model": "Flying Spur sx300",
        "vehicleWeight": 1570,
        "available": True,
        "placeOfManufacture": "Estados Unidos",
        "numberOfDoors": 4,
        "deliveryDate": None,
        "manufacturer": "Bentley",
        "sellerName": "Andrea Lujo",
        "image": {
            "imageName": "11.jpg",
            "imageType": "image/jpg",
            "imageContent": "./friendly_cars/src/main/resources/static/vehicles/11.jpg"
        }
    },
    {
        "vehicleName": "BMW i3",
        "model": "i3",
        "vehicleWeight": 1230,
        "available": True,
        "placeOfManufacture": "Estados Unidos",
        "numberOfDoors": 2,
        "deliveryDate": None,
        "manufacturer": "BMW",
        "sellerName": "Ester Roma",
        "image": {
            "imageName": "12.jpg",
            "imageType": "image/jpg",
            "imageContent": "./friendly_cars/src/main/resources/static/vehicles/12.jpg"
        }
    },
]

for user in users:
    json_data = json.dumps(user)
    response = http.request(
            "POST",
            url_client,
            body = json_data,
            headers={'Content-Type': 'application/json'}
        )
    print(response.data)

for vehicle in data:
    aux = vehicle['image']
    vehicle['image'] = None
    json_data = json.dumps(vehicle)
    response = http.request(
            "POST",
            url,
            body = json_data,
            headers={'Content-Type': 'application/json'}
        )
    vehcile_id = json.loads(response.data)["vehicleId"]
    response_img = http.request(
            "POST",
            url_img % (vehcile_id),
            fields = {
                'file': (
                    aux['imageName'],
                    toBinaryData(aux['imageContent']),
                    aux['imageType']
                )
            }
        )
    print(response.data)
    # print(response_img.data) # data too long
