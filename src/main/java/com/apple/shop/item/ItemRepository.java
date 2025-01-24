package com.apple.shop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // table 의 repository 만드는 규칙 인터페이스로 만들고 JpaRepsitory<테이블 이름, Id 자료형> 상속(extends) 받기

    Page<Item> findPageBy(Pageable page);
}
