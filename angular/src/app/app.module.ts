import { BrowserModule } from "@angular/platform-browser";
import { AppComponent } from "./app.component";
import { AppRoutingModule } from "./app-routing.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from "@angular/core";
import { MainComponent } from "./main/main.component";
import { FooterComponent } from "./footer/footer.component";
import { HeaderComponent } from "./header/header.component";
import { EngineComponent } from "./main/engine/engine.component";
import { EngineReductorComponent } from "./main/engine-reductor/engine-reductor.component";
import { ReductorComponent } from "./main/reductor/reductor.component";
import {MatCheckboxModule} from '@angular/material/checkbox';
import { NgOptimizedImage } from "@angular/common";
import { EmailComponent } from "./main/email/email.component";
import { PaginatorModule } from 'primeng/paginator';
import { LoggerModule, NgxLoggerLevel } from 'ngx-logger';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    MainComponent,
    EngineComponent,
    EngineReductorComponent,
    ReductorComponent,
    EmailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatCheckboxModule,
    NgOptimizedImage,
    PaginatorModule,
    LoggerModule.forRoot({
      level: NgxLoggerLevel.LOG,
      disableConsoleLogging: false,
      serverLogLevel: NgxLoggerLevel.OFF,
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
