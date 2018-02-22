package application.core.library.field.types;

import java.util.ArrayList;
import java.util.List;

public enum FieldTypeEnum {

    EMPTY("Empty", false),
    INT("Integer", false),
    FLOAT("Float", false),
    DOUBLE("Double", false),
    STRING("String", false),
    ARRAY("Array", true),
    REFERENCE("Reference", true);

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

    public boolean isArrayLike() {
        return arrayLike;
    }
}
