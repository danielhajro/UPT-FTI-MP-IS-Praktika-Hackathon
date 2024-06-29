import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-newproject',
  templateUrl: './newproject.component.html',
  styleUrls: ['./newproject.component.css']
})
export class NewprojectComponent {
  newItemForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.newItemForm = this.fb.group({
      projectName: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (this.newItemForm.valid) {
      const formData = this.newItemForm.value;
      console.log('Form data to be submitted:', formData); // Log form data before submission
  
      this.http.post('http://localhost:8080/projects', formData)
        .subscribe(response => {
          console.log('Project added successfully!', response); // Log successful response
          // Handle success, e.g., show a success message
        }, error => {
          console.error('Failed to add project', error); // Log error if request fails
          // Handle error, e.g., show an error message
        });
    } else {
      console.error('Form is invalid'); // Log if form is invalid
      // Handle invalid form submission, if needed
    }
  }
  
}