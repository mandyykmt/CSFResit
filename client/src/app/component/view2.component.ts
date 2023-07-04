import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import { ActivatedRoute } from '@angular/router';
import { BookTitle } from '../models';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-view2',
  templateUrl: './view2.component.html',
  styleUrls: ['./view2.component.css']
})
export class View2Component implements OnInit {

  constructor(
    private bookSvc : BookService,
    private activatedRoute : ActivatedRoute
  ) {}

  character : string = ''
  titles$! : Observable<BookTitle[]>

  ngOnInit(): void {
      this.character = this.activatedRoute.snapshot.params['c']
  }

  // ngOnInit(): void {
  //     this.city = this.activatedRoute.snapshot.params['city']
  //     const units = this.activatedRoute.snapshot.queryParams['units'] || 'metric'

  //     this.weather$ = this.weatherSvc.getWeather(this.city, units)
  // }

  previous() {

  }

  next() {

  }

}
