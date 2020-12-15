package com.lti.controller;

import java.util.List;

import org.hibernate.id.IdentifierGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.model.BuyProduct;
import com.lti.model.Cards;
import com.lti.model.ChangePassword;
import com.lti.model.DeleteUser;
import com.lti.model.Installments;
import com.lti.model.Login;
import com.lti.model.Products;
import com.lti.model.Registration;
import com.lti.model.SaveOTP;
import com.lti.model.StandardRestTemplate;
import com.lti.model.Status;
import com.lti.model.UserDetailsWithName;
import com.lti.resttemplates.BuyProductRestTemplate;
import com.lti.resttemplates.ChangePasswordRestTemplate;
import com.lti.resttemplates.GetOTPRestTemplate;
import com.lti.resttemplates.LoginRestTemplate;
import com.lti.resttemplates.RegisterRestTemplate;
import com.lti.service.GladiatorService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GladiatorRestController {
	
	@Autowired
	private GladiatorService gladiatorService;

	public GladiatorService getServiceApp() {
		return gladiatorService;
	}

	public void setServiceApp(GladiatorService serviceApp) {
		this.gladiatorService = serviceApp;
	}
	
	@PostMapping("/register")
	public ResponseEntity<RegisterRestTemplate> saveUserDetails(@RequestBody Registration registration) {
		return gladiatorService.saveRegistrationDetails(registration);
	} 
	
	@PostMapping("/login")
	public ResponseEntity<LoginRestTemplate> Login(@RequestBody Login login) {
		return gladiatorService.checkUserLogin(login);
	}
	
	@GetMapping("/getProducts")
	private ResponseEntity<List<Products>> getProducts(){
		List<Products> prodList = gladiatorService.getAllProducts();
		
		return new ResponseEntity<List<Products>>(prodList, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/getProduct/{id}")
	private ResponseEntity<Products> getProductById(@PathVariable("id") int id){
		Products p = gladiatorService.getProductById(id);
		if(p == null) {
			throw new IdentifierGenerationException("Id does not exist, so we couldn't find the details.");
		}
		else {
			return new ResponseEntity<Products>(p,new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@PostMapping("/buyProduct")
	public ResponseEntity<BuyProductRestTemplate> buyProduct(@RequestBody BuyProduct bProduct) {
		return gladiatorService.buyProduct(bProduct);
	}
	
	@GetMapping("/getAllTransactions/{id}")
	public ResponseEntity<List<Object>> getAllTransactions(@PathVariable("id") int user_id) {
		return gladiatorService.getAllUserTransactions(user_id);
	}
	
//	@GetMapping("/getAllMonthlyTransactions/{id}")
//	public ResponseEntity<List<Monthly_Transactions>> getAllMonthlyTransactions(@PathVariable("id") int user_id) {
//		return gladiatorService.getAllMonthlyTransactions(user_id);
//	}
	
	@GetMapping("/getAllMonthlyPaidTransactions/{id}")
	public ResponseEntity<List<Installments>> getAllMonthlyPaidTransactions(@PathVariable("id") int user_id) {
		return gladiatorService.getAllMonthlyPaidTransactions(user_id);
	}
	
	@GetMapping("/getAllUpcomingTransactions/{id}")
	public ResponseEntity<List<Installments>> getAllUpcomingTransactions(@PathVariable("id") int user_id) {
		return gladiatorService.getAllUpcomingTransactions(user_id);
	}
	
	@GetMapping("/getBalanceCredit/{id}")
	public ResponseEntity<Number> getBalanceCredit(@PathVariable("id") int user_id) {
		return gladiatorService.getBalanceCredit(user_id);
	}
	
	@GetMapping("/getNextMonthAmount/{id}")
	public ResponseEntity<Number> getNextMonthAmount(@PathVariable("id") int user_id) {
		return gladiatorService.getNextMonthAmount(user_id);
	}
	
	@GetMapping("/getCardDetails/{id}")
	public ResponseEntity<Cards> getCardDetails(@PathVariable("id") int user_id) {
		return gladiatorService.getCardDetails(user_id);
	}
	
	@GetMapping("/getOTP/{email}")
	public ResponseEntity<GetOTPRestTemplate> getOTP(@PathVariable("email") String email) {
		return gladiatorService.sendOTPViaEmail(email);
	}
	
	@PostMapping("/verifyOTP")
	public boolean verifyOTP(@RequestBody SaveOTP saveOTP) {
		return gladiatorService.verifyOTP(saveOTP);
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<ChangePasswordRestTemplate> changePassword(@RequestBody ChangePassword changePassword) {
		return gladiatorService.changePassword(changePassword);
	}
	
	//Admin APIs
	//FOR ADMIN - TO ACTIVATE/REJECT A CARD
	// Get All Users' Data - with name (WITH STATUS = PENDING)
//	@GetMapping("/GetAllUsersWithName")
//	private ResponseEntity<List<UserDetailsWithName>> getAllUserDetailsWithName() {
//		List<UserDetailsWithName> userList = gladiatorService.getAllUserDetailsWithName();
//		return new ResponseEntity<List<UserDetailsWithName>>(userList, new HttpHeaders(), HttpStatus.OK);
//	}
//	
	//FOR ADMIN 
	// Change card status of a particular user from pending to activated/rejected
//	@PutMapping("/changeStatus")
//	private ResponseEntity<String> changeStatus(@RequestBody Status status) {
//		return gladiatorService.changeStatus(status);
//				
//	}
	
	//FOR ADMIN - TO DISPLAY ALL RECORDS TO EDIT/DELETE USER
	//Get all User Details
//	@GetMapping("/GetAllUsers")
//	private ResponseEntity<List<Registration>> getAllUsers() {
//		List<Registration> allUsers = gladiatorService.getAllUsers();
//		return new ResponseEntity<List<Registration>>(allUsers, new HttpHeaders(), HttpStatus.OK);
//	}
//	
//	//FOR ADMIN
//	//Edit User Details
//	@PutMapping("/editUser/{user_name}")
//	private ResponseEntity<String> editUser(@RequestBody Registration registration, @PathVariable("user_name") String user_name) {
//		return gladiatorService.editUser(registration, user_name);
//				
//	}
//	
//	//FOR ADMIN
//	//Delete User Details
//	@PutMapping("/deleteUser")
//	private ResponseEntity<String> deleteUser(@RequestBody DeleteUser deleteUser) {
//		return gladiatorService.deleteUser(deleteUser);
//					
//	}
	@GetMapping("/GetAllUsersWithName")
	private ResponseEntity<List<UserDetailsWithName>> getAllUserDetailsWithName() {
		List<UserDetailsWithName> userList = gladiatorService.getAllUserDetailsWithName();
		return new ResponseEntity<List<UserDetailsWithName>>(userList, new HttpHeaders(), HttpStatus.OK);
	}
	
	//FOR ADMIN 
	// Change card status of a particular user from pending to activated/rejected
	@PutMapping("/changeStatus")
	private ResponseEntity<StandardRestTemplate> changeStatus(@RequestBody Status status) {
		return gladiatorService.changeStatus(status);
				
	}
	
	//FOR ADMIN - TO DISPLAY ALL RECORDS TO EDIT/DELETE USER
	//Get all User Details
	@GetMapping("/GetAllUsers")
	private ResponseEntity<List<Registration>> getAllUsers() {
		List<Registration> allUsers = gladiatorService.getAllUsers();
		return new ResponseEntity<List<Registration>>(allUsers, new HttpHeaders(), HttpStatus.OK);
	}
	
	//FOR ADMIN
	//Edit User Details
	@PutMapping("/editUser/{user_name}")
	private ResponseEntity<StandardRestTemplate> editUser(@RequestBody Registration registration,@PathVariable("user_name") String user_name) {
		System.out.println(registration);
		System.out.println(user_name);
		return gladiatorService.editUser(registration,user_name);
				
	}
	
	//FOR ADMIN
	//Delete User Details
	@PutMapping("/deleteUser")
	private ResponseEntity<StandardRestTemplate> deleteUser(@RequestBody DeleteUser deleteUser) {
		return gladiatorService.deleteUser(deleteUser);
					
	}
}
