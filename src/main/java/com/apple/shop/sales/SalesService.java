package com.apple.shop.sales;

import com.apple.shop.member.CustomUser;
import com.apple.shop.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SalesService {

    private final SalesRepository salesRepository;

    public void saveSales(Integer count, Integer price, String title , Member member) {
        Sales sales = new Sales();
        sales.setCount(count);
        sales.setPrice(price);
        sales.setItemName(title);
        sales.setMember(member);
        salesRepository.save(sales);
    }
}
