package RESTSample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCustomerDAO implements CustomerDAO {

	private static final Map<Integer, Customer> custMap = new HashMap<Integer, Customer>();

	List<Customer> list;
	
	static {
		initCusts();
	}

	private static void initCusts() {
		Customer cust1 = new Customer(1, "Smith");
		Customer cust2 = new Customer(2, "Allen");
		Customer cust3 = new Customer(3, "Jones");

		custMap.put(cust1.getId(), cust1);
		custMap.put(cust2.getId(), cust2);
		custMap.put(cust3.getId(), cust3);
	}

	/* (non-Javadoc)
	 * @see org.dpenny.CustomerDAO#getCustomer(int)
	 */
	@Override
	public Customer getCustomer(int id) {
		return custMap.get(id);
	}

	/* (non-Javadoc)
	 * @see org.dpenny.CustomerDAO#addCustomer(org.dpenny.Customer)
	 */
	@Override
	public Customer addCustomer(Customer cust) {
		custMap.put(cust.getId(), cust);
		return cust;
	}

	/* (non-Javadoc)
	 * @see org.dpenny.CustomerDAO#updateCustomer(org.dpenny.Customer)
	 */
	@Override
	public Customer updateCustomer(Customer cust) {
		custMap.put(cust.getId(), cust);
		return cust;
	}

	/* (non-Javadoc)
	 * @see org.dpenny.CustomerDAO#deleteCustomer(int)
	 */
	@Override
	public void deleteCustomer(int id) {
		custMap.remove(id);
	}

	/* (non-Javadoc)
	 * @see org.dpenny.CustomerDAO#getAllCustomers()
	 */
	@Override
	public List<Customer> getAllCustomers() {
		Collection<Customer> c = custMap.values();
		List<Customer> list = new ArrayList<Customer>();
		list.addAll(c);
		return list;
	}
}
