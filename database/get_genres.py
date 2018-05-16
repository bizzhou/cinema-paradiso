import json

data = open('./academy/oscar_data.json')
genre_list = set()

for line in data:
    json_data = json.loads(line)
    for element in list(map(str.upper, json_data['Genre'].split(","))):
        genre_list.add(element)

print(genre_list)