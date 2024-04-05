import requests
import json
import firebase_admin
from firebase_admin import credentials, firestore

# Initialize Firebase Admin SDK
cred = credentials.Certificate("ServiceAccountKey.json")
firebase_admin.initialize_app(cred)

db = firestore.client()
collection_name = "Recipes"

api_key = '46ea03ff0f3148abb2c9ca808f6de8e9'

document_id_count = 0

cuisineList = ["indian", "chinese", "italian", "mexican", "thai", "japanese", "greek", "african", "american"] # "english", "vietnamese", "mediterranean", "Middle Eastern", "French", "German"]
for cuisine in cuisineList:
    api_url = f"https://api.spoonacular.com/recipes/complexSearch?apiKey={api_key}&cuisine={cuisine}&addRecipeInformation=true&fillIngredients=true&sort=popularity&instructionsRequired=true&sortDirection=desc&number=75"
    headers = {'ApiKeyHeader': api_key}
    response = requests.get(api_url)
    if response.status_code == 200:
        try:
            data_received1 = response.json()
            for a in range(0, 50):
                # Attempt to parse the response as JSON
                if(len(data_received1["results"]) == a): break

                data_received = data_received1["results"][a]

                root_extract = ["vegetarian", "vegan", "glutenFree", "dairyFree", "veryHealthy", "cheap",
                "id", "title", "readyInMinutes", "servings", "image", "cuisines", "diets", "extendedIngredients", "analyzedInstructions"]

                filtered_data = {field: data_received[field] for field in root_extract if field in data_received}

                for i in range(0, len(filtered_data.get("analyzedInstructions"))):
                    temp = filtered_data["analyzedInstructions"][i]
                    filtered_data["analyzedInstructions"][i] = {field: temp[field] for field in temp if field != "name"}
                

                if(len(filtered_data["analyzedInstructions"]) != 0):
                    difficulty = 0.1 * filtered_data["readyInMinutes"] + 0.9 * len(filtered_data["analyzedInstructions"][0]["steps"])
                    # difficulty_json = {"difficulty": difficulty }
                    filtered_data["difficulty"] = difficulty

                    filtered_data["analyzedInstructions"] = filtered_data["analyzedInstructions"][0]["steps"]

                    extract = ["equipment", "ingredients", "number", "step"]
                    for i in range(0, len(filtered_data.get("analyzedInstructions"))):
                        temp = filtered_data["analyzedInstructions"][i]
                        filtered_data["analyzedInstructions"][i] = {field: temp[field] for field in extract if field in temp}
                    
                    equipment_extract = ["id", "image", "name"]

                    if(filtered_data.get("analyzedInstructions") and len(filtered_data["analyzedInstructions"]) > 0):
                        for i in range(0, len(filtered_data["analyzedInstructions"][0])):
                            if(filtered_data["analyzedInstructions"][0] == "equipment"):
                                temp = filtered_data["analyzedInstructions"][0][i]
                                filtered_data["analyzedInstructions"][0][i] = {field: temp[field] for field in equipment_extract if field in temp}
                    
                    extendedIngredients_extract = ["id", "image", "name", "original", "amount", "unit"]
                    for i in range(0, len(filtered_data.get("extendedIngredients"))):
                        temp = filtered_data["extendedIngredients"][i]
                        filtered_data["extendedIngredients"][i] = {field: temp[field] for field in extendedIngredients_extract if field in temp}
                        
                    
                    # Save JSON data to a file
                    file_path = "api_response.json"
                    with open(file_path, "w") as json_file:
                        json.dump(filtered_data, json_file, indent=2)


                    # Insert data into Firebase
                    reference = db.collection(collection_name).document(str(document_id_count))
                    reference.set(filtered_data)
                    document_id_count += 1
                    # print("Document added with ID:", reference.id)
        except json.decoder.JSONDecodeError as e:
            print(f"Error decoding JSON: {e}")
    else:
        print(f"Error: {response.status_code} - {response.reason}")

docs = db.collection(collection_name).get()
print(len(docs))
