// AuthService handles user authentication logic including login, registration,token management, and checking authentication status.
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Login } from '../models/Login';
import { Token } from '../models/Token';
import { environment } from 'src/environments/environment';
import { Register } from '../models/Register';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // Base API path for authentication endpoints
  private pathService = `${environment.baseUrl}/auth`;
  // BehaviorSubject to track whether the user is logged in
  private loggedIn = new BehaviorSubject<boolean>(this.hasToken());

  // Inject HttpClient for making HTTP requests
  constructor(
    private httpClient: HttpClient,
    private tokenService: TokenService
  ) {}

  // Register a new user and return an observable of the token
  public register(registerRequest: Register): Observable<Token> {
    return this.httpClient.post<Token>(
      `${this.pathService}/register`,
      registerRequest
    );
  }

  // Authenticate the user and store the token on success
  public login(loginRequest: Login): Observable<Token> {
    return this.httpClient
      .post<Token>(`${this.pathService}/login`, loginRequest)
      .pipe(
        tap((response: Token) => {
          // Save the received token in localStorage
          this.saveToken(response.token);
          // Update login status
          this.setLoggedIn(true);
        })
      );
  }

  // Fetch the profile of the authenticated user using the stored token
  public getUserProfile(): Observable<any> {
    return this.httpClient.get<any>(`${this.pathService}/me`, {
      headers: this.tokenService.getAuthHeaders(),
    });
  }

  // Save the authentication token in localStorage
  public saveToken(token: string): void {
    localStorage.setItem('authToken', token);
  }

  // Check if a token exists in localStorage
  public hasToken(): boolean {
    return !!localStorage.getItem('authToken');
  }

  // Observable to listen to login status changes
  isLoggedIn$ = this.loggedIn.asObservable();

  // Update the BehaviorSubject with the current login state
  public setLoggedIn(value: boolean): void {
    this.loggedIn.next(value);
  }

  // Remove the token and update login status to false
  public clearToken(): void {
    localStorage.removeItem('authToken');
    this.setLoggedIn(false);
  }
}
