import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewdevComponent } from './newdev.component';

describe('NewdevComponent', () => {
  let component: NewdevComponent;
  let fixture: ComponentFixture<NewdevComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewdevComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewdevComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
