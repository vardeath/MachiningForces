package com.example.stayi.MachiningForces;

import com.example.stayi.MachiningForces.Enumerations.ButtonLockPosition;

public class HoldButtonRelatives {
    private int Button_ID;
    private int FirstFieldPosition = 0;
    private int SecondFieldPosition = 0;
    private ButtonLockPosition ButtonPos= null;

    public HoldButtonRelatives(int B_id, int first_pos, int second_pos, ButtonLockPosition pos) {
        Button_ID = B_id;
        FirstFieldPosition = first_pos;
        SecondFieldPosition = second_pos;
        ButtonPos = pos;
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

    ButtonLockPosition getButtonPos() {
        return ButtonPos;
    }

    void setFirstFieldPosition(int pos){
        FirstFieldPosition = pos;
    }

    void setSecondFieldPosition(int pos){
        SecondFieldPosition = pos;
    }
}