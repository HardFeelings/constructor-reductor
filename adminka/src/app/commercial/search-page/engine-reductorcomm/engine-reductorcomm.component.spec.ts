import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EngineReductorCommComponent } from './engine-reductorcomm.component';

describe('EngineReductorCommComponent', () => {
  let component: EngineReductorCommComponent;
  let fixture: ComponentFixture<EngineReductorCommComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EngineReductorCommComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EngineReductorCommComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
