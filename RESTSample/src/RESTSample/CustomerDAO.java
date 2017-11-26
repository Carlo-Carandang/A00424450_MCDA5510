package RESTSample;

import java.util.List;

public interface CustomerDAO {

	Customer getCustomer(int id);

	Customer addCustomer(Customer cust);

	Customer updateCustomer(Customer cust);

	void deleteCustomer(int id);

	List<Customer> getAllCustomers();

}