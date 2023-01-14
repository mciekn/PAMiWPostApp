package com.example.pamiwpostapp.backend.entities;

import lombok.*;

import javax.persistence.*;


@Entity
@Table
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Package {

    enum State {
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

    @ManyToOne
    private Locker senderLocker;


    @ManyToOne
    private Locker receiverLocker;


    @ManyToOne
    private PostUser sender;


    @ManyToOne
    private PostUser receiver;

    private State state;
}
