import urllib.request

from bs4 import BeautifulSoup

# API_KEY = "&apikey=59891669"
API_KEY = "&apikey=9556063c"
API_URL = "http://www.omdbapi.com/"

reader = open('ab', 'r')
writer = open('sanitized.txt', 'a')

count = 0

for line in reader:
    # imdb_id = line.split('::')[1]
    imdb_id = line.rstrip()
    try:
        count += 1
        movie_api = urllib.request.urlopen(API_URL + '?i=' + imdb_id + API_KEY)
        parser = BeautifulSoup(movie_api, "html.parser")
        print(count)
        writer.write(str(parser) + '\n')
    except urllib.error.HTTPError:
        pass

reader.close()
writer.close()
