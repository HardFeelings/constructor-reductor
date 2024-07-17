import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { AppRoutingModule } from "./app-routing.module";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from "@angular/core";
import { FooterComponent } from "./footer/footer.component";
import { HeaderComponent } from "./header/header.component";
import {MatCheckboxModule} from '@angular/material/checkbox';
import { NgOptimizedImage } from "@angular/common";
import { SearchPageComponent } from "./search-page/search-page.component";
import { CommercialPageComponent } from "./commercial-page/commercial-page.component";
import { ReductorComponent } from "./search-page/reductor/reductor.component";
import { EngineComponent } from "./search-page/engine/engine.component";
import { EngineReductorComponent } from "./search-page/engine-reductor/engine-reductor.component";



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SearchPageComponent,
    CommercialPageComponent,
    ReductorComponent,
    EngineComponent,
    EngineReductorComponent,




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
