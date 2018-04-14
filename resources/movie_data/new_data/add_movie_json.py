import json
import random
from datetime import datetime
import requests

celeb = json.load(open('./academy/oscar_celeb.json'))
data = open('./academy/oscar_data.json')
link = open('./academy/oscar_movie_celeb_id_link.json')

celeb_dict = {}
data_dict = {}
link_dict = {}

# put celec into memory
for line in celeb:
    celeb_dict[line['id']] = line
# print(len(celeb_dict))

for line in link:
    json_data = json.loads(line)
    link_dict[json_data['imdbID']] = json_data
# print(len(link_dict))

for line in data:
    json_data = json.loads(line)
    id = json_data['imdbID']

    # get the director string
    director = link_dict[id]['director']

    # get the actor id array
    actors = link_dict[id]['actors']

    # put the json data into a cast array
    actors_list = []
    for actor in actors:
        # print('finding actor')
        # print(celeb_dict[actor])
        actors_list.append(celeb_dict[actor])
        # print('---------------------------')

    if json_data['Released'] == 'N/A':
        release_date = "null"
    else:
        release_date = datetime.strptime(json_data['Released'], '%d %b %Y').strftime('%y%m%d')
        

    movie_json = {}
    movie_json['imdbId'] = json_data['imdbID']
    movie_json['title'] = json_data['Title']
    movie_json['year'] = json_data['Year']
    movie_json['rated'] = json_data['Rated']
    movie_json['releaseDate'] = release_date
    movie_json['genres'] = list(map(str.upper, json_data['Genre'].split(",")))
    award_list = []
    movie_json['awards'] = award_list.append(json_data['Awards'])
    movie_json['plot'] = json_data['Plot']
    movie_json['language'] = json_data['Language']
    movie_json['country'] = json_data['Country']
    movie_json['poster'] = json_data['Poster']
    movie_json['rating'] = json_data['imdbRating']
    movie_json['production'] = json_data['Production']
    movie_json['website'] = json_data['Website']
    movie_json['boxOffice'] = json_data['BoxOffice']
    movie_json['boxOffice'] = ''.join(x for x in json_data['BoxOffice'] if x.isdigit())
    movie_json['runTime'] = ''.join(x for x in json_data['Runtime'] if x.isdigit())
    movie_json['numberOfRatings'] = random.randrange(30)
    # movie_json['casts'] = actors_list
    # movie_json['director'] = celeb_dict[director]


    # skip difficult situations for now
    if movie_json['rating'] == 'N/A' or movie_json['releaseDate'] == 'N/A' or movie_json['runTime'] == '':
        continue

    print(json.dumps(movie_json))
    # request = requests.post('http://localhost:8080/movie/add', json=json.dumps(movie_json))
    # print(request.text)
    break
    
    
    


