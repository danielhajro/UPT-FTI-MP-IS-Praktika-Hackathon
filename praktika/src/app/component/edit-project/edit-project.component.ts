import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-edit-project',
  templateUrl: './edit-project.component.html',
  styleUrls: ['./edit-project.component.css']
})
export class EditProjectComponent implements OnInit {
  editItemForm: FormGroup;
  projectId!: string;
  project: any; // Assuming project structure matches backend response

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.editItemForm = this.fb.group({
      projectName: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.projectId = params['id']; // Get projectId from route params
      this.fetchProjectDetails(this.projectId);
    });
  }

  fetchProjectDetails(projectId: string): void {
    this.http.get<any>(`http://localhost:8080/projects/${projectId}`)
      .subscribe(project => {
        this.project = project;
        this.editItemForm.patchValue({
          projectName: project.projectName,
          description: project.description
        });
      }, error => {
        console.error('Failed to fetch project details', error);
        // Handle error, e.g., show an error message or navigate back
      });
  }

  onSubmit(): void {
    if (this.editItemForm.valid) {
      const formData = this.editItemForm.value;
      this.http.put(`http://localhost:8080/projects/${this.projectId}`, formData)
        .subscribe(response => {
          console.log('Project updated successfully!', response);
          this.router.navigate(['/projects']); // Navigate to projects list after update
        }, error => {
          console.error('Failed to update project', error);
          // Handle error, e.g., show an error message
        });
    } else {
      console.error('Form is invalid');
      // Handle invalid form submission, if needed
    }
  }
}
