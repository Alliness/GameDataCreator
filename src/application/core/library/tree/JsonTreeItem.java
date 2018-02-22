package application.core.library.tree;

import application.dto.FieldDTO;

public class JsonTreeItem {

    private final FieldDTO dto;

    public JsonTreeItem(FieldDTO dto) {
        this.dto = dto;
    }

    @Override
    public String toString() {
        return dto.getName()+" : " + dto.getType() + " (" + dto.getOpts()+")"; // todo ref sign
    }

    public FieldDTO getDTO() {
        return dto;
    }
}
