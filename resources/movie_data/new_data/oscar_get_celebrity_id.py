from bs4 import BeautifulSoup
import json
import urllib.request
import re

# oscar = open('./academy/oscar_data.json')
recent_movie = open('./recent_movies/recent_data.txt')

# with open('oscar_movie_celeb_id_link.json', 'w') as f:
with open('recent_movie_celeb_id_link.json', 'w') as f:

    final_arr = []

    # for movie in oscar:
    for movie in recent_movie:
        imdb_id = json.loads(movie)['imdbID']
        url = ('http://www.imdb.com/title/' + imdb_id)
        print(url)
        print('-------------')

        movie_page = urllib.request.urlopen(url)
        parser = BeautifulSoup(movie_page, "html.parser")

        director = parser.select('span[itemprop=director] > a')
        director_id = re.search(r'nm(\d+)', str(director)).group()

        actors = parser.select('td[itemprop=actor] > a')
        actor_list = []

        for item in actors:
            result = re.search(r'nm(\d+)', str(item))
            actor_list.append(result.group())

        f.write(json.dumps({"imdbID": imdb_id, "actors": actor_list, "director": director_id}))
        f.write('\n')