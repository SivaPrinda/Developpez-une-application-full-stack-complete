import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Topic } from 'src/app/core/models/response/Topic';
import { AuthService } from 'src/app/core/services/auth.service';
import { TopicService } from 'src/app/core/services/topic.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.scss'],
})
export class TopicComponent implements OnInit {
  form!: FormGroup;
  hide = true;
  topics: Topic[] = [];
  followedTopics: number[] = [];
  onError = false;
  subscribed = false; // État d'abonnement au topic

  constructor(
    private topicService: TopicService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.fetchTopics(); // Récupère les topics au chargement du composant
    this.fetchFollowedTopics(); // Récupère les topics suivis par l'utilisateur
  }

  private fetchTopics(): void {
    this.topicService.getTopics().subscribe({
      next: (data: Topic[]) => {
        this.topics = data; // Stocke les topics récupérés
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des topics:', err);
        this.onError = true; // Affiche un message d'erreur si nécessaire
      },
    });
  }

  private fetchFollowedTopics(): void {
    this.topicService.getUserTopics().subscribe({
      next: (data: Topic[]) => {
        this.followedTopics = data.map((topic) => topic.id); // Stocke les IDs des topics suivis
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des topics suivis:', err);
      },
    });
  }

  // Méthode pour s'abonner à un topic
  public toggleSubscription(topic: Topic): void {
    if (!this.followedTopics.includes(topic.id)) {
      this.topicService.subscribeToTopic(topic.id).subscribe({
        next: () => {
          this.followedTopics.push(topic.id); // Met à jour l'état local du topic
        },
        error: (err) => {
          console.error("Erreur lors de l'abonnement au topic :", err);
        },
      });
    }
  }
}
