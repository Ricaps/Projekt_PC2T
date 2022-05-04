package me.proch240970.projektPC2T.crud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.proch240970.projektPC2T.Database;
import me.proch240970.projektPC2T.MyExceptions;
import me.proch240970.projektPC2T.students.CombinedStudent;
import me.proch240970.projektPC2T.students.HumanitarianStudent;
import me.proch240970.projektPC2T.students.Students;
import me.proch240970.projektPC2T.students.TechnicalStudent;

public class FilesIO {	
	/**
	 * Class for writing Database to and from File
	 * File structure:
	 * ID;specialization;FirstName;LastName;DayOfBirth;MonthOfBirth;YearOfBirth\n
	 * @param db
	 */
	public static void SaveToFile(Database db) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("database.txt"))){
			String content = "";
			Map<Integer, Students> StudentsMap = db.getStudentsMap();
			Iterator<Integer> iter = StudentsMap.keySet().iterator();
			while(iter.hasNext()) {
				int key = iter.next();
				Students student  = StudentsMap.get(key);
				int spec_int = 0;
				if (student instanceof TechnicalStudent) {
					spec_int = 1;
				}
				else if(student instanceof HumanitarianStudent) {
					spec_int = 2;
				}
				else if (student instanceof CombinedStudent) {
					spec_int = 3;
				}
				String line = String.format("%d;%d;%s;%s;%d.%d.%d;", 
						StudentsMap.get(key).getID(),
						spec_int,
						StudentsMap.get(key).getFirstName(),
						StudentsMap.get(key).getLastName(),
						StudentsMap.get(key).getDateOfBirth()[0],
						StudentsMap.get(key).getDateOfBirth()[1],
						StudentsMap.get(key).getDateOfBirth()[2]);
				
				List<Integer> marks = StudentsMap.get(key).getMarks();
				if (marks.size() != 0) {
					for(Integer mark: marks) {
						line += mark + ",";
					}
				} else {
					line += "-1";
				}
				line += "\n";
				content += line;
			}
			bw.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Zapis do souboru se nepodaril.");
		}
	}
	
	public static Database LoadFromFile(Database db) throws NumberFormatException, MyExceptions {
		try (BufferedReader br = new BufferedReader(new FileReader("database.txt"))){
			String line;
			Map<Integer, Students> StudentsMap = db.getStudentsMap();
			StudentsMap.clear();
			while((line = br.readLine()) != null) {
				// rozdeleni pomoci ; na jednotlive atributy
				String parts [] = line.split(";");
				System.out.println(parts[3]);
				db.addStudent(parts[2], parts[3], parts[4], Integer.parseInt(parts[1]));
				if (!parts[5].equals("-1")) {
					// rozdeleni na jednotlive znamky pomoci carky, nasledne postupne prirazeni jednotlivych znamek studentovi
					String marksParts [] = parts[5].split(",");
					for (int i=0; i < marksParts.length; i++) {
						db.findStudent(Integer.parseInt(parts[0])).addMark(Integer.parseInt(marksParts[i]));
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Zapis do souboru se nepodaril.");
		}
		return db;
	}
}
