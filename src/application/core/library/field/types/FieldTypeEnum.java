package application.core.library.field.types;

import java.util.ArrayList;
import java.util.List;

public enum FieldTypeEnum {

    EMPTY("Empty"),
    INT("Integer"),
    FLOAT("Float"),
    DOUBLE("Double"),
    STRING("String"),
    ARRAY("Array"),
    REFERENCE("Reference");

    String name;

    FieldTypeEnum(String name) {
        this.name = name;
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
}
