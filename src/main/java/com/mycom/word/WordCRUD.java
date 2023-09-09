package com.mycom.word;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{ // ICRUD를 구현한 구현체
    // 배열 리스트를 사용하여 동적 데이터를 관리할 수 있도록 ArrayList 선언
    ArrayList<Word> list;
    Scanner s; // 사용자에게 입력받아올 수 있도록 스캐너 정의

    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s =s;
    }
    // 각각의 함수가 어떻게 기능할지 구현
    @Override // 사용자에게 데이터를 입력받는 부분
    public Object add() {
        System.out.println("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();
        // 1 driveway
        System.out.println("뜻 입력 : ");
        String meaning = s.nextLine();
        // 차고 진입로
        return new Word(0, level, word, meaning);
    }

    // 입력받은 데이터를 리스트에 추가하는 부분
    public void addItem(){ // WordManager에서 호출할 함수
        Word one = (Word)add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다. ");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }
}
