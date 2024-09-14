import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReductorCommComponent } from './reductorcomm.component';

describe('ReductorCommComponent', () => {
  let component: ReductorCommComponent;
  let fixture: ComponentFixture<ReductorCommComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReductorCommComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReductorCommComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
