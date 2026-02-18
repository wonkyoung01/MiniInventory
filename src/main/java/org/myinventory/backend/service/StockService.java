package org.myinventory.backend.service;

import jakarta.transaction.Transactional;
import org.aspectj.weaver.patterns.ExactAnnotationTypePattern;
import org.myinventory.backend.entity.Item;
import org.myinventory.backend.entity.StockHistory;
import org.myinventory.backend.repository.ItemRepository;
import org.myinventory.backend.repository.MemberRepository;
import org.myinventory.backend.repository.StockHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StockService {

    private final ItemRepository itemRepository;
    private final StockHistoryRepository stockHistoryRepository;
    private final MemberRepository memberRepository;


    public StockService(ItemRepository _itemRepository , MemberRepository _memberRepository, StockHistoryRepository _stockHistoryRepository) {
        this.itemRepository = _itemRepository;
        this.stockHistoryRepository = _stockHistoryRepository;
        this.memberRepository = _memberRepository;
    }
    @Transactional
    public void updateItemAndCreateStockHistory(StockHistory params)
    {
        try{
            //1. 기존 데이터 조회 및 재고 수량 변경(영속 상태)
            Item findItem = itemRepository.findById(params.getItemId())
                    .orElseThrow(() -> new RuntimeException("제품 조회중 오류발생. 해당 제품이 없습니다."));
            int newQty =0;
            if(params.getType().equals("in")) newQty = findItem.getStock() + params.getQty();
            else newQty = findItem.getStock() - params.getQty();
            findItem.setStock(newQty);

            //2.재고 이력 데이터 생성
            stockHistoryRepository.save(params);

        }catch (Exception e)
        {
            throw new RuntimeException("제품 재고 변경 중 오류 발생."+e.getMessage());
        }
    }

    public Item saveNeItemAndCreateStockHistory(Item item)
    {
        try{

            // 1. 새로운 제품 저장
            Item saveitem = itemRepository.save(item);
            //2. 재고 이력 데이터 생성;
            StockHistory stockhistoryitem = new StockHistory();
            stockhistoryitem.setItemId(saveitem.getId());
            stockhistoryitem.setQty(saveitem.getStock());
            stockhistoryitem.setType("in");
            stockhistoryitem.setCreateDate(new Date());

            stockHistoryRepository.save(stockhistoryitem);

            return saveitem;
        }catch (Exception e)
        {
            throw new RuntimeException("제품 생성 중 오류 발생."+e.getMessage());
        }



    }





}
