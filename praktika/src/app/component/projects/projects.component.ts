import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Project } from 'src/app/interfaces/project';
import { ProjectService } from 'src/app/service/project.service';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent {
  projects: Project[] = [];

  constructor(private projectService: ProjectService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.projectService.getAllProjects().subscribe(
      data => {
        this.projects = data;
      },
      error => {
        console.error('Error fetching projects', error);
      }
    );
  }
  
  editProject(projectId: number): void {
    this.router.navigate(['/edit-project', projectId]);
  }
  
}
