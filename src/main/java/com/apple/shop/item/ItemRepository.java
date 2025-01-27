package com.apple.shop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // table 의 repository 만드는 규칙 인터페이스로 만들고 JpaRepsitory<테이블 이름, Id 자료형> 상속(extends) 받기

    Page<Item> findPageBy(Pageable page);
    Page<Item> findAllByTitleContains(String title, Pageable pageable);

//    // ?1 -> 첫번째 파라미터, ?2-> 두번째 파라미터..
//    @Query(value = "select * from item where id = ?1",nativeQuery = true)
//    Item rawQuery1(String text);

    // full text index 로 단어 검색하기
    @Query(value = "select * from item where match(title) against(?1)",nativeQuery = true)
    List<Item> rawQuery1(String text);
}
