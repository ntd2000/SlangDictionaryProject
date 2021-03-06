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
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

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
            if (dictionary.containsKey(slang)) {
                return 2;
            } else {
                dictionary.put(slang, definition);
            }
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
            if (dictionary.containsKey(slang)) {
                BufferedWriter buffer = new BufferedWriter(new FileWriter("slang.txt"));
                dictionary.replace(slang, definition);
                for (String key : dictionary.keySet()) {
                    buffer.write(key + "`" + dictionary.get(key) + "\r\n");
                }
                buffer.close();
            } else {
                return 2;
            }
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
            if (dictionary.containsKey(deleteSlang)) {
                BufferedWriter buffer = new BufferedWriter(new FileWriter("slang.txt"));
                for (String slang : dictionary.keySet()) {
                    if (slang.equals(deleteSlang)) {
                        continue;
                    }
                    buffer.write(slang + "`" + dictionary.get(slang) + "\r\n");
                }
                buffer.close();
                dictionary.remove(deleteSlang);
            }else return 2;
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

    public String randomSlangWord() throws IOException {
        if (dictionary.isEmpty()) {
            this.docFile();
        }
        Random generator = new Random();
        ArrayList<String> list = new ArrayList<>();
        for (String slang : dictionary.keySet()) {
            list.add(slang);
        }
        int index = generator.nextInt(dictionary.size());
        return list.get(index);
    }

    public String randomDefinition() throws IOException {
        if (dictionary.isEmpty()) {
            this.docFile();
        }
        Random generator = new Random();
        ArrayList<String> list = new ArrayList<>();
        for (String slang : dictionary.values()) {
            list.add(slang);
        }
        int index = generator.nextInt(dictionary.size());
        return list.get(index);
    }

    public void quizSlangWord() throws IOException {
        Scanner scanner = new Scanner(System.in);
        if (dictionary.isEmpty()) {
            this.docFile();
        }
        String randomSlang = randomSlangWord();
        ArrayList<String> listDapAn = new ArrayList<>();
        listDapAn.add(dictionary.get(randomSlang));
        for (int i = 0; i < 3; i++) {
            listDapAn.add(randomDefinition());
        }
        Collections.shuffle(listDapAn);
        System.out.println("Difinition cua " + randomSlang + " la gi?(Co hoi chi co 1 lan)");
        for (int i = 0; i < listDapAn.size(); i++) {
            System.out.println((i + 1) + ". " + listDapAn.get(i));
        }
        boolean check = true;
        String dapAnChon = null;
        do {
            System.out.print("Moi ban chon(1 - 4): ");
            int chon = Integer.parseInt(scanner.nextLine());
            switch (chon) {
                case 1:
                    dapAnChon = listDapAn.get(chon - 1);
                    check = false;
                    break;
                case 2:
                    dapAnChon = listDapAn.get(chon - 1);
                    check = false;
                    break;
                case 3:
                    dapAnChon = listDapAn.get(chon - 1);
                    check = false;
                    break;
                case 4:
                    dapAnChon = listDapAn.get(chon - 1);
                    check = false;
                    break;
                default:
                    System.out.println("Ban chi co the chon tu 1 - 4!");
                    break;
            };
        } while (check);
        if (dapAnChon.equals(dictionary.get(randomSlang))) {
            System.out.println("Chuc mung! Ban da chon dung!");
        } else {
            System.out.println("Rat tiec! Ban da chon sai!");
        }
    }

    public void quizDefinition() throws IOException {
        Scanner scanner = new Scanner(System.in);
        if (dictionary.isEmpty()) {
            this.docFile();
        }
        String randomDefi = randomDefinition();
        ArrayList<String> listSlang = new ArrayList<>();
        for (String slang : dictionary.keySet()) {
            if (dictionary.get(slang).equals(randomDefi)) {
                listSlang.add(slang);
                break;
            }
        }
        for (int i = 0; i < 3; i++) {
            listSlang.add(randomSlangWord());
        }
        Collections.shuffle(listSlang);
        System.out.println(randomDefi + " la definition cua slang word nao?(Co hoi chi co 1 lan)");
        for (int i = 0; i < listSlang.size(); i++) {
            System.out.println((i + 1) + ". " + listSlang.get(i));
        }
        boolean check = true;
        String dapAnChon = null;
        do {
            System.out.print("Moi ban chon(1 - 4): ");
            int chon = Integer.parseInt(scanner.nextLine());
            switch (chon) {
                case 1:
                    dapAnChon = listSlang.get(chon - 1);
                    check = false;
                    break;
                case 2:
                    dapAnChon = listSlang.get(chon - 1);
                    check = false;
                    break;
                case 3:
                    dapAnChon = listSlang.get(chon - 1);
                    check = false;
                    break;
                case 4:
                    dapAnChon = listSlang.get(chon - 1);
                    check = false;
                    break;
                default:
                    System.out.println("Ban chi co the chon tu 1 - 4!");
                    break;
            };
        } while (check);

        if (randomDefi.equals(dictionary.get(dapAnChon))) {
            System.out.println("Chuc mung! Ban da chon dung!");
        } else {
            System.out.println("Rat tiec! Ban da chon sai!");
        }
    }
}
