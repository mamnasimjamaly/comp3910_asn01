package userLogin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import ca.bcit.infosys.employee.Credentials;
import ca.bcit.infosys.employee.Employee;
import ca.bcit.infosys.employee.EmployeeList;

@Named("employees")
@ApplicationScoped
public class Employees implements EmployeeList, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//List of employees
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	//List of users, this is linked with the employees via the username
	private Map<String, String> loginCombos = new HashMap<String, String>();
	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	//The current logged in user
	private Employee currentEmployee;
	//The admin
	Employee adminUser = new Employee("admin", 00001, "admin");
	
	public Employees() {
		loginCombos.put("admin", "admin");
	}
	
	/**
     * @return the ArrayList of users.
     */
	@Override
	public List<Employee> getEmployees() {
		return employees;
	}

    /**
     * @param name the name field of the employee
     * @return the employees.
     */
	@Override
	public Employee getEmployee(String name) {
		for(int i = 0; i < employees.size(); i++) {
			Employee current = employees.get(i);
			if(current.getName() == name) {
				return current;
			}
		}
		return null;
	}

    /**
     * @return the Map containing the valid (userName, password) combinations.
     */
	@Override
	public Map<String, String> getLoginCombos() {
		return loginCombos;
	}

    /**
     * @return the current user.
     */
	@Override
	public Employee getCurrentEmployee() {
		return currentEmployee;
	}
	
	/**
     * @return the current user.
     */
	public void setCurrentEmployee(Employee e) {
		currentEmployee = e;
	}

    /**
     * @return the administrator user object.
     */
	@Override
	public Employee getAdministrator() {	
		return adminUser;
	}

    /**
     * Verifies that the loginID and password is a valid combination.
     *
     * @param credential (userName, Password) pair
     * @return true if it is, false if it is not.
     */
	@Override
	public boolean verifyUser(Credentials credential) {
		//if the username in the credentials matches a key in the login combo nad the password paired with that key
		if(loginCombos.containsKey(credential.getUserName())) {
			if(loginCombos.get(credential.getUserName()) == credential.getPassword()) {
				return true;
			}
		}
		return false;
	}

	public String login(Credentials credential) {
		if(verifyUser(credential) == true) {
			if(credential.getUserName() == "admin") {
				return "successAdmin";
			}
			for(int i = 0; i < employees.size(); i++) {
				if(employees.get(i).getUserName() == credential.getUserName()){
					return "success";
				}
			}
		}
		return "failure";
	}
	
    /**
     * Logs the user out of the system.
     *
     * @param employee the user to logout.
     * @return a String representing the login page.
     */
	@Override
	public String logout(Employee employee) {
		if(employee == currentEmployee) {
			currentEmployee = null;
			return "success";
		}
		return "failure";
	}

    /**
     * Deletes the specified user from the collection of Users.
     *
     * @param userToDelete the user to delete.
     */
	@Override
	public void deleteEmpoyee(Employee userToDelete) {
		employees.remove(userToDelete);
		loginCombos.remove(userToDelete.getUserName());
	}

	/**
	 * Signs up a user/employee into the system
	 *
	*/
	public String signUp(final String employeeName, final int employeeNumber, final String username, final String password) {
		addEmployee(new Employee(employeeName, employeeNumber, username));
		loginCombos.put(username, password);
		return "success";
	}
	
    /**
     * Adds a new Employee to the collection of Employees.
     * @param newEmployee the employee to add to the collection
     */
	@Override
	public void addEmployee(Employee newEmployee) {
		employees.add(newEmployee);
	}

}

