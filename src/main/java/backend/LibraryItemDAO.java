package backend;

import Util.Queries;
import dataClasses.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class LibraryItemDAO implements Dao<LibraryItem> {
    private PreparedStatement stmt;

    @Override
    public Collection<LibraryItem> getAll(Connection con) {
        Collection<LibraryItem> list = new ArrayList<>();
        try {
            stmt = con.prepareStatement(Queries.GET_ALL_ITEMS);
            HashMap<Integer, Category> categoryMap = new HashMap<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int categoryId = rs.getInt(2);
                String categoryName = rs.getString(3);
                Category category = new Category(categoryId, categoryName);
                if (categoryMap.get(categoryId) == null) {
                    categoryMap.put(categoryId, category);
                } else {
                    category = categoryMap.get(categoryId);
                }
                String title = rs.getString(4);
                String author = rs.getString(5);
                int pages = rs.getInt(6);
                int runTimeMinutes = rs.getInt(7);
                boolean isBorrowable = rs.getBoolean(8);
                String borrower = rs.getString(9);
                Date borrowDate = rs.getDate(10);
                Type type = Type.valueOf(rs.getString(11));
                LibraryItem item = null;

                switch (type) {
                    case Book:
                        item = new Book(category, title, author, pages);
                        break;
                    case DVD:
                        item = new DVD(category, title, runTimeMinutes);
                        break;
                    case AudioBook:
                        item = new AudioBook(category, title, runTimeMinutes);
                        break;
                    case ReferenceBook:
                        item = new ReferenceBook(category, title, author, pages);
                        break;
                    default:
                        break;
                }
                if (isBorrowable) {
                    ((BorrowableItem) item).setBorrower(borrower);
                    ((BorrowableItem) item).setBorrowDate(borrowDate);
                }
                item.setId(id);

                list.add(item);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LibraryItem getById(Connection con, int id) {
        try {
            stmt = con.prepareStatement(Queries.GET_ITEM_BY_ID);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int categoryId = rs.getInt(2);
                String categoryName = rs.getString(3);
                Category category = new Category(categoryId, categoryName);
                String title = rs.getString(4);
                String author = rs.getString(5);
                int pages = rs.getInt(6);
                int runTimeMinutes = rs.getInt(7);
                boolean isBorrowable = rs.getBoolean(8);
                String borrower = rs.getString(9);
                Date borrowDate = rs.getDate(10);
                Type type = Type.valueOf(rs.getString(11));

                LibraryItem item = null;

                switch (type) {
                    case Book:
                        item = new Book(category, title, author, pages);
                        break;
                    case DVD:
                        item = new DVD(category, title, runTimeMinutes);
                        break;
                    case AudioBook:
                        item = new AudioBook(category, title, runTimeMinutes);
                        break;
                    case ReferenceBook:
                        item = new ReferenceBook(category, title, author, pages);
                        break;
                    default:
                        break;
                }
                if (isBorrowable) {
                    ((BorrowableItem) item).setBorrower(borrower);
                    ((BorrowableItem) item).setBorrowDate(borrowDate);
                }
                item.setId(id);
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(Connection con, LibraryItem item) {
        try {
            stmt = con.prepareStatement(Queries.CREATE_ITEM);
            //These values are always used
            stmt.setInt(1, item.getCategory().getId());
            stmt.setString(2, item.getTitle());
            stmt.setBoolean(6, item.isBorrowable());
            stmt.setString(9, item.getType().toString());

            //Initiate this as null/0, overwrite later depending on type
            stmt.setString(3, null);
            stmt.setInt(4, 0);
            stmt.setInt(5, 0);
            stmt.setString(7, null);
            stmt.setDate(8, null);

            switch (item.getType()) {
                case Book:
                    Book book = (Book) item;
                    stmt.setString(3, book.getAuthor());
                    stmt.setInt(4, book.getPages());
                    break;
                case DVD:
                    DVD dvd = (DVD) item;
                    stmt.setInt(5, dvd.getRunTimeMinutes());
                    break;
                case AudioBook:
                    AudioBook audioBook = (AudioBook) item;
                    stmt.setInt(5, audioBook.getRunTimeMinutes());
                    break;
                case ReferenceBook:
                    ReferenceBook referenceBook = (ReferenceBook) item;
                    stmt.setString(3, referenceBook.getAuthor());
                    stmt.setInt(4, referenceBook.getPages());
                    break;
                default:
                    break;
            }
            if (item.isBorrowable()) {
                BorrowableItem borrowable = (BorrowableItem) item;
                stmt.setString(7, borrowable.getBorrower());
                stmt.setDate(8, borrowable.getBorrowDate());
            }

            ResultSet rs = stmt.executeQuery();
            rs.next();
            item.setId(rs.getInt(1));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Connection con, LibraryItem item) {
        try {
            stmt = con.prepareStatement(Queries.UPDATE_ITEM);
            //These values are always used
            stmt.setInt(1, item.getCategory().getId());
            stmt.setString(2, item.getTitle());
            stmt.setBoolean(6, item.isBorrowable());
            stmt.setInt(9, item.getId());

            //Initiate this as null/0, overwrite later depending on type
            stmt.setString(3, null);
            stmt.setInt(4, 0);
            stmt.setInt(5, 0);
            stmt.setString(7, null);
            stmt.setDate(8, null);

            if (item instanceof Book) {
                Book book = (Book) item;
                stmt.setString(3, book.getAuthor());
                stmt.setInt(4, book.getPages());
            } else if (item instanceof DVD) {
                DVD dvd = (DVD) item;
                stmt.setInt(5, dvd.getRunTimeMinutes());
            } else if (item instanceof AudioBook) {
                AudioBook audioBook = (AudioBook) item;
                stmt.setInt(5, audioBook.getRunTimeMinutes());
            } else if (item instanceof ReferenceBook) {
                ReferenceBook referenceBook = (ReferenceBook) item;
                stmt.setString(3, referenceBook.getAuthor());
                stmt.setInt(4, referenceBook.getPages());
            }
            //ignore Borrower and BorrowDate if item isn't borrowable
            if (item.isBorrowable()) {
                BorrowableItem borrowable = (BorrowableItem) item;
                stmt.setString(7, borrowable.getBorrower());
                stmt.setDate(8, borrowable.getBorrowDate());
            }

            int res = stmt.executeUpdate();
            System.out.println(res);
            return res >= 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Connection con, LibraryItem item) {
        try {
            stmt = con.prepareStatement(Queries.DELETE_ITEM);
            stmt.setInt(1, item.getId());
            int res = stmt.executeUpdate();
            return res >= 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
