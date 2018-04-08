import {AfterViewInit, Component, OnChanges, OnInit, ViewChild} from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.scss']
})
export class MessageComponent implements AfterViewInit, OnInit{
  closeResult: string;

  constructor(private modalService: NgbModal) {
  }


  open(content) {
    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  ngAfterViewInit(): void {
  }

  ngOnInit(): void {
    this.open(@ViewChild('#content'));
  }

  // ngAfterViewInit(): void {
  //   this.open(this.content);
  // }

  // ngOnChanges(changes: any) {
  //   console.log('aosdfjoas');
  //   this.open(this.content);
  // }


}
