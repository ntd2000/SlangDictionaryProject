/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangdictionary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author NguyenThanhDat
 */
public class Main {

    public static void main(String[] args) throws IOException {
        SlangDictionary dictionary = new SlangDictionary();
        Scanner scanner = new Scanner(System.in);
        int chon = -1;
        do {
            System.out.println("============= MENU =============");
            System.out.println("|1. Tìm kiếm theo slang word.  |");
            System.out.println("|0. Thoát chương trình.        |");
            System.out.println("================================");
            System.out.print("Mời bạn chọn chức năng: ");
            chon = Integer.parseInt(scanner.nextLine());
            switch (chon) {
                case 1:
                    System.out.print("Mời bạn nhập slang word cần tìm: ");
                    String keyword = scanner.nextLine();
                    HashMap<String, String> slangWords = dictionary.timSlangWord(keyword);
                    slangWords.keySet().forEach(slang -> {
                        String meaning = slangWords.get(slang);
                        System.out.println(slang + ": " + meaning);
                    });
                    break;
                case 2:
                    System.out.println("Bạn chọn chức năng xem!");
                    break;
                case 0:
                    System.out.println("Bạn chọn chức năng thoát! Tạm biệt!");
                    System.exit(0); // thoát chương trình
                    break;
            }
        } while (true);
    }

}
