import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { DevsComponent } from './component/devs/devs.component';
import { ProjectsComponent } from './component/projects/projects.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NewdevComponent } from './component/newdev/newdev.component';
import { NewprojectComponent } from './component/newproject/newproject.component';
import { EditDevComponent } from './component/edit-dev/edit-dev.component';
import { EditProjectComponent } from './component/edit-project/edit-project.component';
import { DeleteProjectComponent } from './component/delete-project/delete-project.component';
import { DeleteDevComponent } from './component/delete-dev/delete-dev.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    DevsComponent,
    ProjectsComponent,
    NewdevComponent,
    NewprojectComponent,
    EditDevComponent,
    EditProjectComponent,
    DeleteProjectComponent,
    DeleteDevComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
