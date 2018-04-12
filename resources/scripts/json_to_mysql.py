import json
import random
from datetime import datetime

# from pprint import pprint

# data = json.load(open('../movie_data/new_data/celebrity/oscar_celeb.json'))
# pprint (data[0]['name'] + ' ' + data[0]['id'] + ' ' + data[0]['poster'])

# count = 0
#
# for d in data:
# 	pprint ("INSERT INTO celebrity (name, imdbId, poster, yearOdBirth, locationOfBirth, knownFor, biography)"
# 		+ " VALUES ('" +
# 		d['name'] + "', '" +
# 		d['id'] + "', '" +
# 		d['poster'] + "', '" +
# 		d['yearOfBirth'] + "', '" +
# 		d['locationOfBirth'] + "', '" +
# 		# d['knownFor']+ "', '" +
# 		d['biography'] + "')")


# create INSERT statements
# INSERT INTO celebrity (name, imdbId, poster, yearOdBirth, locationOfBirth, knownFor(?), biography)
# VALUES (d['name'], d['id'], d['poster'], d['yearOdBirth'], d['locationOfBirth'], d['knownFor'], d['biography'])

print('use test;')
print('truncate table movies;')

data = open('../movie_data/new_data/recent_movies/recent_data.txt')
for line in data:
    movie = json.loads(line)

    # Only take one country and one language
    country = movie['Country'].split(",")[0]
    language = movie['Language'].split(",")[0]
    runtime = ''.join(x for x in movie['Runtime'] if x.isdigit())
    boxoffice = ''.join(x for x in movie['BoxOffice'] if x.isdigit())

    if movie['Released'] == 'N/A':
        release_date = "null"
    else:
        release_date = str(datetime.strptime(movie['Released'], '%d %b %Y'))

    if boxoffice is '':
        boxoffice = 0

    # Take out the quote in plot for MYSQL syntax error
    plot = movie['Plot'].replace('\"', '')
    plot = plot.replace('\'', '')

    # Skip difficult cases for now
    if movie['imdbRating'] == 'N/A' or movie['Released'] == 'N/A' or runtime == '':
        continue

    result = [movie['imdbID'], country, language, random.randrange(30), plot,
              movie['Poster'], movie['Production'], movie['Rated'], movie['imdbRating'], release_date,
              movie['Title'],
              movie['Website'], movie['Year'], boxoffice, runtime, 0]

    str_result = []
    for item in result:
        str_result.append('\"' + str(item) + '\"')

    query_string = ','.join(str_result)

    print("INSERT INTO movies (imdb_id, country, language, number_of_ratings, plot, poster, production, "
          "rated, rating, released_date, title, website, year, box_office, runtime, director_celebrity_id) "
          "VALUES (%s)" % query_string)
