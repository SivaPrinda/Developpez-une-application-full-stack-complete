// SignUpComponent handles user registration.
// It provides a reactive form for new users to create an account and saves the authentication token on success.
import { Token } from 'src/app/core/models/Token';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { Register } from 'src/app/core/models/Register';

@Component({
  selector: 'app-signUp',
  templateUrl: './signUp.component.html',
  styleUrls: ['./signUp.component.scss'],
})
export class SignUpComponent implements OnInit {
  // Reactive form group for registration inputs
  form!: FormGroup;
  // Controls visibility toggle for password field
  hide = true;
  // Flag to indicate if an error occurred during registration
  onError = false;

  // Inject FormBuilder for reactive forms, AuthService for API interaction, and Router for navigation
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  // Initialize the registration form with validation rules
  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  // Handle registration form submission and call AuthService to register the user
  public submit(): void {
    // Prevent submission if form is invalid
    if (this.form.invalid) return;

    // Extract form values and cast them to the Register model
    const registerRequest = this.form.value as Register;
    this.authService.register(registerRequest).subscribe({
      next: (response: Token) => {
        // Save the returned token and navigate to the articles page
        this.authService.saveToken(response.token);
        this.router.navigate(['/articles']);
      },
      error: (error) => {
        // Set error flag if registration fails
        this.onError = true
      },
    });
  }
}
