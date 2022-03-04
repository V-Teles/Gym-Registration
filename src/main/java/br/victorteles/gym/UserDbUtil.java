package br.victorteles.gym;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDbUtil {
	
	private static UserDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/Gym";
	
	// Singleton design pattern
	public static UserDbUtil getInstance() throws Exception {
		if(instance == null) {
			instance=new UserDbUtil();
		}
		return instance;
	}
	private UserDbUtil() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
		
	public List<User> getUsers() throws Exception {

		List<User> users = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from users order by id desc";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String birthday = myRs.getString("birthday");
				String phoneNumber = myRs.getString("phone_number");
				String address = myRs.getString("address");
				String email = myRs.getString("email");
				// create new student object
				User tempUsers = new User(id, firstName, lastName, birthday, phoneNumber, address, email);

				// add it to the list of students
				users.add(tempUsers);
			}
			
			return users;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}

	public void addUser(User theUser) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into users (first_name, last_name, birthday, phone_number, address, email) "+
						 "values (?, ?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theUser.getFirstName());
			myStmt.setString(2, theUser.getLastName());
			myStmt.setString(3, theUser.getBirthday());
			myStmt.setString(4, theUser.getPhoneNumber());
			myStmt.setString(5, theUser.getAddress());
			myStmt.setString(6, theUser.getEmail());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public User getUser(int userId) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from users where id=?";

			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, userId);
			
			myRs = myStmt.executeQuery();

			User theUser = null;
			
			// retrieve data from result set row
			if (myRs.next()) {
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String birthday = myRs.getString("birthday");
				String phoneNumber = myRs.getString("phone_number");
				String address = myRs.getString("address");
				String email = myRs.getString("email");
				
				theUser = new User(id, firstName, lastName, birthday, phoneNumber, address, email);
			}
			else {
				throw new Exception("Could not find user id: " + userId);
			}

			return theUser;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void updateUser(User theUser) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update users "
						+ " set first_name=?, last_name=?, birthday=?, phone_number=?, address=?, email=?"
						+ " where id=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theUser.getFirstName());
			myStmt.setString(2, theUser.getLastName());
			myStmt.setString(3, theUser.getBirthday());
			myStmt.setString(4, theUser.getPhoneNumber());
			myStmt.setString(5, theUser.getAddress());
			myStmt.setString(6, theUser.getEmail());
			myStmt.setInt(7, theUser.getId());
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void deleteUser(int userId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from users where id=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, userId);
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}		
	}	
	
	private Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();
		
		return theConn;
	}
	
	private void close(Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);
	}
	
	private void close(Connection theConn, Statement theStmt, ResultSet theRs) {

		try {
			if (theRs != null) {
				theRs.close();
			}

			if (theStmt != null) {
				theStmt.close();
			}

			if (theConn != null) {
				theConn.close();
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public List<User> searchUsers(String theSearchName)  throws Exception {

		List<User> users = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int userId;
		
		try {
			
			// get connection to database
			myConn = dataSource.getConnection();
			
	        //
	        // only search by name if theSearchName is not empty
	        //
			if (theSearchName != null && theSearchName.trim().length() > 0) {

				// create sql to search for users by name
				String sql = "select * from users where lower(first_name) like ? or lower(last_name) like ?";

				// create prepared statement
				myStmt = myConn.prepareStatement(sql);

				// set params
				String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
				myStmt.setString(1, theSearchNameLike);
				myStmt.setString(2, theSearchNameLike);
				
			} else {
				// create sql to get all users
				String sql = "select * from users order by last_name";

				// create prepared statement
				myStmt = myConn.prepareStatement(sql);
			}
	        
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			while (myRs.next()) {
				
				// retrieve data from result set row
				
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String birthday = myRs.getString("birthday");
				String phoneNumber = myRs.getString("phone_number");
				String address = myRs.getString("address");
				String email = myRs.getString("email");

				// create new user object
				User tempUser = new User(id, firstName, lastName, birthday, phoneNumber, address, email);
				
				// add it to the list of users
				users.add(tempUser);			
			}
			
			return users;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
}
