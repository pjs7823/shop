package com.apple.shop.sales;

import com.apple.shop.member.CustomUser;
import com.apple.shop.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SaleController {
    private final SalesService salesService;
    private final SalesRepository salesRepository;

    @PostMapping("/order")
    String postOrder(@RequestParam String itemName,
                     @RequestParam Integer price,
                     @RequestParam Integer count,
                     Authentication auth) {
        CustomUser user = (CustomUser) auth.getPrincipal();
        Member member = new Member();
        member.setId(user.id);
        salesService.saveSales(count,price,itemName,member);

        return "redirect:list/page/1";
    }

    @GetMapping("/order/all")
    String getOrder(Authentication auth) {
        List<Sales> result = salesRepository.customFindAllSales();
        System.out.println(result);
        var salesDto = new SalesDto();
        salesDto.itemName= result.get(0).getItemName();
        return "sales.html";
    }
}
//주문기능 3 숙제(service 레이어 분리, 주문 폼 예외처리)해야함
class SalesDto{
    public String itemName;
    public Integer price;
    public String userName;
    public String tmp;
}
