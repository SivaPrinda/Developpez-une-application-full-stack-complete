// This component displays a list of posts filtered by topics the user follows.
// It also provides sorting functionality based on post creation dates.
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Post } from 'src/app/core/models/Post';
import { Topic } from 'src/app/core/models/response/Topic';
import { PostService } from 'src/app/core/services/post.service';
import { TopicService } from 'src/app/core/services/topic.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss'],
})
export class PostComponent implements OnInit {
  // Array to hold posts fetched from the server
  posts: Post[] = [];
  // Names of topics the user is subscribed to
  followedTopics: string[] = [];
  // Flag to indicate if an error occurred during API requests
  onError = false;
  // Controls the sorting direction: true for ascending, false for descending
  isAscending: boolean = false;

  // Inject services for retrieving posts and user topics, and for triggering view updates
  constructor(
    private postService: PostService,
    private topicService: TopicService,
    private cdr: ChangeDetectorRef
  ) {}

  // Initialize component by fetching followed topics and corresponding posts
  ngOnInit(): void {
    this.fetchFollowedTopics(); 
    this.fetchPosts(); 
  }

  // Sort the posts array by creation date in ascending or descending order
  public sortPostsByDate(): void {
    this.posts.sort((a, b) => {
      const dateA = new Date(a.createdAt).getTime();
      const dateB = new Date(b.createdAt).getTime();
      return this.isAscending ? dateA - dateB : dateB - dateA;
    });
    this.isAscending = !this.isAscending;
    // Manually trigger change detection to reflect the new sort order in the view
    this.cdr.detectChanges();
  }

  // Fetch topics the user is subscribed to and update the followedTopics list
  private fetchFollowedTopics(): void {
    this.topicService.getUserTopics().subscribe({
      next: (topics: Topic[]) => {
        this.followedTopics = topics.map((topic) => topic.name); 
        // Once topics are fetched, retrieve the related posts
        this.fetchPosts(); 
      },
      error: (err) => {
        // Handle error state if fetching topics fails
        this.onError = true;
      },
    });
  }

  // Fetch all posts and filter them to include only those from followed topics
  private fetchPosts(): void {
    this.postService.getPosts().subscribe({
      next: (data: Post[]) => {
        this.posts = data.filter((post) =>
          this.followedTopics.includes(post.topic)
        ); 
      },
      error: (err) => {
        // Handle error state if fetching posts fails
        this.onError = true; 
      },
    });
  }
}
