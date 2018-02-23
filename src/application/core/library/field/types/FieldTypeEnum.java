package application.core.library.field.types;

import java.util.ArrayList;
import java.util.List;

public enum FieldTypeEnum {

    NULL("Null", false),
    NUMBER("Number", false),
    STRING("String", false),
    BOOL("Boolean", false),
    ARRAY("Array", true),
    OBJECT("Object", true);

    String name;
    private boolean arrayLike;

    FieldTypeEnum(String name, boolean arrayLike) {

        this.name = name;
        this.arrayLike = arrayLike;
    }

    public String getName() {
        return name;
    }


    public static List<String> names() {
        List<String> list = new ArrayList<>();
        for (FieldTypeEnum fieldTypeEnum : values()) {
            list.add(fieldTypeEnum.name);
        }
        return list;
    }

    public static FieldTypeEnum getByName(String name) {
        for (FieldTypeEnum fieldTypeEnum : values()) {
            if (fieldTypeEnum.name.equals(name)) {
                return fieldTypeEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isArrayLike() {
        return arrayLike;
    }
}
