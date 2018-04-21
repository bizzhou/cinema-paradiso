import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-stars',
  templateUrl: './stars.component.html',
  styleUrls: ['./stars.component.scss']
})
export class StarsComponent {
  @Input()
  currentRate: number;

  @Input()
  readonly: boolean;

  @Output()
  eventEmitter: EventEmitter<number> = new EventEmitter();

  clickedNumber() {
    this.eventEmitter.emit(this.currentRate);
    console.log('event emitted');
  }

}
