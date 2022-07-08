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
        String keyword;
        HashMap<String, String> slangWords;
        HashMap<String, String> history = new HashMap<>();
        do {
            System.out.println("============= MENU =============");
            System.out.println("|1. Tìm kiếm theo slang word.  |");
            System.out.println("|2. Tìm kiếm theo definition.  |");
            System.out.println("|3. Hiển thị history.          |");
            System.out.println("|4. Thêm một slang word mới.   |");
            System.out.println("|0. Thoát chương trình.        |");
            System.out.println("================================");
            System.out.print("Mời bạn chọn chức năng: ");
            chon = Integer.parseInt(scanner.nextLine());
            switch (chon) {
                case 1:
                    System.out.print("Mời bạn nhập slang word: ");
                    keyword = scanner.nextLine();
                    slangWords = dictionary.timTheoSlangWord(keyword);
                    for (String slang : slangWords.keySet()) {
                        history.put(slang, slangWords.get(slang));
                        System.out.println(slang + " = " + slangWords.get(slang));
                    }
                    break;
                case 2:
                    System.out.print("Mời bạn nhập definition: ");
                    keyword = scanner.nextLine();
                    slangWords = dictionary.timTheoDefinition(keyword);
                    for (String slang : slangWords.keySet()) {
                        history.put(slang, slangWords.get(slang));
                        System.out.println(slang + " = " + slangWords.get(slang));
                    }
                    break;
                case 3:
                    System.out.println("Hisory: ");
                    for (String slang : history.keySet()) {
                        System.out.println(slang + " = " + history.get(slang));
                    }
                    break;
                case 4:
                    System.out.println("Mời bạn nhập slang word mới: ");
                    System.out.print("Slang: ");
                    String slang = scanner.nextLine();
                    System.out.print("Definition: ");
                    String definition = scanner.nextLine();
                    int check = dictionary.themSlangWord(slang, definition);
                    if (check == 1) {
                        System.out.println("Thêm thành công!");
                    } else {
                        System.out.println("Thêm thất bại!");
                    }
                    break;
                case 0:
                    System.out.println("Bạn chọn chức năng thoát! Tạm biệt!");
                    System.exit(0); // thoát chương trình
                    break;
            }
        } while (true);
    }

}
