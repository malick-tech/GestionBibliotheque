import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';

interface User {
  id: number;
  email: string;
  name: string;
}

interface AuthResponse {
  token: string;
  user: User;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api';
  private tokenKey = 'auth_token';
  private userKey = 'current_user';
  private rolesKey = 'roles';
  
  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser$: Observable<User | null>;
  private userRoles = new BehaviorSubject<string[]>([]);

  constructor(private http: HttpClient) {
    const storedUser = localStorage.getItem(this.userKey);
    this.currentUserSubject = new BehaviorSubject<User | null>(storedUser ? JSON.parse(storedUser) : null);
    this.currentUser$ = this.currentUserSubject.asObservable();
    const storedRoles = localStorage.getItem(this.rolesKey);
    if (storedRoles) {
      this.userRoles.next(JSON.parse(storedRoles));
    }
  }

  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

  login(credentials: { email: string; password: string }): Observable<AuthResponse> {
    // Pour le moment, simulons une connexion réussie
    return of({
      token: 'fake-jwt-token',
      user: {
        id: 1,
        email: credentials.email,
        name: 'Utilisateur Test'
      }
    }).pipe(
      tap(response => {
        localStorage.setItem(this.tokenKey, response.token);
        localStorage.setItem(this.userKey, JSON.stringify(response.user));
        this.currentUserSubject.next(response.user);
        localStorage.setItem(this.rolesKey, JSON.stringify(['USER']));
        this.userRoles.next(['USER']);
      })
    );
  }

  register(userData: { name: string; email: string; password: string }): Observable<AuthResponse> {
    // Pour le moment, simulons une inscription réussie
    return of({
      token: 'fake-jwt-token',
      user: {
        id: 1,
        email: userData.email,
        name: userData.name
      }
    }).pipe(
      tap(response => {
        localStorage.setItem(this.tokenKey, response.token);
        localStorage.setItem(this.userKey, JSON.stringify(response.user));
        this.currentUserSubject.next(response.user);
        localStorage.setItem(this.rolesKey, JSON.stringify(['USER']));
        this.userRoles.next(['USER']);
      })
    );
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.userKey);
    localStorage.removeItem(this.rolesKey);
    this.currentUserSubject.next(null);
    this.userRoles.next([]);
  }

  isAuthenticated(): boolean {
    return !!this.currentUserValue;
  }

  isAdmin(): boolean {
    return this.userRoles.value.includes('ADMIN');
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  getCurrentUser(): User | null {
    return this.currentUserValue;
  }
}
