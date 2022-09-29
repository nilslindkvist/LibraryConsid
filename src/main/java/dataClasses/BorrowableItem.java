package dataClasses;

import java.sql.Date;

public class BorrowableItem extends LibraryItem {
    private String borrower;
    private Date borrowDate;

    public BorrowableItem(Type type) {
        super(type);
    }

    public BorrowableItem(Category category, String title, Type type) {
        super(category, title, true, type);
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }
}
