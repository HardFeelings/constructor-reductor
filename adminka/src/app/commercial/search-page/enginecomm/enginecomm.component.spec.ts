import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EngineCommComponent } from './enginecomm.component';

describe('EngineCommComponent', () => {
  let component: EngineCommComponent;
  let fixture: ComponentFixture<EngineCommComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EngineCommComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EngineCommComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
