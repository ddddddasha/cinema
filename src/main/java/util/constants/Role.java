package util.constants;

public enum Role {
    ADMIN ("admin"),
    MANAGER ("manager"),
    USER ("user");

    private final String title;
    Role(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
