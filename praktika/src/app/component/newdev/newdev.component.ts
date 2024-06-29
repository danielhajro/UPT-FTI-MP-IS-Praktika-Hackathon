import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PersonService } from 'src/app/service/person.service';

@Component({
  selector: 'app-newdev',
  templateUrl: './newdev.component.html',
  styleUrls: ['./newdev.component.css']
})
export class NewdevComponent implements OnInit {
  devForm!: FormGroup;

  constructor(private fb: FormBuilder, private personService: PersonService) { }

  ngOnInit(): void {
    this.devForm = this.fb.group({
      name: ['', [Validators.required, Validators.maxLength(100)]],
      surname: ['', [Validators.required, Validators.maxLength(100)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(100)]],
      skills: this.fb.array([this.createSkill()]),
      profilePicture: [null]
    });
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

    this.personService.addPerson(formData).subscribe(response => {
      console.log('Person added successfully', response);
    }, error => {
      console.error('Error adding person', error);
    });
  }
}
