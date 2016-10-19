package userLogin;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import ca.bcit.infosys.employee.Employee;

@Named("employee-user")
@ApplicationScoped
public class EmployeeUser extends Employee implements Serializable {
	
}
