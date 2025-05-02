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
  form!: FormGroup;
  hide = true;
  followedTopics: Topic[] = []; // Liste des abonnements de l'utilisateur
  onError = false;

  constructor(
    private fb: FormBuilder,
    private topicService: TopicService,
    private userService: UserService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    // Initialisation du formulaire
    this.form = this.fb.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: [''], // Le mot de passe est optionnel pour la mise à jour
    });

    this.fetchUserProfile(); // Récupère les informations utilisateur
    this.fetchFollowedTopics(); // Récupère les abonnements
  }

  // Récupère les informations utilisateur
  private fetchUserProfile(): void {
    this.authService.getUserProfile().subscribe({
      next: (user) => {
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
        this.onError = true;
      },
    });
  }

  // Récupère les abonnements de l'utilisateur
  private fetchFollowedTopics(): void {
    this.topicService.getUserTopics().subscribe({
      next: (topics) => {
        this.followedTopics = topics;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des abonnements:', err);
        this.onError = true;
      },
    });
  }

  // Sauvegarde les modifications du profil utilisateur
  public saveProfile(): void {
    if (this.form.invalid) return;

    this.userService.updateUserProfile(this.form.value).subscribe({
      next: () => {
        console.log('Profil mis à jour avec succès');
      },
      error: (err) => {
        console.error('Erreur lors de la mise à jour du profil:', err);
        this.onError = true;
      },
    });
  }

  // Se désabonner d'un topic
  public unsubscribe(topicId: number): void {
    this.topicService.unsubscribeFromTopic(topicId).subscribe({
      next: () => {
        this.followedTopics = this.followedTopics.filter(
          (topic) => topic.id !== topicId
        );
        console.log('Désabonné avec succès');
      },
      error: (err) => {
        console.error('Erreur lors du désabonnement:', err);
        this.onError = true;
      },
    });
  }
}
