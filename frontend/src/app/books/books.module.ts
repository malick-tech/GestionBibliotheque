import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '../material.module';
import { BooksComponent } from './books.component';
import { BOOKS_ROUTES } from './books.routes';

@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    RouterModule.forChild(BOOKS_ROUTES)
  ],
  declarations: []
})
export class BooksModule { }
