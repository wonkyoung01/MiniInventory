package org.myinventory.backend.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@Entity
@Table(name ="stockhistory")
public class StockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    // 1. 기준 컬럼 (값이 저장되고 수정되는 주체)
    @Column
    private Integer itemId;

    private Integer  qty;
    @Column
    private String type;
    private Date createDate;
    private String createUser;

    // 2. 조회용 객체 (단순 조회용으로만 사용, 저장/수정 시에는 무시됨)
    @ManyToOne(fetch = FetchType.LAZY) // stockHistory 조회시 무조건 Item 도 같이 조인해서 가져옴.
    @JoinColumn(name = "itemId", insertable = false, updatable = false) // name 에 있는 값 = stockhistory 의 컬럼과 Item 의 pk 컬럼과 조인된다.
    private Item item;
}
