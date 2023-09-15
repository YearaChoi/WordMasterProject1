package com.mycom.word;

import java.io.PrintWriter;
import java.security.PrivateKey;

public class Word {
    // 단어를 다루기 위해서 사용하는 변수 구성, 4개의 변수로 구성되어있는 클래스
    // 같은 클래스 내에서만 접근 가능
    private int id;
    private int level;
    private String word;
    private String meaning;

    // 생성자
    Word(){} // 비어있는 기본형
    Word(int id, int level, String word, String meaning){ // 파라미터를 멤버 변수와 동일하게
        // 받아온 데이터를 멤버 변수에 세팅
        this.id = id;
        this.level = level;
        this.word = word;
        this.meaning = meaning;
    }

    // 외부에서 변경할 수 있도록 getter와 setter추가

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        String slevel = "";
        for(int i=0; i< level; i++) slevel += "*";
        String str = String.format("%-3s", slevel) // 왼쪽 정렬
                + String.format("%15s", word) + " " + meaning; // 오른쪽 정렬
        return str;
    }
    public String toFileString(){
        // 파일에 출력하기 위한 문자열
        return this.level + "|" + this.word + "|" + this.meaning;
    }
}
