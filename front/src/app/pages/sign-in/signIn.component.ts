// SignInComponent handles user authentication.
// It provides a login form, validates inputs, and processes authentication through AuthService.
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
  // Reactive form group for login inputs
  form!: FormGroup;
  // Controls password visibility toggle
  hide = true;
  // Flag to track if an error occurred during login
  onError = false;
  // Optional error message for login failure feedback
  errorMessage: string | null = null;

  // Inject form builder, authentication service, and router
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  // Initialize the login form with validation rules
  ngOnInit(): void {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  // Handle login form submission and call authentication service
  public submit(): void {
    // Prevent submission if form is invalid
    if (this.form.invalid) return;

    // Extract form values and cast to Login model
    const loginRequest = this.form.value as Login;
    this.authService.login(loginRequest).subscribe({
      next: (response: Token) => {
        // Save received token and navigate to articles page
        this.authService.saveToken(response.token);
        this.router.navigate(['/articles']);
      },
      error: (error) => {
        // Show error state if login fails
        this.onError = true;
      },
    });
  }
}
