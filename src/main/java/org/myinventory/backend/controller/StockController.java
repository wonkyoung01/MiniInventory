package org.myinventory.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.myinventory.backend.entity.ApiResponse;
import org.myinventory.backend.entity.StockHistory;
import org.myinventory.backend.repository.ItemRepository;
import org.myinventory.backend.repository.StockHistoryRepository;
import org.myinventory.backend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Stock API", description = "재고 관리 API")
public class StockController {

    @Autowired
    StockHistoryRepository stockHistoryRepository;

    @Autowired
    ItemRepository ItemRepository;

    @Autowired
    StockService stockService;

    //1. 등록
    @Operation(summary = "입/출고 등록", description = "재고 입/출고를 등록하고 재고 수량을 변경합니다.")
    @PostMapping("/api/stock/save")
    public ResponseEntity<ApiResponse<StockHistory>> create(@RequestBody StockHistory params) {

        try{
            stockService.updateItemAndCreateStockHistory(params);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            201,
                            "입/출고가 정상적으로 처리되었습니다.",
                            null
                    ));
        }catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(
                            400,
                            "처리 중 오류가 발생했습니다.",
                            null
                    ));
        }
    }
    //2. 전체 이력 조회
    @Operation(summary = "입/출고 이력 조회", description = "재고 입/출고 이력 조회합니다.")
    @GetMapping("/api/stock/stockHistoryList")
    public ResponseEntity<ApiResponse<List<StockHistory>>> getList()
    {
        try{
            var res = stockHistoryRepository.findAll();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            200,
                            "제품 조회 성공했습니다.",
                            res
                    ));
        }catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(
                            400,
                            "처리 중 오류가 발생했습니다.",
                            null
                    ));
        }

    }

    //3. 특정 이력 조회
    @Operation(summary = "특정 제품 입/출고 이력 조회", description = "특정 제품 입/출고 이력 조회합니다.")
    @GetMapping("/api/stock/stockHistoryListByItemId")
    public ResponseEntity<ApiResponse<List<StockHistory>>> getListByItemId(@RequestParam Integer itemId)
    {
        try{
            var res = stockHistoryRepository.findByItemIdOrderByCreateDateDesc(itemId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            200,
                            "제품 조회 성공했습니다.",
                            res
                    ));
        }catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(
                            400,
                            "처리 중 오류가 발생했습니다.",
                            null
                    ));
        }

    }

    //4. 마지막 업데이트 데이터 조회
    @Operation(summary = "마지막 업데이트 데이터 조회", description = "마지막 이력 등록 데이터를 조회합니다.")
    @GetMapping("/api/stock/getLastStockHisotry")
    public ResponseEntity<ApiResponse<StockHistory>> getLastStockHisotry()
    {
        try{
            var res = stockHistoryRepository.findTop1ByOrderByCreateDateDesc();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            200,
                            "조회 성공했습니다.",
                            res
                    ));
        }catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(
                            400,
                            "처리 중 오류가 발생했습니다.",
                            null
                    ));
        }
    }

    @GetMapping("/api/alerts/changes")
    public Map<String, Object> hasChanges(@RequestParam int lastId) {
        var res = stockHistoryRepository.findTop1ByOrderByCreateDateDesc(); // select max(id) ...
        boolean changed = res.getId() > lastId;
        return Map.of("changed", changed, "latestId", res.getId());
    }
}
