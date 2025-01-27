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
    @ManyToOne // N:1 매핑
    @JoinColumn(name = "member_id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @CreationTimestamp
    private LocalDateTime created;
}

