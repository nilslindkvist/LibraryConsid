package Util;

public class Queries {
    public static final String GET_ALL_ITEMS = "SELECT LibraryItem.Id, Category.Id AS cid, Category.CategoryName, Title, Author, Pages, RunTimeMinutes, isBorrowable, Borrower, BorrowDate, Type FROM LibraryItem INNER JOIN Category ON Category.Id=LibraryItem.CategoryId ORDER BY CategoryName";
    public static final String GET_ITEM_BY_ID = "SELECT LibraryItem.Id, Category.Id AS cid, Category.CategoryName, Title, Author, Pages, RunTimeMinutes, isBorrowable, Borrower, BorrowDate, Type FROM LibraryItem INNER JOIN Category ON Category.Id=LibraryItem.CategoryId WHERE LibraryItem.Id = ?";
    public static final String CREATE_ITEM = "INSERT INTO LibraryItem (Id, CategoryId, Title, Author, Pages, RunTimeMinutes, isBorrowable, Borrower, BorrowDate, Type)" +
            " VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING ID";
    public static final String UPDATE_ITEM = "UPDATE LibraryItem SET CategoryId = ?, Title = ?, Author = ?, Pages = ?, " +
            "RunTimeMinutes = ?, isBorrowable = ?, Borrower = ?, BorrowDate = ? WHERE ID = ?";
    public static final String DELETE_ITEM = "DELETE FROM LibraryItem WHERE ID = ?";
    public static final String SET_BORROWED = "UPDATE LibraryItem SET Borrower = ?, BorrowDate = ? WHERE ID = ?";

    public static final String GET_CATEGORY_BY_ID = "SELECT Id, CategoryName FROM Category WHERE ID = ?";
    public static final String GET_ALL_CATEGORIES = "SELECT Id, CategoryName FROM Category";
    public static final String CREATE_CATEGORY = "INSERT INTO Category (Id, CategoryName) VALUES (DEFAULT, ?)";
    public static final String UPDATE_CATEGORY = "UPDATE Category SET CategoryName = ? WHERE ID = ?";
    public static final String DELETE_CATEGORY = "DELETE FROM Category WHERE ID = ?";
    public static final String CHECK_CATEGORY_REFERENCES = "SELECT LibraryItem.Id FROM LibraryItem INNER JOIN Category ON Category.Id=LibraryItem.CategoryId WHERE Category.Id = ?";

    String sortByCategoryName = "SELECT LibraryItem.Id, Category.Id AS cid, Category.CategoryName, Title, Author, Pages, RunTimeMinutes, isBorrowable, Borrower, BorrowDate, Type FROM LibraryItem INNER JOIN Category ON Category.Id=LibraryItem.CategoryId ORDER BY CategoryName";
}
