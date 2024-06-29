import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonService } from 'src/app/service/person.service';

@Component({
  selector: 'app-delete-dev',
  templateUrl: './delete-dev.component.html',
  styleUrls: ['./delete-dev.component.css']
})
export class DeleteDevComponent implements OnInit {
  developerId!: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private personService: PersonService
  ) { }

  ngOnInit(): void {
    this.developerId = this.route.snapshot.paramMap.get('id')!;
  }

  deleteDeveloper(): void {
    this.personService.deletePerson(this.developerId).subscribe(() => {
      this.router.navigate(['/developers']);
    });
  }

  cancel(): void {
    this.router.navigate(['/developers']);
  }
}
