import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ExampleDataService } from '../../data/example-data.service';

export interface Loan {
  id: number;
  bookId: number;
  memberId: number;
  loanDate: Date;
  dueDate: Date;
  returnDate: Date | null;
  status: 'LOANED' | 'RETURNED' | 'OVERDUE';
}

@Injectable({
  providedIn: 'root'
})
export class LoanService {
  constructor(
    private http: HttpClient,
    private exampleDataService: ExampleDataService
  ) {}

  getLoans(): Observable<Loan[]> {
    return of(this.exampleDataService.getExampleLoans());
  }

  getLoan(id: number): Observable<Loan> {
    return of(this.exampleDataService.getExampleLoans().find(loan => loan.id === id));
  }

  addLoan(loan: Loan): Observable<Loan> {
    const newLoan = { ...loan, id: this.exampleDataService.getExampleLoans().length + 1 };
    return of(newLoan);
  }

  updateLoan(loan: Loan): Observable<Loan> {
    return of(loan);
  }

  deleteLoan(id: number): Observable<void> {
    return of(undefined);
  }

  returnBook(loanId: number): Observable<Loan> {
    const loan = this.exampleDataService.getExampleLoans().find(l => l.id === loanId);
    if (loan) {
      const updatedLoan = { ...loan, returnDate: new Date(), status: 'RETURNED' };
      return of(updatedLoan);
    }
    return of(null);
  }
}
