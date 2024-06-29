import { Component, OnInit } from '@angular/core';
import { Person } from '../../interfaces/person';
import { PersonService } from 'src/app/service/person.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-devs',
  templateUrl: './devs.component.html',
  styleUrls: ['./devs.component.css']
})
export class DevsComponent {
  persons: any[] = [];

  constructor(private personService: PersonService, private router: Router,private http: HttpClient) { }

  ngOnInit(): void {
    this.loadDevelopers();
  }

  loadDevelopers(): void {
    this.personService.getAllPersons().subscribe(data => {
      this.persons = data;
    });
  }

  editPerson(id: string): void {
    this.router.navigate([`/edit-dev/${id}`]);
  }

  deletePerson(id: string): void {
    this.router.navigate([`/delete-dev/${id}`]);
  }
  private apiUrl = 'http://localhost:8080/api/pdf/generate-specific';

  printPersonPDF(personId: number) {
    const url = `${this.apiUrl}?developerId=${personId}`;
    this.http.get(url, { responseType: 'blob' }).subscribe((response) => {
      const blob = new Blob([response], { type: 'application/pdf' });
      const url = window.URL.createObjectURL(blob);
      window.open(url);
    });
  }
}
