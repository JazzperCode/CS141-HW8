package src;
public class PrintJobThread extends Thread
{	
	//  PrintJobThread handles printing an entire print request.  It must get a 
	//  free printer (blocking if the are all busy) then read sectors from the 
	//  disk and send to the printer - one at a time. A UserThread will create 
	//  and start a new PrintJobThread for each print request.  Note if each 
	//  PrintJob is not a new thread you will get very little parallelism in your OS.

	//Variables 
	int start, d; 
	StringBuffer fileToPrint;

	//Constructor
	public PrintJobThread(String fileName) throws InterruptedException
	{
		FileInfo f = OS141.diskManager.directoryManager.lookup(fileName);
		start = f.startingSector;
		d = f.diskNumber;
		int printerNumber = OS141.printerManager.request();
		StringBuffer line = new StringBuffer();
		for(int i = 0; i<f.fileLength;i++)
		{
			OS141.disks[d].read(start+i, line);
			OS141.printers[printerNumber].print(line);
			line.delete(0, line.length());
		}
		OS141.printerManager.release(printerNumber);
	}
}