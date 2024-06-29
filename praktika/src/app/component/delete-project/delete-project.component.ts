import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-delete-project',
  templateUrl: './delete-project.component.html',
  styleUrls: ['./delete-project.component.css']
})
export class DeleteProjectComponent implements OnInit {
  projectId!: number; // Assuming projectId is passed via route or component input

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.projectId = params['id']; // Get projectId from route params
    });
  }

  confirmDelete(): void {
    this.http.delete(`http://localhost:8080/projects/${this.projectId}`)
      .subscribe(response => {
        console.log('Project deleted successfully!', response);
        this.router.navigate(['/projects']); // Navigate to projects list after deletion
      }, error => {
        console.error('Failed to delete project', error);
        // Handle error, e.g., show an error message
      });
  }

  cancelDelete(): void {
    // Optionally navigate back or perform any cancelation actions
    this.router.navigate(['/projects']);
  }
}
