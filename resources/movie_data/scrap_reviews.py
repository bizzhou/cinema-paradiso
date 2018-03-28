"""
get reviews from IMDb
usage: python3 scrap_reviews.py

This file appends data to the output file
so make sure to delete the output file before re-run this file

"""
import urllib.request
from bs4 import BeautifulSoup
import json
from pprint import pprint

def load_reviews(movie_file, outfile_name):
	IMDb_URL_PREFIX = "http://www.imdb.com/title/"
	IMDb_URL_SUFFIX = "/reviews"

	# open file to get reference id
	movie_file = open(movie_file)
	for line in movie_file.readlines():
		movie_data = json.loads(line)
		
		# pass if key does not exist
		if "imdbID" not in movie_data:
			continue
		
		# get imdb id, name
		imdb_id = movie_data["imdbID"]
		title = movie_data["Title"]

		# extract movie reviews and save to outfile
		get_reviews(IMDb_URL_PREFIX + imdb_id + IMDb_URL_SUFFIX, imdb_id, title, outfile_name)



def get_reviews(url, imdb_id,title, outfile_name):
	try: 
		page = urllib.request.urlopen(url)
	except Exception:
		return
		
	page_parser = BeautifulSoup(page, "html.parser")

	# find all divs where class = lister-item-content, then retrieve elements
	reviews = page_parser.findAll("div", {"class": "lister-item-content"})
	for review in reviews:
		review_title = review.find("div", {"class": "title"})
		review_author = review.find("span", {"class": "display-name-link"})
		review_date = review.find("span", {"class": "review-date"})
		review_content = review.find("div", {"class": "text"}) 

		# use json format
		# use .text to get contents inside a tag
		data = {}
		data["id"] = imdb_id
		data["movie"] = title
		data["review_title"] = review_title.text
		data["review_author"] = review_author.a.text
		data["review_date"] = review_date.text
		data["review_content"] = review_content.text

		# save to file
		with open(outfile_name, 'a') as outfile:
			json.dump(data, outfile)
		with open(outfile_name, 'a') as outfile:
			outfile.write("\n")
	

FILE_1 = "academy_award_data.json"
FILE_1_OUTPUT = "academy_award_reviews.json"

# FILE_2 = "recent_film_data.json"
# FILE_2_OUTPUT = "recent_film_reviews.json"

# FILE_3 = "movies_of_interest_data.json"
# FILE_3_OUTPUT = "movies_of_interest_reviews.json"

if __name__ == '__main__':
	# load_reviews(FILE_1, FILE_1_OUTPUT)
	# load_reviews(FILE_2, FILE_2_OUTPUT)
	# load_reviews(FILE_3, FILE_3_OUTPUT)



















	