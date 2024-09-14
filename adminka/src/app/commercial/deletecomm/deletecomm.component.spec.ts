import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletecommComponent } from './deletecomm.component';

describe('DeletecommComponent', () => {
  let component: DeletecommComponent;
  let fixture: ComponentFixture<DeletecommComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeletecommComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeletecommComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
