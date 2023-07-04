import { Component, OnInit, Output } from '@angular/core';
import { CHARACTERS } from '../constants';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { BookAction } from '../models';

@Component({
  selector: 'app-view1',
  templateUrl: './view1.component.html',
  styleUrls: ['./view1.component.css']
})
export class View1Component {
  
  @Output()
  onClick = new Subject<BookAction>()
  
  characters = CHARACTERS

  constructor(
    private router : Router
  ) {}

  process(c : string) {

    const action : BookAction = {
      character: c, 
    }
    this.onClick.next(action)
    console.info(action)
    this.router.navigate(['view2', c])
  }
}