// TopicService provides methods for interacting with topic-related API endpoints.
// It handles retrieving all topics, user-specific subscriptions, and subscribing/unsubscribing from topics.
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Topic } from '../models/response/Topic';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root',
})
export class TopicService {
  // Base URL for topic-related API endpoints
  private pathService = `${environment.baseUrl}/topic`;

  // Inject HttpClient to perform HTTP requests
  constructor(private httpClient: HttpClient,
      private tokenService: TokenService) {}

  // Fetch all available topics from the backend
  public getTopics(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.pathService}`, {
      headers: this.tokenService.getAuthHeaders(),
    });
  }

  // Fetch topics the current user is subscribed to
  public getUserTopics(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.pathService}/subscriptions`, {
      headers: this.tokenService.getAuthHeaders(),
    });
  }

  // Subscribe the current user to a topic by its ID
  public subscribeToTopic(topicId: number): Observable<void> {
    return this.httpClient.put<void>(
      `${this.pathService}/subscribe/${topicId}`,
      { topicId }, 
      { headers: this.tokenService.getAuthHeaders() }
    );
  }

  // Unsubscribe the current user from a topic by its ID
  public unsubscribeFromTopic(topicId: number): Observable<void> {
    return this.httpClient.delete<void>(
      `${this.pathService}/unsubscribe/${topicId}`,
      { headers: this.tokenService.getAuthHeaders() }
    );
  }
}
