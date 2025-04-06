import { Routes } from '@angular/router';
import { AuthGuard } from './auth/guards/auth.guard';
import { BooksComponent } from './books/books.component';
import { MemberListComponent } from './members/components/member-list/member-list.component';
import { LoanListComponent } from './loans/components/loan-list/loan-list.component';

export const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: 'books',
    component: BooksComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'members',
    component: MemberListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'loans',
    component: LoanListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'categories',
    loadChildren: () => import('./categories/categories.module').then(m => m.CategoriesModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
    canActivate: [AuthGuard]
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'books'
  }
];
