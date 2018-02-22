package application.core.library.field.options;

public enum LibraryFieldOptionEnum {

    UNIQUE("UQ", false),
    MANDATORY("M", false),
    AUTO_INCREMENT("AI", false);


    private boolean byDefault;
    private String  text;

    LibraryFieldOptionEnum(String text, boolean byDefault) {
        this.text = text;
        this.byDefault = byDefault;
    }

    public static LibraryFieldOptionEnum getByText(String text){
        for (LibraryFieldOptionEnum libraryFieldOptionEnum : values()) {
            if(libraryFieldOptionEnum.text.equals(text)){
                return libraryFieldOptionEnum;
            }
        }
        return null;
    }

    public boolean isByDefault() {
        return byDefault;
    }

    public String getText() {
        return text;
    }
}
