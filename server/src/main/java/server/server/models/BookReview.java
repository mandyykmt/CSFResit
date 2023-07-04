package server.server.models;

public record BookReview (
    String book_title,
    String book_author,
    String byline,
    String publication_dt,
    String summary,
    String url
) {}