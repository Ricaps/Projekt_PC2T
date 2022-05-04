package me.proch240970.projektPC2T;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import me.proch240970.projektPC2T.students.CombinedStudent;
import me.proch240970.projektPC2T.students.HumanitarianStudent;
import me.proch240970.projektPC2T.students.Students;
import me.proch240970.projektPC2T.students.TechnicalStudent;

public class Database {
	private Map<Integer, Students> StudentsMap = new HashMap<>();
	
	public void addStudent(String firstName, String lastName, String dateOfBirth, int specialization) throws NumberFormatException, MyExceptions {
		int ID = 0 ;
		Set<Integer> ids = StudentsMap.keySet();
		if (ids.size() != 0) {
			 ID = Collections.max(ids) + 1;
		}
		
		switch (specialization) {
		case 1: // Technical
			StudentsMap.put(ID, new TechnicalStudent(ID, firstName, lastName, dateOfBirth));
			break;
			
		case 2: // Humanitariran
			StudentsMap.put(ID, new HumanitarianStudent(ID, firstName, lastName, dateOfBirth));
			break;
			
		case 3: // Combined
			StudentsMap.put(ID, new CombinedStudent(ID, firstName, lastName, dateOfBirth));
			break;
		}
	}

	
	public boolean removeStudent(int ID) {
		if (StudentsMap.containsKey(ID)) {
			if (StudentsMap.remove(ID) != null) {
				return true;
			}
		}
		return false;
	}
	
	public Students findStudent(int ID) {
		if (StudentsMap.containsKey(ID)) {
			return StudentsMap.get(ID);
		}
		return null;
	}
	 public <T> void listOfStudents(Class<T> specialization) {
		 String string;
		 ArrayList<String> list = new ArrayList<>();
		 Set<Integer> keys = StudentsMap.keySet();
		 for (int key : keys) {
			 if (specialization.isInstance(StudentsMap.get(key)))  {
				 string = String.format("%s %s, ID %d, rocnik %d, prumer %2.2f", 
						 StudentsMap.get(key).getLastName(), 
						 StudentsMap.get(key).getFirstName(), 
						 StudentsMap.get(key).getID(),
						 StudentsMap.get(key).getDateOfBirth()[2],
						 StudentsMap.get(key).getAverage());
				 list.add(string);
			 }
	       }
		Collections.sort(list);
		list.forEach(System.out::println);
	 }
	 
	 public Map<Integer, Students> getStudentsMap() {
		return StudentsMap; 
	 }
	 
	 public void clearStudentsMap() {
		 this.StudentsMap.clear();
	 }
	 
	 public <T> float getSpecsAverage(Class<T> specialization) {
		 float sum = 0;
		 float actualAvg = 0;
		 int idx = 0;
		 float avg;
		 Set<Integer> keys = StudentsMap.keySet();
		 for (int key: keys) {
			 if (specialization.isInstance(StudentsMap.get(key))) {
				 actualAvg = StudentsMap.get(key).getAverage();
				 if (actualAvg != 0) {
					 idx += 1;
				 	 sum += actualAvg;
				 }
			 }
		 }
		 avg = sum / (float)idx;
		 return avg;
	 }
}
