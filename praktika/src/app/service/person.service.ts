// src/app/service/person.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Person } from '../interfaces/person';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private apiUrl = 'http://localhost:8080/developers';

  constructor(private http: HttpClient) { }

  getAllPersons(): Observable<Person[]> {
    return this.http.get<Person[]>(`${this.apiUrl}/search`);
  }

  addPerson(personData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/addDeveloper`, personData);
  }

  getPersonById(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  updatePerson(id: string, personData: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, personData);
  }
  deletePerson(id: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }
}
