public enum Category {
    Red("\033[0;31m;"),
    White("\033[0;37m"),
    Blue("\033[0;34m"),
    Purple("\033[0;35m"),
    Yellow("\033[0;33m"),
    Green("\033[0;32m");

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    Category(String color) {
        this.color = color;
    }
}
