import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ExampleDataService } from '../../data/example-data.service';

export interface Member {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  registrationDate: Date;
  active: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class MemberService {
  constructor(
    private http: HttpClient,
    private exampleDataService: ExampleDataService
  ) {}

  getMembers(): Observable<Member[]> {
    return of(this.exampleDataService.getExampleMembers());
  }

  getMember(id: number): Observable<Member> {
    return of(this.exampleDataService.getExampleMembers().find(member => member.id === id));
  }

  addMember(member: Member): Observable<Member> {
    const newMember = { ...member, id: this.exampleDataService.getExampleMembers().length + 1 };
    return of(newMember);
  }

  updateMember(member: Member): Observable<Member> {
    return of(member);
  }

  deleteMember(id: number): Observable<void> {
    return of(undefined);
  }
}
