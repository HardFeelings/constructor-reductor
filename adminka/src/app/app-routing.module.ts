
import { RouterModule,Routes } from '@angular/router';

import { AdminkaComponent } from './adminka/adminka.component';
import { NgModule } from '@angular/core';
import { CommercialPageComponent } from './commercial/commercial-page/commercial-page.component';
import { LoginComponent } from './login/login/login.component';
import { AuthGuard } from './auth/auth.guard';


 const routes: Routes = [
    { path: 'admin', component: AdminkaComponent, canActivate: [AuthGuard]},
    { path: 'commercial', component: CommercialPageComponent, canActivate: [AuthGuard]},
    { path: '', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

