import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import { MatTooltipModule } from '@angular/material/tooltip';

import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/category.model';
import { CategoryDialogComponent } from '../category-dialog/category-dialog.component';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule,
    MatTooltipModule,
    FormsModule
  ]
})
export class CategoryListComponent implements OnInit {
  displayedColumns: string[] = ['name', 'description', 'booksCount', 'actions'];
  dataSource: MatTableDataSource<Category>;
  totalCategories = 0;
  pageSize = 10;
  pageSizeOptions = [5, 10, 25, 50];
  searchTerm = '';

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private categoryService: CategoryService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) {
    this.dataSource = new MatTableDataSource<Category>();
  }

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(page: number = 0): void {
    this.categoryService.getCategories(page, this.pageSize, this.searchTerm)
      .subscribe({
        next: (response) => {
          this.dataSource.data = response.content;
          this.totalCategories = response.totalElements;
        },
        error: () => {
          this.showNotification('Erreur lors du chargement des catégories', 'error');
        }
      });
  }

  applyFilter(): void {
    if (this.paginator) {
      this.paginator.firstPage();
    }
    this.loadCategories();
  }

  onPageChange(event: any): void {
    this.pageSize = event.pageSize;
    this.loadCategories(event.pageIndex);
  }

  openCategoryDialog(category?: Category): void {
    const dialogRef = this.dialog.open(CategoryDialogComponent, {
      width: '500px',
      data: { category: category || {}, isEdit: !!category }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (result.id) {
          this.updateCategory(result.id, result);
        } else {
          this.createCategory(result);
        }
      }
    });
  }

  createCategory(category: Category): void {
    this.categoryService.createCategory(category).subscribe({
      next: () => {
        this.showNotification('Catégorie ajoutée avec succès', 'success');
        this.loadCategories();
      },
      error: () => {
        this.showNotification('Erreur lors de l\'ajout de la catégorie', 'error');
      }
    });
  }

  updateCategory(id: number, category: Category): void {
    this.categoryService.updateCategory(id, category).subscribe({
      next: () => {
        this.showNotification('Catégorie mise à jour avec succès', 'success');
        this.loadCategories();
      },
      error: () => {
        this.showNotification('Erreur lors de la mise à jour de la catégorie', 'error');
      }
    });
  }

  deleteCategory(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette catégorie ?')) {
      this.categoryService.deleteCategory(id).subscribe({
        next: () => {
          this.showNotification('Catégorie supprimée avec succès', 'success');
          this.loadCategories();
        },
        error: () => {
          this.showNotification('Erreur lors de la suppression de la catégorie', 'error');
        }
      });
    }
  }

  private showNotification(message: string, type: 'success' | 'error'): void {
    this.snackBar.open(message, 'Fermer', {
      duration: 3000,
      horizontalPosition: 'end',
      verticalPosition: 'top',
      panelClass: type === 'success' ? ['success-snackbar'] : ['error-snackbar']
    });
  }
}
