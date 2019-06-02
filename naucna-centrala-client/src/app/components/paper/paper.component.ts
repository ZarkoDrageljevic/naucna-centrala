import {Component, OnInit} from '@angular/core';
import {PaperService} from '../../services/paper.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-paper',
  templateUrl: './paper.component.html',
  styleUrls: ['./paper.component.css']
})
export class PaperComponent implements OnInit {

  constructor(private router: Router, private paperService: PaperService) {
  }

  ngOnInit() {
  }



}
