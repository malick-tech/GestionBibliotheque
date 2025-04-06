import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { BookService } from './services/book.service';
import { Book } from './services/book.service';
import { MatDialog } from '@angular/material/dialog';
import { BookDialogComponent } from './components/book-dialog/book-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { MatTooltipModule } from '@angular/material/tooltip';

@Component({
  selector: 'app-books',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    FormsModule,
    MatTooltipModule
  ],
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss']
})
export class BooksComponent implements OnInit {
  displayedColumns: string[] = ['id', 'title', 'author', 'isbn', 'category', 'publicationYear', 'available', 'actions'];
  dataSource = new MatTableDataSource<Book>();
  totalBooks = 0;
  searchTerm = '';

  constructor(
    private bookService: BookService,
    public dialog: MatDialog,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    this.bookService.getBooks().subscribe({
      next: (books: Book[]) => {
        this.dataSource.data = books;
        this.totalBooks = books.length;
      },
      error: (error) => {
        this.snackBar.open('Erreur lors du chargement des livres', 'Fermer', {
          duration: 3000
        });
      }
    });
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(BookDialogComponent, {
      width: '600px',
      data: null
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.bookService.addBook(result).subscribe({
          next: () => {
            this.loadBooks();
            this.snackBar.open('Livre ajouté avec succès', 'Fermer', {
              duration: 3000
            });
          },
          error: (error) => {
            this.snackBar.open('Erreur lors de l\'ajout du livre', 'Fermer', {
              duration: 3000
            });
          }
        });
      }
    });
  }

  editBook(book: Book): void {
    const dialogRef = this.dialog.open(BookDialogComponent, {
      width: '600px',
      data: book
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.bookService.updateBook(result).subscribe({
          next: () => {
            this.loadBooks();
            this.snackBar.open('Livre mis à jour avec succès', 'Fermer', {
              duration: 3000
            });
          },
          error: (error) => {
            this.snackBar.open('Erreur lors de la mise à jour du livre', 'Fermer', {
              duration: 3000
            });
          }
        });
      }
    });
  }

  deleteBook(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce livre ?')) {
      this.bookService.deleteBook(id).subscribe({
        next: () => {
          this.loadBooks();
          this.snackBar.open('Livre supprimé avec succès', 'Fermer', {
            duration: 3000
          });
        },
        error: (error) => {
          this.snackBar.open('Erreur lors de la suppression du livre', 'Fermer', {
            duration: 3000
          });
        }
      });
    }
  }
}
