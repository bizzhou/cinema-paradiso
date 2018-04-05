import json
from pprint import pprint

data = json.load(open('../movie_data/new_data/celebrity/oscar_celeb.json'))
# pprint (data[0]['name'] + ' ' + data[0]['id'] + ' ' + data[0]['poster'])

count = 0

for d in data:
	pprint ("INSERT INTO celebrity (name, imdbId, poster, yearOdBirth, locationOfBirth, knownFor, biography)"
		+ " VALUES ('" +
		d['name'] + "', '" +
		d['id'] + "', '" +
		d['poster'] + "', '" +
		d['yearOfBirth'] + "', '" +
		d['locationOfBirth'] + "', '" +
		# d['knownFor']+ "', '" +
		d['biography'] + "')")


# create INSERT statements
# INSERT INTO celebrity (name, imdbId, poster, yearOdBirth, locationOfBirth, knownFor(?), biography)
# VALUES (d['name'], d['id'], d['poster'], d['yearOdBirth'], d['locationOfBirth'], d['knownFor'], d['biography'])

