package com.example.stayi.myapplication;

public class FieldBaseInfo {
    private int FieldId;
    private FieldType Ftype;
    private DataType Dtype;
    private InputFieldLength Ilenght;

    FieldBaseInfo(int id, FieldType ftype) {
        FieldId = id;
        Ftype = ftype;
        switch (Ftype) {
            case Diameter: case CuttingSpeed: case Revolution: case ToothFeed:

                break;
        }
    }
}