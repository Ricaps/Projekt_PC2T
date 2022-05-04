package me.proch240970.projektPC2T.students;

import me.proch240970.projektPC2T.MyExceptions;

public class CombinedStudent extends Students {
	private static int counter;

	public CombinedStudent(int ID, String firstName, String lastName, String dateOfBirth) throws NumberFormatException, MyExceptions {
		super(ID, firstName, lastName, dateOfBirth);
		counter++;
	}

	@Override
	public void Skill() {
		Zodiac();
		leapYear();
	}
	
	public static int getInstanceCount() {		
		return counter;
	}

}
