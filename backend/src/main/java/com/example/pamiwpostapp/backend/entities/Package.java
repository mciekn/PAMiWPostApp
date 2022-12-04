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

    enum State{
        POSTED,
        TRANSFERRED,
        DELIVERED,
        RECEIVED
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String content;
    private Long sender_locker_id;
    private Long receiver_locker_id;
    private Long sender_id;
    private Long receiver_id;
    private State state;
}
