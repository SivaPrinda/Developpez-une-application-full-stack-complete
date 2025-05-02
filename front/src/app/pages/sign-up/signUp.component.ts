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
  form!: FormGroup;
  hide = true;
  onError = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  public submit(): void {
    if (this.form.invalid) return;

    const registerRequest = this.form.value as Register;
    this.authService.register(registerRequest).subscribe({
      next: (response: Token) => {
        this.authService.saveToken(response.token);
        this.router.navigate(['/articles']);
      },
      error: (error) => (this.onError = true),
    });
  }
}
