import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostFavoriteComponent } from './most-favorite.component';

describe('MostFavoriteComponent', () => {
  let component: MostFavoriteComponent;
  let fixture: ComponentFixture<MostFavoriteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MostFavoriteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MostFavoriteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
