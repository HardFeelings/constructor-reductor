import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { AppRoutingModule } from "./app-routing.module";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from "@angular/core";
import { AdminkaComponent } from "./adminka/adminka.component";
import {MatCheckboxModule} from '@angular/material/checkbox';
import { LoginComponent } from "./login/login/login.component";
import { NgOptimizedImage } from "@angular/common";
import { AddProductComponent } from "./adminka/add-product/add-product.component";
import { EngineComponent } from "./adminka/add-product/engine/engine.component";
import { EngineReductorComponent } from "./adminka/add-product/engine-reductor/engine-reductor.component";
import { ReductorComponent } from "./adminka/add-product/reductor/reductor.component";


@NgModule({
  declarations: [
    AppComponent,
    AdminkaComponent,
    LoginComponent,
    AddProductComponent,
    EngineComponent,
    EngineReductorComponent,
    ReductorComponent,



  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatCheckboxModule,
    NgOptimizedImage,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
