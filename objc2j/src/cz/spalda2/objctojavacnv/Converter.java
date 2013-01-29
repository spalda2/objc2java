package cz.spalda2.objctojavacnv;

import java.io.File;
import java.io.IOException;

import org.antlr.runtime.RecognitionException;

/**
 * User: spalda2
 * Date: 18.06.12
 * Time: 9:35
 */

public class Converter {

    public static final boolean NOT_IFS = false;
    public static final boolean ONLY_IFS = true;

    public void convert(String pathIn, String pathOut) {

        convertInternal(pathIn,pathOut);
    }

    private void convertInternal(String pathIn, String pathOut) {
        File dir = new File(pathIn);
        File[] files = dir.listFiles();

        for (File f : files) {
            if (f.isDirectory()) {
            	String pOut = pathOut + f.getName() + File.separator;
                convertInternal(f.getAbsolutePath(),pOut);
            }
            if (f.getName().endsWith(".m") || f.getName().endsWith(".mm")) {
            	System.out.println(f.getAbsolutePath());
                try {
                	String name = f.getName();
                	name = name.replaceAll("\\..+", "");
                    ConverterObjc2Java.convert_m(name, f.getParent() + File.separator, pathOut);
                } catch (IOException e) {
                } catch (RecognitionException e) {
                	break;
                }
            }
        }

    }
}
