import urllib.request
from bs4 import BeautifulSoup

API_KEY = "i=tt3896198&apikey=59891669"
API_URL = "http://www.omdbapi.com/"

# def get_recent_movie_names():
# 	URL = "http://www.imdb.com/search/title?year=2017&title_type=feature&"
# 	page = urllib.request.urlopen(URL)
# 	page_parser = BeautifulSoup(page, "html.parser")

# 	# find all h3 tags, which include each movie title, year, banner, etc...
# 	all_contents = page_parser.findAll("h3", {"class": "lister-item-header"})	
# 	for content in all_contents:
# 		print (content)

def get_academy_awards():
	URL = "https://en.wikipedia.org/wiki/List_of_Academy_Award-winning_films"
	page = urllib.request.urlopen(URL)
	page_parser = BeautifulSoup(page, "html.parser")

	year = 2017
	# highlighted movies
	contents = page_parser.findAll("tr", {"style": "background:#EEDD82"})
	for c in contents:
		# c.a.string is the movie name
		movie_name = c.a.string.replace(' ', '+')
		# access the API
		movie_url = API_URL + "?t=" + movie_name + "+&y=" + str(year) + "?" + API_KEY
		year -= 1

		movie_api = urllib.request.urlopen(movie_url)
		parser = BeautifulSoup(movie_api, "html.parser")

		print (parser)


if __name__ == '__main__':
	# get_recent_movie_names()
	get_academy_awards()











