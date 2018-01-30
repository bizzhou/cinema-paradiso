"""
get academy award reviews from IMDb

This file appends data to the output file
so make sure to delete the output file before re-run this file

"""
import urllib.request
from bs4 import BeautifulSoup
import json
from pprint import pprint

def get_reviews(url, title):
	page = urllib.request.urlopen(url)
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
		data["movie"] = title
		data["review_title"] = review_title.text
		data["review_author"] = review_author.a.text
		data["review_date"] = review_date.text
		data["review_content"] = review_content.text

		with open("academy_award_reviews.json", 'a') as outfile:
			json.dump(data, outfile)
		with open("academy_award_reviews.json", 'a') as outfile:
			outfile.write("\n")

if __name__ == '__main__':
	IMDb_URL_PREFIX = "http://www.imdb.com/title/"
	IMDb_URL_SUFFIX = "/reviews"

	# open file to get reference id
	movie_file = open("academy_award_list.json")
	for line in movie_file.readlines():
		movie_data = json.loads(line)
		# get imdb id, name
		imdb_id = movie_data["imdbID"]
		title = movie_data["Title"]

		# extract movie reviews
		get_reviews(IMDb_URL_PREFIX + imdb_id + IMDb_URL_SUFFIX, title)







	