package com.geneology;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;

/**
 * For all local html files, transform them into json blobs
 */
public class App 
{
    public static void main( String[] args )
    {
        String in = System.getProperty("in");
        String out = System.getProperty("out");
        System.out.println( in );
        System.out.println( out );
        File outDir = new File(out);
        String[] inFiles = new String[0];
        if (outDir.isDirectory()) {
            inFiles = getNamesOfHtmlFiles(in);
        }
        for (String fileIn : inFiles) {
            String outFile = getOutFile(fileIn, out);
            try {
                MathematicianParser parser = new MathematicianParser();
                Mathematician mathematician = parser.parse(fileIn);
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(new File(outFile), mathematician);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String getOutFile(String inFile, String outDir) {
        File file = new File(inFile);
        if (file.exists() && file.isFile()) {
            String name = file.getName();
            return outDir + "/" + name.replace(".html","") + ".json";
        }
        return null;
    }

    public static String[] getNamesOfHtmlFiles(String path) {
        File file = new File(path);
        if(file.exists() && file.isDirectory()) {
            String[] files = file.list(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".html");
                }
            });
            LinkedList<String> result = new LinkedList<String>();
            for (String fileName : files) {
                result.add(path + "/" + fileName);
            }
            return result.toArray(new String[result.size()]);
        }
        if(file.exists() && file.isFile()) {
            return new String[]{path};
        }
        return new String[]{};
    }
}
