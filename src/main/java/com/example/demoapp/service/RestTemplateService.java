package com.example.demoapp.service;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    public static final String OMDB_API_URL = "http://www.omdbapi.com/";
    public static final String API_KEY = "PASTE_THE_KEY";
    private final RestTemplate restTemplate = new RestTemplate();

    public RestTemplate getRestTemplate() {
        return this.restTemplate;
    }

    public String callOmdbApiHttpRequest(String queryString) {
        URI uri = UriComponentsBuilder
                .fromUriString(OMDB_API_URL + "?apikey=" + API_KEY + "&s={queryString}")
                .buildAndExpand(queryString)
                .toUri();
        RequestEntity<?> request = RequestEntity.get(uri).build();
        ResponseEntity<String> response = this.restTemplate.exchange(request, String.class);
        return response.getBody();
        //<200,{"Search":[{"Title":"Harry Potter and the Deathly Hallows: Part 2","Year":"2011","imdbID":"tt1201607","Type":"movie",
        // "Poster":"https://m.media-amazon.com/images/M/MV5BMjIyZGU4YzUtNDkzYi00ZDRhLTljYzctYTMxMDQ4M2E0Y2YxXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg"},
        // {"Title":"Harry Potter and the Sorcerer's Stone","Year":"2001","imdbID":"tt0241527","Type":"movie",
        // "Poster":"https://m.media-amazon.com/images/M/MV5BNjQ3NWNlNmQtMTE5ZS00MDdmLTlkZjUtZTBlM2UxMGFiMTU3XkEyXkFqcGdeQXVyNjUwNzk3NDc@._V1_SX300.jpg"},
        // {"Title":"Harry Potter and the Chamber of Secrets","Year":"2002","imdbID":"tt0295297","Type":"movie",
        // "Poster":"https://m.media-amazon.com/images/M/MV5BMTcxODgwMDkxNV5BMl5BanBnXkFtZTYwMDk2MDg3._V1_SX300.jpg"},
        // {"Title":"Harry Potter and the Prisoner of Azkaban","Year":"2004","imdbID":"tt0304141","Type":"movie",
        // "Poster":"https://m.media-amazon.com/images/M/MV5BMTY4NTIwODg0N15BMl5BanBnXkFtZTcwOTc0MjEzMw@@._V1_SX300.jpg"},
        // {"Title":"Harry Potter and the Goblet of Fire","Year":"2005","imdbID":"tt0330373","Type":"movie",
        // "Poster":"https://m.media-amazon.com/images/M/MV5BMTI1NDMyMjExOF5BMl5BanBnXkFtZTcwOTc4MjQzMQ@@._V1_SX300.jpg"},
        // {"Title":"Harry Potter and the Order of the Phoenix","Year":"2007","imdbID":"tt0373889","Type":"movie",
        // "Poster":"https://m.media-amazon.com/images/M/MV5BMTM0NTczMTUzOV5BMl5BanBnXkFtZTYwMzIxNTg3._V1_SX300.jpg"},
        // {"Title":"Harry Potter and the Deathly Hallows: Part 1","Year":"2010","imdbID":"tt0926084","Type":"movie",
        // "Poster":"https://m.media-amazon.com/images/M/MV5BMTQ2OTE1Mjk0N15BMl5BanBnXkFtZTcwODE3MDAwNA@@._V1_SX300.jpg"},
        // {"Title":"Harry Potter and the Half-Blood Prince","Year":"2009","imdbID":"tt0417741","Type":"movie",
        // "Poster":"https://m.media-amazon.com/images/M/MV5BNzU3NDg4NTAyNV5BMl5BanBnXkFtZTcwOTg2ODg1Mg@@._V1_SX300.jpg"},
        // {"Title":"When Harry Met Sally...","Year":"1989","imdbID":"tt0098635","Type":"movie",
        // "Poster":"https://m.media-amazon.com/images/M/MV5BMjE0ODEwNjM2NF5BMl5BanBnXkFtZTcwMjU2Mzg3NA@@._V1_SX300.jpg"},
        // {"Title":"Dirty Harry","Year":"1971","imdbID":"tt0066999","Type":"movie",
        // "Poster":"https://m.media-amazon.com/images/M/MV5BMzdhMTM2YTItOWU2YS00MTM0LTgyNDYtMDM1OWM3NzkzNTM2XkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SX300.jpg"}],
        // "totalResults":"561","Response":"True"},[Date:"Wed, 15 May 2019 01:13:38 GMT", Content-Type:"application/json; charset=utf-8",
        // Content-Length:"2221", Connection:"keep-alive", Set-Cookie:"__cfduid=d92206b865f6b351e9ee86bbf6609fd1b1557882818; expires=Thu, 14-May-20 01:13:38 GMT; path=/; domain=.omdbapi.com; HttpOnly",
        // Cache-Control:"public, max-age=86400", Expires:"Thu, 16 May 2019 01:13:38 GMT", Last-Modified:"Wed, 15 May 2019 01:13:38 GMT",
        // Vary:"*", X-AspNet-Version:"4.0.30319", X-Powered-By:"ASP.NET", Access-Control-Allow-Origin:"*", CF-Cache-Status:"MISS",
        // Accept-Ranges:"bytes", Server:"cloudflare", CF-RAY:"4d71401e4ab18289-ATL"]>
    }
}