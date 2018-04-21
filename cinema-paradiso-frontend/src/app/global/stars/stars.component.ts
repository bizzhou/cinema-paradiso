import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-stars',
  templateUrl: './stars.component.html',
  styleUrls: ['./stars.component.scss']
})
export class StarsComponent {
  @Input()
  currentRate = 3.14;

  @Input()
  readonly = false;


  @Output()
  private hoveredNumberEmitter: EventEmitter<number> = new EventEmitter<number>();

  clickedNumber() {
    console.log(this.currentRate);
    this.hoveredNumberEmitter.emit(this.currentRate);
  }

}
