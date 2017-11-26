package RESTSample;


public class Customer {

	private int id;
	private String firstName;
	
	public Customer( ) {

	}	
	
	public Customer( int id, String fName) {
		this.id=id;
		this.firstName = fName;
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
}
