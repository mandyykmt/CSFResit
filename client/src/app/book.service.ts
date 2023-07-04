import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable, Input } from "@angular/core";
import { Observable, firstValueFrom } from "rxjs";
import { BookAction, BookResponse, BookReview, BookSummary, BookTitle } from "./models";

const url2 = "/api/titles"
const url3 = "/api/summary"
const url4 = "/api/review"

@Injectable()
export class BookService {

    constructor(
        private http: HttpClient
    ) {}

    public getTitles(character : string, limit : number, offset : number) : Observable<BookTitle[]> {
        const params = new HttpParams().set("character", character).set("limit", limit).set("offset", offset)
        return this.http.get<BookTitle[]>(`${'url2'}`, { params })
    }

    public getSummary(title : string) : Observable<BookSummary[]> {
        const params = new HttpParams().set("title", title)
        return this.http.get<BookSummary[]>(`${'url3'}`, { params })
    }

    public getReview(title : string) : Observable<BookReview[]> {
        const params = new HttpParams().set("book_title", title)
        return this.http.get<BookReview[]>(`${'url4'}`, { params })
    }
    
}