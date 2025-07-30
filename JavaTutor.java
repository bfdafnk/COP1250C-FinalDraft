/**
 *  Project Team: 	Nikhil Karri, Kevin Norman, Frank Roscoe
 *  Date:			07/20/2025
 *  Description:	Project – Summer 2025
					Write a menu driven Java application called “Java Tutor” and
						add features to teach basics of Java.
					Each Team member should have a module to work on. Provide the following
						features
					1) Login – User must enter an UserId and Password to access your
						application.
					2) Menu options to teach Basics of programming
						a. Include a Topic, Explanations, Simple Quizzes etc. Be unique.
					3) Log entries in a file to show user’s learning track
					4) Display the log entries
					5) The program must end if user decides to log out.
					
	IPO Chart:
		Input:		1) UserID: Name entered by user to represent login userID
					2) Password: Password for accessing the correct user logfile record
					3) Option: The menu number entered by user after successfully logging in
					4) Enter key: Keyboard key entered by user to traverse thru tutorial content
					5) Quiz answer:	Keyboard entry of digits 1 thru 4 to answer quiz questions
					6) Module learning text: For each of the four learning modules, hard-coded
						string data is loaded into memory for display of each module's learning text
					7) Quiz text: Fore each of the four learning module quizes, hard-coded text
						is loaded into memory to display each questions, multiple choice options,
						answer, and explanation of answer
					8) Log file text: For each registered user, the logfile text is read from the
						file system to populate data in a User object for display of tutorial status
		Processing:
					1) Create Scanner object to capture input from "System.in"
					2) Create User object to store user's progress for traversing thru the tutorial
					3) Create Timestamp object for the formatting of date/time strings used to track
						logfile entry timestamps and quiz completion timestamps
					4) Display tutorial welcome message
					5) Prompt user for userID and password; create new user or access existing user
					6) If existing user and overall module completion is false
						- open logfile
						- update User object member fields with status values stored in logfile
						- close logfile
					7) Display user menu
					8) Prompt user for menu option
					9) Execute tutor learning module if option 1 thru 4 selected
				   10) Quit program if EXIT option selected
				   11) For learning modules
				   		- Display learning module content
				   		- Quiz the user with 10 multiple choice questions
				   		- Update the quiz results in the User object and write to logfile
				   12) When all modules are complete, display completion message and end program
		Output:
					1) Program welcome message
					2) Program option menu
					3) Logfile stored in filesystem
					4) Module learning text (for all four modules)
					5) Quiz question, multiple choice options, answer and explanation for all four modules
					6) Status message based on User object data populated from logfile while user
						progresses thru the tutorial
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JavaTutor {
    
	// create Scanner object to capture System.in input entered by user
	public static Scanner scanner1 = new Scanner(System.in);
	// userID variable
	public static String username;
	// password variable
	public static String password;
	// instantiate User object with default initialization
	public static User user = new User();
	// instantiate Timestamp object for formatting of system timestamps
	public static Timestamp ts = new Timestamp();
		
	public static void main(String[] args) {

		// set boolean flag used to determine user's overall completion status of all modules
		boolean complete = false;
		// create boolean array to store completion status of each of the four modules
		boolean[] modcomplete = new boolean[4];
		// initialize the completion status array to false of each module
		for (int i = 1; i <= 4; i++)
			modcomplete[i-1] = false;
		String login = "false";
		
		// Flag used to signify user has not taken quiz yet
		final String NO_QUIZ = "00/00_00:00";
		// Variable for recording timestamp of quiz completions
		String quizDate = "";

		System.out.println("\nWelcome to the JAVA TUTORIAL program!\n");
		
		// create a User object with default constructor (default status of modules/quizzes)
		
		
		//validates user
		while (login.equals("false"))
		{
				//get username
				System.out.println("Please input your username or press only enter to exit\n");
				username = scanner1.nextLine();
				// call User mutator to store username in user object
				user.setUserID(username);
				//begins login
				login = loginProccess(username);
		}
		
		//begin modules
		
		while (login.equals("true") && complete == false)
		{
			//reads progress stored on file
			try
			{
				File fileObj = new File(username + ".txt");
				Scanner scannerObj = new Scanner(fileObj);
				//skips password line
				scannerObj.nextLine();
				
				for (int i = 1; i <= 4; i++)
				{
				// retrieve logfile entry with status info
				String line = scannerObj.nextLine();
				//System.out.println(line);
				// split logfile string into separate tokens using whitespace as separator
				String[] tokens = line.split(" ");
				// tokens[0] = timestamp
				// tokens[1] = module name
				// tokens[2] = completion status (true or false)
				// tokens[3] = QUIZ_SCORE constant
				// tokens[4] = quiz result (0 to 100)
				// tokens[5] = QUIZ_DATE constant
				// tokens[6] = quiz date/time completion
				// call User mutator setQuizScore() to store quiz score in User object 
				user.setQuizScore(i, Integer.parseInt(tokens[4]));
				// call User mutator setQuizTimestamp() to store quiz timestamp in User object
				user.setQuizTimestamp(i, tokens[6]);
				// check module completion status extracted from logfile
				if (tokens[2].equals("true"))
					modcomplete[i-1] = true;
					
				}
				
				// close Scanner object
				scannerObj.close();
				
				// update logfile using Timestamp getTimestamp() method and User object data
				// via class accessors for status of modules and quiz results
				FileWriter fwriter = new FileWriter(username + ".txt");
				fwriter.write(password + "\n");
				for (int i = 1; i <= 4; i++)
				{
				fwriter.write(ts.getTimestamp() + " Module-" + i + " " + modcomplete[i-1]  + " QUIZ_SCORE " + 
						user.getQuizScore(i) + " QUIZ_DATE " + user.getQuizTimestamp(i) + "\n");
				}
				fwriter.close();
			}
			catch(IOException e)
			{
				System.out.println("An Exception occurred!");
			}
			
			// display menu header text
			System.out.println("Please select a Java Tutor Module or choose to exit");
			System.out.println("   MENU OPTION                      MODULE COMPLETED?");
			System.out.println("-----------------------------------------------------");
			
			for (int i = 1; i <= 4; i++)
			{
			// format output
			// get quiz timestamp via User object's getQuizTimestamp() accessor method
			quizDate = user.getQuizTimestamp(i);
			// if quizDate equals NO_QUIZ flag, display module results without quiz_score and quiz_date
			if (quizDate.equals(NO_QUIZ))
				System.out.println(i + ". Module " + i + " - Java Data Types      " + " COMPLETE = " + modcomplete[i-1]);
			// if quizDate NOT equals NO_QUIZ flag, display module results with quiz_score and quiz_date
			else
				System.out.println(i + ". Module " + i + " - Java Data Types      " + " COMPLETE = " + modcomplete[i-1]  + " --> QUIZ_SCORE = " + 
						user.getQuizScore(i) + "% QUIZ_DATE = " + user.getQuizTimestamp(i));
			}
			
			System.out.println("5. Exit");
			System.out.println("-----------------------------------------------------");

			int option;
			option = scanner1.nextInt();
			scanner1.nextLine();
			
			
			switch (option)
			{	
				case 1:
					// call displayModulule_1() method with USER object argument and result from return value
					modcomplete[0] = Module_DataTypes.displayModule_1(user);
					break;
				case 2:
					// call displayModulule_2() method with USER object argument and result from return value
					modcomplete[1] = Module_Loops.displayModule_2(user);
					break;
				case 3:
					// call displayModulule_3() method with USER object argument and result from return value
					modcomplete[2] = Module_FileOperations.displayModule_3(user);
					break;
				case 4:
					// call displayModulule_4() method with USER object argument and result from return value
					modcomplete[3] = Module_Classes.displayModule_4(user);
					break;										
				case 5:
					// set exit flag to TRUE based on user input
					login = "exit";
					System.out.println("Thank you for using the Java Tutorial program. Good bye . . . ");
					break;
				default:
					System.out.println("Invalid Option");
					break;
			}		
			try
			{
			//updates logfile after taking module quiz
			FileWriter fwriter = new FileWriter(username + ".txt");
			fwriter.write(password + "\n");
			for (int i = 1; i <= 4; i++)
			{
			fwriter.write(ts.getTimestamp() + " Module-" + i + " " + modcomplete[i-1]  + " QUIZ_SCORE " + 
					user.getQuizScore(i) + " QUIZ_DATE " + user.getQuizTimestamp(i) + "\n");
			}
			fwriter.close();
			}

			catch(IOException e)
			{
				System.out.println("An Exception occurred!");
			}
			
			
			if (modcomplete[0] == true && modcomplete[1] == true && modcomplete[2] == true && modcomplete[3] == true)
				{
				complete = true;
				System.out.println("You have completed all the modules!");
				System.out.println("Thank you for using the Java Tutorial program. Good bye . . . ");
				}
		}
		scanner1.close();
	}

	private static String loginProccess (String username)
	{
		if (!username.equals(""))
		{
			try
			{
				FileWriter fwriter = new FileWriter(username + ".txt",true);
				File fileObj = new File(username + ".txt");
				Scanner scannerObj = new Scanner(fileObj);
					
				if (scannerObj.hasNextLine())
				{
					//password already exists, logging in
					System.out.println("Please input your password");
					password = scannerObj.nextLine();
					// call User mutator to store password in user object
					user.setPassword(password);
					String enteredPassword = scanner1.nextLine();
		            //checks password
					if (enteredPassword.equals(password)) 
		            {
		                System.out.println("Login successful!");
		                scannerObj.close();
						fwriter.close();
		                return "true";
		            }
		            else 
		            {
		                System.out.println("Invalid password.");
		                scannerObj.close();
						fwriter.close();
		                return "false";
		            }
				}
				else
				{
					//creating new user
					scannerObj.close();
					fwriter.close();
					return newUser();
				}
			}
			catch(IOException e)
			{
				System.out.println("An Exception occurred!");
				return "false";
			}
		}
		else
			return "exit";
	}
	
	private static String newUser ()
	{
		try
		{
			FileWriter fwriter = new FileWriter(username + ".txt",true);
			File fileObj = new File(username + ".txt");
			Scanner scannerObj = new Scanner(fileObj);
			//password does not exist
			System.out.println("New User detected, please select a password: ");
			password = scanner1.nextLine();
			//creates new blank file for new user
			fwriter.write(password + "\n");
			for (int i = 1; i <= 4; i++)
                fwriter.write("0 Module-" + i + " false QUIZ_SCORE 0 QUIZ_DATE 00/00_00:00\n");							
			scannerObj.close();
			fwriter.close();
			return "true";
		}
		catch(IOException e)
		{
			System.out.println("An Exception occurred!");
			return "false";
		}
	}
	
	
}