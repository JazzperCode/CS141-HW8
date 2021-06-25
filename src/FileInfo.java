package src;
public class FileInfo {
	//  A FileInfo object will hold the disk number, length of the file (in sectors), 
	//  and the index of the starting sector (i.e. which sector the first line of the 
	//  file is in). This FileInfo will be used in the DirectoryManager to hold meta 
	//  information about a file during saving as well as to inform a PrintJobThread when printing.
	
	//Variables
	public int diskNumber;
	public int startingSector;
	public int fileLength;

	//Constructor 
	public FileInfo(int diskNumber, int startingSector, int fileLength)
	{
		this.diskNumber = diskNumber;
		this.startingSector = startingSector;
		this.fileLength = fileLength;
	}
}
