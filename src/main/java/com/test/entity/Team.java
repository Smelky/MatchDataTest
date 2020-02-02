package com.test.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Team {

    @Id
    private Long id;

    private String name;

    private String slug;

    private String gender;
}
