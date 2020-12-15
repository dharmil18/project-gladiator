import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ConfirmEqualValidatorDirective } from 'src/app/custom-validators/confirm-equal-validator.directive';
import { Registration } from 'src/app/models/Registration';
import { RegistrationService } from 'src/app/service/registration-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registrationForm : FormGroup;
  cityName: any;
  state: any;
  registerMessage: any;

  constructor(private fb:FormBuilder,private registerService: RegistrationService ,private router: Router) {
    
  }
  
  //only number will be add
  keyPress(event: any) {
    const pattern = /[0-9\+\-\ ]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (event.keyCode != 8 && !pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  ngOnInit()//: void
  {
    this.registrationForm = this.fb.group({
      first_name: ['', [Validators.required,Validators.minLength(2), Validators.maxLength(10)]],
      last_name: ['',[Validators.required, Validators.minLength(2), Validators.maxLength(10)]],
      email_id: ['', [Validators.required,Validators.email]],
      phone_number: ['', [ Validators.required, Validators.pattern("^[0-9]*$"), Validators.minLength(10), Validators.maxLength(10)]],
      user_name: ['',  Validators.required],
      password_hash:['',[Validators.required, Validators.minLength(8)]],
      confirmPassword:['', Validators.required,ConfirmEqualValidatorDirective],
      address_line1: ['', Validators.required],
      address_line2: ['', Validators.required],
      city: ['', Validators.required],
      
      zipcode:['',[Validators.required, Validators.minLength(6), Validators.maxLength(6)]],
      bank_name: ['', Validators.required],
      account_no: ['', Validators.required],
      ifsc_code: ['', Validators.required],
    
      state:['', Validators.required],
      card_type:['Gold',Validators.required] 
    })
  }

  State:any=['Andhra Pradesh','Arunachal Pradesh','Assam','Bihar','Chhattisgarh','Goa','Gujarat','Haryana','Himachal Pradesh','Jammu and Kashmir','	Jharkhand','Karnataka','Kerala',
            'Madhya Pradesh','Maharashtra','Manipur','Meghalaya','Mizoram','Nagaland','	Odisha','	Punjab','Rajasthan',
              '	Sikkim','	Tamil Nadu','	Telangana','Tripura','Uttar Pradesh','Uttarakhand','West Bengal']
  
   // Choose state using select dropdown
  changeState(e) {
    this.state.setValue(e.target.value, {
      onlySelf: true
    })
  }

  onReset():any
  {
    this.registrationForm.reset();
  }

  
    onSubmit(adduser:Registration):any//:void
    {
      console.log(this.registrationForm.value);
      console.log(adduser);
      this.registerService.addUser(adduser).subscribe(
        data => {
          this.registerMessage=data;
          console.log(this.registerMessage);
          if (this.registerMessage.status == 'Failed')
            alert(this.registerMessage.message);
          else if (this.registerMessage.status == 'Success') {
            alert(this.registerMessage.message + "Registered");
            this.router.navigate(['login']);
          }
        }
      );
    }
}
