package domain;

public class StoneColor {
    public static final StoneColor BLACK = new StoneColor("Black");
    public static final StoneColor WHITE = new StoneColor("White");
    public static final StoneColor EMPTY = new StoneColor("Empty");

    private final String color;

    private StoneColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StoneColor that = (StoneColor) obj;
        return color.equals(that.color);
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }
}

