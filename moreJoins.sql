/*
More JOIN operations

movie
id  title   yr  director    budget  gross 
...

actor
id  name
...
 
casting
movieid actorid ord
...
*/

/*
1962 movies
1.
List the films where the yr is 1962 [Show id, title]
*/

SELECT id, title
 FROM movie
 WHERE yr=1962

/*
When was Citizen Kane released?
2.
Give year of 'Citizen Kane'.
*/

SELECT yr FROM movie
WHERE title LIKE 'Citizen Kane'

/*
Star Trek movies
3.
List all of the Star Trek movies, include the id, title and yr (all of these 
movies include the words Star Trek in the title). Order results by year.
*/

SELECT id, title, yr FROM movie
WHERE title LIKE '%Star Trek%'
ORDER BY yr ASC

/*
id for actor Glenn Close
4.
What id number does the actor 'Glenn Close' have?
*/

SELECT id FROM actor
WHERE name = 'Glenn Close'

/*
id for Casablanca
5.
What is the id of the film 'Casablanca'
*/

SELECT id FROM movie
WHERE title = 'Casablanca'

/*
Cast list for Casablanca
6.
Obtain the cast list for 'Casablanca'. what is a cast list?
Use movieid=11768, (or whatever value you got from the previous question)
*/

SELECT name FROM casting 
  JOIN actor ON casting.actorid=actor.id
WHERE movieid = '11768'

/*
Alien cast list
7.
Obtain the cast list for the film 'Alien'
*/

SELECT name FROM casting
  JOIN actor ON casting.actorid=actor.id
  JOIN movie ON movie.id=casting.movieid
WHERE title = 'Alien'

/*
Harrison Ford movies
8.
List the films in which 'Harrison Ford' has appeared
*/

SELECT DISTINCT title FROM 
  movie JOIN casting ON movie.id=casting.movieid
         JOIN actor ON casting.actorid=actor.id
WHERE name = 'Harrison Ford'

/*
Harrison Ford as a supporting actor
9.
List the films where 'Harrison Ford' has appeared - but not in the starring 
role. [Note: the ord field of casting gives the position of the actor. If ord=1 
then this actor is in the starring role]
*/

SELECT DISTINCT title FROM
  movie JOIN casting ON movie.id=casting.movieid
        JOIN actor ON casting.actorid=actor.id
WHERE name = 'Harrison Ford'
AND ord NOT LIKE '1'

/*
Lead actors in 1962 movies
10.
List the films together with the leading star for all 1962 films.
*/

SELECT DISTINCT title, name FROM
  movie JOIN casting on movie.id=casting.movieid
         JOIN actor ON casting.actorid=actor.id
WHERE yr = '1962' 
AND ord LIKE '1'


/*
Busy years for John Travolta
11.
Which were the busiest years for 'John Travolta', show the year and the number 
of movies he made each year for any year in which he made more than 2 movies.
*/

SELECT yr,COUNT(title) FROM
  movie JOIN casting ON movie.id=movieid
         JOIN actor   ON casting.actorid=actor.id
where name='John Travolta'
GROUP BY yr
HAVING COUNT(title)>2

/*
Lead actor in Julie Andrews movies
12.
List the film title and the leading actor for all of the films 'Julie Andrews' 
played in. Did you get "Little Miss Marker twice"?
*/

SELECT title, name FROM casting
  JOIN movie ON casting.movieid=movie.id 
  JOIN actor ON casting.actorid=actor.id
WHERE ord = '1'
AND movieid IN (SELECT movieid FROM casting
                  JOIN movie ON casting.movieid=movie.id 
                  JOIN actor ON casting.actorid=actor.id
                 WHERE name LIKE 'Julie Andrews')


/*
Actors with 30 leading roles
13.
Obtain a list, in alphabetical order, of actors who've had at least 30 
starring roles.
*/

SELECT name FROM casting
  JOIN movie ON casting.movieid=movie.id 
  JOIN actor ON casting.actorid=actor.id
WHERE ord = '1'
GROUP BY name
HAVING COUNT(name)>=30
ORDER BY name ASC

/*
14.
List the films released in the year 1978 ordered by the number of actors in the 
cast, then by title.
*/

SELECT title, COUNT(name) FROM casting
  JOIN movie ON casting.movieid=movie.id 
  JOIN actor ON casting.actorid=actor.id
WHERE yr = '1978'
GROUP BY title
ORDER BY COUNT(name) DESC, title ASC


/*
15.
List all the people who have worked with 'Art Garfunkel'.
*/

SELECT DISTINCT name FROM casting
  JOIN movie ON casting.movieid=movie.id 
  JOIN actor ON casting.actorid=actor.id
WHERE movieid IN (SELECT movieid FROM casting
                  JOIN movie ON casting.movieid=movie.id 
                  JOIN actor ON casting.actorid=actor.id
                 WHERE name LIKE 'Art Garfunkel')
AND name NOT LIKE 'Art Garfunkel'


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Sawyer
 * Created: Sep 26, 2018
 */

