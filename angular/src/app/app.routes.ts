import { Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { AdminkaComponent } from './adminka/adminka.component';


// add another rote with path "admin" and compontnt AdminkaComponent
export const routes: Routes = [
    { path: '', component: MainComponent },
    { path: 'admin', component: AdminkaComponent }
];