package RESTSample;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Customers")
public class CustomerController {

    // URI:
    // /contextPath/servletPath/Customers
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Customer> getEmployees_JSON() {
        List<Customer> listOfCustomers = CustomerDAO.getAllCustomers();
        return listOfCustomers;
    }
 
    // URI:
    // /contextPath/servletPath/Customers/{id}
    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Customer getCustomer(@PathParam("id") int id) {
        return CustomerDAO.getCustomer(id);
    }
 
    // URI:
    // /contextPath/servletPath/Customers
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Customer addCustomer(Customer cust) {
        return CustomerDAO.addCustomer(cust);
    }
 
    // URI:
    // /contextPath/servletPath/Customers
    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Customer updateCustomer(Customer cust) {
        return CustomerDAO.updateCustomer(cust);
    }
 
    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteEmployee(@PathParam("id") int id) {
    	CustomerDAO.deleteCustomer(id);
    }	
	
	
}
