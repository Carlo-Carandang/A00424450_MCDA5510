import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class MergedFile
{

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
    			    obj.add(row);
    			}			
    			
    		} 
    		catch ( IOException e)
    		{
    			e.printStackTrace();    
		}
    }
}