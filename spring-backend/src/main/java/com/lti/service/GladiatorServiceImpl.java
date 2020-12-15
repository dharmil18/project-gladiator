package com.lti.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lti.dao.GladiatorDao;
import com.lti.model.BuyProduct;
import com.lti.model.Cards;
import com.lti.model.ChangePassword;
import com.lti.model.DeleteUser;
import com.lti.model.Installments;
import com.lti.model.Login;
import com.lti.model.LoginUserData;
import com.lti.model.Monthly_Transactions;
import com.lti.model.Products;
import com.lti.model.Registration;
import com.lti.model.SaveOTP;
import com.lti.model.StandardRestTemplate;
import com.lti.model.Status;
import com.lti.model.Transactions;
import com.lti.model.UserDetailsWithName;
import com.lti.model.User_Details;
import com.lti.model.Users;
import com.lti.resttemplates.BuyProductRestTemplate;
import com.lti.resttemplates.ChangePasswordRestTemplate;
import com.lti.resttemplates.GetOTPRestTemplate;
import com.lti.resttemplates.LoginRestTemplate;
import com.lti.resttemplates.RegisterRestTemplate;

@Service
@Transactional
public class GladiatorServiceImpl implements GladiatorService {

	@Autowired
	private GladiatorDao gladiatorDao;
	
	@Autowired
	private EmailService emailService;

	public GladiatorDao getDao() {
		return gladiatorDao;
	}

	public void setDao(GladiatorDao dao) {
		this.gladiatorDao = dao;
	}

	//Registration API
	@Override
	public ResponseEntity<RegisterRestTemplate> saveRegistrationDetails(Registration registration) {
		String userName = registration.getUser_name();
		String emailId = registration.getEmail_id();
	
		RegisterRestTemplate registerRestTemplate;
		if (gladiatorDao.getUserByUserName(userName).size() > 0) {
			registerRestTemplate = new RegisterRestTemplate("Failed", "Username must be unique", HttpStatus.NOT_ACCEPTABLE);
			return new ResponseEntity<RegisterRestTemplate>(registerRestTemplate, HttpStatus.OK);
		}
		if (gladiatorDao.getUserByEmail(emailId).size() > 0) {
			registerRestTemplate = new RegisterRestTemplate("Failed", "Email must be unique", HttpStatus.NOT_ACCEPTABLE);
			return new ResponseEntity<RegisterRestTemplate>(registerRestTemplate, HttpStatus.OK);
		}
		
		System.out.println("Username & Email is unique so inserting rows");
		Users user = new Users();
		user.setFirst_name(registration.getFirst_name());
		user.setLast_name(registration.getLast_name());
		user.setEmail_id(registration.getEmail_id());
		user.setPhone_number(registration.getPhone_number());
		user.setUser_name(registration.getUser_name());
		user.setPassword_hash(generate(registration.getPassword_hash()));
		user.setRole("User");
		user.setUser_status("Active");

		gladiatorDao.saveUser(user);

		User_Details user_Details = new User_Details();
		user_Details.setUser_id(user.getUser_id());
		LocalDate joined_date = LocalDate.now();
		user_Details.setJoined_date(joined_date);
		user_Details.setBank_name(registration.getBank_name());
		user_Details.setAccount_no(registration.getAccount_no());
		user_Details.setIfsc_code(registration.getIfsc_code());
		user_Details.setAddress_line1(registration.getAddress_line1());
		user_Details.setAddress_line2(registration.getAddress_line2());
		user_Details.setCity(registration.getCity());
		user_Details.setState(registration.getState());
		user_Details.setZipcode(registration.getZipcode());

		gladiatorDao.saveUserDetails(user_Details);

		Cards card = new Cards();
		card.setUser_id(user.getUser_id());
		card.setCard_no(gladiatorDao.getCardNumber());
		card.setCard_type(registration.getCard_type());
		card.setExpiry_date(joined_date.plusYears(2));
		if (registration.getCard_type().equals("Gold")) {
			card.setJoining_fee(500);
			card.setLimit(50000);
		}
		else {
			card.setJoining_fee(800);
			card.setLimit(80000);
		}
		card.setStatus("Pending");
		gladiatorDao.saveCard(card);
		registerRestTemplate = new RegisterRestTemplate("Success", "Success", HttpStatus.OK);
		return new ResponseEntity<RegisterRestTemplate>(registerRestTemplate, HttpStatus.OK);
	}
	
