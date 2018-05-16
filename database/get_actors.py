import json

reader = open('json_data.txt', 'r')


for line in reader:
    json_data = json.loads(line)
    print(json_data['Actors'])
