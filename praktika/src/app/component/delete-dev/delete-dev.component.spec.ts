import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteDevComponent } from './delete-dev.component';

describe('DeleteDevComponent', () => {
  let component: DeleteDevComponent;
  let fixture: ComponentFixture<DeleteDevComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteDevComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeleteDevComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
