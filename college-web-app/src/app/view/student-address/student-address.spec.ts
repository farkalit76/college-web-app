import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentAddress } from './student-address';

describe('StudentAddress', () => {
  let component: StudentAddress;
  let fixture: ComponentFixture<StudentAddress>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudentAddress]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudentAddress);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
