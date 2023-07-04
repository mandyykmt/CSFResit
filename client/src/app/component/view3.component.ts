import { Component } from '@angular/core';
import { BookSummary } from '../models';
import { Observable } from 'rxjs';
import { BookService } from '../book.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-view3',
  templateUrl: './view3.component.html',
  styleUrls: ['./view3.component.css']
})
export class View3Component {

  constructor(
    private bookSvc : BookService,
    private activatedRoute : ActivatedRoute,
    private http : HttpClient
  ) {}

  title : string = ''
  summary$! : Observable<BookSummary[]>

  ngOnInit(): void {
      this.title = this.activatedRoute.snapshot.params['title']
      this.summary$ = this.bookSvc.getSummary(this.title)
  }

  returnToView1() {}

}
