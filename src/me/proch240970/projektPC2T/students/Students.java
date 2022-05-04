package me.proch240970.projektPC2T.students;

import java.util.ArrayList;
import java.util.List;

import me.proch240970.projektPC2T.MyExceptions;

public abstract class Students{
	private int ID;
	private String firstName;
	private String lastName;
	
	private short [] dateOfBirth = new short [3];
	private List<Integer> marks = new ArrayList<Integer>();
	
	public Students(int ID, String firstName, String lastName, String dateOfBirth) throws NumberFormatException, MyExceptions{
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		parseDate(dateOfBirth);
	}
	
	public boolean addMark(int mark) {
		if (mark > 0 && mark < 6) {
			return marks.add(mark);
		}
		return false;
	}
	
	public List<Integer> getMarks(){
		return this.marks;
	}
	
	public void setMarks(List<Integer> marks) {
		this.marks = marks;
	}
	
	public float getAverage() {
		int sum = 0;
		float avg = 0;
		
		for (int mark: marks) {
			sum += mark;
		}
		if (marks.size() != 0)
			avg = (float)sum / (float)marks.size();
		return avg;
	}
	
	public void parseDate(String dateOfBirth) throws NumberFormatException, MyExceptions{
		// dodelat validaci datumu
		String [] parts = dateOfBirth.split("\\.", 3);
		if (parts.length == 3) {
			this.dateOfBirth[0] = Short.valueOf(parts[0]);
			this.dateOfBirth[1] = Short.valueOf(parts[1]);
			this.dateOfBirth[2] = Short.valueOf(parts[2]);
		} else {
			throw new MyExceptions("Bad birthday input. Please enter DD.MM.YYYY format.");
		}
		
	}
		
	public int getID() {
		return ID;
	}

	public String getLastName() {
		return this.lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}

	public short [] getDateOfBirth() {
		return dateOfBirth;
	}
	
	protected void Zodiac() {
		short day = this.getDateOfBirth()[0];
		short month = this.getDateOfBirth()[1];
		if ((day >= 21 && month == 3) || (day <= 20 && month == 4)) {
			System.out.println("Narozen ve znameni Berana.");
		} 
		else if ((day >= 21 && month == 4) || (day <= 21 && month == 5)) {
			System.out.println("Narozen ve znameni Byka.");
		}
		else if ((day >= 22 && month == 5) || (day <= 21 && month == 6)) {
			System.out.println("Narozen ve znameni Blizence.");
		}
		else if ((day >= 22 && month == 6) || (day <= 22 && month == 7)) {
			System.out.println("Narozen ve znameni Raka.");
		}
		else if ((day >= 23 && month == 7) || (day <= 22 && month == 8)) {
			System.out.println("Narozen ve znameni Lva.");
		}
		else if ((day >= 23 && month == 8) || (day <= 22 && month == 9)) {
			System.out.println("Narozen ve znameni Panna.");
		}
		else if ((day >= 23 && month == 9) || (day <= 23 && month == 10)) {
			System.out.println("Narozen ve znameni Vahy.");
		}
		else if ((day >= 24 && month == 10) || (day <= 22 && month == 11)) {
			System.out.println("Narozen ve znameni Stir.");
		}
		else if ((day >= 23 && month == 11) || (day <= 21 && month == 12)) {
			System.out.println("Narozen ve znameni Strelce.");
		}
		else if ((day >= 22 && month == 12) || (day <= 20 && month == 1)) {
			System.out.println("Narozen ve znameni Kozoroh.");
		}
		else if ((day >= 21 && month == 1) || (day <= 20 && month == 2)) {
			System.out.println("Narozen ve znameni Vodnare.");
		}
		else if ((day >= 21 && month == 2) || (day <= 20 && month == 3)) {
			System.out.println("Narozen ve znameni Ryba.");
		}
	}
	
	protected void leapYear() {
		short birthYear = this.getDateOfBirth()[2];
		if ((birthYear % 4 == 0 && birthYear % 100 != 0) | (birthYear % 100 == 0 && birthYear % 400 == 0) ) {
			System.out.println("Rok je prestupny!");
		} else {
			System.out.println("Rok neni prestupny");
		}
	}

	public abstract void Skill();
}
