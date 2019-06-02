import {Component, OnInit} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';
import {MagazineService} from '../../services/magazine.service';
import {Magazine} from '../../model/magazine';
import {FormField} from '../../model/form-field';
import {VariableValue} from '../../model/variable-value';

@Component({
  selector: 'app-magazine-list',
  templateUrl: './magazine-list.component.html',
  styleUrls: ['./magazine-list.component.css']
})
export class MagazineListComponent implements OnInit {

  magazine: Magazine;
  taskId: string;

  magazines = new Array<Magazine>();

  constructor(private magazineService: MagazineService, private toastService: ToastrService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.taskId = params['taskId'];
      this.getMagazines();
    });
  }

  getMagazines(): any {
    this.magazineService.getMagazines().subscribe(res => {
      this.magazines = res;
    });
  }

  choose() {
    const formfields = new Array<FormField>();
    formfields.push(this.createFormField('magazineId', this.magazine.id));

    this.magazineService.chooseMagazine(this.taskId, formfields).subscribe(res => {
      this.toastService.success('Magazine choosen');
      this.router.navigate(['task']);
    });
  }

  createFormField(key: string, value: any): FormField {
    const formField = new FormField();
    formField.name = key;
    formField.value = new VariableValue();
    formField.value.value = value;

    return formField;
  }

}
