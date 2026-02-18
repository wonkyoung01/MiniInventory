package org.myinventory.backend.controller;

import org.myinventory.backend.entity.Item;
import org.myinventory.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/api/items")
    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    @PostMapping("/api/item/save")
    public ResponseEntity<Integer> Save(@RequestBody Item item){

        try{
            Item saveitem = itemRepository.save(item);
            return ResponseEntity.ok(saveitem.getId());
        }catch (Exception e)
        {
            throw new RuntimeException("사용자 저장 중 오류 발생"+e.getMessage() );
        }
    }

    @GetMapping("/api/items/monitor")
    public int[] monitor(){

        try{
            List<Item> items = itemRepository.findAll();
            int[] res = new int[3];
            res[0] = items.size();
            res[1] = (int)items.stream().mapToInt(Item::getStock).sum();
            res[2]  = (int)items.stream().filter(item -> item.getStock()==0).count();

            return res;
        }catch (Exception e)
        {
            throw new RuntimeException("조회중 오류 발생"+e.getMessage() );
        }
    }

}
