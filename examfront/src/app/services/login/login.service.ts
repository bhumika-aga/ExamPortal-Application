import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import baseUrl from '../helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubject = new Subject<boolean>();

  constructor(private http: HttpClient) { }

  //gets details of the currently logged in user
  public getCurrentUser() {
    return this.http.get(`${baseUrl}/current-user`);
  }

  public generateToken(loginData: any) {
    return this.http.post(`${baseUrl}/generate-token`, loginData);
  }

  //loginUser : set token in local storage
  public loginUser(token) {
    localStorage.setItem('token', token);
    return true;
  }

  //isLogin: whether the user is logged in
  public isLoggedIn() {
    let tokenString = localStorage.getItem('token');
    if (tokenString == undefined || tokenString == '' || tokenString == null) {
      return false;
    } else {
      return true;
    }
  }

  //logout : to remove the token from local storage
  public logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }

  //getToken: returns the token
  public getToken() {
    console.log('token');
    return localStorage.getItem('token');
  }

  //set userDetail
  public setUser(user) {
    localStorage.setItem('user', JSON.stringify(user));
  }

  //getUser
  public getUser() {
    let userString = localStorage.getItem('user');
    if (userString != null) {
      return JSON.parse(userString);
    } else {
      this.logout();
      return null;
    }
  }

  //getUserRole
  public getUserRole() {
    let user = this.getUser();
    return user.authorities[0].authority;
  }
}