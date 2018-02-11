from builtins import Exception
from bs4 import BeautifulSoup
import urllib.request
import json

url = 'http://www.imdb.com/title/'


def find_trailer(input_file, output_file):
    for line in file_reader:
        json_line = json.loads(line)
        movie_title = json_line['Title']
        movie_id = json_line['imdbID']
        print(movie_id)
        try:
            page = urllib.request.urlopen(url + movie_id)
        except Exception:
            print('error')

        page_parser = BeautifulSoup(page, 'html.parser')

        try:
            trailers = page_parser.find("a", {"class", "slate_button prevent-ad-overlay video-modal"}).get('data-video')
        except Exception:
            file_writer.writelines(movie_title + "::" + movie_id + '::' + 'No Trailer\n')
            continue
        video_detail = movie_title + "::" + movie_id + '::' + url + movie_id + '/videoplayer/' + trailers + '\n'
        print(video_detail)
        file_writer.writelines(video_detail)


file_reader = open('movies_of_interest_data.json', 'r')
file_writer = open('movies_of_interest_data_trailer.json', 'w')
find_trailer(file_reader, file_writer)
file_reader.close()
file_writer.close()
