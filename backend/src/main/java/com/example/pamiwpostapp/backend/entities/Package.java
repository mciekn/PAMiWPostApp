package com.example.pamiwpostapp.backend.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Package {


    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String content;
    private String sender_locker_id;
    private String receiver_locker_id;
    private String sender_id;
    private String receiver_id;
    private Integer state;
}
