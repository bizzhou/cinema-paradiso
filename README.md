# Cinema Paradiso

Cinema Paradiso is a Rotten Tomatoes-like website that allows users to obtain information on movies and TV shows. 

**Development environment**
  - Frontend: Angular 2, Bootstrap
  - Backend: Spring Boot, Spring MVC, Hibernate, MySQL
  
**Developers:** [Melanie Lin](https://github.com/captain-melanie), [Bin Zhou](https://github.com/bizzhou)

## Table of contents
* [How to run the project](#how-to-run-the-project)
* [Updates](#updates)
* [To do list](#to-do-list)

## How to run the project <a id="how-to-run-the-project"></a>
>>This project requires [Node.js](https://nodejs.org/) v6+ and [Angular CLI](https://cli.angular.io) v1.7.4+ to run the frontend, and [python](https://www.python.org/download/releases/3.0/) v3.0 to set up the database.

To set up the backend environment, open ``./cinema-paradiso-backend/pom.xml`` as project in IntelliJ. Run ``CinemaParadisoApplication.java``.

To set up the database, create a database called ``cinema_paradiso`` in MySQL, and enter your database username and password in backend's configuration file ``application.yml`` line 5 and 6. Then, run the following command to import the data (make sure your backend is running). This process will take approximately 5 minutes. 
```sh
$ python3 database/add_movie_json.py
```

To set up the frontend environment, install the following dependencies.

```sh
$ cd cinema-paradiso-frontend
$ npm install
$ npm install -g @angular/cli
```
then, run
```sh
$ ng serve
```

Finally, open up your favorate browser, and go to http://localhost:4200/

## Updates <a id="updates"></a>

## To do list <a id="to-do-list"></a>
See our [use case list](https://github.com/captain-melanie/rottentomatoes/blob/master/use-case-list.md) and feel free to contribute! 


