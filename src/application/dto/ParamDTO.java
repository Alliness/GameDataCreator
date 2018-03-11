package application.dto;

import application.core.library.field.options.LibraryFieldOptionEnum;

import java.util.List;

public class ParamDTO {

    private String ref;

    private List<LibraryFieldOptionEnum> opts;

    private String name;

    private String type;

    public String getRef() {
        return ref;
    }

    public List<LibraryFieldOptionEnum> getOpts() {
        return opts;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
