import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Post } from '../models/Post';
import { PostRequest } from '../models/request/PostRequest';
import { PostComment } from '../models/PostComment';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  private pathService = `${environment.baseUrl}/posts`;

  constructor(private httpClient: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('authToken'); // Récupère le token depuis le localStorage
    return new HttpHeaders({
      Authorization: `Bearer ${token}`, // Ajoute le token dans l'en-tête Authorization
    });
  }

  public getPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(`${this.pathService}`, {
      headers: this.getAuthHeaders(),
    });
  }

  public createPost(postRequest: PostRequest): Observable<Post> {
    return this.httpClient.post<Post>(`${this.pathService}`, postRequest, {
      headers: this.getAuthHeaders(),
    });
  }

  public getPostById(postId: number): Observable<Post> {
    return this.httpClient.get<Post>(`${this.pathService}/${postId}`, {
      headers: this.getAuthHeaders(),
    });
  }

  // Récupère les commentaires d'un post
  public getCommentsByPostId(postId: number): Observable<PostComment[]> {
    return this.httpClient.get<PostComment[]>(
      `${this.pathService}/${postId}/comments`,
      { headers: this.getAuthHeaders() }
    );
  }

  // Ajoute un commentaire à un post
  public addComment(
    commentData: { content: string },
    postId: number
  ): Observable<PostComment> {
    return this.httpClient.post<PostComment>(
      `${this.pathService}/${postId}/comments`,
      commentData,
      { headers: this.getAuthHeaders() }
    );
  }
}
