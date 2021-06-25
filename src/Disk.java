package src;
public class Disk {
	static final int NUM_SECTORS = 1024;
	StringBuffer sectors[] = new StringBuffer[NUM_SECTORS];

	// The constructor must allocate all the StringBuffers (one per sector)
	// when the Disk is created and must not allocate any after that
	public Disk() {
		for (int i = 0; i < NUM_SECTORS; i++) {
			sectors[i] = new StringBuffer();
		}
	}

	// Reads and writes each take 200 milliseconds,
	// so your read/write functions must sleep for
	// that amount of time before copying the data to/from any disk sector
	synchronized void write(int sector, StringBuffer data) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sectors[sector].append(data);
	}

	synchronized void read(int sector, StringBuffer data) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.append(sectors[sector]);
	}

}
