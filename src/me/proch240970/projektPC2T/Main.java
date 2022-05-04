package me.proch240970.projektPC2T;

import java.util.InputMismatchException;
import java.util.Scanner;

import me.proch240970.projektPC2T.crud.FilesIO;
import me.proch240970.projektPC2T.students.CombinedStudent;
import me.proch240970.projektPC2T.students.HumanitarianStudent;
import me.proch240970.projektPC2T.students.Students;
import me.proch240970.projektPC2T.students.TechnicalStudent;

public class Main {

	public static void main(String[] args) {
		String firstName;
		String lastName;
		String dayOfBirth;
		int ID;
		int mark;
		int specialization;
		Students obj;
		boolean running = true;
		/*Students st = new TechnicalStudent(20, "Martin", "Prochazka", "24.4.3003");
		System.out.println(st.getClass().getSimpleName());*/
		
		System.out.println();
		
		Database database = new Database();
	    Scanner sc = new Scanner(System.in);
		while(running) {
			System.out.println("1) Vlozit studenta");
			System.out.println("2) Zadat znamku");
			System.out.println("3) Smazani studenta");
			System.out.println("4) Vypis informaci dle ID");
			System.out.println("5) Spusteni dovednosti dle ID");
			System.out.println("6) Vypis studentu daneho oboru");
			System.out.println("7) Vypis prumeru znamek pro dany obor");
			System.out.println("8) Vypis celkoveho poctu studentu");
			System.out.println("9) Nacteni udaju ze souboru");
			System.out.println("10) Ulozeni do souboru");
			System.out.println("11) Ukoncit program");
			System.out.println("Zadejte moznost: ");
			try {
				int choose = sc.nextInt();
			
			
				switch (choose) {
				case 1:
					System.out.println("Zadejte jmeno, prijmeni, rok narozeni (DD.MM.YYYY) a obor (1 - Tech, 2 - Hum, 3 - Comb)");
					try {
						firstName = sc.next();
						lastName = sc.next();
						dayOfBirth = sc.next();
						specialization = sc.nextInt();				
						database.addStudent(firstName, lastName, dayOfBirth, specialization);
					} catch (NumberFormatException e) {
						System.out.println("Zadani neplatneho cisla v datu narozeni.");
					} catch (MyExceptions e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println("Zadan neplatny vstup.");
					}
					break;
					
				case 2:
					System.out.println("Zadejte ID studenta a znamku pro zapsani:");
					try {
						ID = sc.nextInt();
						mark = sc.nextInt();
						obj = database.findStudent(ID);
						if (obj instanceof Students) {
							obj.addMark(mark);
						}
					} catch (InputMismatchException e) {
						System.out.println("Zadan neplatny vstup.");
						sc.next();
	
					}
					
					break;
					
				case 3:
					System.out.println("Zadejte ID studenta pro smazani");
					try {
						ID = sc.nextInt();
						if (database.removeStudent(ID)) {
							System.out.println("Student s ID " + ID + " byl uspesne smazan");
						} else {
							System.out.println("Pri mazani studenta s ID " + ID + " doslo k chybe.");
						}
					} catch (InputMismatchException e) {
						System.out.println("Zadan neplatny vstup.");
					}
					break;
					
				case 4:
					System.out.println("Zadejte ID studenta pro vypis informaci");
					try {
						ID = sc.nextInt();
						obj = database.findStudent(ID);
						if (obj instanceof Students) {
							System.out.format("ID: %d, %s %s, narozen %d.%d.%d, studijni prumer %.2f\n",
									obj.getID(),
									obj.getFirstName(),
									obj.getLastName(),
									obj.getDateOfBirth()[0],
									obj.getDateOfBirth()[1],
									obj.getDateOfBirth()[2],
									obj.getAverage());
						} else {
							System.out.println("Student pod timto ID nenalezen");
						}
					}  catch (InputMismatchException e) {
						System.out.println("Zadan neplatny vstup.");
					}
					
					break;
					
				case 5:
					System.out.println("Zadejte ID studenta pro spusteni dovednosti");
					try {
						ID = sc.nextInt();				
						obj = database.findStudent(ID);
						if (obj instanceof Students) {
							obj.Skill();
						}
					}  catch (InputMismatchException e) {
						System.out.println("Zadan neplatny vstup.");
					}
					break;
					
				case 6:
					System.out.println("Zadejte obor (1 - Tech, 2 - Hum, 3 - Comb)");
					try {
						specialization = sc.nextInt();
						switch (specialization) {
						case 1:
							database.listOfStudents(Class.forName("me.proch240970.projektPC2T.students.TechnicalStudent"));
							break;
						case 2:
							database.listOfStudents(Class.forName("me.proch240970.projektPC2T.students.HumanitarianStudent"));
							break;
						case 3:
							database.listOfStudents(Class.forName("me.proch240970.projektPC2T.students.CombinedStudent"));
							break;
						}
					}  catch (InputMismatchException e) {
						System.out.println("Zadan neplatny vstup.");
					} catch (ClassNotFoundException e) {
						System.out.println("Nastala chyba v pojemnovani baliku a trid.");
					}
					break;
				
				case 7:
					System.out.println("Zadejte obor (1 - Tech, 2 - Hum, 3 - Comb)");
					float avg = 0;
					try {
						specialization = sc.nextInt();
						switch (specialization) {
						case 1:
							avg = database.getSpecsAverage(Class.forName("me.proch240970.projektPC2T.students.TechnicalStudent"));
							break;
						case 2:
							avg = database.getSpecsAverage(Class.forName("me.proch240970.projektPC2T.students.HumanitarianStudent"));
							break;
						case 3:
							avg = database.getSpecsAverage(Class.forName("me.proch240970.projektPC2T.students.CombinedStudent"));
							break;
						}
						System.out.println("Celkovy prumer pro dany obor je: " + avg);
					}  catch (InputMismatchException e) {
						System.out.println("Zadan neplatny vstup.");
					} catch (ClassNotFoundException e) {
						System.out.println("Nastala chyba v pojemnovani baliku a trid.");
					}
					break;
					
				case 8:
					System.out.println("Pocet studentu v Tech oboru: " + TechnicalStudent.getInstanceCount());
					System.out.println("Pocet studentu v Hum oboru: " + HumanitarianStudent.getInstanceCount());
					System.out.println("Pocet studentu v Comb oboru: " + CombinedStudent.getInstanceCount());
					break;
					
				case 9:
					try {
						database = FilesIO.LoadFromFile(database);
					} catch (NumberFormatException | MyExceptions e) {
						System.out.println("Chyba databazoveho souboru, nacteni se nezdarilo.");
					}
					break;
					
				case 10:
					FilesIO.SaveToFile(database);
					break;
					
				case 11:
					running = false;
					break;
				case 20:/*
					System.out.println("Case 3");
					database.addStudent("Martin", "Prochazka", "24.03.2002", 1);
					database.addStudent("Karel", "Vomacka", "24.03.2002", 1);
					database.addStudent("Karel", "Novak", "24.03.2002", 1);
					
					database.addStudent("Jirka", "Prochazka", "24.03.2002", 2);
					database.addStudent("Marek", "Vomacka", "24.03.2002", 2);
					database.addStudent("Pavel", "Novak", "24.03.2002", 2);*/
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Zadan neplatny vstup.");
				sc.next();
			}
		}
		sc.close();
		
	}

}
