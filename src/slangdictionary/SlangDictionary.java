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
import java.util.HashMap;

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
            BufferedWriter buffer = new BufferedWriter(new FileWriter("slang.txt", true));
            dictionary.replace(slang, definition);
            for (String key : dictionary.keySet()) {
                buffer.write("\r\n" + key + "`" + dictionary.get(key));
            }
            buffer.close();
        } catch (IOException ex) {
            return -1;
        }
        return 1;
    }
}
