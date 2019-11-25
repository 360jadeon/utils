package com.jadeon.java8.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dish {

    private String name;

    public boolean vegetarian;

    private Integer calories;

    private Type type;

    public enum Type {MEAT, FISH, OTHER};

}
