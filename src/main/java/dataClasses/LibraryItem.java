package dataClasses;

public class LibraryItem {
    private int id;
    private Category category;
    private String title;
    private boolean isBorrowable;
    private final Type type;

    /**
     * Constructor for empty item
     * @param type LibraryItems must have a type
     */
    public LibraryItem(Type type) {
        this.type = type;
        this.category = new Category();
    }

    public LibraryItem(Category category, String title, boolean isBorrowable, Type type) {
        this.category = category;
        this.title = title;
        this.isBorrowable = isBorrowable;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isBorrowable() {
        return isBorrowable;
    }

    public void setBorrowable(boolean borrowable) {
        isBorrowable = borrowable;
    }

    public Type getType() {
        return type;
    }
}
