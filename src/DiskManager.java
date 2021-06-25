package src;
public class DiskManager extends ResourceManager
{
    //Variables 
    DirectoryManager directoryManager;
    int[] nextFreeSectors;

    //numberOfItems refers to NUM_DISK
    //Constructor
    public DiskManager(int numberOfItems)
    {
        super(numberOfItems);
        directoryManager = new DirectoryManager();
        nextFreeSectors = new int[numberOfItems];
        for(int i = 0; i<numberOfItems; i++) 
        {
            nextFreeSectors[i] = 0;
        }
    }

    public int getNextFreeSectorOnDisk(int DiskNumber)
    {
        return nextFreeSectors[DiskNumber];
    }

    public void setNextFreeSectorOnDisk(int DiskNumber, int nextFreeSector){
        nextFreeSectors[DiskNumber] = nextFreeSector;
    };
}