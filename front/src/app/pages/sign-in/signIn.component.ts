import { Token } from 'src/app/core/models/Token';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from 'src/app/core/models/Login';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-signIn',
  templateUrl: './signIn.component.html',
  styleUrls: ['./signIn.component.scss'],
})
export class SignInComponent implements OnInit {
  form!: FormGroup;
  hide = true;
  onError = false;
  errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  public submit(): void {
    if (this.form.invalid) return;

    const loginRequest = this.form.value as Login;
    this.authService.login(loginRequest).subscribe({
      next: (response: Token) => {
        this.authService.saveToken(response.token);
        this.router.navigate(['/articles']);
      },
      error: (error) => {
        this.onError = true;
      },
    });
  }
}
