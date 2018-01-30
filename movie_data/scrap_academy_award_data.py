"""
Get basic information from omdb api
Usage: python3 scrap_academy_award_data.py > NAME_YOUR_FILE

"""

import urllib.request
from bs4 import BeautifulSoup

API_KEY = "i=tt3896198&apikey=59891669"
API_URL = "http://www.omdbapi.com/"

def get_academy_awards():
	URL = "https://en.wikipedia.org/wiki/List_of_Academy_Award-winning_films"
	page = urllib.request.urlopen(URL)
	page_parser = BeautifulSoup(page, "html.parser")

	year = 2016
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
	get_academy_awards()
	











