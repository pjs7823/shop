package com.apple.shop.member;

public class MemberDto {
    // object 안에 있는 데이터를 변환해서 전송하려면 Map 자료형을 사용하거나 이처럼 DTO 클래스를 만들어서 사용한다.
    // 보통 별도 파일에 public 클래스를 만들어서 사용한다.
    // Map 자료형보다 장점-> 타입체크가 쉬움, 재사용 용이
    // 요즘에는 Mapstruct 같은 Mapping 라이브러리를 사용하기도함
    public String username;
    public String displayName;

    MemberDto(String username, String displayName) {
        this.username = username;
        this.displayName = displayName;
    }
}
