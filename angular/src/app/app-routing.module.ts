
import { RouterModule,Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { NgModule } from '@angular/core';


// add another rote with path "admin" and compontnt AdminkaComponent
 const routes: Routes = [
    { path: '', component: MainComponent },
    // { path: 'admin', component: AdminkaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

