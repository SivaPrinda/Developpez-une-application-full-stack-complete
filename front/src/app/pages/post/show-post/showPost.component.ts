// This component displays the details of a single post and allows users to view and add comments.
// It initializes the post data, loads comments, and manages the comment form logic.

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Post } from 'src/app/core/models/Post';
import { PostService } from 'src/app/core/services/post.service';
import { PostComment } from 'src/app/core/models/PostComment';

@Component({
  selector: 'app-show-post',
  templateUrl: './showPost.component.html',
  styleUrls: ['./showPost.component.scss'],
})
export class ShowPostComponent implements OnInit {
  // Stores the current post data
  post!: Post; 
  // List of comments related to the post
  comments: PostComment[] = []; 
  // Reactive form group for comment input
  commentForm!: FormGroup; 
  // Flag to track if an error occurred during data retrieval or submission
  onError = false;

  // Inject required services: ActivatedRoute for route params, PostService for API calls, FormBuilder for reactive forms
  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private fb: FormBuilder
  ) {}

  // Initialize post and comment data when the component loads, and configure the comment form
  ngOnInit(): void {
    // Get post ID from the route parameters
    const postId = Number(this.route.snapshot.paramMap.get('id')); 
    if (postId) {
      this.fetchPost(postId); 
      this.fetchComments(postId); 
    }

    // Set up the comment form with validation
    this.commentForm = this.fb.group({
      content: ['', [Validators.required]],
    });
  }

  // Fetch the post data from the server using the post ID
  private fetchPost(postId: number): void {
    this.postService.getPostById(postId).subscribe({
      next: (data: Post) => {
        // Assign the retrieved post data
        this.post = data;
      },
      error: (err) => {
        // Show error state if the post couldn't be loaded
        this.onError = true;
      },
    });
  }

  // Fetch the list of comments for the specified post
  private fetchComments(postId: number): void {
    this.postService.getCommentsByPostId(postId).subscribe({
      next: (data: PostComment[]) => {
        // Store the retrieved comments in the component
        this.comments = data;
      },
      error: (err) => {
        // Show error state if comments couldn't be loaded
        this.onError = true;
      },
    });
  }

  // Submit a new comment for the current post using the comment form
  public addComment(postId: number): void {
    if (this.commentForm.invalid) return;

    // Retrieve the comment content from the form
    const content = this.commentForm.get('content')?.value;

    this.postService.addComment({ content }, postId).subscribe({
      next: (newComment: PostComment) => {
        // Add the new comment to the local comment list
        this.comments.push(newComment); 
        // Reset the form after successful submission
        this.commentForm.reset();

        // Reset individual form state (errors)
        this.commentForm.get('content')?.setErrors(null);
      },
      error: (err) => {
        // Show error state if the comment submission fails
        this.onError = true;
      },
    });
  }
}
