import { Component, OnInit, Output } from '@angular/core';
import { CHARACTERS } from '../constants';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view1',
  templateUrl: './view1.component.html',
  styleUrls: ['./view1.component.css']
})
export class View1Component {
  
 
  characters = CHARACTERS

  constructor(
    private router : Router
  ) {}

  clickLetter(c : string) {
    this.router.navigate(['titles', c])
  }
}