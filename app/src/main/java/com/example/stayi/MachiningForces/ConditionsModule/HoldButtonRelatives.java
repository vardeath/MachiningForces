package com.example.stayi.MachiningForces.ConditionsModule;

import com.example.stayi.MachiningForces.Enumerations.ButtonLockPosition;

public class HoldButtonRelatives {
    private int Button_ID;
    private int FirstFieldPosition;
    private int SecondFieldPosition;
    private ButtonLockPosition ButtonPos;

    public HoldButtonRelatives(int B_id, int first_pos, int second_pos, ButtonLockPosition pos) {
        Button_ID = B_id;
        FirstFieldPosition = first_pos;
        SecondFieldPosition = second_pos;
        ButtonPos = pos;
    }

    public int getButtonId() {
        return Button_ID;
    }

    public int getFirstFieldPosition() {
        return FirstFieldPosition;
    }

    public int getSecondFieldPosition() {
        return SecondFieldPosition;
    }

    public ButtonLockPosition getLockedFieldPosition() {
        return ButtonPos;
    }
}