import { Component, OnInit, Output } from '@angular/core';
import { BookService } from '../book.service';
import { ActivatedRoute, Router } from '@angular/router';
import { BookAction, BookTitle } from '../models';
import { Observable, Subject } from 'rxjs';
import { observableToBeFn } from 'rxjs/internal/testing/TestScheduler';

@Component({
  selector: 'app-view2',
  templateUrl: './view2.component.html',
  styleUrls: ['./view2.component.css']
})
export class View2Component implements OnInit {

  constructor(
    private bookSvc : BookService,
    private activatedRoute : ActivatedRoute, 
    private router : Router
  ) {}

  character : string = ''
  titles$! : Observable<BookTitle[]>
  offset: number = 0
  limit: number = 10
  bookAction : BookAction = {
    currentPage: 0
  }

  ngOnInit(): void {
      this.character = this.activatedRoute.snapshot.params['c']
      this.titles$ = this.bookSvc.getTitles(this.character, this.limit, this.offset)
  }

  clickTitle(title: string) {
    this.router.navigate(['summary', title])
  }


  // to work on numbers and logic

  previous() {
    let currentPage = this.bookAction.currentPage
    const action : BookAction = {
      currentPage : -10
    }
    
    if (currentPage <= 10 ) {
      return
    } else if (action.currentPage > 10) {
      currentPage += action.currentPage
    }
    this.titles$ = this.bookSvc.getTitles(this.character, this.limit, currentPage)
  }

  next() {
    let maxLength: number
    this.titles$ = this.bookSvc.getTitles(this.character, this.limit, this.offset)
    let currentPage = this.bookAction.currentPage
    const action : BookAction = {
      currentPage : 10
    }
    // to calculate number of titles: fix this
    if (currentPage > (10000 - 10)) {
      return
    } else {
      currentPage += action.currentPage
    }
    this.titles$ = this.bookSvc.getTitles(this.character, this.limit, currentPage)
  }

}
