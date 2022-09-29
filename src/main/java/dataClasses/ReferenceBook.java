package dataClasses;

public class ReferenceBook extends LibraryItem {
    private String author;
    private int pages;

    public ReferenceBook() {
        super(Type.ReferenceBook);
    }

    public ReferenceBook(Category category, String title, String author, int pages) {
        super(category, title, false, Type.ReferenceBook);
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
