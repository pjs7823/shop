package com.apple.shop.sales;

import com.apple.shop.member.CustomUser;
import com.apple.shop.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.util.List;

@RequiredArgsConstructor
@Service
public class SalesService {

    private final SalesRepository salesRepository;

    public void saveSales(Integer count, Integer price, String title , CustomUser user) {
        Sales sales = new Sales();
        Member member = new Member();
        member.setId(user.id);

        sales.setCount(count);
        sales.setPrice(price);
        sales.setItemName(title);
        sales.setMember(member);
        salesRepository.save(sales);
    }
    // 주문 처리 로직 (유효성 검사 포함)
    public void processOrder(String itemName, Integer price, Integer count, Authentication auth) {
        if (itemName == null || itemName.isBlank()) {
            throw new IllegalArgumentException("Item name cannot be empty.");
        }
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }
        if (count == null || count <= 0) {
            throw new IllegalArgumentException("Count must be greater than zero.");
        }

        CustomUser user = (CustomUser) auth.getPrincipal();
        saveSales(count, price, itemName, user);
    }

    // 모든 주문 조회 로직
    public List<SalesDto> getAllSales() {
        List<Sales> salesList = salesRepository.customFindAllSales();
        //List<Sales> result = salesRepository.findAll();

        // Sales 객체를 SalesDto 로 변환
        return salesList.stream().map(sales -> {
            SalesDto dto = new SalesDto();
            dto.setItemName(sales.getItemName());
            dto.setPrice(sales.getPrice());
            dto.setCount(sales.getCount());
            dto.setUserName(sales.getMember().getUsername());
            return dto;
        }).toList();
    }
}
