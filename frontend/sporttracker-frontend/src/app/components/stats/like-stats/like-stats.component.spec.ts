import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LikeStatsComponent } from './like-stats.component';

describe('LikeStatsComponent', () => {
  let component: LikeStatsComponent;
  let fixture: ComponentFixture<LikeStatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LikeStatsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LikeStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
