/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangdictionary;

import java.io.IOException;
import java.util.ArrayList;
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
        int chon, check;
        String keyword;
        HashMap<String, String> slangWords;
        ArrayList<String> history = new ArrayList<>();
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
            System.out.println();
            switch (chon) {
                case 1:
                    System.out.println("-------------------------------");
                    System.out.print("Moi ban nhap slang word: ");
                    keyword = scanner.nextLine();
                    slangWords = dictionary.timTheoSlangWord(keyword);
                    if (slangWords.isEmpty()) {
                        System.out.print("Ket qua tim kiem: ");
                        System.out.println("Khong tim thay slang word!");
                    } else {
                        System.out.println("Ket qua tim kiem: ");
                        for (String slang : slangWords.keySet()) {
                            history.add(slang);
                            System.out.println("\t" + slang + ": " + slangWords.get(slang));
                        }
                    }
                    System.out.println("-------------------------------");
                    System.out.println();
                    break;
                case 2:
                    System.out.println("-------------------------------");
                    System.out.print("Moi ban nhap definition: ");
                    keyword = scanner.nextLine();
                    slangWords = dictionary.timTheoDefinition(keyword);
                    if (slangWords.isEmpty()) {
                        System.out.print("Ket qua tim kiem: ");
                        System.out.println("Khong tim thay slang word!");
                    } else {
                        System.out.println("Ket qua tim kiem: ");
                        for (String slang : slangWords.keySet()) {
                            System.out.println("\t" + slang + ": " + slangWords.get(slang));
                        }
                    }
                    System.out.println("-------------------------------");
                    System.out.println();
                    break;
                case 3:
                    System.out.println("-------------------------------");
                    if (history.isEmpty()) {
                        System.out.println("Ban chua tim kiem slang word nao!");
                    } else {
                        System.out.println("Lich su tim kiem: ");
                        for (String slang : history) {
                            System.out.println("\t" + slang);
                        }
                    }
                    System.out.println("-------------------------------");
                    System.out.println();
                    break;
                case 4:
                    System.out.println("-------------------------------");
                    System.out.println("Moi ban nhap slang word moi: ");
                    System.out.print("Slang: ");
                    String slang = scanner.nextLine();
                    System.out.print("Definition: ");
                    String definition = scanner.nextLine();
                    check = dictionary.themSlangWord(slang, definition);
                    if (check == 1) {
                        System.out.println("Them thanh cong!");
                    } else if (check == 2) {
                        System.out.println("Slang word da ton tai!");
                    } else {
                        System.out.println("Them that bai!");
                    }
                    System.out.println("-------------------------------");
                    System.out.println();
                    break;
                case 5:
                    System.out.println("-------------------------------");
                    System.out.print("Moi ban nhap slang word can chinh sua: ");
                    String editSlang = scanner.nextLine();
                    System.out.print("Definition moi cua slang word: ");
                    String editDefinition = scanner.nextLine();
                    check = dictionary.suaSlangWord(editSlang, editDefinition);
                    if (check == 1) {
                        System.out.println("Sua thanh cong!");
                    } else if (check == 2) {
                        System.out.println("Slang word khong ton tai!");
                    } else {
                        System.out.println("Sua that bai!");
                    }
                    System.out.println("-------------------------------");
                    System.out.println();
                    break;
                case 6:
                    System.out.println("-------------------------------");
                    System.out.print("Moi ban nhap slang word can xoa: ");
                    String deleteSlang = scanner.nextLine();
                    System.out.println("Ban co chac la muon xoa hay khong? ");
                    do {
                        System.out.println("==== OPTION ====");
                        System.out.println("|1. Xoa!       |");
                        System.out.println("|0. Khong xoa! |");
                        System.out.println("================");
                        System.out.print("Chon (1 hoac 0): ");
                        chon = Integer.parseInt(scanner.nextLine());
                        if (chon == 1) {
                            check = dictionary.xoaSlangWord(deleteSlang);
                            if (check == 1) {
                                System.out.println("Xoa thanh cong!");
                            } else if (check == 2) {
                                System.out.println("Slang word khong ton tai!");
                            } else {
                                System.out.println("Xoa that bai!");
                            }
                            break;
                        } else if (chon == 0) {
                            break;
                        } else {
                            System.out.println("Ban chi co the chon 1 hoac 0!");
                        }
                    } while (true);
                    System.out.println("-------------------------------");
                    System.out.println();
                    break;
                case 7:
                    System.out.println("-------------------------------");
                    if (dictionary.resetSlangWords() == 1) {
                        System.out.println("Reset thanh cong!");
                    } else {
                        System.out.println("Reset that bai!");
                    }
                    System.out.println("-------------------------------");
                    System.out.println();
                    break;
                case 8:
                    System.out.println("-------------------------------");
                    String randomSlang = dictionary.randomSlangWord();
                    System.out.println("On this day slang word");
                    System.out.println("\t" + randomSlang + ": " + dictionary.getDictionary().get(randomSlang));
                    System.out.println("-------------------------------");
                    System.out.println();
                    break;
                case 9:
                    System.out.println("-------------------------------");
                    dictionary.quizSlangWord();
                    System.out.println("-------------------------------");
                    System.out.println();
                    break;
                case 10:
                    System.out.println("-------------------------------");
                    dictionary.quizDefinition();
                    System.out.println("-------------------------------");
                    System.out.println();
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
