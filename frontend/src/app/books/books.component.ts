import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../material.module';
import { MatTableModule } from '@angular/material/table';
import { MatChipsModule } from '@angular/material/chips';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    MaterialModule,
    MatTableModule,
    MatChipsModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule
  ]
})
export class BooksComponent {
  books = [
    { id: 1, title: 'Le Petit Prince', author: 'Antoine de Saint-Exupéry', available: true },
    { id: 2, title: 'L\'Étranger', author: 'Albert Camus', available: true },
    { id: 3, title: 'Les Misérables', author: 'Victor Hugo', available: false }
  ];
}
