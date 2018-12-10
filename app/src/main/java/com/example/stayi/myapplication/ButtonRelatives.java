package com.example.stayi.myapplication;

public class ButtonRelatives {
    private int Butt_id;
    private int Tw_1_position = 0;
    private int Tw_2_position = 0;

    ButtonRelatives(int B_id) {
        Butt_id = B_id;
    }

    int getButtonId(){
        return Butt_id;
    }

    int getTw_1_position(){
        return Tw_1_position;
    }

    int getTw_2_position(){
        return Tw_2_position;
    }

    void setTw_1_position(int pos){
        Tw_1_position = pos;
    }

    void setTw_2_position(int pos){
        Tw_2_position = pos;
    }
}