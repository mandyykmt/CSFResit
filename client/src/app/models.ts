
export interface BookAction {
    character: string 
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
    pages: string
    rating: string
    genre: string
    cover: string
}