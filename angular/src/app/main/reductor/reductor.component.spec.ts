import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReductorComponent } from './reductor.component';

describe('ReductorComponent', () => {
  let component: ReductorComponent;
  let fixture: ComponentFixture<ReductorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReductorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReductorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
