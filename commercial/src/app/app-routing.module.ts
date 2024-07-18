
import { RouterModule,Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommercialPageComponent } from './commercial-page/commercial-page.component';
import { SearchPageComponent } from './search-page/search-page.component';


// add another rote with path "admin" and compontnt AdminkaComponent
 const routes: Routes = [
    { path: '', component: CommercialPageComponent },
    // { path: 'items', component: SearchPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

