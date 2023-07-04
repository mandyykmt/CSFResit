import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { BookReview } from '../models';

@Component({
  selector: 'app-view4',
  templateUrl: './view4.component.html',
  styleUrls: ['./view4.component.css']
})
export class View4Component {

  review$!: Observable<BookReview[]>

  
}
