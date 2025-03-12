import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-compatiblity-check',
  templateUrl: './compatiblity-check.component.html',
  styleUrls: ['./compatiblity-check.component.css'],
})
export class CompatiblityCheckComponent implements OnInit {
  recipientBloodType: string = '';
  donorBloodType: string = '';
  alertMessage: string = '';
  alertClass: string = '';
  constructor(private http: HttpClient) {}

  checkCompatibility() {
    const url = `http://localhost:8080/api/distribution/compatibility?recipientType=${this.recipientBloodType}&donorType=${this.donorBloodType}`;

    this.http
      .get<any>(url)
      .pipe(
        catchError((error) => {
          this.showAlert(
            'Failed to connect to remote server. Please contact your system administrator',
            'alert-danger'
          );
          return of(null); // to prevent further execution
        })
      )
      .subscribe((response) => {
        if (response) {
          if (response.isCompatible === true) {
            this.showAlert('Blood type is compatible', 'alert-success');
          } else {
            this.showAlert('Blood type is NOT compatible', 'alert-warning');
          }
        }
      });
  }

  showAlert(message: string, alertClass: string) {
    this.alertMessage = message;
    this.alertClass = alertClass;
  }

  ngOnInit(): void {}
}
