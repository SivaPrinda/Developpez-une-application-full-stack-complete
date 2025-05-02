import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Topic } from '../models/response/Topic';

@Injectable({
  providedIn: 'root',
})
export class TopicService {
  private pathService = `${environment.baseUrl}/topic`;

  constructor(private httpClient: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('authToken'); // Récupère le token depuis le localStorage
    return new HttpHeaders({
      Authorization: `Bearer ${token}`, // Ajoute le token dans l'en-tête Authorization
    });
  }

  public getTopics(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.pathService}`, {
      headers: this.getAuthHeaders(),
    });
  }

  public getUserTopics(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.pathService}/subscriptions`, {
      headers: this.getAuthHeaders(),
    });
  }

  // Méthode pour s'abonner à un topic
  public subscribeToTopic(topicId: number): Observable<void> {
    return this.httpClient.put<void>(
      `${this.pathService}/subscribe/${topicId}`,
      { topicId }, // Payload contenant l'ID du topic
      { headers: this.getAuthHeaders() }
    );
  }

  // Méthode pour se désabonner d'un topic (optionnel)
  public unsubscribeFromTopic(topicId: number): Observable<void> {
    return this.httpClient.delete<void>(
      `${this.pathService}/unsubscribe/${topicId}`,
      { headers: this.getAuthHeaders() }
    );
  }
}
