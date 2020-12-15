package com.lti.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lti.model.Cards;
import com.lti.model.ChangePassword;
import com.lti.model.DeleteUser;
import com.lti.model.Installments;
import com.lti.model.Monthly_Transactions;
import com.lti.model.Products;
import com.lti.model.Registration;
import com.lti.model.SaveOTP;
import com.lti.model.Status;
import com.lti.model.Transactions;
import com.lti.model.UserDetailsWithName;
import com.lti.model.User_Details;
import com.lti.model.Users;
import com.lti.resttemplates.ChangePasswordRestTemplate;

public interface GladiatorDao {
	public void saveUser(Users user);
	public void saveUserDetails(User_Details user_details);
	public void saveCard(Cards card);
	public String getPassword(String user_name);
	List<Products> getAllProducts();
	Products getProductById(int id);
	public List<Users> getUserByUserName(String user_name);
	public List<Users> getUserByEmail(String email_id);
	public Users getSingleUserByUserName(String user_name);
	public String getCardNumber();
	
	public ResponseEntity<ChangePasswordRestTemplate> changePassword(ChangePassword changePassword);
	
	public void buyProduct(Transactions transaction);
	public int getProductCost(int product_id);
	
	public void saveMonthlyTransaction(Monthly_Transactions mTransaction);
	
	public ResponseEntity<List<Object>> getAllUserTransactions(int user_id);
	public ResponseEntity<Cards> getCardDetails(int user_id);
	public ResponseEntity<List<Installments>> getAllMonthlyPaidTransactions(int user_id);
	public ResponseEntity<List<Installments>> getAllUpcomingTransactions(int user_id);
	public ResponseEntity<Number> getBalanceCredit(int user_id);
	public ResponseEntity<Number> getNextMonthAmount(int user_id);
	
	public void saveOTP(SaveOTP saveOTP);
	
	public SaveOTP verifyOTP(SaveOTP saveOTP);
	public void deleteUserOTP(SaveOTP saveOTP);
	
	//Admin APIs
	public List<UserDetailsWithName> getAllUserDetailsWithName();
	
	public int changeStatus(Status status);
	
	public List<Registration> getAllUsers();
	
	public int editUser(Registration registration, String user_name);
	
	public int deleteUser(DeleteUser deleteUser);
	
	public int checkAccountNumberUniqueness(String acc_no, String user_name);
}
