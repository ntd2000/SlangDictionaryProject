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
            System.out.println("|1. Tim kiem theo slang word.  |");
            System.out.println("|2. Tim kiem theo definition.  |");
            System.out.println("|3. Hien thi history.          |");
            System.out.println("|4. Them mot slang word moi.   |");
            System.out.println("|5. Chinh sua mọt slang word.  |");
            System.out.println("|6. Xoa mot slang word.        |");
            System.out.println("|7. Reset slang words goc.     |");
            System.out.println("|8. Random 1 slang word.       |");
            System.out.println("|9. Do vui slang word.         |");
            System.out.println("|10. Do vui definition.        |");
            System.out.println("|0. Thoat chuong trinh.        |");
            System.out.println("================================");
            System.out.print("Moi ban chon chuc nang: ");
            chon = Integer.parseInt(scanner.nextLine());
            switch (chon) {
                case 1:
                    System.out.print("Moi ban nhap slang word: ");
                    keyword = scanner.nextLine();
                    slangWords = dictionary.timTheoSlangWord(keyword);
                    for (String slang : slangWords.keySet()) {
                        history.put(slang, slangWords.get(slang));
                        System.out.println(slang + " = " + slangWords.get(slang));
                    }
                    break;
                case 2:
                    System.out.print("Moi ban nhap definition: ");
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
                    System.out.println("Moi ban nhap slang word moi: ");
                    System.out.print("Slang: ");
                    String slang = scanner.nextLine();
                    System.out.print("Definition: ");
                    String definition = scanner.nextLine();
                    int check = dictionary.themSlangWord(slang, definition);
                    if (check == 1) {
                        System.out.println("Them thanh cong!");
                    } else {
                        System.out.println("Them that bai!");
                    }
                    break;
                case 5:
                    System.out.print("Moi ban nhap slang word can chinh sua: ");
                    String editSlang = scanner.nextLine();
                    System.out.print("Definition moi cua slang word: ");
                    String editDefinition = scanner.nextLine();
                    if (dictionary.suaSlangWord(editSlang, editDefinition) == 1) {
                        System.out.println("Sua thanh cong!");
                    } else {
                        System.out.println("Sua that bai!");
                    }
                    break;
                case 6:
                    System.out.print("Moi ban nhap slang word can xoa: ");
                    String deleteSlang = scanner.nextLine();
                    System.out.println("Ban co chac la muon xoa hay khong? ");
                    do {
                        System.out.println("1. Xoa!");
                        System.out.println("0. Khong xoa!");
                        System.out.print("Chon (1 hoac 0): ");
                        chon = Integer.parseInt(scanner.nextLine());
                        if (chon == 1) {
                            if (dictionary.xoaSlangWord(deleteSlang) == 1) {
                                System.out.println("Xoa thanh cong!");
                            } else {
                                System.out.println("Xoa that bai!");
                            }
                            break;
                        } else if(chon == 0) {
                            break;
                        }else{
                            System.out.print("Ban chi co the chon 1 hoac 0? ");
                        }
                    } while (true);
                    break;
                case 7:
                    if(dictionary.resetSlangWords() == 1){
                        System.out.println("Reset thanh cong!");
                    }else
                        System.out.println("Reset that bai!");
                    break;
                case 8:
                    String randomSlang = dictionary.randomSlangWord();
                    System.out.println(randomSlang + " = " + dictionary.getDictionary().get(randomSlang));
                    break;
                case 9:
                    dictionary.quizSlangWord();
                    break;
                case 10:
                    dictionary.quizDefinition();
                    break;
                case 0:
                    System.out.println("Ban chon chuc nang thoat! Tam biet!");
                    System.exit(0); // thoát chương trình
                    break;
                default:
                    System.out.println("Moi ban chon theo menu!");
                    break;
            }
        } while (true);
    }

}
