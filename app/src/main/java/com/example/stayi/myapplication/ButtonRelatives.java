package com.example.stayi.myapplication;

public class ButtonRelatives {
    private int Button_ID;
    private int FirstFieldPosition = 0;
    private int SecondFieldPosition = 0;

    ButtonRelatives(int B_id) {
        Button_ID = B_id;
    }

    int getButtonId(){
        return Button_ID;
    }

    int getFirstFieldPosition(){
        return FirstFieldPosition;
    }

    int getSecondFieldPosition(){
        return SecondFieldPosition;
    }

    void setFirstFieldPosition(int pos){
        FirstFieldPosition = pos;
    }

    void setSecondFieldPosition(int pos){
        SecondFieldPosition = pos;
    }
}