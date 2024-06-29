import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonService } from 'src/app/service/person.service';

@Component({
  selector: 'app-edit-dev',
  templateUrl: './edit-dev.component.html',
  styleUrls: ['./edit-dev.component.css']
})
export class EditDevComponent implements OnInit {
  devForm!: FormGroup;
  developerId!: string;

  constructor(
    private fb: FormBuilder,
    private personService: PersonService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.devForm = this.fb.group({
      name: ['', [Validators.required, Validators.maxLength(100)]],
      surname: ['', [Validators.required, Validators.maxLength(100)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(100)]],
      skills: this.fb.array([]),
      profilePicture: [null]
    });

    this.developerId = this.route.snapshot.paramMap.get('id')!;
    this.loadDeveloperData(this.developerId);
  }

  createSkill(): FormGroup {
    return this.fb.group({
      skill: ['', Validators.required],
      experience: ['', [Validators.required, Validators.min(0)]]
    });
  }

  get skills(): FormArray {
    return this.devForm.get('skills') as FormArray;
  }

  addSkill(): void {
    this.skills.push(this.createSkill());
  }

  removeSkill(index: number): void {
    if (this.skills.length > 1) {
      this.skills.removeAt(index);
    }
  }

  loadDeveloperData(id: string): void {
    this.personService.getPersonById(id).subscribe(data => {
      this.devForm.patchValue({
        name: data.name,
        surname: data.surname,
        email: data.email
      });

      // Clear existing skills
      this.skills.clear();
      data.skills.forEach((skill: any) => {
        this.skills.push(this.fb.group({
          skill: [skill.skill, Validators.required],
          experience: [skill.experience, Validators.required]
        }));
      });

      if (data.profilePicture) {
        this.devForm.patchValue({
          profilePicture: data.profilePicture
        });
      }
    });
  }

  onFileChange(event: any): void {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        const base64String = (reader.result as string).split(',')[1];
        this.devForm.patchValue({
          profilePicture: base64String
        });
      };
      reader.readAsDataURL(file);
    }
  }

  onSubmit(): void {
    if (this.devForm.invalid) {
      return;
    }

    const formData = {
      ...this.devForm.value,
      skills: this.devForm.get('skills')?.value
    };

    this.personService.updatePerson(this.developerId, formData).subscribe(response => {
      console.log('Person updated successfully', response);
      this.router.navigate(['/developers']); // Navigate back to the developer listing page
    }, error => {
      console.error('Error updating person', error);
    });
  }
}
