
import { RouterModule,Routes } from '@angular/router';

import { AdminkaComponent } from './adminka/adminka.component';
import { NgModule } from '@angular/core';
import { CommercialPageComponent } from './commercial/commercial-page/commercial-page.component';


// add another rote with path "admin" and compontnt AdminkaComponent
 const routes: Routes = [
    { path: '', component: AdminkaComponent },
    { path: 'comm', component: CommercialPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

