package org.myinventory.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name ="members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    @Column
    private String loginId;
    @Column
    private String password;
    private String name;
    private String remark;




}
