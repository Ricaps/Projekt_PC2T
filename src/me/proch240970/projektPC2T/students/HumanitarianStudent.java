package me.proch240970.projektPC2T.students;

import me.proch240970.projektPC2T.MyExceptions;

public class HumanitarianStudent extends Students {
	private static int counter;

	public HumanitarianStudent(int ID, String firstName, String lastName, String dateOfBirth) throws NumberFormatException, MyExceptions {
		super(ID, firstName, lastName, dateOfBirth);
		counter++;
	}

	@Override
	public void Skill() {
		Zodiac();
	}
	
	public static int getInstanceCount() {		
		return counter;
	}

}
