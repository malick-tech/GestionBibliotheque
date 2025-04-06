import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ExampleDataService {
  private books = [
    {
      id: 1,
      title: '1984',
      author: 'George Orwell',
      isbn: '9780451524935',
      category: 'Science Fiction',
      publicationYear: 1949,
      available: true
    },
    {
      id: 2,
      title: 'To Kill a Mockingbird',
      author: 'Harper Lee',
      isbn: '9780060935467',
      category: 'Classics',
      publicationYear: 1960,
      available: true
    },
    {
      id: 3,
      title: 'The Great Gatsby',
      author: 'F. Scott Fitzgerald',
      isbn: '9780743273565',
      category: 'Classics',
      publicationYear: 1925,
      available: false
    }
  ];

  private categories = [
    { id: 1, name: 'Science Fiction' },
    { id: 2, name: 'Classics' },
    { id: 3, name: 'Mystery' },
    { id: 4, name: 'Fantasy' },
    { id: 5, name: 'History' }
  ];

  private members = [
    {
      id: 1,
      firstName: 'John',
      lastName: 'Doe',
      email: 'john.doe@example.com',
      phone: '555-0123',
      registrationDate: new Date('2023-01-15'),
      active: true
    },
    {
      id: 2,
      firstName: 'Jane',
      lastName: 'Smith',
      email: 'jane.smith@example.com',
      phone: '555-0124',
      registrationDate: new Date('2023-02-20'),
      active: true
    }
  ];

  private loans = [
    {
      id: 1,
      bookId: 3,
      memberId: 1,
      loanDate: new Date('2023-04-01'),
      dueDate: new Date('2023-04-30'),
      returnDate: null,
      status: 'LOANED'
    },
    {
      id: 2,
      bookId: 1,
      memberId: 2,
      loanDate: new Date('2023-03-15'),
      dueDate: new Date('2023-04-14'),
      returnDate: new Date('2023-04-10'),
      status: 'RETURNED'
    }
  ];

  getExampleBooks() {
    return [...this.books];
  }

  getExampleCategories() {
    return [...this.categories];
  }

  getExampleMembers() {
    return [...this.members];
  }

  getExampleLoans() {
    return [...this.loans];
  }
}
