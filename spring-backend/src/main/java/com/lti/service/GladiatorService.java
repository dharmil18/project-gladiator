package com.lti.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

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

public interface GladiatorService {
	
	public ResponseEntity<RegisterRestTemplate> saveRegistrationDetails(Registration registration);
	public List<Products> getAllProducts();
	public Products getProductById(int id);
	public String generate(String password);
	public boolean checkPassword(Login login);
	public ResponseEntity<LoginRestTemplate> checkUserLogin(Login login);
	public String getCardNumber();
	
	public ResponseEntity<BuyProductRestTemplate> buyProduct(BuyProduct buyProduct);
	public ResponseEntity<List<Object>> getAllUserTransactions(int user_id);
	public ResponseEntity<List<Installments>> getAllMonthlyPaidTransactions(int user_id);
	public ResponseEntity<List<Installments>> getAllUpcomingTransactions(int user_id);
	public ResponseEntity<Number> getBalanceCredit(int user_id);
	public ResponseEntity<Number> getNextMonthAmount(int user_id);
	public ResponseEntity<Cards> getCardDetails(int user_id);
	
	public ResponseEntity<GetOTPRestTemplate> sendOTPViaEmail(String email_id);
	
	public boolean verifyOTP(SaveOTP saveOTP);
	
	public ResponseEntity<ChangePasswordRestTemplate> changePassword(ChangePassword changePassword);
	
	//Admin APIs
	public List<UserDetailsWithName> getAllUserDetailsWithName();	

	public ResponseEntity<StandardRestTemplate> changeStatus(Status status);
		
	public List<Registration> getAllUsers(); 
	
	public ResponseEntity<StandardRestTemplate> editUser(Registration registration, String user_name);
	
	public ResponseEntity<StandardRestTemplate> deleteUser(DeleteUser deleteUser);
}
