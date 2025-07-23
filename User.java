
public class User {

	private String userID = "";
	private String password = "";
	private String moduleTimestamp[];
	private int moduleLessonNumber[];
	private String quizTimestamp[];
	private int quizScore[];
	
	public User()
	{
		userID = "";
		password = "";
		 // Initialize arrays with size 4
        moduleTimestamp = new String[4];
        moduleLessonNumber = new int[4];
        quizTimestamp = new String[4];
        quizScore = new int[4];
        
        // Set default values
        /*for (int i = 0; i < 4; i++) {
            moduleTimestamp[i] = "";
            moduleLessonNumber[i] = 0;
            quizTimestamp[i] = "";
            quizScore[i] = 0;
        }*/
        for (int i = 0; i < 4; i++) {
            moduleTimestamp[i] = "00/00_00:00";
            moduleLessonNumber[i] = 0;
            quizTimestamp[i] = "00/00_00:00";
            quizScore[i] = 0;
        }
	}
	
	public User(String u, String p, String mts[], int mll[], String qts[], int qs[])
	{
		userID = u;
		password = p;
		 // Initialize arrays
        moduleTimestamp = new String[4];
        moduleLessonNumber = new int[4];
        quizTimestamp = new String[4];
        quizScore = new int[4];

        for (int i = 0; i < 4; i++) {
            moduleTimestamp[i] = mts[i];
            moduleLessonNumber[i] = mll[i];
            quizTimestamp[i] = qts[i];
            quizScore[i] = qs[i];
        }
	}
	
	public User(String u, String p)
	{
		userID = u;
		password = p;
		// Initialize arrays
        moduleTimestamp = new String[4];
        moduleLessonNumber = new int[4];
        quizTimestamp = new String[4];
        quizScore = new int[4];

        for (int i = 0; i < 4; i++) {
            moduleTimestamp[i] = "00/00_00:00";
            moduleLessonNumber[i] = 0;
            quizTimestamp[i] = "00/00_00:00";
            quizScore[i] = 0;
        }
	}
	
	// mutator methods
	public void setUserID(String uid)
	{
		userID = uid;
	}
	
	public void setPassword(String pw)
	{
		password = pw;
	}
	
	public void setModuleTimestamp(int module, String ts)
	{
		moduleTimestamp[module-1] = ts;
	}
	
	public void setModuleLessonNumber(int module, int ln)
	{
		moduleLessonNumber[module-1] = ln;
	}
	
	public void setQuizTimestamp(int module, String ts)
	{
		moduleTimestamp[module-1] = ts;
	}
	
	public void setQuizScore(int module, int qs)
	{
		quizScore[module-1] = qs;
	}
	
	// accessor methods
	public String getUserID()
	{
		return userID;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getModuleTimestamp(int module)
	{
		return moduleTimestamp[module-1];
	}
	
	public int getModuleLessonNumber(int module)
	{
		return moduleLessonNumber[module-1];
	}
	
	public String getQuizTimestamp(int module)
	{
		return moduleTimestamp[module-1];
	}
	
	public int getQuizScore(int module)
	{
		return quizScore[module-1];
	}
}
