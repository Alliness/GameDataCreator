package application.core.library.field;

import application.core.library.field.options.LibraryFieldOptionEnum;
import application.core.library.field.types.FieldType;

import java.util.ArrayList;
import java.util.List;

/**
 * Library FieldController model todo replace to model folder
 */
public class LibraryField {

    private String name;

    private FieldType type;

    private List<LibraryFieldOptionEnum> options;


    public LibraryField(String name, FieldType type) {
        this.name = name;
        this.type = type;
        options = new ArrayList<>();
    }

    public LibraryField(String name, FieldType type, List<LibraryFieldOptionEnum> options) {
        this.name = name;
        this.type = type;
        this.options = options;
    }

    /**
     * add option to field
     * @param option {@link LibraryFieldOptionEnum}
     */
    public void addOption(LibraryFieldOptionEnum option){
        options.add(option);
    }


    /**
     *
     * @return name of field
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return library type {@link FieldType}
     */
    public FieldType getType() {
        return type;
    }

    /**
     *
     * @return list of {@link LibraryFieldOptionEnum}
     */
    public List<LibraryFieldOptionEnum> getOptions() {
        return options;
    }
}
