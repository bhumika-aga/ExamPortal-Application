import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category/category.service';
import { QuizService } from 'src/app/services/quiz/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.css'],
})
export class UpdateQuizComponent implements OnInit {
  constructor(
    private _route: ActivatedRoute,
    private _quiz: QuizService,
    private _cat: CategoryService,
    private _router: Router
  ) { }

  qId = 0;
  quiz;
  categories;

  ngOnInit(): void {
    this.qId = this._route.snapshot.params.qId;
    this._quiz.getQuiz(this.qId).subscribe(
      (data: any) => {
        this.quiz = data;
        console.log(this.quiz);
      },
      (error) => {
        console.log(error);
      }
    );

    this._cat.categories().subscribe(
      (data: any) => {
        this.categories = data;
      },
      (error) => {
        alert('Error while loading Categories!');
      }
    );
  }

  public updateData() {
    this._quiz.updateQuiz(this.quiz).subscribe(
      (data) => {
        Swal.fire('Success', 'Quiz Successfully Updated!', 'success').then(
          (e) => {
            this._router.navigate(['/admin/quizzes']);
          }
        );
      },
      (error) => {
        Swal.fire(
          'Error',
          'Error while updating the quiz! Please try again later!',
          'error'
        );
        console.log(error);
      }
    );
  }
}
