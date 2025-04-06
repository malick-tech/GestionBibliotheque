import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ExampleDataService } from '../../data/example-data.service';

export interface Book {
  id: number;
  title: string;
  author: string;
  isbn: string;
  category: string;
  publicationYear: number;
  available: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private apiUrl = `${environment.apiUrl}/books`;

  constructor(
    private http: HttpClient,
    private exampleDataService: ExampleDataService
  ) {}

  getBooks(): Observable<Book[]> {
    return of(this.exampleDataService.getExampleBooks());
  }

  getBook(id: number): Observable<Book> {
    const book = this.exampleDataService.getExampleBooks().find(book => book.id === id);
    return book ? of(book) : of({} as Book);
  }

  addBook(book: Book): Observable<Book> {
    const newBook = { ...book, id: this.exampleDataService.getExampleBooks().length + 1 };
    return of(newBook);
  }

  updateBook(book: Book): Observable<Book> {
    return of(book);
  }

  deleteBook(id: number): Observable<void> {
    return of(undefined);
  }
}
