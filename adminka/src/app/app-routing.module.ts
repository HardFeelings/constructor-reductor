
import { RouterModule,Routes } from '@angular/router';

import { AdminkaComponent } from './adminka/adminka.component';
import { NgModule } from '@angular/core';


// add another rote with path "admin" and compontnt AdminkaComponent
 const routes: Routes = [
    { path: '', component: AdminkaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

