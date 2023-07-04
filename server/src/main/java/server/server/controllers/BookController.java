package server.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import server.server.repositories.BookRepository;
import server.server.services.BookException;
import server.server.services.BookService;

// http://developer.nytimes.com/docs/books-product/1/routes/reviews.json.get

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
  
    @Autowired
    private BookService bookSvc;

    @Autowired
    private BookRepository bookRepo; 

    @GetMapping(path="/title")
    @ResponseBody
    public ResponseEntity<String> getTitles (
                @RequestParam String c,
                @RequestParam String offset,
                @RequestParam String limit) {

        List<JsonObject> titles = bookRepo.getBookTitles(c, offset,limit)
                                    .stream()
                                    .map(t ->
                                        Json.createObjectBuilder()
                                            .add("title", t.title())
                                            .build()
                                    ).toList();
                                    
        JsonArray resp = Json.createArrayBuilder(titles).build();
        return ResponseEntity.ok(resp.toString());
    }

    @GetMapping("/summary")
    @ResponseBody
    public ResponseEntity<String> getBookSummary (@RequestParam String title) {
      List<JsonObject> books = bookRepo.getBookSummary(title)
                                        .stream()
                                        .map(d -> 
                                            Json.createObjectBuilder()
                                            .add("authors", d.authors())
                                            .add("pages", d.pages())
                                            .add("rating", d.rating())
                                            .add("rating_count", d.rating_count())
                                            .add("genres", d.genres())
                                            .add("url", d.url())
                                            .build())
                                        .toList();
      JsonArray resp = Json.createArrayBuilder(books).build();
      return ResponseEntity.ok(resp.toString());
   }

    @GetMapping(path="/review")
    @ResponseBody
    public ResponseEntity<String> getBookReview (
                @RequestParam String title) {
        
        try {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            bookSvc.getBookReview(title).stream()
                        .map(d -> Json.createObjectBuilder()
                                    .add("authors", d.authors())
                                    .add("pages", d.pages())
                                    .add("rating", d.rating())
                                    .add("rating_count", d.rating_count())
                                    .add("genres", d.genres())
                                    .add("url", d.url())
                                    .build())
                        .forEach(arrayBuilder::add);
            return ResponseEntity.ok(arrayBuilder.build().toString());
                                   
        } catch (BookException ex) {
            return ResponseEntity.status(400)
                                .body(
                                    Json.createObjectBuilder()
                                        .add("error", ex.getMessage())
                                        .build().toString()
                                );
        }   
    }
}
}