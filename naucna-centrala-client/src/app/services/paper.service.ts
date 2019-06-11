import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {Paper} from '../model/paper';
import {FormField} from '../model/form-field';
import {Review} from '../model/review';
import {Reviewer} from '../model/reviwer';

@Injectable({
  providedIn: 'root'
})
export class PaperService {

  private baseUrl = '/api/paper';

  constructor(private http: HttpClient, toastr: ToastrService) {
  }

  startPaperProcess() {
    return this.http.post(`${this.baseUrl}/start-paper-process`, {});
  }

  createPaper(taskId: string, formData: FormData) {

    return this.http.post<Paper>(`${this.baseUrl}/new/${taskId}`, formData);
  }

  getPaperByTaskId(taskId: string) {
    return this.http.get<Paper>(`${this.baseUrl}/task/${taskId}`);

  }

  submitTvalidation(taskId: string, formFields: FormField[]) {
    return this.http.post(`${this.baseUrl}/tvalidation/${taskId}`, formFields);
  }

  rejectPaperThematic(taskId: string, formData: FormData) {
    return this.http.post<Paper>(`${this.baseUrl}/reject/thematic/${taskId}`, formData);
  }

  submitFormatValidation(taskId: string, formFields: FormField[]) {
    return this.http.post(`${this.baseUrl}/format-validation/${taskId}`, formFields);
  }

  resubmitFormat(taskId: string, formData: FormData) {
    return this.http.post<Paper>(`${this.baseUrl}/format-correction/${taskId}`, formData);
  }

  getReviewers(taskId: string) {
    return this.http.get<Reviewer[]>(`${this.baseUrl}/get-reviewers/${taskId}`);
  }

  getReviewersScienceField(taskId: string) {
    return this.http.get<Reviewer[]>(`${this.baseUrl}/get-reviewers/${taskId}?scienceField=da`);
  }

  chooseReviewers(taskId: string, formData: FormData) {
    return this.http.post(`${this.baseUrl}/select-reviewers/${taskId}`, formData);
  }

  submitReview(taskId: string, formData: FormField[]) {
    return this.http.post<Review[]>(`${this.baseUrl}/submit-review/${taskId}`, formData);
  }

  getPaperReviews(taskId: string) {
    return this.http.get<Review[]>(`${this.baseUrl}/get-reviews/${taskId}`);
  }


  submitEditorReview(taskId: string, formFields: FormField[]) {
    return this.http.post(`${this.baseUrl}/submit-editor-review/${taskId}`, formFields);
  }

  submitRevision(taskId: string, formData: FormData) {
    return this.http.post<Paper>(`${this.baseUrl}/resubmit-paper/${taskId}`, formData);
  }

  submitReviewOfRevision(taskId: string, formFields: FormField[]) {
    return this.http.post(`${this.baseUrl}/review-revision/${taskId}`, formFields);
  }

  submitPayment(taskId: string, array: FormField[]) {
    return this.http.post(`${this.baseUrl}/payment/${taskId}`, array);

  }

  downloadPaper(taskId: string) {
    return this.http.post(`${this.baseUrl}/download/${taskId}`, Object(), { responseType: 'blob' });

  }
}
