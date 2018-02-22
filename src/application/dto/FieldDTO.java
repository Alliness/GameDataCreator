package application.dto;

import application.core.library.field.options.LibraryFieldOptionEnum;
import application.core.library.field.types.FieldTypeEnum;

import java.util.List;

public class FieldDTO extends Serializable {

    private List<LibraryFieldOptionEnum> opts;
    private String                       name;
    private FieldTypeEnum                type;
    private String                       ref;

    public FieldDTO(List<LibraryFieldOptionEnum> opts, String name, FieldTypeEnum type) {
        this.opts = opts;
        this.name = name;
        this.type = type;
        this.ref = null;
    }

    public FieldDTO(List<LibraryFieldOptionEnum> opts, String name, FieldTypeEnum type, String ref) {
        this.opts = opts;
        this.name = name;
        this.type = type;
        this.ref = ref;
    }

    public String getRef() {
        return this.ref;
    }

    public List<LibraryFieldOptionEnum> getOpts() {
        return this.opts;
    }

    public String getName() {
        return this.name;
    }


    public FieldTypeEnum getType() {
        return type;
    }

}
