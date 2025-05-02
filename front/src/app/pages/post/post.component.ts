import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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
  posts: Post[] = []; // Liste des posts
  followedTopics: string[] = [];
  onError = false; // Indicateur d'erreur
  isAscending: boolean = false;

  constructor(
    private postService: PostService,
    private topicService: TopicService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.fetchFollowedTopics(); // Récupère les topics suivis par l'utilisateur
    this.fetchPosts(); // Récupère les posts au chargement du composant
  }

  public sortPostsByDate(): void {
    this.posts.sort((a, b) => {
      const dateA = new Date(a.createdAt).getTime();
      const dateB = new Date(b.createdAt).getTime();
      return this.isAscending ? dateA - dateB : dateB - dateA;
    });
    this.isAscending = !this.isAscending;
    this.cdr.detectChanges();
  }

  // Récupère les topics suivis par l'utilisateur
  private fetchFollowedTopics(): void {
    this.topicService.getUserTopics().subscribe({
      next: (topics: Topic[]) => {
        this.followedTopics = topics.map((topic) => topic.name); // Stocke les IDs des topics suivis
        this.fetchPosts(); // Récupère les posts après avoir récupéré les abonnements
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des abonnements :', err);
        this.onError = true;
      },
    });
  }

  private fetchPosts(): void {
    this.postService.getPosts().subscribe({
      next: (data: Post[]) => {
        this.posts = data.filter((post) =>
          this.followedTopics.includes(post.topic)
        ); // Stocke les posts récupérés
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des posts:', err);
        this.onError = true; // Affiche un message d'erreur si nécessaire
      },
    });
  }
}
