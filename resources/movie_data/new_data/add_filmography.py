import requests
import json

def make_request(tv_celeb):
	for celeb_json in tv_celeb:

		try:
			knownFor_array = celeb_json['knownFor']
			celeb_id = celeb_json['id']
		except:
			continue

		request_param = {}
		
		request_param['id'] = celeb_id
		request_param['filmograhy'] = knownFor_array

		resp = requests.post('http://localhost:8080/celebrity/add_filmogrpahy', json=(movie_json))
	    if (request.status_code == 400):
	        print(request.text)


tv_celeb = json.load(open('./tv/tv_celeb.json'))
recent_celeb = open('./recent_movies/recent_celeb.json')
oscar_celeb = open('./academy/oscar_celeb.json')


make_request(tv_celeb)
make_request(recent_celeb)
make_request(oscar_celeb)


	