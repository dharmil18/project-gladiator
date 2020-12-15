package com.lti.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

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

@Repository
public class GladiatorDaoImpl implements GladiatorDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public GladiatorDaoImpl() {
		super();
	}

	@Override
	public void saveUser(Users user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@Override
	public void saveUserDetails(User_Details user_details) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user_details);
	}

	@Override
	public void saveCard(Cards card) {
		Session session = sessionFactory.getCurrentSession();
		session.save(card);
	}

	@Override
	public String getPassword(String user_name) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT u FROM Users u WHERE u.user_name=:name");
		query.setParameter("name", user_name);
		Users user = (Users) query.getSingleResult();
		return user.getPassword_hash();
	}
	

	@Override
	public List<Products> getAllProducts() {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("SELECT p FROM Products p");
		@SuppressWarnings("unchecked")
		List<Products> prodList = q.getResultList();
		return prodList;
	}

	@Override
	public Products getProductById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(Products.class, id);
	}
	
	@Override
	public Users getSingleUserByUserName(String user_name) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		NativeQuery query = session.createSQLQuery("SELECT * FROM Users WHERE user_name=:name");
		query.setParameter("name", user_name);
		query.addEntity(Users.class);
		@SuppressWarnings("unchecked")
		List<Users> users = (List<Users>) query.list();
		Users user = null;
		if (users.size() > 0)
			user = users.get(0);
		return user;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Users> getUserByUserName(String user_name) {
		Session session = sessionFactory.getCurrentSession();
		NativeQuery query = session.createSQLQuery("SELECT * FROM Users u WHERE u.user_name=:name");
		query.setParameter("name", user_name);
		query.addEntity(Users.class);
		List<Users> users = (List<Users>)query.list();
		return users;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Users> getUserByEmail(String email_id) {
		Session session = sessionFactory.getCurrentSession();
		NativeQuery query = session.createSQLQuery("SELECT * FROM Users u WHERE u.email_id=:email");
		query.setParameter("email", email_id);
		query.addEntity(Users.class);
		List<Users> users = (List<Users>) query.list();
		return users; 
	}

	@Override
	public String getCardNumber() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT MAX(c.card_no) FROM Cards c");
		String cardNo = (String) query.getSingleResult();
		long card_no = Long.parseLong(cardNo);
		card_no = card_no + 1;
		cardNo = String.valueOf(card_no);
		return cardNo;
	}

	@Override
	public void buyProduct(Transactions transaction) {
		Session session = sessionFactory.getCurrentSession();
		session.save(transaction);
	}

	@Override
	public int getProductCost(int product_id) {
		Session session = sessionFactory.getCurrentSession();
		Products product = session.find(Products.class, product_id);
		return product.getProduct_cost();
	}

	@Override
	public void saveMonthlyTransaction(Monthly_Transactions mTransaction) {
		Session session = sessionFactory.getCurrentSession();
		session.save(mTransaction);
	}

	@Override
	public ResponseEntity<List<Object>> getAllUserTransactions(int user_id) {
		Session session = sessionFactory.getCurrentSession();
		String sqlQuery = "SELECT transaction_id, product_id, product_name, product_cost, monthly_installment, tenure, transaction_date, status, processing_fee  From transactions join Products USING(product_id) WHERE user_id=:id ORDER BY transaction_date DESC";
		Query query = session.createNativeQuery(sqlQuery);
		query.setParameter("id", user_id);	
		@SuppressWarnings("unchecked")
		List<Object> userTransactions = query.getResultList();
		return new ResponseEntity<List<Object>>(userTransactions, HttpStatus.OK);
	}
	

	@Override
	public ResponseEntity<List<Installments>> getAllMonthlyPaidTransactions(int user_id) {
		Session session = sessionFactory.getCurrentSession();
		String sqlQuery = "SELECT mtxn_id, transaction_id, product_name, amount, mtxn_date, m_status from monthly_transactions INNER JOIN transactions USING(transaction_id) JOIN products USING(product_id) WHERE user_id=:id AND LOWER(m_Status)='paid' ORDER BY mtxn_date DESC";
		Query query = session.createNativeQuery(sqlQuery, Installments.class);
		query.setParameter("id", user_id);
		@SuppressWarnings("unchecked")
		List<Installments> installments = query.getResultList();
		return new ResponseEntity<List<Installments>>(installments, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<Installments>> getAllUpcomingTransactions(int user_id) {
		Session session = sessionFactory.getCurrentSession();
		String sqlQuery = "SELECT mtxn_id, transaction_id, product_name, amount, mtxn_date, m_status from monthly_transactions INNER JOIN transactions USING(transaction_id) JOIN products USING(product_id) WHERE user_id=:id AND LOWER(m_Status)='pending' ORDER BY mtxn_date";
		Query query = session.createNativeQuery(sqlQuery, Installments.class);
		query.setParameter("id", user_id);
		@SuppressWarnings("unchecked")
		List<Installments> installments = query.getResultList();
		return new ResponseEntity<List<Installments>>(installments, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Number> getBalanceCredit(int user_id) {
		Session session = sessionFactory.getCurrentSession();
		String sqlQuery = "SELECT NVL(sum(amount),0) from monthly_transactions join transactions using(transaction_id) WHERE m_status='Paid' AND TO_CHAR(mtxn_date, 'MM-YY') = TO_CHAR(sysdate, 'MM-YY') AND user_id=:id";
		Query query = session.createNativeQuery(sqlQuery);
		query.setParameter("id", user_id);
		Number balance = (Number) query.getSingleResult();
		return new ResponseEntity<Number>(balance, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Number> getNextMonthAmount(int user_id) {
		Session session = sessionFactory.getCurrentSession();
		String sqlQuery = "SELECT NVL(sum(amount), 0) from monthly_transactions join transactions using(transaction_id) WHERE m_status='Pending' AND TO_CHAR(mtxn_date, 'MM-YY') = TO_CHAR(ADD_MONTHS(sysdate, 1), 'MM-YY') AND user_id=:id";
		Query query = session.createNativeQuery(sqlQuery);
		query.setParameter("id", user_id);
		Number balance = (Number) query.getSingleResult();
		return new ResponseEntity<Number>(balance, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Cards> getCardDetails(int user_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT c FROM Cards c WHERE user_id=:id");
		query.setParameter("id", user_id);
		Cards card = (Cards) query.getSingleResult();
		return new ResponseEntity<Cards>(card, new HttpHeaders(), HttpStatus.OK);
	}

	@Override
	public void saveOTP(SaveOTP saveOTP) {
		Session session = sessionFactory.getCurrentSession();
		session.save(saveOTP);
	}

	@Override
	public SaveOTP verifyOTP(SaveOTP saveOTP) {
		Session session = sessionFactory.getCurrentSession();
		String email = saveOTP.getEmail_id();
		SaveOTP newSaveOtp = session.find(SaveOTP.class, email);
		return newSaveOtp;
	}

	@Override
	public void deleteUserOTP(SaveOTP saveOTP) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(saveOTP);
	}

	@Override
	public ResponseEntity<ChangePasswordRestTemplate> changePassword(ChangePassword changePassword) {
		ChangePasswordRestTemplate changePasswordRestTemplate;
		Session session = sessionFactory.getCurrentSession();
		if (getUserByEmail(changePassword.getEmail_id()).size() > 0) {
			Users user = getUserByEmail(changePassword.getEmail_id()).get(0);
			System.out.println("User = " + user);
			System.out.println("Old Password = " + user.getPassword_hash());
			user.setPassword_hash(changePassword.getNewPassword());	
			System.out.println("New Password = " + user.getPassword_hash());
			session.save(user);
			changePasswordRestTemplate = new ChangePasswordRestTemplate("Success", "Password Changed", HttpStatus.OK);
			return new ResponseEntity<ChangePasswordRestTemplate>(changePasswordRestTemplate, HttpStatus.OK);
		}
		else {
			changePasswordRestTemplate = new ChangePasswordRestTemplate("Failed", "Error while changing password", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<ChangePasswordRestTemplate>(changePasswordRestTemplate, HttpStatus.OK);
		}
	}
	
	//Admin APIs
	
//	@Override
//	public List<UserDetailsWithName> getAllUserDetailsWithName() {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "SELECT u.user_id, u.first_name, u.last_name, ud.bank_name, ud.account_no, ud.ifsc_code from Users u join User_Details ud  on u.user_id=ud.user_id join Cards c on u.user_id=c.user_id where lower(c.status)='pending' AND lower(u.user_status)='active'";
//		Query query = session.createNativeQuery(sql,UserDetailsWithName.class);
//		@SuppressWarnings("unchecked")
//		List<UserDetailsWithName> list = query.getResultList();
//		return list;
//	}
//
//	
//	@Override
//	public void changeStatus(Status status) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "select c from Cards c where user_id = "+status.getUser_id();
//		Query query = session.createQuery(sql);
//		Cards c = (Cards) query.getSingleResult();
//		c.setStatus(status.getAction());
//		LocalDate action_date = LocalDate.now();
//		c.setIssue_date(action_date);
//		session.save(c);
//	}
//
//	
//	@Override
//	public List<Registration> getAllUsers() {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "SELECT u.first_name, u.last_name, u.email_id, u.phone_number, u.user_name, u.password_hash, ud.bank_name, ud.account_no, ud.ifsc_code, ud.address_line1, ud.address_line2, ud.city, ud.state, ud.zipcode, c.card_type from Users u join User_Details ud  on u.user_id=ud.user_id join Cards c on u.user_id=c.user_id AND lower(u.user_status)='active'";
//		Query query = session.createNativeQuery(sql);	
//		@SuppressWarnings("unchecked")
//		List<Registration> list = query.getResultList();
//		return list;
//	}
//	
//
//	@Override
//	public void editUser(Registration registration, String user_name) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql1 = "select u from Users u where user_name='"+user_name+"'";
//		Query query1 = session.createQuery(sql1);
//		Users user = (Users) query1.getSingleResult();
//		user.setFirst_name(registration.getFirst_name());
//		user.setLast_name(registration.getLast_name());
//		//user.setEmail_id(registration.getEmail_id());
//		user.setPhone_number(registration.getPhone_number());
//		//user.setUser_name(registration.getUser_name());
//		//user.setPassword_hash(registration.getPassword_hash());
//		
//		String sql2 = "select ud from User_Details ud where user_id="+user.getUser_id();
//		Query query2 = session.createQuery(sql2);
//		User_Details user_Details = (User_Details) query2.getSingleResult();
//		user_Details.setBank_name(registration.getBank_name());
//		user_Details.setAccount_no(registration.getAccount_no());
//		user_Details.setIfsc_code(registration.getIfsc_code());
//		user_Details.setAddress_line1(registration.getAddress_line1());
//		user_Details.setAddress_line2(registration.getAddress_line2());
//		user_Details.setCity(registration.getCity());
//		user_Details.setState(registration.getState());
//		user_Details.setZipcode(registration.getZipcode());
//		
//		/*String sql3 = "select c from Cards c where user_id="+user.getUser_id();
//		Query query3 = session.createQuery(sql3);
//		Cards cards = (Cards) query3.getSingleResult();
//		cards.setCard_type(registration.getCard_type());*/
//		
//		session.save(user);
//		session.save(user_Details);
//		//session.save(cards);
//	}
//
//	@Override
//	public void deleteUser(DeleteUser deleteUser) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "select u from Users u where user_name='"+deleteUser.getUser_name()+"'";
//		Query query = session.createQuery(sql);
//		Users user = (Users) query.getSingleResult();
//		user.setUser_status("deleted");
//		session.update(user);	
//		
//		String cardSql = "select c from Cards c where user_id='"+user.getUser_id()+"'";
//		Query cardQuery = session.createQuery(cardSql);
//		Cards card = (Cards) cardQuery.getSingleResult();
//		card.setStatus("Deactivate");
//		session.update(card);
//	}
	@Override
	public List<UserDetailsWithName> getAllUserDetailsWithName() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT u.user_id, u.first_name, u.last_name, ud.bank_name, ud.account_no, ud.ifsc_code from Users u join User_Details ud  on u.user_id=ud.user_id join Cards c on u.user_id=c.user_id where lower(c.status)='pending' AND lower(u.user_status)='active'";
		Query query = session.createNativeQuery(sql,UserDetailsWithName.class);
		@SuppressWarnings("unchecked")
		List<UserDetailsWithName> list = query.getResultList();
		return list;
	}

	
	@Override
	public int changeStatus(Status status) {
		
		try{
		Session session = sessionFactory.getCurrentSession();
		String sql = "select c from Cards c where user_id = "+status.getUser_id();
		Query query = session.createQuery(sql);
		Cards c = (Cards) query.getSingleResult();
		c.setStatus(status.getAction());
		LocalDate action_date = LocalDate.now();
		c.setIssue_date(action_date);
		session.save(c);
		}
		catch(Exception e){
			return 0;
		}
		return 1;
	}

	
	@Override
	public List<Registration> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT u.first_name, u.last_name, u.email_id, u.phone_number, u.user_name, u.password_hash, ud.bank_name, ud.account_no, ud.ifsc_code, ud.address_line1, ud.address_line2, ud.city, ud.state, ud.zipcode, c.card_type from Users u join User_Details ud  on u.user_id=ud.user_id join Cards c on u.user_id=c.user_id AND lower(u.user_status)='active'";
		Query query = session.createNativeQuery(sql,Registration.class);	
		@SuppressWarnings("unchecked")
		List<Registration> list = query.getResultList();
		return list;
	}


//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Override
//	public int checkAccountNumberUniqueness(String acc_no) {
//		Session session = sessionFactory.getCurrentSession();
//		NativeQuery query = session.createSQLQuery("SELECT * FROM User_Details ud WHERE ud.account_no=:acc_no");
//		query.setParameter("acc_no",acc_no);
//		List<User_Details> ud = (List<User_Details>) query.list();
//		if (ud.size()>0)
//			return 0;
//		else
//			return 1;
//	}
	public int checkAccountNumberUniqueness(String acc_no,String user_name) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		NativeQuery query = session.createSQLQuery("SELECT * FROM User_Details ud WHERE ud.account_no=:acc_no");
		query.setParameter("acc_no",acc_no);
		@SuppressWarnings("unchecked")
		List<User_Details> ud = (List<User_Details>) query.list();
		if (ud.size()>0){
			Query query1 = session.createQuery("SELECT ud FROM User_Details ud WHERE ud.account_no=:acc_no");
			query1.setParameter("acc_no",acc_no);
			User_Details ud1 = (User_Details) query1.getSingleResult();
			//User_Details obj=(User_Details)ud.get(0);
			Users u=(Users)getSingleUserByUserName(user_name);
			if(ud1.getUser_id()!=u.getUser_id()){
				return 0;
			}
			else
				return 1;
			}
			else
				return 1;
		}
	
	@Override
	public int editUser(Registration registration, String user_name) {
		Session session = sessionFactory.getCurrentSession();
		
		int flag = checkAccountNumberUniqueness(registration.getAccount_no(), user_name);
		
		if(flag==0)
			return 0;
		
		try{
		String sql1 = "select u from Users u where user_name='"+user_name+"'";
		Query query1 = session.createQuery(sql1);
		Users user = (Users) query1.getSingleResult();
		user.setFirst_name(registration.getFirst_name());
		user.setLast_name(registration.getLast_name());
		user.setPhone_number(registration.getPhone_number());
		
		session.save(user);
		
		String sql2 = "select ud from User_Details ud where user_id="+user.getUser_id();
		Query query2 = session.createQuery(sql2);
		User_Details user_Details = (User_Details) query2.getSingleResult();
		user_Details.setBank_name(registration.getBank_name());
		user_Details.setAccount_no(registration.getAccount_no());
		user_Details.setIfsc_code(registration.getIfsc_code());
		user_Details.setAddress_line1(registration.getAddress_line1());
		user_Details.setAddress_line2(registration.getAddress_line2());
		user_Details.setCity(registration.getCity());
		user_Details.setState(registration.getState());
		user_Details.setZipcode(registration.getZipcode());
		
		session.save(user_Details);
		}
		catch(Exception e){
			return -1;
		}
		
		return 1;
	
	}

	@Override
	public int deleteUser(DeleteUser deleteUser) {
		try{
		Session session = sessionFactory.getCurrentSession();
		String sql = "select u from Users u where user_name='"+deleteUser.getUser_name()+"'";
		Query query = session.createQuery(sql);
		Users user = (Users) query.getSingleResult();
		user.setUser_status("Deleted");
		session.update(user);	
		
		String cardSql = "select c from Cards c where user_id='"+user.getUser_id()+"'";
		Query cardQuery = session.createQuery(cardSql);
		Cards card = (Cards) cardQuery.getSingleResult();
		card.setStatus("Deactivate");
		session.update(card);
		}
		catch(Exception e){
			return 0;
		}
		return 1;
	}
}
