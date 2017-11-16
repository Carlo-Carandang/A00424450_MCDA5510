import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class MergedFile
{
	static ArrayList<String> arr = new ArrayList<String>();
    public static void walk( String path )
    {
    	File root = new File( path );
        File[] list = root.listFiles();
        if (list == null) return;
        for ( File f : list )
        {
            if ( f.isDirectory() )
            {
                walk( f.getAbsolutePath() );
                System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else
            {	
            		String fileName = f.getName();
            		int ln = fileName.length();
            		if(fileName.substring(ln-3, ln).compareTo("csv") == 0)
            		{
            			MergedFile.SimpleCsvParser(f.getAbsolutePath());
            		}
                System.out.println( "File:" + f.getAbsoluteFile() );
            }
        }
    }
    
    public static void SimpleCsvParser( String path )
    {
    		Reader in;
    		try
    		{
    			in = new FileReader(path);
    			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
    			for (CSVRecord record : records)
    			{
    			    if(record.get(0).toString().compareTo("First Name") == 0)
    			    {
    			    		continue;
    			    }
    			    int len = record.size();
    			    String row = "";
    			    for(int i=0;i<len;i++)
    			    {
    			    		row = row + record.get(i);
    			    		if(i != len - 1)
    			    		{
    			    			row = row + ",";
    			    		}
    			    }
				arr.add(row);
    			}			
    			
    		} 
    		catch ( IOException e)
    		{
    			e.printStackTrace();    
		}
    }
    
    static Logger logger = Logger.getLogger("MyLog");
    
    @SuppressWarnings("null")
	public static void main(String[] args)
    {
    		final String Header = "First Name,Last Name,Street Number,Street,City,Province,Postal Code,Country,Phone Number,email Address";
		FileWriter fileWriter = null;
    		try
		{
			// This block configure the logger with handler and formatter
			FileHandler fh = new FileHandler("./MyLogFile.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		}
    		catch ( IOException e)
    		{
    			e.printStackTrace();    
		}
    		// read times
		final long startTimeRead = System.currentTimeMillis();
		MergedFile.walk("/Users/carlocarandang/git/a00424450_mcda5510/JavaSamples/Sample Data");
		final long endTimeRead = System.currentTimeMillis();
		System.out.println("Total execution time to read: " + (endTimeRead - startTimeRead) + " ms");
		
		// write files and write times
		final long startTimeWrite = System.currentTimeMillis();
		try
		{
			fileWriter = new FileWriter("records.csv");
			fileWriter.append(Header);
			fileWriter.append("\n");
			CSVRecord arr = null;
			for (int i = 0; i < arr.size() ; i++)
			{
				fileWriter.append(arr.get(i));
				fileWriter.append("\n");
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fileWriter.flush();
				fileWriter.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();	
			}
		}
		final long endTimeWrite = System.currentTimeMillis();
		System.out.println("Total execution time to write: " + (endTimeWrite - startTimeWrite) + " ms");
	}	
}