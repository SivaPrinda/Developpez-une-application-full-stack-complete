// UserService handles operations related to user profile management.
// It includes methods for updating the user's profile information via HTTP requests.
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/User';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  // Base URL for user-related API endpoints
  private pathService = `${environment.baseUrl}/user`;

  // Inject HttpClient for performing HTTP requests
  constructor(
    private httpClient: HttpClient,
    private tokenService: TokenService
  ) {}

  // Send a PUT request to update the user's profile with the provided user data
  public updateUserProfile(user: User): Observable<User> {
    return this.httpClient.put<any>(`${this.pathService}/update`, user, {
      headers: this.tokenService.getAuthHeaders(),
    });
  }
}
