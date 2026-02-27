package org.myinventory.backend.repository;

import org.myinventory.backend.entity.Member;
import org.myinventory.backend.entity.StockHistory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Integer> {

    // item 객체를 전체 조회시 자동 바인딩합니다.
    @Override
    @EntityGraph(attributePaths = {"item"})
    List<StockHistory> findAll();

    @EntityGraph(attributePaths = {"item"})
    List<StockHistory> findByItemIdOrderByCreateDateDesc(Integer itemId);

    @EntityGraph(attributePaths = {"item"})
    StockHistory findTop1ByOrderByCreateDateDesc();



}
