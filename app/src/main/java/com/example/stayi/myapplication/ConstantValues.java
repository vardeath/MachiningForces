package com.example.stayi.myapplication;

public enum ConstantValues {
    Six(6),
    Four(4);
    private int val;

    ConstantValues(int i) {
        this.val = i;
    }

    public int getVal(){return val;}
}