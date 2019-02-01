package com.example.stayi.MachiningForces;

import com.example.stayi.MachiningForces.Enumerations.ButtonLockPosition;

public class RelativeButton {
    private int ButtonId = 0;
    private int firstField_ID = 0;
    private int secondField_ID = 0;
    private ButtonLockPosition LockPos = null;

    public RelativeButton(int butt_id, int field_ID_1, int field_ID_2, ButtonLockPosition pos) {
        ButtonId = butt_id;
        firstField_ID = field_ID_1;
        secondField_ID = field_ID_2;
        LockPos = pos;
    }

    int getButtonId() {
        return ButtonId;
    }

    int getFirstField_ID() {
        return firstField_ID;
    }

    int getSecondField_ID() {
        return secondField_ID;
    }

    ButtonLockPosition getLockPos() {
        return LockPos;
    }
}