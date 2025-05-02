import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { SignInComponent } from './pages/sign-in/signIn.component';
import { SignUpComponent } from './pages/sign-up/signUp.component';
import { PostComponent } from './pages/post/post.component';
import { TopicComponent } from './pages/topic/topic.component';
import { CreatePostComponent } from './pages/post/create-post/createPost.component';
import { UserProfilComponent } from './pages/user-profil/userProfil.component';
import { ShowPostComponent } from './pages/post/show-post/showPost.component';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'signin', component: SignInComponent },
  { path: 'signup', component: SignUpComponent },
  { path: 'articles', component: PostComponent },
  { path: 'article/:id', component: ShowPostComponent },
  { path: 'themes', component: TopicComponent },
  { path: 'create-article', component: CreatePostComponent },
  { path: 'profil', component: UserProfilComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
