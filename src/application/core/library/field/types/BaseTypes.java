package application.core.library.field.types;

public enum BaseTypes  {

    EMPTY("Empty"),
    INT("Integer"),
    FLOAT("Float"),
    DOUBLE("Double"),
    STRING("String"),
    ARRAY("Array"),
    OBJECT("Object");

    String name;

    BaseTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
