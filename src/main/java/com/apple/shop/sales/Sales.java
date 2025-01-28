package com.apple.shop.sales;

import com.apple.shop.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private Integer price;
    private Integer count;
    // 외래키 설정(JoinColumn)
    // ConstraintMode.NO_CONSTRAINT 외래키 제약사항 X
    // ManyToOne 은 성능문제를 야기할 수 있으니 직접 SQL/JPQL 을 짜야할 수 있음
    // Repository 에 작성
    @ManyToOne(fetch = FetchType.LAZY) // N:1 매핑 , LAZY->필요할때만 가져오기
    @JoinColumn(name = "member_id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @CreationTimestamp
    private LocalDateTime created;

    // @ManyToOne 해당 칼럼이 가리키는 테이블도 출력 가능하다.
    // 성능 문제때문에 Join Fetch 를 사용한다.
    // 가리켜지는 테이블에서는 @OneToMany 사용

    // 요즘에는 마이크로 서비스를 많이 사용하기때문에 잘 사용하지 않기도 한다.
    // 마이크로 서비스란? 하나의 서버에서 모든 기능을 개발하는게 아니라
    // 회원기능 담당하는 서버, 주문기능 담당하는 서버 등 서버를 쪼개 놓고 개발 후 가각 배포
    // 이런 식으로 물리떨어진 데이터베이스 테이블끼리는 JOIN 할 수 없어서 사용 못함
}

