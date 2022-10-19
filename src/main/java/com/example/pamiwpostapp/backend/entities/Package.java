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
    Long id;

    String name;
    String content;
}
