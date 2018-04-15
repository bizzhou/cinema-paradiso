import json
import random
from datetime import datetime
import requests

celeb = json.load(open('./academy/oscar_celeb.json'))
data = open('./academy/oscar_data.json')
link = open('./academy/oscar_movie_celeb_id_link.json')

celeb = json.load(open('./recent_movies/recent_celeb.json'))
data = open('./recent_movies/recent_data.txt')
link = open('./recent_movies/recent_movie_celeb_id_link.json')
images = json.load(open('./recent_movies/recent_images.json'))


    

celeb_dict = {}
data_dict = {}
link_dict = {}
image_dict = {}

# put celec into memory
for line in celeb:
    celeb_dict[line['id']] = line
# print(len(celeb_dict))
for image in images:
    image_dict[image['id']] = image['images']

for line in link:
    json_data = json.loads(line)
    link_dict[json_data['imdbID']] = json_data
# print(len(link_dict))
i = 0

for line in data:

    json_data = json.loads(line)
    id = json_data['imdbID']

    # get the director string
    director = celeb_dict[link_dict[id]['director']]

    # try:
    #     print(celeb_dict[director])
    # except Exception:
        # print('OJINOOO')

    new_director = {}
    # new_director['PHOTO_LOCATION'] = director['poster']
    new_director['celebrityId'] = director['id']
    new_director['name'] = director['name']
    new_director['profileImage'] = director['poster']
    new_director['biography'] = director['biography'].strip()
    new_director['birthDate'] = None
    new_director['birthCity'] = None
    new_director['birthState'] = None
    new_director['birthCountry'] = None
    new_director['filography'] = director['knownFor']
    new_director['director'] = True
    new_director['photo_LOCATION'] = None
    new_director['profileImageName'] = None

    # get the actor id array
    actors = link_dict[id]['actors']

    # put the json data into a cast array
    actors_list = []
    for actor in actors:
        try:
            new_actor = {}
            new_actor['PHOTO_LOCATION'] = celeb_dict[actor]['poster']
            new_actor['celebrityId'] = celeb_dict[actor]['id']
            new_actor['name'] = celeb_dict[actor]['name']
            new_actor['profileImage'] = celeb_dict[actor]['poster']
            new_actor['biography'] = celeb_dict[actor]['biography'].strip()
            new_actor['birthDate'] = None
            new_actor['birthCity'] = None
            new_actor['birthState'] = None
            new_actor['birthCountry'] = None
            new_actor['filography'] = celeb_dict[actor]['knownFor']
            new_actor['director'] = False
            new_actor['photo_LOCATION'] = None
            new_actor['profileImageName'] = None
            actors_list.append(new_actor)
            # actors_list.append(celeb_dict[actor])
        except KeyError:
            continue
    

    if json_data['Released'] == 'N/A':
        release_date = "null"
    else:
        release_date = datetime.strptime(json_data['Released'], '%d %b %Y').strftime('%Y-%m-%d')
        
    result = ''
    if json_data['Rated'] == 'PG-13':
        result = 'PG13'
    elif json_data['Rated'] == 'R':
        result = 'R'
    elif json_data['Rated'] == 'P':
        result = 'P'
    elif json_data['Rated'] == 'PG':
        result = 'PG'
    elif json_data['Rated'] == 'NC-17':
        result = 'NC17'
    else:
        result = 'NOT_RATED'

    list_of_genre = list(map(str.upper, json_data['Genre'].split(",")))
    for (i, item) in enumerate(list_of_genre):
        list_of_genre[i] = list_of_genre[i].strip()
        if item.strip() == 'FILM-NOIR':
            list_of_genre[i] = 'FILM_NOIR'
        if item.strip() == 'SCI-FI':
            list_of_genre[i] = 'SCI_FI'

    movie_json = {}
    movie_json['imdbId'] = json_data['imdbID']
    movie_json['title'] = json_data['Title']
    movie_json['year'] = json_data['Year']
    movie_json['rated'] = result
    movie_json['releaseDate'] = release_date
    movie_json['genres'] = list_of_genre
    award_list = []
    movie_json['awards'] = award_list.append(json_data['Awards'])
    movie_json['plot'] = json_data['Plot']
    movie_json['language'] = json_data['Language']
    movie_json['country'] = json_data['Country']
    movie_json['poster'] = json_data['Poster']

    if json_data['imdbRating'] == 'N/A':
        json_data['imdbRating'] = 0
    movie_json['rating'] = round(float(json_data['imdbRating'])/2 , 1)
    movie_json['production'] = json_data['Production']
    movie_json['website'] = json_data['Website']
    movie_json['boxOffice'] = json_data['BoxOffice']
    movie_json['boxOffice'] = ''.join(x for x in json_data['BoxOffice'] if x.isdigit())
    movie_json['runTime'] = ''.join(x for x in json_data['Runtime'] if x.isdigit())
    movie_json['numberOfRatings'] = random.randrange(30)
    movie_json['casts'] = actors_list
    movie_json['director'] = new_director
    movie_json['photos'] = image_dict[json_data['imdbID']]

    if movie_json['rating'] == 'N/A' or movie_json['releaseDate'] == 'N/A' or movie_json['runTime'] == '':
        continue

    # skip difficult situations for now

    # print(json.dumps(movie_json))
    # break
    request = requests.post('http://localhost:8080/movie/add', json=(movie_json))
    if(request.status_code == 400):
        print(request.text)

    
    
    


