package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserThread extends Thread {
	// Each user will read from a file named USERi where i is the index of this
	// user. Each USERi file will contain a series of the following three commands:
	// .save X
	// .end
	// .print X
	// All lines between a .save and a .end is part of the data in file X.
	// Each line in the file takes up a sector in the disk. UserThread will
	// handle saving files to disk, but it will create a new PrintJobThread to
	// handle each print request.
	// Each UserThread may have only one local StringBuffer to hold the current line
	// of text. If you read
	// a line from the input file, you must immediately copy that String into this
	// single StringBuffer
	// owned by the UserThread. The UserThread interprets the command in the
	// StringBuffer or saves the
	// StringBuffer to a disk as appropriate for the line of input.

	// Variables
	String fileName;
	StringBuffer line;
	int index; 
	static boolean inUse;

	// Constructor
	public UserThread(String fileName) {
		this.fileName = fileName;
		line = new StringBuffer();
		index = fileName.charAt(fileName.length() - 1 );
		System.out.println(fileName);
	}

	@Override
	public void run() {
		try {
			processUserCommands(fileName);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processUserCommands(String inputFile) throws InterruptedException
	{
		try
		{
			FileReader fr = new FileReader("./inputs/" + inputFile);
			BufferedReader br = new BufferedReader(fr);
			String str;
			while((str = br.readLine()) != null)
			{
				this.line.append(str);
				String command = str.split(" ")[0];
				switch(command)
				{
					case ".save": 
						this.line.delete(0, this.line.length());
						saveFile(str.split(" ")[1], inputFile, br);
						break;
					case ".print":
						//printFile(argumentOf(line)) should be one-liner, starts a new PrintJobThread
						new PrintJobThread(str.split(" ")[1]).start();
						break;
					default: 
						System.out.println("Unknown command: " + command); 
				}
				this.line.delete(0, this.line.length());
			}
			br.close();
		}
		catch (IOException e) { 
			e.printStackTrace();
		}
	}

	public FileInfo makeFileInfo(int diskNumber, int startingSector, int fileLength)
	{
		return new FileInfo(diskNumber,startingSector,fileLength);
	}

	synchronized void saveFile(String name, String inputFile, BufferedReader br) throws InterruptedException
	{
		int diskNumber = OS141.diskManager.request();
		int offset = OS141.diskManager.getNextFreeSectorOnDisk(diskNumber);
		int fileLines = 0;
		String str;
		try {
			while ((str = br.readLine()) != null) {
				this.line.append(str);
				if(str.equals(".end"))
				{
					OS141.diskManager.directoryManager.enter(name, makeFileInfo(diskNumber, offset, fileLines));
					break;
				}
				else {
					OS141.disks[diskNumber].write(offset + fileLines, line);
					fileLines++; 
				}
				this.line.delete(0, this.line.length());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OS141.diskManager.setNextFreeSectorOnDisk(diskNumber, offset+fileLines);
		OS141.diskManager.release(diskNumber);
	}
}