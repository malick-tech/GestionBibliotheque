import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ExampleDataService } from '../../data/example-data.service';

export interface Category {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  constructor(
    private http: HttpClient,
    private exampleDataService: ExampleDataService
  ) {}

  getCategories(pageIndex: number = 0, pageSize: number = 10): Observable<{ categories: Category[], total: number }> {
    const categories = this.exampleDataService.getExampleCategories();
    const total = categories.length;
    const pagedCategories = categories.slice(pageIndex * pageSize, (pageIndex + 1) * pageSize);
    return of({ categories: pagedCategories, total });
  }

  getCategory(id: number): Observable<Category> {
    const category = this.exampleDataService.getExampleCategories().find(cat => cat.id === id);
    return category ? of(category) : of({} as Category);
  }

  addCategory(category: Category): Observable<Category> {
    const newCategory = { ...category, id: this.exampleDataService.getExampleCategories().length + 1 };
    return of(newCategory);
  }

  updateCategory(category: Category): Observable<Category> {
    return of(category);
  }

  deleteCategory(id: number): Observable<void> {
    return of(undefined);
  }
}
