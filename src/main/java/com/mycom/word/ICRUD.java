package com.mycom.word;

public interface ICRUD { // 4개의 함수 정의
    public Object add(); // 추가
    public int update(Object obj); // 수정
    public int delete(Object obj); // 삭제
    public void selectOne(int id); // 데이터 한 개를 조회
}
