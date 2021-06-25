package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Printer {
	// Each printer will write data to a file named PRINTERi where i is the index of
	// this printer (starting at 1).
	// A printer can only handle one line of text at a time. It will take 2750
	// milliseconds to print one line;
	// the thread needs to sleep to simulate this delay before the printing job
	// finishes.
	int index;
	File outFile;

	public Printer(int index) {
		this.index = index;
		outFile = new File("./PRINTER" + index);
		try {
			outFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void print(StringBuffer data) {
		try {
			Thread.sleep(2750);
			File outFile = new File("./PRINTER" + index);
			outFile.createNewFile();
			BufferedWriter output = new BufferedWriter(new FileWriter("./PRINTER" + index,true));
			output.newLine();
			output.write(data.toString());
			output.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  // call sleep
}
