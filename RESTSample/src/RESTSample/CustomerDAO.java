package RESTSample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAO {

	private static final Map<Integer, Customer> custMap = new HashMap<Integer, Customer>();

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

	public static Customer getCustomer(int id) {
		return custMap.get(id);
	}

	public static Customer addCustomer(Customer cust) {
		custMap.put(cust.getId(), cust);
		return cust;
	}

	public static Customer updateCustomer(Customer cust) {
		custMap.put(cust.getId(), cust);
		return cust;
	}

	public static void deleteCustomer(int id) {
		custMap.remove(id);
	}

	public static List<Customer> getAllCustomers() {
		Collection<Customer> c = custMap.values();
		List<Customer> list = new ArrayList<Customer>();
		list.addAll(c);
		return list;
	}

	List<Customer> list;

}
