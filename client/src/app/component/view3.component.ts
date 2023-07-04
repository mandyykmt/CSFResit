import { Component } from '@angular/core';
import { BookSummary } from '../models';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-view3',
  templateUrl: './view3.component.html',
  styleUrls: ['./view3.component.css']
})
export class View3Component {

  book$!: Observable<BookSummary[]> 
}
