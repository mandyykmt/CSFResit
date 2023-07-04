package server.server.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import server.server.models.BookSummary;
import server.server.models.BookTitle;

@Repository
public class BookRepository {
 
    public static final String SQL_GET_IMAGE_BY_CHARACTER = """
        select title from book2018 where title like ? order by title limit ? offset ? 
        """;

    public static final String SQL_GET_BOOKSUMMARY_BY_TITLE = """
        select title, authors, pages, rating, rating_count, genres, 
        image_url from book2018 where title like ? 
        """;

    @Autowired
    private JdbcTemplate template;

    public Optional<BookTitle> getBookTitles(String character, String limit, String offset) {
      return template.query(SQL_GET_IMAGE_BY_CHARACTER,
          rs -> {
              if (!rs.next())
                return Optional.empty();
              return Optional.of(new BookTitle(rs.getString("book_title")));
        },
        character, limit, offset
        );
    }

    public Optional<BookSummary> getBookSummary(String title) {
      return template.query(SQL_GET_BOOKSUMMARY_BY_TITLE,
          rs -> {
              if (!rs.next())
                return Optional.empty();
              return Optional.of(new BookSummary(
                rs.getString("title"),
                rs.getString("authors"),
                rs.getInt("pages"),
                rs.getInt("rating"),
                rs.getInt("rating_count"),
                rs.getString("genres"),
                rs.getString("url")
          ));
        },
        title
        );
    }
}