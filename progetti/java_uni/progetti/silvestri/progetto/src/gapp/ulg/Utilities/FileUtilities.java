package gapp.ulg.Utilities;

import java.io.*;
import java.util.*;

/**
 * Created by Edoardo on 24/08/2016.
 */
public class FileUtilities {
    public static boolean overwriteFile(String filepath, String fileContent){
        try {

            FileWriter fstreamWrite = new FileWriter(filepath);
            BufferedWriter out = new BufferedWriter(fstreamWrite);
            out.write(fileContent);
            out.close();

        } catch (IOException e) {return false; }

        return true;
    }

    public static boolean editParam(String filepath, String paramName, String paramValue){
        try {
            InputStream fileStream = FileUtilities.class.getResourceAsStream(filepath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream));

            String currentParam;
            StringBuilder fileContent = new StringBuilder();

            boolean paramFound = false;

            while ((currentParam = bufferedReader.readLine()) != null) {
                String param[] = currentParam.split("(\\s|\\s)");
                if (param.length > 0) {
                    if (param[0].equals(paramName)) {
                        paramFound = true;
                        param[1] = paramValue;
                        String updatedParam = param[0] + " | " + param[1];
                        fileContent.append(updatedParam);
                        fileContent.append("\n");
                    } else {
                        fileContent.append(currentParam);
                        fileContent.append("\n");
                    }
                }
            }
            if(!paramFound) return false;
            fileStream.close();
            //FileWriter fileWriter = new FileWriter("/resources/config.rc");
            //BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(FileUtilities.class.getResource(filepath).toURI()))));
            bufferedWriter.write(fileContent.toString());
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static ArrayList<String> readParamValue(String filepath, String paramName){
        try {
            InputStream input = FileUtilities.class.getResourceAsStream(filepath);
            //FileInputStream fileStream = new FileInputStream(filepath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));

            String currentParam;

            ArrayList<String> paramSeeked = null;

            while ((currentParam = bufferedReader.readLine()) != null) {
                String param[] = currentParam.split("(\\s|\\s)");
                if (param.length > 0) {
                    if (param[0].equals(paramName)){
                        paramSeeked = new ArrayList<>();
                        String[] allValues = param[2].split(",");
                        for(String value : allValues){
                            paramSeeked.add(value);
                        }
                    }
                }
                else return null;
            }
            bufferedReader.close();
            //fileStream.close();
            if(paramSeeked == null) return null;
            return paramSeeked;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String readTranslation(String filepath, int langIndex, String paramName){
        try {
            InputStream fileStream = FileUtilities.class.getResourceAsStream(filepath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream));

            String currentParam;

            String paramSeeked = null;

            while ((currentParam = bufferedReader.readLine()) != null) {
                String param[] = currentParam.split("(\\s\\u007c\\s){1}");
                if (param.length > 0) {
                    if (param[0].equals(paramName)){
                        if(langIndex == 0) return param[0];
                        else return param[1];
                    }
                }
                else return null;
            }
            fileStream.close();
            if(paramSeeked == null) return null;
            return paramSeeked;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String> readAllTranslations(String filepath) {
        try {
            FileInputStream fileStream = new FileInputStream(filepath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream));

            HashMap<String,String> mappa = new HashMap<>();
            String currentParam;

            while ((currentParam = bufferedReader.readLine()) != null) {
                String param[] = currentParam.split("(\\s\\u007c\\s){1}");
                if (param.length > 0) {
                    mappa.put(param[0], param[1]);
                }
            }
            fileStream.close();
            return mappa;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

}
