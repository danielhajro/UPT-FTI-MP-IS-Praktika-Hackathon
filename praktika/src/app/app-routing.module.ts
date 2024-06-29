import { Component,NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { DevsComponent } from './component/devs/devs.component';
import { ProjectsComponent } from './component/projects/projects.component';
import { NewdevComponent } from './component/newdev/newdev.component';
import { NewprojectComponent } from './component/newproject/newproject.component';
import { EditDevComponent } from './component/edit-dev/edit-dev.component';
import { EditProjectComponent } from './component/edit-project/edit-project.component';
import { DeleteProjectComponent } from './component/delete-project/delete-project.component';
import { DeleteDevComponent } from './component/delete-dev/delete-dev.component';

const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent }, 
  {path:'devs',component:DevsComponent},
  {path:'projects', component:ProjectsComponent},
  {path:'newdev',component:NewdevComponent},
  {path:'newproject',component:NewprojectComponent},
  {path:'edit-dev/:id',component:EditDevComponent},
  {path: 'edit-project/:id', component:EditProjectComponent},
  {path:'delete-project/:id',component:DeleteProjectComponent},
  {path:'delete-dev/:id', component:DeleteDevComponent},
  
    
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
