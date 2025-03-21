import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { environment } from 'src/environments/environment';

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

  constructor(private http: HttpClient) {  }

  checkCompatibility() {
    const recipient = encodeURIComponent(this.recipientBloodType);
    const donor = encodeURIComponent(this.donorBloodType);
    const url = environment.API_BASE_URL + `/utilities/compatibility?recipientType=${recipient}&donorType=${donor}`;

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
          if(response.ok === false)
          {
            this.showAlert('Failed to perform operation. Reason: ' + response.errors.join("; ") , 'alert-danger');
          }
          else if (response.compatible === true) {
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
