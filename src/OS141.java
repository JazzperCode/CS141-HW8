package src;
public class OS141 {

	//Variables 
	private static int NUM_USERS, NUM_DISKS, NUM_PRINTERS;
	public static String userFileNames[]; 
	public static UserThread users[]; 
	public static Disk disks[];
	public static Printer printers[];
	public static DiskManager diskManager;
	public static  PrinterManager printerManager;
	static OS141 myOS;

	public static void main(String args[])
	{	

		myOS = new OS141(args); 
		for (int i=0;i<NUM_USERS;++i)
		{
			users[i].start();
		}
	}

	//Constructor 
	public OS141(String args[])
	{
		//Initialize CommandLine Args
		NUM_USERS = Integer.valueOf(args[0].substring(1));
		NUM_DISKS = Integer.valueOf(args[NUM_USERS+1].substring(1));
		NUM_PRINTERS = Integer.valueOf(args[NUM_USERS+2].substring(1));
		// NUM_USERS = 1;
		// NUM_DISKS = 2;
		// NUM_PRINTERS = 1;
		userFileNames = new String[NUM_USERS];
		users = new UserThread[NUM_USERS];
		disks = new Disk[NUM_DISKS];
		printers = new Printer[NUM_PRINTERS];
		diskManager = new DiskManager(NUM_DISKS);
		printerManager = new PrinterManager(NUM_PRINTERS);
		configure(args);
	}


	private void configure(String args[])
	{
		//Populate userFileNames array with users given
		for(int i = 0; i < NUM_USERS; i++)
		{
			userFileNames[i] = args[i+1];
			// userFileNames[i] = "USER" + i+1;
		}

		//Populate disks array
		for(int i = 0; i < NUM_DISKS; i++)
		{
			disks[i] = new Disk();
		}

		//Populate printers array
		for(int i = 0; i < NUM_PRINTERS; i++)
		{
			printers[i] = new Printer(i+1); 
		}

		//Populate user
		for(int i = 0; i< NUM_USERS; i++)
		{
			users[i] = new UserThread(userFileNames[i]);
		}
	}
}