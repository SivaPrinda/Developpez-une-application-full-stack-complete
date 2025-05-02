import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Login } from '../models/Login';
import { Token } from '../models/Token';
import { environment } from 'src/environments/environment';
import { Register } from '../models/Register';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private pathService = `${environment.baseUrl}/auth`;
  private loggedIn = new BehaviorSubject<boolean>(this.hasToken());


  constructor(private httpClient: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
      const token = localStorage.getItem('authToken'); // Récupère le token depuis le localStorage
      return new HttpHeaders({
        Authorization: `Bearer ${token}`, // Ajoute le token dans l'en-tête Authorization
      });
    }
    
  public register(registerRequest: Register): Observable<Token> {
    return this.httpClient.post<Token>(
      `${this.pathService}/register`,
      registerRequest
    );
  }

  public login(loginRequest: Login): Observable<Token> {
    return this.httpClient.post<Token>(`${this.pathService}/login`, loginRequest).pipe(
      tap((response: Token) => {
        this.saveToken(response.token); // Sauvegarde le token
        this.setLoggedIn(true); // Met à jour l'état de connexion
      })
    );
  }

  // Récupère le profil utilisateur
    public getUserProfile(): Observable<any> {
      return this.httpClient.get<any>(`${this.pathService}/me`, {
        headers: this.getAuthHeaders(),
      });
    }

  public saveToken(token: string): void {
    localStorage.setItem('authToken', token);
  }

  public hasToken(): boolean {
    return !!localStorage.getItem('authToken');
  }

  isLoggedIn$ = this.loggedIn.asObservable();
  
  public setLoggedIn(value: boolean): void {
    this.loggedIn.next(value); // Met à jour le sujet BehaviorSubject
  }

  public clearToken(): void {
    localStorage.removeItem('authToken'); // Supprime le token du stockage local
    this.setLoggedIn(false); 
  }
}
