import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { QuestionService } from 'src/app/services/question/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-quiz-question',
  templateUrl: './update-quiz-question.component.html',
  styleUrls: ['./update-quiz-question.component.css'],
})
export class UpdateQuizQuestionComponent implements OnInit {
  public Editor = ClassicEditor;
  questionId = 0;
  question;

  constructor(
    private _route: ActivatedRoute,
    private _question: QuestionService,
    private _router: Router
  ) { }
  ngOnInit(): void {
    this.questionId = this._route.snapshot.params.questionId;
    this._question.getQuestionsOfQuiz(this.question.quiz.qId).subscribe(
      (data: any) => {
        this.question = data;
        console.log(this.question);
      },
      (error) => {
        console.log(error);
        console.log(this.question);
      }
    );
  }

  public updateQuestion() {
    this._question.updateQuestion(this.question).subscribe(
      (data) => {
        Swal.fire('Success', 'Successfully Updated Question!', 'success').then(
          (e) => {
            this._router.navigate(['/admin/questions']);
          }
        );
      },
      (error) => {
        Swal.fire(
          'Error!',
          'Error while updating question! Please try again later!',
          'error'
        );
        console.log(error);
      }
    );
  }
}
