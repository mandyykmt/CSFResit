import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { View1Component } from './component/view1.component';
import { View2Component } from './component/view2.component';
import { View3Component } from './component/view3.component';
import { View4Component } from './component/view4.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { BookService } from './book.service';

const appRoutes : Routes = [
  {path: '', component: View1Component},
  {path: 'titles/:c', component: View2Component},
  {path: 'summary/:title', component: View3Component},
  {path: 'review/:title', component: View4Component},
  {path: '**', redirectTo: '/', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    View1Component,
    View2Component,
    View3Component,
    View4Component
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  providers: [BookService],
  bootstrap: [AppComponent]
})
export class AppModule { }
