from bs4 import BeautifulSoup
import json
import urllib.request

url = 'http://www.imdb.com/movies-coming-soon/'
writer = open('movie_info.txt', 'w')

count = 0
# Movies in 2012-2017
for i in range(2012, 2018):
    for j in range(1, 13):
        print(i, j, count)
        # format is 2012-01
        if j < 10:
            scrape_url = url + str(i) + '-' + '0' + str(j)
        else:
            scrape_url = url + str(i) + '-' + str(j)

        page = urllib.request.urlopen(scrape_url)
        page_parser = BeautifulSoup(page, 'html.parser')

        movies = page_parser.find_all("table", {"class": "nm-title-overview-widget-layout"})

        for movie in movies:
            count += 1
            name = movie.find('h4').find('a').get('title')[:-7]
            id = movie.find('h4').find('a').get('href').split('/')[2]
            try:
                trailer = movie.find("a", {"class", "btn2 btn2_simple small title-trailer video-modal"}).get(
                    'data-video')
            except Exception:
                trailer = 'no trailer'
            line = name + '::' + id + '::' + trailer + '\n'
            writer.write(line)

writer.close()
