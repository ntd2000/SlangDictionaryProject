/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangdictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author NguyenThanhDat
 */
public class SlangDictionary {

    private HashMap<String, String> dictionary;

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

    public HashMap<String, String> timSlangWord(String keyword) throws IOException {
        this.docFile();
        if (!dictionary.isEmpty()) {
            HashMap<String, String> slangWords = new HashMap<>();
            dictionary.keySet().forEach((String slang) -> {
                if (slang.contains(keyword)) {
                    slangWords.put(slang, dictionary.get(slang));
                }
            });
            return slangWords;
        }
        return null;
    }
}
