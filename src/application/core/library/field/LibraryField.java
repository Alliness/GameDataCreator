package application.core.library.field;

import application.core.library.field.options.LibraryOptionsEnum;
import application.core.library.field.types.LibraryType;

import java.util.ArrayList;
import java.util.List;

/**
 * Library FieldController model todo replace to model folder
 */
public class LibraryField {

    private String name;

    private LibraryType type;

    private List<LibraryOptionsEnum> options;


    public LibraryField(String name, LibraryType type) {
        this.name = name;
        this.type = type;
        options = new ArrayList<>();
    }

    public LibraryField(String name, LibraryType type, List<LibraryOptionsEnum> options) {
        this.name = name;
        this.type = type;
        this.options = options;
    }

    /**
     * add option to field
     * @param option {@link LibraryOptionsEnum}
     */
    public void addOption(LibraryOptionsEnum option){
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
     * @return library type {@link LibraryType}
     */
    public LibraryType getType() {
        return type;
    }

    /**
     *
     * @return list of {@link LibraryOptionsEnum}
     */
    public List<LibraryOptionsEnum> getOptions() {
        return options;
    }
}
