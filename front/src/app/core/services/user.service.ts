import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Topic } from '../models/response/Topic';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private pathService = `${environment.baseUrl}/user`;

  constructor(private httpClient: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('authToken'); // Récupère le token depuis le localStorage
    return new HttpHeaders({
      Authorization: `Bearer ${token}`, // Ajoute le token dans l'en-tête Authorization
    });
  }

  // Met à jour le profil utilisateur
  public updateUserProfile(user: User): Observable<User> {
    return this.httpClient.put<any>(`${this.pathService}/update`, user, {
      headers: this.getAuthHeaders(),
    });
  }
}
