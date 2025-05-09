// PostService provides methods to interact with the post-related endpoints of the API.
// It handles creating posts, retrieving posts and comments, and adding comments to posts.
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Post } from '../models/Post';
import { PostRequest } from '../models/request/PostRequest';
import { PostComment } from '../models/PostComment';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  // Base URL for post-related API endpoints
  private pathService = `${environment.baseUrl}/posts`;

  // Inject HttpClient for making HTTP requests
  constructor(
    private httpClient: HttpClient,
    private tokenService: TokenService
  ) {}

  // Fetch all posts from the server
  public getPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(`${this.pathService}`, {
      headers: this.tokenService.getAuthHeaders(),
    });
  }

  // Send a request to create a new post with the given data
  public createPost(postRequest: PostRequest): Observable<Post> {
    return this.httpClient.post<Post>(`${this.pathService}`, postRequest, {
      headers: this.tokenService.getAuthHeaders(),
    });
  }

  // Retrieve a specific post by its ID
  public getPostById(postId: number): Observable<Post> {
    return this.httpClient.get<Post>(`${this.pathService}/${postId}`, {
      headers: this.tokenService.getAuthHeaders(),
    });
  }

  // Fetch all comments for a specific post by post ID
  public getCommentsByPostId(postId: number): Observable<PostComment[]> {
    return this.httpClient.get<PostComment[]>(
      `${this.pathService}/${postId}/comments`,
      { headers: this.tokenService.getAuthHeaders() }
    );
  }

  // Add a new comment to a specific post
  public addComment(
    commentData: { content: string },
    postId: number
  ): Observable<PostComment> {
    return this.httpClient.post<PostComment>(
      `${this.pathService}/${postId}/comments`,
      commentData,
      { headers: this.tokenService.getAuthHeaders() }
    );
  }
}
