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


const appRoutes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'home', component: HomeComponent },
  { path: 'upload', component: UploadMagazineComponent },
  { path: 'search', component: SearchComponent },
  { path: '**', component: NotFoundComponent }
]


@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    RegistrationComponent,
    HomeComponent,
    LoginComponent,
    RegistrationFormComponent,
    UploadMagazineComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorServiceService,
      multi: true
    },
    SearchService,
    UploadMagazineService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
