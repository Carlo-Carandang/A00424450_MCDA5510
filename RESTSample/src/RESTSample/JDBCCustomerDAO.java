package RESTSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
 

public class JDBCCustomerDAO implements CustomerDAO{
 
    Connection connection = null;   
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if(connection == null)
                connection = DriverManager.getConnection("jdbc:mysql://cs.smu.ca:3306/mcda551003?user=mcda551003&password=mcda551003password");
 
        } catch (ClassNotFoundException e) {
 
            e.printStackTrace();
             
        } catch (SQLException e) {
             
            e.printStackTrace();
             
        }
        return connection;
    }

    public void closeConnection(){
        try {
              if (connection != null) {
                  connection.close();
              }
            } catch (Exception e) { 
                //do nothing
            }
    } 
    
	@Override
	public Customer addCustomer(Customer customer) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO inclass.customer (firstname) VALUES (?)");
            preparedStatement.setString(1,  customer.getFirstName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
         
    }
 

	@Override   
    public List<Customer> getAllCustomers() {
        List<Customer> persons = new LinkedList<Customer>();
         try {
                Statement statement = getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM inclass.customer");
                 
                Customer customer = null;
                while(resultSet.next()){
                	customer = new Customer();
                	customer.setId(Integer.parseInt(resultSet.getString("id")));
                	customer.setFirstName(resultSet.getString("firstname"));
                     
                    persons.add(customer);
                }
                resultSet.close();
                statement.close();
                 
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(persons);
            return persons;
    }
     

	@Override
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Customer updateCustomer(Customer cust) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(int id) {
		// TODO Auto-generated method stub
		
	}


 
}