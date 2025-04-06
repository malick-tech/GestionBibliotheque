import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../material.module';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { BookService } from './services/book.service';
import { Book } from './models/book.model';
import { BookDialogComponent } from './components/book-dialog/book-dialog.component';

@Component({
  selector: 'app-books',
  standalone: true,
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    RouterModule
  ],
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss']
})
export class BooksComponent implements OnInit {
  displayedColumns: string[] = ['title', 'author', 'isbn', 'available', 'actions'];
  dataSource: MatTableDataSource<Book>;
  totalBooks = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private bookService: BookService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) {
    this.dataSource = new MatTableDataSource<Book>([]);
  }

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    this.bookService.getBooks().subscribe(
      books => {
        this.dataSource.data = books;
        this.totalBooks = books.length;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
    );
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  openDialog(book?: Book): void {
    const dialogRef = this.dialog.open(BookDialogComponent, {
      width: '400px',
      data: book || {}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (book) {
          this.updateBook(result);
        } else {
          this.addBook(result);
        }
      }
    });
  }

  addBook(book: Book): void {
    this.bookService.addBook(book).subscribe(
      () => {
        this.loadBooks();
        this.snackBar.open('Livre ajouté avec succès', 'Fermer', { duration: 3000 });
      }
    );
  }

  updateBook(book: Book): void {
    this.bookService.updateBook(book).subscribe(
      () => {
        this.loadBooks();
        this.snackBar.open('Livre mis à jour avec succès', 'Fermer', { duration: 3000 });
      }
    );
  }

  deleteBook(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce livre ?')) {
      this.bookService.deleteBook(id).subscribe(
        () => {
          this.loadBooks();
          this.snackBar.open('Livre supprimé avec succès', 'Fermer', { duration: 3000 });
        }
      );
    }
  }
}
