package server.server.models;

public record BookSummary (
    String title,
    String authors,
    Integer pages,
    Integer rating,
    Integer rating_count,
    String genres,
    String url
) {}

// create table book2018 (
// 	book_id varchar(8) not null,
// 	title varchar(512) not null,
// 	authors varchar(512) not null,
// 	description text,
// 	edition varchar(64) default '',
// 	format varchar(64),
// 	pages int default 0,
// 	rating decimal(4, 2) default 1.0,
// 	rating_count int default 0,
// 	review_count int,
// 	genres varchar(512),
// 	image_url text,

// 	primary key(book_id)
// );

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