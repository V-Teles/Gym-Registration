package br.victorteles.gym;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String birthday;
	private String phoneNumber;
	private String address;
	private String email;
	
	public User() {
		
	}
	
	public User(int id, String firstName, String lastName, String birthday, String phoneNumber, String address,
			String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", email=" + email + "]";
	}
	
}
