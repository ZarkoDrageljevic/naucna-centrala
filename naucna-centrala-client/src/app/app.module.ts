import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';


import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { TokenInterceptorServiceService } from './services/token-interceptor-service.service';
import { RegistrationFormComponent } from './components/registration-form/registration-form.component';
import { UploadMagazineComponent } from './components/upload-magazine/upload-magazine.component';
import { SearchComponent } from './components/search/search.component';
import { SearchService } from './services/search.service';
import { UploadMagazineService } from './services/upload-magazine.service';
import { DownloadFile } from './model/download-file';
import { ToastrModule } from 'ngx-toastr';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { TaskComponent } from './components/task/task.component';
import {TaskService} from './services/task.service';
import {UserService} from './services/user.service';
import {PaperComponent} from './components/paper/paper.component';
import { MagazineListComponent } from './components/magazine-list/magazine-list.component';
import {MagazineService} from './services/magazine.service';
import { NavbarComponent } from './navbar/navbar.component';
import {AlreadyLoggedInGuard} from './guards/already-logged-in.guard';
import {OnlyLoggedInGuard} from './guards/only-logged-in.guard';
import { PaperSubmissionComponent } from './components/paper-submission/paper-submission.component';
import {ScienceField} from './model/science-field';
import {ScienceFieldService} from './services/science-field.service';
import { TvalidationComponent } from './components/tvalidation/tvalidation.component';
import { RejectExplanationComponent } from './components/reject-explanation/reject-explanation.component';



const appRoutes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent , canActivate: [AlreadyLoggedInGuard]},
  { path: 'task', component: TaskComponent , canActivate: [OnlyLoggedInGuard]},
  { path: 'registration', component: RegistrationComponent },
  { path: 'home', component: HomeComponent , canActivate: [OnlyLoggedInGuard]},
  { path: 'upload', component: UploadMagazineComponent , canActivate: [OnlyLoggedInGuard]},
  { path: 'search', component: SearchComponent , canActivate: [OnlyLoggedInGuard]},
  { path: 'paper', component: PaperComponent , canActivate: [OnlyLoggedInGuard] },
  { path: 'magazine/choose/:taskId', component: MagazineListComponent , canActivate: [OnlyLoggedInGuard]},
  { path: 'paper/submit/:taskId', component: PaperSubmissionComponent , canActivate: [OnlyLoggedInGuard]},
  { path: 'paper/tvalidation/:taskId', component: TvalidationComponent , canActivate: [OnlyLoggedInGuard]},
  { path: 'paper/reject-explanation/:taskId', component: RejectExplanationComponent , canActivate: [OnlyLoggedInGuard]},


  { path: '**', component: NotFoundComponent }
];


@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    PaperComponent,
    RegistrationComponent,
    HomeComponent,
    LoginComponent,
    RegistrationFormComponent,
    UploadMagazineComponent,
    SearchComponent,
    TaskComponent,
    MagazineListComponent,
    NavbarComponent,
    PaperSubmissionComponent,
    TvalidationComponent,
    RejectExplanationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ToastrModule.forRoot({
      preventDuplicates: true,
      positionClass: 'toast-bottom-right'
    }),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorServiceService,
      multi: true
    },
    SearchService,
    TaskService,
    UserService,
    UploadMagazineService,
    MagazineService,
    ScienceFieldService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
