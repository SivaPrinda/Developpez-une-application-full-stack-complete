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
  post!: Post; // Détails du post
  comments: PostComment[] = []; // Liste des commentaires
  commentForm!: FormGroup; // Formulaire pour ajouter un commentaire
  onError = false;

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    const postId = Number(this.route.snapshot.paramMap.get('id')); // Récupère l'ID du post depuis l'URL
    if (postId) {
      this.fetchPost(postId); // Récupère les détails du post
      this.fetchComments(postId); // Récupère les commentaires associés
    }

    // Initialisation du formulaire pour les commentaires
    this.commentForm = this.fb.group({
      content: ['', [Validators.required]],
    });
  }

  // Récupère les détails du post
  private fetchPost(postId: number): void {
    this.postService.getPostById(postId).subscribe({
      next: (data: Post) => {
        this.post = data;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération du post :', err);
        this.onError = true;
      },
    });
  }

  // Récupère les commentaires associés au post
  private fetchComments(postId: number): void {
    this.postService.getCommentsByPostId(postId).subscribe({
      next: (data: PostComment[]) => {
        this.comments = data;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des commentaires :', err);
        this.onError = true;
      },
    });
  }

  // Ajoute un commentaire
  public addComment(postId: number): void {
    if (this.commentForm.invalid) return;

    const content = this.commentForm.get('content')?.value;

    this.postService.addComment({ content }, postId).subscribe({
      next: (newComment: PostComment) => {
        this.comments.push(newComment); // Ajoute le nouveau commentaire à la liste
        this.commentForm.reset(); // Réinitialise le formulaire

        this.commentForm.get('content')?.markAsPristine();
        this.commentForm.get('content')?.markAsUntouched();
        this.commentForm.get('content')?.setErrors(null);
      },
      error: (err) => {
        console.error("Erreur lors de l'ajout du commentaire :", err);
        this.onError = true;
      },
    });
  }
}
