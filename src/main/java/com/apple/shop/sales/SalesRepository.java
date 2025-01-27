package com.apple.shop.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales,Long> {

    //@Query(value="",nativeQuery = true) 생 sql 문법을 쓰고 싶을 때
    @Query(value="SELECT s FROM Sales s JOIN FETCH s.member ") // JPQL(JPA 용 쿼리 문법)
    List<Sales> customFindAllSales();
}
