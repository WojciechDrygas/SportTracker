import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NbaStandingComponent } from './nba-standing.component';

describe('NbaStandingComponent', () => {
  let component: NbaStandingComponent;
  let fixture: ComponentFixture<NbaStandingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NbaStandingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NbaStandingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
