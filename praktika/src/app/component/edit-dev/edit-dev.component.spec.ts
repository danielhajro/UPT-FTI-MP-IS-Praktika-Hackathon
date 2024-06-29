import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditDevComponent } from './edit-dev.component';

describe('EditDevComponent', () => {
  let component: EditDevComponent;
  let fixture: ComponentFixture<EditDevComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditDevComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditDevComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
