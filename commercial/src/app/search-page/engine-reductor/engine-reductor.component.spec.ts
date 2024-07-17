import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EngineReductorComponent } from './engine-reductor.component';

describe('EngineReductorComponent', () => {
  let component: EngineReductorComponent;
  let fixture: ComponentFixture<EngineReductorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EngineReductorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EngineReductorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
