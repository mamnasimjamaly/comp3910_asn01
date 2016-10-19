package userLogin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import ca.bcit.infosys.employee.Employee;
import ca.bcit.infosys.timesheet.Timesheet;
import ca.bcit.infosys.timesheet.TimesheetCollection;

@Named("timesheets")
@ApplicationScoped
public class Timesheets implements TimesheetCollection, Serializable{
	ArrayList<Timesheet> timesheets = new ArrayList<Timesheet>();
	
	/**
     * @return all of the timesheets.
     */
	@Override
	public List<Timesheet> getTimesheets() {
		return timesheets;
	}

	/**
     * @param e the employee whose timesheets are returned
     * @return all of the timesheets for an employee.
     */
	@Override
	public List<Timesheet> getTimesheets(Employee e) {
		ArrayList<Timesheet> employeeTimesheets = new ArrayList<Timesheet>();
		
		for(int i = 0; i < timesheets.size(); i++) {
			Timesheet pointer = timesheets.get(i);
			if(timesheets.get(i).getEmployee() == e) {
				employeeTimesheets.add(pointer);
			}
		}
		return employeeTimesheets;
	}

	/**
     * @param e the employee whose current timesheet is returned
     * @return the current timesheet for an employee.
     */
	@Override
	public Timesheet getCurrentTimesheet(Employee e) {
		Calendar c = new GregorianCalendar();
		int currentWeek = c.get(Calendar.WEEK_OF_YEAR);

		for(int i = 0; i < timesheets.size(); i++) {
			Timesheet pointer = timesheets.get(i);
			c.setTime(pointer.getEndWeek());
			int weekToCompare = c.get(Calendar.WEEK_OF_YEAR);
			if(currentWeek == weekToCompare) {
				if(timesheets.get(i).getEmployee() == e) {
					return pointer;
				}
			}
		}
		return null;
	}

	public void setTimesheets(ArrayList<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}

	/**
     * Creates a Timesheet object and adds it to the collection.
     *
     * @return a String representing navigation to the newTimesheet page.
     */
	@Override
	public String addTimesheet() {
		Timesheet timesheet = new Timesheet();
		timesheets.add(timesheet);
		return "Success";
	}

}
