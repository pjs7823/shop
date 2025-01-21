package com.apple.shop.item;

import com.apple.shop.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequiredArgsConstructor
public class ItemController {
    // JPA 로 데이터 입출력하는 방법
    // 1. repository 만들기, 인터페이스로 만든다.
    // 2. 원하는 클래스에 repository 등록
    //     -> private final Repository repository 로 만들기, @RequiredArgsConstructor 어노테이션 사용
    //                                                                         //이건 lombok 문법 lombok 사용하기 싫으면
    //                                                                         // repository 생성자 만들어야함
    // 3. repository. 입출력문법() 쓰기 -> itemRepository.findAll() 등

    private final ItemRepository itemRepository;
    private final ItemService itemService;
//    lombok 문법 @RequiredArgsConstructor 없으면 생성자 만들어서 등록하면 됨
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//        this.itemService = itemService;
//    }


    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemService.getAllItems();
        model.addAttribute("items", result);
        return "list.html";
    }

    @GetMapping("/write")
    String write() {
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(@RequestParam String title, @RequestParam Integer price, Authentication auth) {
        // Item 저장
        itemService.saveItem(title, price,auth.getName());
        System.out.println("Authenticated user: " + auth.getPrincipal()); // 디버그 코드
        return "redirect:/list";
    }

    @GetMapping("detail/{id}")
    String detail(@PathVariable Long id, Model model) {
        // Optional 자료형 -> <자료형> 타입일 수도 있고 NULL 값일 수도 있을 때 사용, 겁생 결과가 없으면 NULL 값이 반환되기 때문
        // 그래서 값을 꺼낼라면 무조건 if 문으로 null 인지 검사해야함
        Optional<Item> result = itemService.findItemById(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "detail.html";
        } else {
            model.addAttribute("errorMessage", "해당 상품을 찾을 수 없습니다.");
            return "error.html";
        }


    }

    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable Long id) {
        Optional<Item> result = itemService.findItemById(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit.html";
        } else {
            return "redirect:/list";
        }
    }
    @PostMapping("/edit")
    public String editItem(@RequestParam Long id ,String title, Integer price) {
        itemService.editItem(id,title,price);
        return "redirect:/list";
    }

    @DeleteMapping("/item")
    public ResponseEntity<String> deleteItem(@RequestParam Long id) {
        //새로고침 없이 요청 날리고 데이터 받아오려면 AJAX 사용, 이때 자바스크립트 안에 Thymeleaf 변수 넣기 가능
        itemService.deleteItem(id);
        return ResponseEntity.status(200).body("삭제완료");
    }
}

