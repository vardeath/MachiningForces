package com.example.stayi.myapplication;

public enum InputFieldLength {
    Six(6),
    Four(4);
    private int val;

    InputFieldLength(int i) {
        this.val = i;
    }

    public int getVal(){return val;}
}