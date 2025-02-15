import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from 'src/app/services/quiz/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css'],
})
export class InstructionsComponent implements OnInit {
  qId;
  quiz;

  constructor(
    private _route: ActivatedRoute,
    private _quiz: QuizService,
    private _router: Router
  ) { }

  ngOnInit(): void {
    this.qId = this._route.snapshot.params.qId;
    this._quiz.getQuiz(this.qId).subscribe(
      (data: any) => {
        this.quiz = data;
      },
      (error) => {
        console.log(error);
        alert('Error while loading quiz data');
      }
    );
  }

  startQuiz() {
    Swal.fire({
      title: 'Are you sure you want to start the quiz?',
      showCancelButton: true,
      confirmButtonText: `Start`,
      denyButtonText: `Don't Save`,
      icon: 'warning',
    }).then((result) => {
      if (result.isConfirmed) {
        this._router.navigate(['/start/' + this.qId]);
      } else if (result.isDenied) {
        Swal.fire('Changes are not saved', '', 'warning');
      }
    });
  }
}
