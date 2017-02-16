package com.geneology;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.File;

/**
 * For all local html files, transform them into json blobs
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String in = System.getProperty("in");
        String out = System.getProperty("out");
        String log = System.getProperty("log");
        System.out.println( in );
        System.out.println( out );
        System.out.println( log );
        try {
            MathematicianParser parser = new MathematicianParser();
            Mathematician mathematician = parser.parse(in);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(out), mathematician);
        } catch( Exception ex) {
            ex.printStackTrace();
        }
    }
}
