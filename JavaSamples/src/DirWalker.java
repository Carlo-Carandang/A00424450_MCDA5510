import java.io.File;

public class DirWalker {

    public void walk( String path ) {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else
            {	
            		String fileName = f.getName();
            		int ln = fileName.length();
            		if(fileName.substring(ln-3, ln).compareTo("csv") == 0)
            		{
            			DirWalker.SimpleCsvParser(f.getAbsolutePath())
            		}
                System.out.println( "File:" + f.getAbsoluteFile() );
            }
        }
    }

    public static void main(String[] args) {
    	DirWalker fw = new DirWalker();
        fw.walk("/Users/carlocarandang/git/a00424450_mcda5510/JavaSamples/Sample Data" );
    }

}