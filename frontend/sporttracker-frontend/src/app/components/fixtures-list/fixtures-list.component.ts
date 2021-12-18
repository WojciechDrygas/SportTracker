import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { Fixture } from 'src/app/models/sport-data-models/fixtures.model';

@Component({
  selector: 'app-fixtures-list',
  templateUrl: './fixtures-list.component.html',
  styleUrls: ['./fixtures-list.component.css']
})
export class FixturesListComponent implements OnInit, OnChanges {

  constructor() { }
  @Input()
  nextFixtures:Fixture[] = [];
  @Input()
  lastFixtures:Fixture[] = [];
  showLast:boolean[]=[];
  showNext:boolean[]=[];

  ngOnChanges():void{
    this.showLast=Array(this.lastFixtures.length).fill(false);
    this.showNext=Array(this.nextFixtures.length).fill(false);
  }

  ngOnInit(): void {

  }
  onShow(i:number, isLastFixtures:boolean){
    if (isLastFixtures){
      this.showLast[i]=!this.showLast[i];
    }else{
      this.showNext[i]=!this.showNext[i];
    }
  }
}
