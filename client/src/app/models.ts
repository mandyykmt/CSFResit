
export interface BookAction {
    currentPage : number 
}

export interface BookResponse {
    character: string
}

export interface BookTitle {
    title: string
}

export interface BookSummary {
    title: string
    authors: string[]
    pages: number
    rating: number
    rating_count: number
    genres: string
    url: string
}

export interface BookReview {
    book_title: string
    book_author: string
    byline: string
    publication_dt: string
    summary: string
    url: string
}