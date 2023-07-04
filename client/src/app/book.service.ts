import { HttpClient } from "@angular/common/http";
import { Injectable, Input } from "@angular/core";
import { Observable, firstValueFrom } from "rxjs";
import { BookAction, BookResponse } from "./models";

@Injectable()
export class BookService {

    // @Input()
    // character : string = 

    constructor(
        private http: HttpClient
    ) {}

    // const url = "/api/weather"

    // getWeather(city : string, units = 'metric'): Observable<WeatherResponse[]> {
    //     const params = new HttpParams()
    //                     .set("city", city)
    //                     .set("units", units)

    //     return this.http.get<WeatherResponse[]>(`${url}`, {params })
    // }
}