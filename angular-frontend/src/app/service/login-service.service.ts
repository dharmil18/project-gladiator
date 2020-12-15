import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

    body: Object = {}
    constructor(private httpClient: HttpClient) {}

    login(userName: string, passwd: string) {
        const baseUrl: string = "http://localhost:2020/gladiator/login";
        this.body = {   
            'user_name': userName,
            'password': passwd
        }
        return this.httpClient.post(baseUrl, this.body);
    }

}