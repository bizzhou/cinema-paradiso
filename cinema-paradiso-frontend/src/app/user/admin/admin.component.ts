import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    $(document).ready( function() {

      $('body').on("click", ".larg div h3", function(){
        if ($(this).children('span').hasClass('close')) {
          $(this).children('span').removeClass('close');
        }
        else {
          $(this).children('span').addClass('close');
        }
        $(this).parent().children('p').slideToggle(250);
      });

      $('body').on("click", "nav ul li a", function(){
        var title = $(this).data('title');
        $('.title').children('h2').html(title);
      });

    });
  }

}
