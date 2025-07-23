import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JavaTutor {
    
	public static Scanner scanner1 = new Scanner(System.in);
	public static String username;
	public static String password;
		
	public static void main(String[] args) {

		Timestamp ts = new Timestamp();
		
		boolean complete = false;
		boolean mod1complete = false;
		boolean mod2complete = false;
		boolean mod3complete = false;
		boolean mod4complete = false;
		boolean exit = false;
		boolean login = false;
		final int MOD_1 = 1;
		final int MOD_2 = 2;
		final int MOD_3 = 3;
		final int MOD_4 = 4;
		final String NO_QUIZ = "00/00_00:00";
		String quizDate = "";

		System.out.println("\nWelcome to the JAVA TUTORIAL program!\n");
		User user = new User();
		
		//validates user
		while (login == false && exit == false)
		{
				//get username
				System.out.println("Please input your username or press only enter to exit\n");
				username = scanner1.nextLine();
				user.setUserID(username);
				if (!username.equals(""))
				{
					try
					{
						FileWriter fwriter = new FileWriter(username + ".txt",true);
						File fileObj = new File(username + ".txt");
						Scanner scannerObj = new Scanner(fileObj);
							
						if (scannerObj.hasNextLine())
						{
							//password already exists
							System.out.println("Please input your password");
							password = scannerObj.nextLine();
							user.setPassword(password);
							if (scanner1.nextLine().equals(password))
							{
								login = true;
								System.out.println("Login successful");
							}
							else
								System.out.println("Invalid password");
						}
						else
						{
							try
							{
								//password does not exist
								System.out.println("New User detected, please select a password: ");
								password = scanner1.nextLine();
								fwriter.write(password + "\n");
								fwriter.write(ts.getTimestamp() + " Module-1 " + mod1complete  + " QUIZ_SCORE=" + 
										user.getQuizScore(MOD_1) + " QUIZ_DATE=" + user.getQuizTimestamp(MOD_1) + "\n");
								fwriter.write(ts.getTimestamp() + " Module-2 " + mod2complete  + " QUIZ_SCORE=" + 
										user.getQuizScore(MOD_2) + " QUIZ_DATE=" + user.getQuizTimestamp(MOD_2) + "\n");
								fwriter.write(ts.getTimestamp() + " Module-3 " + mod3complete  + " QUIZ_SCORE=" + 
										user.getQuizScore(MOD_3) + " QUIZ_DATE=" + user.getQuizTimestamp(MOD_3) + "\n");
								fwriter.write(ts.getTimestamp() + " Module-4 " + mod4complete  +  " QUIZ_SCORE=" + 
										user.getQuizScore(MOD_4) + " QUIZ_DATE=" + user.getQuizTimestamp(MOD_4) + "\n");								
								login = true;
							}
							catch(IOException e)
							{
								System.out.println("An Exception occurred!");
							}

						}
						scannerObj.close();
						fwriter.close();
					}
					catch(IOException e)
					{
						System.out.println("An Exception occurred!");
					}
				}
				else
					exit = true;
		}
		
		//begin modules
		
		while (login == true && exit == false && complete == false)
		{
			//reads progress stored on file
			try
			{
				File fileObj = new File(username + ".txt");
				Scanner scannerObj = new Scanner(fileObj);
				//skips password line
				scannerObj.nextLine();
				//mod1
				String line = scannerObj.nextLine();
				if (line.charAt(29) == 't')
					mod1complete = true;
				//mod2
				line = scannerObj.nextLine();
				if (line.charAt(29) == 't')
					mod2complete = true;
				//mod3
				line = scannerObj.nextLine();
				if (line.charAt(29) == 't')
					mod3complete = true;
				//mod4
				line = scannerObj.nextLine();
				if (line.charAt(29) == 't')
					mod4complete = true;
				scannerObj.close();
				
				//updates folder
				FileWriter fwriter = new FileWriter(username + ".txt");
				fwriter.write(password + "\n");
				fwriter.write(ts.getTimestamp() + " Module-1 " + mod1complete  + " QUIZ_SCORE=" + 
						user.getQuizScore(MOD_1) + " QUIZ_DATE=" + user.getQuizTimestamp(MOD_1) + "\n");
				fwriter.write(ts.getTimestamp() + " Module-2 " + mod2complete  + " QUIZ_SCORE=" + 
						user.getQuizScore(MOD_2) + " QUIZ_DATE=" + user.getQuizTimestamp(MOD_2) + "\n");
				fwriter.write(ts.getTimestamp() + " Module-3 " + mod3complete  + " QUIZ_SCORE=" + 
						user.getQuizScore(MOD_3) + " QUIZ_DATE=" + user.getQuizTimestamp(MOD_3) + "\n");
				fwriter.write(ts.getTimestamp() + " Module-4 " + mod4complete  +  " QUIZ_SCORE=" + 
						user.getQuizScore(MOD_4) + " QUIZ_DATE=" + user.getQuizTimestamp(MOD_4) + "\n");

				fwriter.close();
			}
			catch(IOException e)
			{
				System.out.println("An Exception occurred!");
			}
			
			System.out.println("Please select a Java Tutor Module or choose to exit");
			System.out.println("   MENU OPTION                      MODULE COMPLETED?");
			System.out.println("-----------------------------------------------------");
			
			// format MOD1 output
			quizDate = user.getQuizTimestamp(MOD_1);
			if (quizDate.equals(NO_QUIZ))
				System.out.println("1. Module 1 - Java Data Types      " + " COMPLETE = " + mod1complete);
			else
				System.out.println("1. Module 1 - Java Data Types      " + " COMPLETE = " + mod1complete  + " --> QUIZ_SCORE = " + 
						user.getQuizScore(MOD_1) + "% QUIZ_DATE = " + user.getQuizTimestamp(MOD_1));
			
			// format MOD2 output
			quizDate = user.getQuizTimestamp(MOD_2);
			if (quizDate.equals(NO_QUIZ))
				System.out.println("2. Module 2 - Java Loops           " + " COMPLETE = " + mod1complete);
			else
				System.out.println("2. Module 2 - Java Loops           " + " COMPLETE = " + mod2complete  + " --> QUIZ_SCORE = " + 
						user.getQuizScore(MOD_2) + "% QUIZ_DATE = " + user.getQuizTimestamp(MOD_2));
			
			// format MOD3 output
			quizDate = user.getQuizTimestamp(MOD_3);
			if (quizDate.equals(NO_QUIZ))
				System.out.println("3. Module 3 - File Operations      " + " COMPLETE = " + mod3complete);
			else
				System.out.println("3. Module 3 - File Operations      " + " COMPLETE = " + mod3complete  + " --> QUIZ_SCORE = " + 
						user.getQuizScore(MOD_3) + "% QUIZ_DATE = " + user.getQuizTimestamp(MOD_3));
	
			
			// format MOD4 output
			quizDate = user.getQuizTimestamp(MOD_4);
			if (quizDate.equals(NO_QUIZ))
				System.out.println("4. Module 4 - Java Classes         " + " COMPLETE = " + mod4complete);
			else
				System.out.println("4. Module 4 - Java Classes         " + " COMPLETE = " + mod4complete  + " --> QUIZ_SCORE = " + 
						user.getQuizScore(MOD_4) + "% QUIZ_DATE = " + user.getQuizTimestamp(MOD_4));
	
			System.out.println("5. Exit");
			System.out.println("-----------------------------------------------------");
			
			int option;
			option = scanner1.nextInt();
			scanner1.nextLine();
			
			
			switch (option)
			{	
				case 1:
					//Module1 mod1 = new Module1();
					//mod1complete = mod1.Start();
					break;
				case 2:
					//Module2 mod2 = new Module2();
					//mod2complete = mod2.Start();
					break;
				case 3:
					//Module3 mod3 = new Module3();
					//mod3complete = mod3.Start();
					
					mod3complete = Module_FileOperations.displayModule_3(user);
					break;
				case 4:
					//Module4 mod4 = new Module4();
					//mod4complete = mod4.Start();
					mod4complete = Module_Classes.displayModule_4(user);
					break;
										
				case 5:
					exit = true;
					System.out.println("Thank you for using the Java Tutorial program. Good bye . . . ");
					break;
				default:
					System.out.println("Invalid Option");
					break;
			}			
			
			
			if (mod1complete == true && mod2complete == true && mod3complete == true && mod4complete == true)
				{
				complete = true;
				System.out.println("You have completed all the modules!");
				
				}

		}
		scanner1.close();
	}

}
