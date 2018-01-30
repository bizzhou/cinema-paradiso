"""
Get basic information from omdb api
Usage: python3 scrap_recent_films.py > NAME_YOUR_FILE

"""
import urllib.request
from bs4 import BeautifulSoup
import re

API_KEY = "i=tt3896198&apikey=59891669"
API_URL = "http://www.omdbapi.com/"

# get movies information
def get_movies(year, start_index, end_index):

	for page_number in range(start_index, end_index):
		url = "http://www.imdb.com/search/title?year=" + str(year) + "&title_type=feature&page=" + str(page_number)
		page = urllib.request.urlopen(url)
		page_parser = BeautifulSoup(page, "html.parser")
		contents = page_parser.findAll("h3", {"class": "lister-item-header"})

		for content in contents:
			title = content.a.text
			# year is in (YEAR) format, so replace parenthesis with empty char
			year = content.find("span", {"class": "lister-item-year"}).text.replace('(', '').replace(')', '')

			try:
				title.encode('ascii')
				get_movie_info(title, year)

			# skip movie name containing special characters
			except UnicodeEncodeError:
				pass



def get_movie_info(title, year):
	title = title.replace(' ', '+')
	# access the API
	movie_url = API_URL + "?t=" + title + "+&y=" + str(year) + "?" + API_KEY

	try: 
		movie_api = urllib.request.urlopen(movie_url)
		parser = BeautifulSoup(movie_api, "html.parser")
		print (parser)
	except urllib.error.HTTPError:
		pass

if __name__ == '__main__':
	# get movie list from page 1 to page 6 (I believe is considered as recent?)
	# get_movies(2017, 1, 6)

	# get_movies(2017, 7, 51)
	


