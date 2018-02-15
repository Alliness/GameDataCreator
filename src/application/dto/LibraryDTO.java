package application.dto;

import java.util.ArrayList;
import java.util.List;

public class LibraryDTO extends Serializable{

    private String     name;
    private List<FieldDTO> fields;

    public String getName() {
        return this.name;
    }

    public List<FieldDTO> getFields() {
        return this.fields;
    }

    public LibraryDTO(String name, List<FieldDTO> fields) {
        this.name = name;
        this.fields = fields;
    }

    public LibraryDTO(String name) {
        this.name = name;
        this.fields = new ArrayList<>();
    }

    public void addField(FieldDTO field){
        fields.add(field);
    }
}
