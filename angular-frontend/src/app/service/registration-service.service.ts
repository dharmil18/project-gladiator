import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Registration } from '../models/Registration';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

    body: Object = {}
    constructor(private httpClient: HttpClient) {}

    public addUser(adduser: Registration) {
        const baseUrl: string = "http://localhost:2020/gladiator/register";
        return this.httpClient.post<any>(baseUrl, adduser);
    }
}