package com.example.pamiwpostapp.backend.entities;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Package {
    Long id;
    String name;
    String content;
}
