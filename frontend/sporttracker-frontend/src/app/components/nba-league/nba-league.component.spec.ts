import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NbaLeagueComponent } from './nba-league.component';

describe('NbaLeagueComponent', () => {
  let component: NbaLeagueComponent;
  let fixture: ComponentFixture<NbaLeagueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NbaLeagueComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NbaLeagueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
