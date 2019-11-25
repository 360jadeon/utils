package com.jadeon.java8.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction {

    private Trader trader;

    private int year;

    private int value;

}
