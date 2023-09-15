package com.mycom.word;

import java.sql.SQLOutput;
import java.util.Scanner; // 스케너 함수 임포트

public class WordManager { // WordCRUD를 이용해 CRUD 기능을 구현
    Scanner s = new Scanner(System.in); // 멤버변수로 스캐너 선언
    WordCRUD wordCRUD;

    WordManager() {
        wordCRUD = new WordCRUD(s);
    }

    public int selectMenu() {
        System.out.print("*** 영단어 마스터 ***\n"
                + "********************\n"
                + "1. 모든 단어 보기\n"
                + "2. 수준별 단어 보기\n"
                + "3. 단어 검색\n"
                + "4. 단어 추가\n"
                + "5. 단어 수정\n"
                + "6. 단어 삭제\n"
                + "7. 파일 저장\n"
                + "0. 나가기\n"
                + "********************\n"
                + "=> 원하는 메뉴는? ");
        return s.nextInt(); // 사용자에게서 원하는 메뉴 받아와서 리턴
    }

    public void start() { // WordManager에서 메인 역할을 하는 start함수

        wordCRUD.loadFile();

        while (true) {
            int menu = selectMenu(); // 리턴된 값을 그대로 출력
            if (menu == 0) break;
            if (menu == 4) {
                // 데이터를 추가하는 구문
                wordCRUD.addItem();
            } else if (menu == 1) {
                // 리스트를 보여주는 구문
                wordCRUD.listAll();
            }else if (menu == 5) {
                // 데이터를 수정하는 부분
                wordCRUD.updateItem();
            } else if (menu == 6) {
                // 데이터를 삭제하는 부분
                wordCRUD.deleteItem();
            }
        }
    }
}
