package server.server.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import server.server.models.BookReview;
import server.server.repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;
    
    public static final String URL = "https://api.nytimes.com/svc/books/v3/reviews.json";
    
    @Value("${NYT_KEY}")
    private String appId;

    public List<BookReview> getBookReview(String title) throws BookException {
        
        // https://api.nytimes.com/svc/books/v3/reviews.json?title=<title>&api-key=<appId>
        String url = UriComponentsBuilder.fromUriString(URL)
                        .queryParam("title", title.replaceAll(" ", "+"))
                        .queryParam("appid", appId)
                        .toUriString();

        RequestEntity<Void> req = RequestEntity.get(url)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .build(); 

        RestTemplate template = new RestTemplate(); 
        ResponseEntity<String> resp = null; 
        
        try {
            resp = template.exchange(req, String.class);
        } catch (RestClientException ex) {
            throw new BookException(ex.getMessage()); 
        }
        
        String payload = resp.getBody();
        JsonReader reader = Json.createReader(new StringReader(payload)); 
        JsonObject data = reader.readObject();

        BookReview br = new BookReview();

        br.set(data.getString("book_title"));
        br.set(data.getString("book_author"));
        br.set(data.getString("byline"));
        br.set(data.getString("publication_dt"));
        br.set(data.getString("summary"));
        br.set(data.getString("url"));

        JsonArray arrAuthors = data.getJsonArray("authors");
        List<String> list = new LinkedList<String>();
        for(int i = 0; i < arrAuthors.length(); i++){
            list.add(arr.getJSONObject(i));
        }

        br.setAuthors(list);

        return data.getJsonArray("bookSummary").stream()
                .map(v -> v.asJsonObject())
                .map(o -> new BookReview(
                    o.getString("book_title"), 
                    o.getString("book_author"), 
                    o.getString("byline")),
                    o.getString("publication_dt"),
                    o.getString("summary"),
                    o.getString("url"))
                .toList();
    }
}

// {
//   "status": "OK",
//   "copyright": "Copyright (c) 2019 The New York Times Company.  All Rights Reserved.",
//   "num_results": 2,
//   "results": [
//     {
//       "url": "http://www.nytimes.com/2011/11/10/books/1q84-by-haruki-murakami-review.html",
//       "publication_dt": "2011-11-10",
//       "byline": "JANET MASLIN",
//       "book_title": "1Q84",
//       "book_author": "Haruki Murakami",
//       "summary": "In “1Q84,” the Japanese novelist Haruki Murakami writes about characters in a Tokyo with two moons.",
//       "isbn13": [
//         "9780307476463"
//       ]
//     }
//   ]
// }