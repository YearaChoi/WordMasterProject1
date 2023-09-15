package com.mycom.word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{ // ICRUD를 구현한 구현체
    // 배열 리스트를 사용하여 동적 데이터를 관리할 수 있도록 ArrayList 선언
    ArrayList<Word> list;
    Scanner s; // 사용자에게 입력받아올 수 있도록 스캐너 정의
    final String fname = "Dictionary.txt";

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

    public void listAll(){ // 1번 메뉴를 누르면 리스트가 출력되도록 하는 함수
        System.out.println("------------------------------");
        // 리스트에 있는 단어가 나오도록 반복문을 돌림
        for(int i=0; i< list.size(); i++){
            System.out.print((i+1)+ " "); // " " 옆에 공백 넣어주기
            System.out.println(list.get(i).toString()); // 리스트에서 데이터를 하나씩 가져다가 toString
        }
        System.out.println("------------------------------");
    }

    public ArrayList<Integer> listAll(String keyword){

        ArrayList<Integer> idlist = new ArrayList<>();
        int j=0;
        System.out.println("------------------------------");
        for(int i=0; i< list.size(); i++){
            String word = list.get(i).getWord(); // 리스트에 있는 단어를 가져옴
            if(!word.contains(keyword)) continue; // 키워드가 포함되어 있지 않다면 구문을 중단
            System.out.print((j+1)+ " "); // 순차적으로 하나씩 늘어남
            System.out.println(list.get(i).toString());
            idlist.add(i); // i를 idlist에 추가하고 리턴
            j++;
        }
        System.out.println("------------------------------");
        return idlist;
    }

    public void listAll(int level){
        int j=0;
        System.out.println("------------------------------");
        for(int i=0; i< list.size(); i++){
            int ilevel = list.get(i).getLevel();
            if(ilevel != level) continue; // 레벨이 같지 않은 경우에는 실행하지 않음
            System.out.print((j+1)+ " ");
            System.out.println(list.get(i).toString());
            j++;
        }
        System.out.println("------------------------------");
    }

    public void updateItem() {
        System.out.println("=> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword); // 단어가 몇변째인지 나타냄
        System.out.println("=> 수정할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();

        System.out.println("=> 뜻 입력 : ");
        String meaning = s.nextLine(); // 공백도 포함해서 입력받음
        Word word = list.get(idlist.get(id-1));
        word.setMeaning(meaning); // 원하는 객체의 뜻을 바꿈
        System.out.println("단어가 수정되었습니다. ");
    }

    public void deleteItem() {
        System.out.println("=> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.println("=> 삭제할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();

        System.out.println("=> 정말로 삭제하실래요? (Y/n) ");
        String ans = s.next(); // 사용자 대답을 받는 변수
        if(ans.equalsIgnoreCase("y")){ // 사용자가 Y라고 답했을 때 데이터를 삭제, 소문자도 가능
            list.remove((int)idlist.get(id-1)); // 해당 위치의 데이터 삭제
            System.out.println("단어가 삭제되었습니다. ");
        }else // Y가 아닌 다른 것을 입력하였을 경우
            System.out.println("취소되었습니다. ");
    }
    public void loadFile(){
        // Dictionary.txt 파일을 열고, 파일에 있는 데이터를 한 줄씩 읽어서 워드 객체를 리스트에 추가
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname)); // 이 부분이 안됨
            String line; // 데이터를 한 줄씩 읽어오기 위한 변수
            int count =0; // n개 데이터 로딩 완료!! 데이터를 세기 위한 변수

            while(true) {
                line = br.readLine(); // 한 줄로 읽어오고
                if (line == null) break; // 파일의 끝을 만나면 끝낸다

                String data[] = line.split("\\|"); // 한 글자씩 쪼개서 읽기
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++; // 데이터 개수 증가
            }
            br.close();
            System.out.println("==> " + count + "개 데이터 로딩 완료!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(fname));
            for(Word one : list){ // 리스트에 있는 모든 데이터를 하나씩 가져와
                pr.write(one.toFileString() + "\n"); // 파일에 추가
            }
            pr.close();
            System.out.println("==> 데이터 저장 완료 !!!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchLevel() {
        System.out.println("=> 원하는 레벨은? (1~3) ");
        int level = s.nextInt(); // 사용자가 입력
        listAll(level);
    }
}
