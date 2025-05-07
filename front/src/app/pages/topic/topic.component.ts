// TopicComponent displays a list of all topics and allows users to subscribe to them.
// It fetches available topics and the user's current subscriptions on initialization.
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Topic } from 'src/app/core/models/response/Topic';
import { TopicService } from 'src/app/core/services/topic.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.scss'],
})
export class TopicComponent implements OnInit {
  // Placeholder for potential form usage (currently unused)
  form!: FormGroup;
  // Toggle for password visibility if form is added (currently unused)
  hide = true;
  // List of all topics fetched from the backend
  topics: Topic[] = [];
  // List of topic IDs the user is subscribed to
  followedTopics: number[] = [];
  // Flag to indicate error state during data fetching
  onError = false;
  // Unused flag for subscription state
  subscribed = false; 

  // Inject the TopicService to interact with topic-related API endpoints
  constructor(
    private topicService: TopicService,
  ) {}

  // On component initialization, fetch topics and user's subscriptions
  ngOnInit(): void {
    this.fetchTopics(); 
    this.fetchFollowedTopics(); 
  }

  // Fetch the list of all topics from the TopicService
  private fetchTopics(): void {
    this.topicService.getTopics().subscribe({
      next: (data: Topic[]) => {
        // Store the retrieved topics
        this.topics = data; 
      },
      error: (err) => {
        // Set error flag if the request fails
        console.error('Erreur lors de la récupération des topics:', err);
        this.onError = true; 
      },
    });
  }

  // Fetch the list of topics the user is subscribed to
  private fetchFollowedTopics(): void {
    this.topicService.getUserTopics().subscribe({
      next: (data: Topic[]) => {
        // Extract and store the IDs of followed topics
        this.followedTopics = data.map((topic) => topic.id); 
      },
      error: (err) => {
        // Log an error if the fetch fails
        console.error('Erreur lors de la récupération des topics suivis:', err);
      },
    });
  }

  // Subscribe the user to a topic if not already subscribed
  public toggleSubscription(topic: Topic): void {
    if (!this.followedTopics.includes(topic.id)) {
      this.topicService.subscribeToTopic(topic.id).subscribe({
        next: () => {
          // Add topic ID to followed list on success
          this.followedTopics.push(topic.id); 
        },
        error: (err) => {
          // Log an error if the subscription fails
          console.error("Erreur lors de l'abonnement au topic :", err);
        },
      });
    }
  }
}
