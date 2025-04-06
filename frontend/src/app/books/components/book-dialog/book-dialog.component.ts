import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../../material.module';
import { FormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Book } from '../../models/book.model';

@Component({
  selector: 'app-book-dialog',
  standalone: true,
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule
  ],
  template: `
    <h2 mat-dialog-title>{{ data.id ? 'Modifier' : 'Ajouter' }} un livre</h2>
    <mat-dialog-content>
      <form #bookForm="ngForm">
        <mat-form-field appearance="fill" class="full-width">
          <mat-label>Titre</mat-label>
          <input matInput [(ngModel)]="data.title" name="title" required>
        </mat-form-field>

        <mat-form-field appearance="fill" class="full-width">
          <mat-label>Auteur</mat-label>
          <input matInput [(ngModel)]="data.author" name="author" required>
        </mat-form-field>

        <mat-form-field appearance="fill" class="full-width">
          <mat-label>ISBN</mat-label>
          <input matInput [(ngModel)]="data.isbn" name="isbn" required>
        </mat-form-field>

        <mat-form-field appearance="fill" class="full-width">
          <mat-label>Description</mat-label>
          <textarea matInput [(ngModel)]="data.description" name="description" rows="3"></textarea>
        </mat-form-field>
      </form>
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button (click)="onCancel()">Annuler</button>
      <button mat-raised-button color="primary" (click)="onSave()" [disabled]="!bookForm.form.valid">
        {{ data.id ? 'Modifier' : 'Ajouter' }}
      </button>
    </mat-dialog-actions>
  `,
  styles: [
    '.full-width { width: 100%; margin-bottom: 15px; }',
    'form { margin-top: 15px; }'
  ]
})
export class BookDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<BookDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Book
  ) {}

  onCancel(): void {
    this.dialogRef.close();
  }

  onSave(): void {
    this.dialogRef.close(this.data);
  }
}
