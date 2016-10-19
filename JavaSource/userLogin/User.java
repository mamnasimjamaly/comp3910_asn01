package userLogin;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ca.bcit.infosys.employee.Credentials;

@Named("user")
@RequestScoped
public class User extends Credentials  implements Serializable{
	 
}

