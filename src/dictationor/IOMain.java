/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictationor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public interface IOMain {
    
    public static final ArrayList<String> WORDLIST =new ArrayList<>(8500);
    
    /**
     * write a line of text to the indicated file. 
     * @param input text to write
     * @param location Relative path of file, will create new file if doesn't exist
     * @param append <code>false</code> to clear the file before writes
     */
    public static void writeLine(String input, String location, boolean append){
        write(new String[]{input}, location, append);
    }
    /**
     * write a line of text to the indicated file. 
     * @param input text to write, every element is a new line
     * @param location Relative path of file, will create new file if doesn't exist
     * @param append <code>false</code> to clear the file before writes
     */
    public static void write(String[] input, String location, boolean append){
        String str = System.getProperty("line.separator");
        try {
            File writeFile =new File(location);
            if(!writeFile.exists()){
                writeFile.createNewFile();
            }
            FileWriter fw = new FileWriter(writeFile,append);
            for(String x:input){
                fw.write(x);
                fw.write(str);
            }
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * append a word to the word list. 
     * @param input word to append
     */
    public static void appendWord(String input){
        writeLine(input,"tools/WordList.txt", true);
    }
    
    /**
     * read the word list to the prepared ArrayList. 
     */
    public static void readWordList(){
        try {
            FileReader fr = new FileReader(new File("tools/WordList.txt"));
            BufferedReader br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
                WORDLIST.add(str);
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
     public static void deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }
    
}
