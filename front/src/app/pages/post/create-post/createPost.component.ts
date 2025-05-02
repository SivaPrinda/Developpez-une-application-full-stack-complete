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
  form!: FormGroup;
  topics: Topic[] = [];
  onError = false;

  constructor(
    private fb: FormBuilder,
    private topicService: TopicService,
    private postService: PostService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      title: ['', [Validators.required]], // Champ pour le titre
      content: ['', [Validators.required]], // Champ pour le contenu
      topicId: ['', [Validators.required]], // Champ pour le thème sélectionné
    });

    this.fetchTopics(); // Récupère les topics au chargement
  }

  private fetchTopics(): void {
    this.topicService.getTopics().subscribe({
      next: (data: Topic[]) => {
        this.topics = data; // Stocke les topics récupérés
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des topics:', err);
        this.onError = true;
      },
    });
  }

  public submit(): void {
    if (this.form.invalid) return;

    const postRequest: PostRequest = this.form.value; // Récupère les données du formulaire
    this.postService.createPost(postRequest).subscribe({
      next: () => {
        this.router.navigate(['/articles']); // Redirige vers la liste des posts après succès
      },
      error: (error) => {
        console.error('Erreur lors de la création du post:', error);
        this.onError = true; // Affiche un message d'erreur si nécessaire
      },
    });
  }
}
