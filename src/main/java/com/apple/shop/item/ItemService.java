package com.apple.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    // 검사 및 DB 입출력 등의 비지니스 로직을 담는 클래스를 Service 라고 부름
    // DB 입출력의 3단계 1. repository interface 만들기 2. 등록(생성자를 만들거나 @RequiredArgsConstructor 등록 3. 사용

    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> findItemById(Long id) {
        return itemRepository.findById(id);
    }


    public void saveItem(String title, Integer price) {
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    public void editItem(Long id,String title, Integer price) {
        // 입력 값 검증
        if (title == null || title.length() > 1000) {
            throw new IllegalArgumentException("Title must not be null and should be less than or equal to 1000 characters.");
        }
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Price must not be null and should be a positive number.");
        }
        Item item = new Item();
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);

    }
}
