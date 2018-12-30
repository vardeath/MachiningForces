package com.example.stayi.myapplication.FragmentField;

public enum FieldLength {

    Nine(9),
    Eight(8),
    Seven(7),
    Six(6),
    Five(5),
    Four(4),
    Three(3),
    Two(2),
    One(1),
    Zero(0);

    private int Value;

    FieldLength(int i) {this.Value = i;}

    public int getValue() {return Value;}
}