	//Get Product By ID API
	@Override
	public Products getProductById(int id) {
		return gladiatorDao.getProductById(id);
	}
	
	//Get All products API
	@Override
	public List<Products> getAllProducts(){
		return gladiatorDao.getAllProducts();
	}
	
	//Method to generated hash value of the password
	@Override
	public String generate(String password) {
		StringBuilder pass = new StringBuilder();
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-256");
			byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
			for (byte b : hashedPassword)
				pass.append(String.format("%02x", b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pass.toString();
	}

	//Method to check whether newly generated hash value is equal to hashed password 
	//stored in DB. Used with Login API
	@Override
	public boolean checkPassword(Login login){
		
		String password = login.getPassword();
		String dbPassword = gladiatorDao.getPassword(login.getUser_name());
		StringBuilder passNew = new StringBuilder();
		String newPassword = null;
		
		try {
			MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");

            byte[] hashedPasswordNew = md.digest(password.getBytes(StandardCharsets.UTF_8));
            
            for (byte b : hashedPasswordNew)
                passNew.append(String.format("%02x", b));
            
            newPassword = passNew.toString();
		}            
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if (dbPassword.equals(newPassword))
			return true;
		else 
			return false;
	}

	//Login API to check whether user is valid or not
	@Override
	public ResponseEntity<LoginRestTemplate> checkUserLogin(Login login) {
		Users user = gladiatorDao.getSingleUserByUserName(login.getUser_name());
		LoginRestTemplate restTemplate;
		if (user == null) {
			restTemplate = new LoginRestTemplate("Failed", "No User Found", HttpStatus.NOT_FOUND, null);
			return new ResponseEntity<LoginRestTemplate>(restTemplate, HttpStatus.NOT_FOUND);
		}
		else if (user.getUser_status().toLowerCase().equals("deleted")) {
			restTemplate = new LoginRestTemplate("Failed", "No User Found", HttpStatus.NOT_FOUND, null);
			return new ResponseEntity<LoginRestTemplate>(restTemplate, HttpStatus.NOT_FOUND);
		}
		else {
			if(checkPassword(login)) {
				LoginUserData loginUserData = new LoginUserData(user.getUser_id(), user.getFirst_name(), user.getLast_name(), user.getUser_name(), user.getRole());
				restTemplate = new LoginRestTemplate("Success", "Login Successfull", HttpStatus.OK, loginUserData);
				return new ResponseEntity<LoginRestTemplate >(restTemplate, HttpStatus.OK);
			}
		}
		restTemplate = new LoginRestTemplate("Failed", "Enter Valid Credentials", HttpStatus.NOT_FOUND, null);
		return new ResponseEntity<LoginRestTemplate >(restTemplate, HttpStatus.OK);
	}

	//Gets new Card number for new registration
	@Override
	public String getCardNumber() {
		return gladiatorDao.getCardNumber();
	}

	//API when a product is purchased.
	@Override
	public ResponseEntity<BuyProductRestTemplate> buyProduct(BuyProduct bProduct) {
		BuyProductRestTemplate buyProductRestTemplate;
		try {
			Transactions transaction = new Transactions();
			transaction.setUser_id(bProduct.getUser_id());
			transaction.setProduct_id(bProduct.getProduct_id());
			transaction.setTenure(bProduct.getTenure());
			transaction.setStatus("Pending");
			transaction.setTransaction_date(LocalDate.now());
			int cost = gladiatorDao.getProductCost(bProduct.getProduct_id());
			transaction.setMonthly_installment(cost / bProduct.getTenure());
			int tenure = bProduct.getTenure();
			if (tenure == 3) {
				transaction.setProcessing_fee(cost * 0.01);
			}
			else if (tenure == 6) {
				transaction.setProcessing_fee(cost * 0.02);
			}
			else if (tenure == 9) {
				transaction.setProcessing_fee(cost * 0.03);
			}
			else {
				transaction.setProcessing_fee(cost * 0.04);
			}
			
			gladiatorDao.buyProduct(transaction);
			
			LocalDate mDate = LocalDate.now();
			for (int i = 0; i < tenure; i++) {
				Monthly_Transactions mTransaction = new Monthly_Transactions();
				mTransaction.setTransaction_id(transaction.getTransaction_id());
				mTransaction.setM_status("Pending");
				if (i == 0)
					mTransaction.setAmount(transaction.getMonthly_installment() + transaction.getProcessing_fee());
				else
					mTransaction.setAmount(transaction.getMonthly_installment());
				mTransaction.setMtxn_date(mDate.plusMonths(i + 1));
				gladiatorDao.saveMonthlyTransaction(mTransaction);
			}
			buyProductRestTemplate = new BuyProductRestTemplate("Success", "Order Placed", HttpStatus.OK);
			return new ResponseEntity<BuyProductRestTemplate>(buyProductRestTemplate, HttpStatus.OK);
		}
		catch (Exception e) {
			buyProductRestTemplate = new BuyProductRestTemplate("Failed", "Failed to place order", HttpStatus.OK);
			return new ResponseEntity<BuyProductRestTemplate>(buyProductRestTemplate, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<List<Object>> getAllUserTransactions(int user_id) {
		return gladiatorDao.getAllUserTransactions(user_id);
	}
	
	@Override
	public ResponseEntity<List<Installments>> getAllMonthlyPaidTransactions(int user_id) {
		return gladiatorDao.getAllMonthlyPaidTransactions(user_id);
	}
	
	@Override
	public ResponseEntity<List<Installments>> getAllUpcomingTransactions(int user_id) {
		return gladiatorDao.getAllUpcomingTransactions(user_id);
	}
	
	@Override
	public ResponseEntity<Number> getBalanceCredit(int user_id) {
		return gladiatorDao.getBalanceCredit(user_id);
	}
	
	@Override
	public ResponseEntity<Number> getNextMonthAmount(int user_id) {
		return gladiatorDao.getNextMonthAmount(user_id);
	}

	@Override
	public ResponseEntity<Cards> getCardDetails(int user_id) {
		return gladiatorDao.getCardDetails(user_id);
	}

	@Override
	public ResponseEntity<GetOTPRestTemplate> sendOTPViaEmail(String email_id) {
		GetOTPRestTemplate restTemplate;
		if (gladiatorDao.getUserByEmail(email_id).size() <= 0) {
			restTemplate = new GetOTPRestTemplate("Failed", "Invalid email ID", HttpStatus.NOT_FOUND);
			return new ResponseEntity<GetOTPRestTemplate>(restTemplate, HttpStatus.OK);
		}
		
		else {
			String subject = "Password Recovery Email";
			Random random = new Random();
			
			int OTP = random.nextInt(99999);
			emailService.sendMail(email_id, subject, OTP);
			
			SaveOTP saveOTP = new SaveOTP();
			saveOTP.setEmail_id(email_id);
			saveOTP.setOtp(OTP);
			
			gladiatorDao.saveOTP(saveOTP);
			
			restTemplate = new GetOTPRestTemplate("Success", "OTP sent", HttpStatus.OK);
			return new ResponseEntity<GetOTPRestTemplate>(restTemplate, HttpStatus.OK);
		}
	}

	@Override
	public boolean verifyOTP(SaveOTP saveOTP) {
		boolean flag = false;
		try {
			SaveOTP newSaveOTP = gladiatorDao.verifyOTP(saveOTP);
			if (newSaveOTP.getOtp() == saveOTP.getOtp()) {
				flag = true;
				gladiatorDao.deleteUserOTP(newSaveOTP);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public ResponseEntity<ChangePasswordRestTemplate> changePassword(ChangePassword changePassword) {
		System.out.println("New Password in changePassword = " + changePassword.getNewPassword());
		changePassword.setNewPassword(generate(changePassword.getNewPassword()));
		System.out.println("New Password in Service = " + changePassword.getNewPassword());
		return gladiatorDao.changePassword(changePassword);
	}
	
	//Admin APIs
//	@Override
//	public List<UserDetailsWithName> getAllUserDetailsWithName() {
//		return gladiatorDao.getAllUserDetailsWithName();
//	}
	
//	@Override
//	public ResponseEntity<String> changeStatus(Status status) {
//		gladiatorDao.changeStatus(status);
//		return new ResponseEntity<String>("Status changed!", HttpStatus.OK);
//	}
//		
//	@Override
//	public List<Registration> getAllUsers() {
//		return gladiatorDao.getAllUsers();
//	}
//
//	@Override
//	public ResponseEntity<String> editUser(Registration registration, String user_name) {
//		registration.setPassword_hash(generate(registration.getPassword_hash()));
//		gladiatorDao.editUser(registration, user_name);
//		return  new ResponseEntity<String>("User edited!", HttpStatus.OK);
//	}
//	
//	
//	@Override
//	public ResponseEntity<String> deleteUser(DeleteUser deleteUser) {
//		gladiatorDao.deleteUser(deleteUser);
//		return  new ResponseEntity<String>("User deleted!", HttpStatus.OK);
//	}
	@Override
	public List<UserDetailsWithName> getAllUserDetailsWithName() {
		return gladiatorDao.getAllUserDetailsWithName();
	}
	
	@Override
	public ResponseEntity<StandardRestTemplate> changeStatus(Status status) {
		StandardRestTemplate restTemplate;
		if(gladiatorDao.changeStatus(status)==0)
			restTemplate = new StandardRestTemplate("Failure", "Card status change unsuccessful", HttpStatus.NOT_IMPLEMENTED);
		else
			restTemplate = new StandardRestTemplate("Success", "Card status change successful", HttpStatus.OK);
		return new ResponseEntity<StandardRestTemplate>(restTemplate, HttpStatus.OK);
	}
		
	@Override
	public List<Registration> getAllUsers() {
		return gladiatorDao.getAllUsers();
	}

	@Override
	public ResponseEntity<StandardRestTemplate> editUser(Registration registration, String user_name) {
		StandardRestTemplate restTemplate;
		
		int f = gladiatorDao.editUser(registration, user_name);
		System.out.println(f);
		if(f==0){
			restTemplate = new StandardRestTemplate("Failure", "Account number unique constraint violated", HttpStatus.BAD_REQUEST);
		}
		else if(f==-1){
			restTemplate = new StandardRestTemplate("Failure", "Some exception", HttpStatus.NOT_IMPLEMENTED);
		}
		else{
			restTemplate = new StandardRestTemplate("Success", "User details edited", HttpStatus.OK);
		}
			
			
		return new ResponseEntity<StandardRestTemplate>(restTemplate, HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<StandardRestTemplate> deleteUser(DeleteUser deleteUser) {
		StandardRestTemplate restTemplate;
		if(gladiatorDao.deleteUser(deleteUser)==0)
			restTemplate = new StandardRestTemplate("Failure", "User deletion unsuccessful", HttpStatus.NOT_IMPLEMENTED);
		else
			restTemplate = new StandardRestTemplate("Success", "User deletion successful", HttpStatus.OK);
		
		return new ResponseEntity<StandardRestTemplate>(restTemplate, HttpStatus.OK);
	}
}
