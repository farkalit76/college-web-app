import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Examination } from './examination';

describe('Examination', () => {
  let component: Examination;
  let fixture: ComponentFixture<Examination>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Examination]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Examination);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
