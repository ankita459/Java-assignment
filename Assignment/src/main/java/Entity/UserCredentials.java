package Entity;

public class UserCredentials {
	
	private String login_id;
    private String password;
    private String first_name;
    private String last_name;
    private String street;
    private String address;
    private String city;
    private String state;
    private String email;
    private String phone;
    
    
	/**
	 * @param first_name
	 * @param last_name
	 * @param street
	 * @param address
	 * @param city
	 * @param state
	 * @param email
	 * @param phone
	 */
	public UserCredentials(String first_name, String last_name, String street, String address, String city,
			String state, String email, String phone) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.street = street;
		this.address = address;
		this.city = city;
		this.state = state;
		this.email = email;
		this.phone = phone;
	}
	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}
	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the login_id
	 */
	public String getLogin_id() {
		return login_id;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param login_id the login_id to set
	 */
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param login_id
	 * @param password
	 */
	public UserCredentials(String login_id, String password) {
		super();
		this.login_id = login_id;
		this.password = password;
	}
	/**
	 * 
	 */
	public UserCredentials() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Constructors, getters, setters
    
    

}
