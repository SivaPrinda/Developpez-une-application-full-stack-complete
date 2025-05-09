// UserProfilComponent allows users to view and edit their profile, as well as manage topic subscriptions.
// It retrieves the user's information and subscribed topics on initialization, and provides methods to update or unsubscribe.
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/core/services/user.service';
import { Topic } from 'src/app/core/models/response/Topic';
import { AuthService } from 'src/app/core/services/auth.service';
import { TopicService } from 'src/app/core/services/topic.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './userProfil.component.html',
  styleUrls: ['./userProfil.component.scss'],
})
export class UserProfilComponent implements OnInit {
  // Reactive form group for the user profile fields
  form!: FormGroup;
  // Controls password visibility toggle
  hide = true;
  // List of topics the user is currently subscribed to
  followedTopics: Topic[] = []; 
  // Flag to track error states during API operations
  onError = false;

  // Inject services needed for form handling, user data, authentication, and topic management
  constructor(
    private fb: FormBuilder,
    private topicService: TopicService,
    private userService: UserService,
    private authService: AuthService
  ) {}

  // Initialize the form and fetch profile and followed topics data
  ngOnInit(): void {
   
    this.form = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: [''], 
    });

    this.fetchUserProfile(); 
    this.fetchFollowedTopics(); 
  }

  
  // Retrieve the user's profile information and pre-fill the form
  private fetchUserProfile(): void {
    this.authService.getUserProfile().subscribe({
      next: (user) => {
        // Populate form fields with retrieved user data
        this.form.patchValue({
          name: user.name,
          email: user.email,
        });
      },
      error: (err) => {
        console.error(
          'Erreur lors de la récupération du profil utilisateur:',
          err
        );
        // Show error if profile fetch fails
        this.onError = true;
      },
    });
  }

 
  // Fetch the list of topics the user is subscribed to
  private fetchFollowedTopics(): void {
    this.topicService.getUserTopics().subscribe({
      next: (topics) => {
        // Store retrieved topics in local list
        this.followedTopics = topics;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des abonnements:', err);
        // Set error flag if topic fetch fails
        this.onError = true;
      },
    });
  }

  
  // Submit the updated profile information to the backend
  public saveProfile(): void {
    // Abort if form is invalid
    if (this.form.invalid) return;

    this.userService.updateUserProfile(this.form.value).subscribe({
      next: () => {
        // Log success message upon successful update
        console.log('Profil mis à jour avec succès');
      },
      error: (err) => {
        console.error('Erreur lors de la mise à jour du profil:', err);
        // Handle error during profile update
        this.onError = true;
      },
    });
  }

  
  // Unsubscribe the user from a specific topic and update the list
  public unsubscribe(topicId: number): void {
    this.topicService.unsubscribeFromTopic(topicId).subscribe({
      next: () => {
        // Remove topic from the local list after successful unsubscribe
        this.followedTopics = this.followedTopics.filter(
          (topic) => topic.id !== topicId
        );
        console.log('Désabonné avec succès');
      },
      error: (err) => {
        // Log error and set error flag if unsubscribe fails
        console.error('Erreur lors du désabonnement:', err);
        this.onError = true;
      },
    });
  }
}
