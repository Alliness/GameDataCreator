package application.dto;

import java.util.List;

public class FieldDTO extends Serializable{

    private List<String> opts;
    private String       name;
    private String       type;
    private String       ref;

    public FieldDTO(List<String> opts, String name, String type, String ref) {
        this.opts = opts;
        this.name = name;
        this.type = type;
        this.ref = ref;
    }

    public FieldDTO(List<String> opts, String name, String type) {
        this.opts = opts;
        this.name = name;
        this.type = type;
    }

    public String getRef() {
        return this.ref;
    }

    public List<String> getOpts() {
        return this.opts;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

}
