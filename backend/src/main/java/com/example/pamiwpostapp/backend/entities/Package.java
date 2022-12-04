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
    Long id;

    String name;
    String content;
    Long sender_locker_id;
    Long receiver_locker_id;
    Long sender_id;
    Long receiver_id;
    State state;
}
