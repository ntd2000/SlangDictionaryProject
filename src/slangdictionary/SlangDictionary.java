/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangdictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author NguyenThanhDat
 */
public class SlangDictionary {

    private HashMap<String, String> dictionary = new HashMap<>();

    public HashMap<String, String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    public void docFile() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("slang.txt"));
        String line;
        dictionary = new HashMap<>();
        while ((line = br.readLine()) != null) {
            if (line.contains("`")) {
                String[] words = line.split("`");
                dictionary.put(words[0], words[1]);
            }
        }
    }

    public HashMap<String, String> timTheoSlangWord(String keyword) throws IOException {
        if (dictionary.isEmpty()) {
            this.docFile();
        }
        HashMap<String, String> slangWords = new HashMap<>();
        dictionary.keySet().forEach((String slang) -> {
            if (slang.equals(keyword)) {
                slangWords.put(slang, dictionary.get(slang));
            }
        });
        return slangWords;
    }

    public HashMap<String, String> timTheoDefinition(String keyword) throws IOException {
        if (dictionary.isEmpty()) {
            this.docFile();
        }
        HashMap<String, String> slangWords = new HashMap<>();
        dictionary.entrySet().forEach(entry -> {
            String slang = entry.getKey();
            String definition = entry.getValue();
            if (definition.contains(keyword)) {
                slangWords.put(slang, definition);
            }
        });
        return slangWords;
    }

    public int themSlangWord(String slang, String definition) throws IOException {
        if (dictionary.isEmpty()) {
            this.docFile();
        }
        try {
            BufferedWriter buffer = new BufferedWriter(new FileWriter("slang.txt", true));
            buffer.write("\r\n" + slang + "`" + definition);
            dictionary.put(slang, definition);
            buffer.close();
        } catch (IOException ex) {
            return -1;
        }
        return 1;
    }

    public int suaSlangWord(String slang, String definition) throws IOException {
        if (dictionary.isEmpty()) {
            this.docFile();
        }
        try {
            BufferedWriter buffer = new BufferedWriter(new FileWriter("slang.txt"));
            dictionary.replace(slang, definition);
            for (String key : dictionary.keySet()) {
                buffer.write(key + "`" + dictionary.get(key) + "\r\n");
            }
            buffer.close();
        } catch (IOException ex) {
            return -1;
        }
        return 1;
    }

    public int xoaSlangWord(String deleteSlang) throws IOException {
        if (dictionary.isEmpty()) {
            this.docFile();
        }
        try {
            BufferedWriter buffer = new BufferedWriter(new FileWriter("slang.txt"));
            for (String slang : dictionary.keySet()) {
                if (slang.equals(deleteSlang)) {
                    continue;
                }
                buffer.write(slang + "`" + dictionary.get(slang) + "\r\n");
            }
            buffer.close();
            dictionary.remove(deleteSlang);
        } catch (IOException ex) {
            return -1;
        }
        return 1;
    }

    public int resetSlangWords() {
        try {
            BufferedReader readBF = new BufferedReader(new FileReader("slang_origin.txt"));
            BufferedWriter writeBF = new BufferedWriter(new FileWriter("slang.txt"));
            String line;
            while ((line = readBF.readLine()) != null) {
                line += "\r\n";
                writeBF.append(line);
            }
            writeBF.flush();
            writeBF.close();
            readBF.close();
            return 1;
        } catch (IOException ex) {
            return -1;
        }
    }
    
    public void randomSlangWord() throws IOException{
        if (dictionary.isEmpty()) {
            this.docFile();
        }
        Random generator = new Random();
        ArrayList<String> list = new ArrayList<>();
        for(String slang : dictionary.keySet()){
            list.add(slang);
        }
        int index = generator.nextInt(dictionary.size());
        System.out.println(list.get(index) + " = " +dictionary.get(list.get(index)));
    }
}
