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



@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
  
    @Autowired
    private BookService bookSvc;

    @Autowired
    private BookRepository bookRepo; 

    @GetMapping(path="/titles")
    @ResponseBody
    public ResponseEntity<String> getTitles (
                @RequestParam String character,
                @RequestParam Integer offset,
                @RequestParam Integer limit) {

        List<JsonObject> titles = bookRepo.getBookTitles(character, offset,limit)
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
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            bookSvc.getBookReview(title).forEach(d -> {
                JsonObject jsonReview = Json.createObjectBuilder()
                .add("book_title", d.book_title())
                .add("book_authors", d.book_author())
                .add("byline", d.byline())
                .add("publication_dt", d.publication_dt())
                .add("summary", d.summary())
                .add("url", d.url())
                .build();
            arrBuilder.add(jsonReview); 
            }); 
            
            return ResponseEntity.ok(arrBuilder.build().toString());
                                   
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