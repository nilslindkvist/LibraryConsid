package dataClasses;

public class Book extends BorrowableItem {
    private String author;
    private int pages;

    public Book() {
        super(Type.Book);
    }

    public Book(Category category, String title, String author, int pages) {
        super(category, title, Type.Book);
        this.author = author;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}

