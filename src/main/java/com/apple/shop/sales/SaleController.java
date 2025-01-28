package com.apple.shop.sales;

import com.apple.shop.member.CustomUser;
import com.apple.shop.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SaleController {
    private final SalesService salesService;
    // private final SalesRepository salesRepository;

    @PostMapping("/order")
    String postOrder(@RequestParam String itemName,
                     @RequestParam Integer price,
                     @RequestParam Integer count,
                     Authentication auth) {
        CustomUser user = (CustomUser) auth.getPrincipal();

        salesService.saveSales(count,price,itemName,user);

        return "redirect:list/page/1";
    }

    @GetMapping("/order/all")
    String getOrder(Authentication auth, Model model) {
        List<SalesDto> result = salesService.getAllSales();
        model.addAttribute("result",result);

        return "sales.html";
    }
}


