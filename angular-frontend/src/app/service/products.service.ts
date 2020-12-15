import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  index: number;

  private getAllProducts: string = "http://localhost:2020/gladiator/getProducts";
  private getById: string = "http://localhost:2020/gladiator/getProduct/";
  private buyProd: string = "http://localhost:2020/gladiator/buyProduct/";
  private restUrl: string = "";

  constructor(private http: HttpClient) { }

  getProductById(id : string){
    this.restUrl = this.getById + id;
    console.log(this.restUrl);
    return this.http.get(this.restUrl);
  }

  getProducts(){
    return this.http.get(this.getAllProducts);
  }

  buyProduct(userId: string, productId: string, tenure: number) {
    const body: Object = {
      "user_id": userId,
      "product_id": productId,
      "tenure": tenure
    }
    console.log(userId, productId, tenure);
    return this.http.post<any>(this.buyProd, body);
  }
}
