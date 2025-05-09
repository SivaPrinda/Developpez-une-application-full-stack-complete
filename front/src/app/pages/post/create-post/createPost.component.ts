// This component provides a form for users to create a new post.
// It initializes form controls, fetches available topics, and submits the post data.
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Topic } from 'src/app/core/models/response/Topic';
import { TopicService } from 'src/app/core/services/topic.service';
import { PostService } from 'src/app/core/services/post.service';
import { PostRequest } from 'src/app/core/models/request/PostRequest';

@Component({
  selector: 'app-create-post',
  templateUrl: './createPost.component.html',
  styleUrls: ['./createPost.component.scss'],
})
export class CreatePostComponent implements OnInit {
  // Reactive form group for post creation inputs
  form!: FormGroup;
  // List of available topics fetched from the API
  topics: Topic[] = [];
  // Flag to display error message if API calls fail
  onError = false;

  // Inject services for form building, topic fetching, post creation, and navigation
  constructor(
    private fb: FormBuilder,
    private topicService: TopicService,
    private postService: PostService,
    private router: Router
  ) {}

  // Initialize the form and fetch topics when the component is loaded
  ngOnInit(): void {
    // Define form fields and validation rules
    this.form = this.fb.group({
      title: ['', [Validators.required]], 
      content: ['', [Validators.required]], 
      topicId: ['', [Validators.required]], 
    });

    this.fetchTopics(); 
  }

  // Fetch available topics from the TopicService
  private fetchTopics(): void {
    this.topicService.getTopics().subscribe({
      next: (data: Topic[]) => {
        // Store the fetched topics in the component
        this.topics = data; 
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des topics:', err);
        // Show error state if the request fails
        this.onError = true;
      },
    });
  }

  // Handle form submission and send the post data to the server
  public submit(): void {
    if (this.form.invalid) return;

    const postRequest: PostRequest = this.form.value; 
    this.postService.createPost(postRequest).subscribe({
      next: () => {
        // Redirect to article list on successful creation
        this.router.navigate(['/articles']); 
      },
      error: (error) => {
        console.error('Erreur lors de la création du post:', error);
        // Show error state if post creation fails
        this.onError = true; 
      },
    });
  }
}
