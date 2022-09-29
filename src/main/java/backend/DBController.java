package backend;

import Util.Queries;
import dataClasses.BorrowableItem;
import dataClasses.Category;
import dataClasses.LibraryItem;

import java.sql.*;
import java.util.Collection;

//#TODO More Exception handling instead of just returning false
public class DBController {
    private Connection dbConnection;
    private static PreparedStatement stmt;
    private final Dao<Category> categoryDao = new CategoryDAO();
    private final Dao<LibraryItem> itemDao = new LibraryItemDAO();

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    //#TODO cli input user and password with constructor for login to db
    private final String user = "postgres";
    private final String password = "password";
    // run psql with command below for setup of database and some datarows
    // \i C:/Users/lindk/IntelliJIDEAProjects/Library/src/main/resources/db/db.sql

    public DBController() {
    }

    public boolean openConnection() {
        try {
            dbConnection = DriverManager.getConnection(url, user, password);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Collection<Category> getAllCategories() {
            return categoryDao.getAll(dbConnection);


    }

    public Category getCategoryById(int id) {
            return categoryDao.getById(dbConnection, id);


    }

    public boolean addCategory(Category category) {
        if(category != null) {
            return categoryDao.add(dbConnection, category);
        }
        return false;
    }

    public boolean updateCategory(Category category) {
        if(category != null) {
            return categoryDao.update(dbConnection, category);
        }
        return false;
    }

    public boolean deleteCategory(Category category) {
        if(category != null) {
            return categoryDao.delete(dbConnection, category);
        }
        return false;
    }

    public Collection<LibraryItem> getAllItems() {
            return itemDao.getAll(dbConnection);


    }

    public LibraryItem getItemById(int id) {
            return itemDao.getById(dbConnection, id);


    }

    public boolean addItem(LibraryItem item) {
        if(item != null) {
            return itemDao.add(dbConnection, item);
        }
        return false;
    }

    //@Override
    public boolean updateItem(LibraryItem item) {
        if(item != null) {
            return itemDao.update(dbConnection, item);
        }
        return false;
    }

    public boolean deleteItem(LibraryItem item) {
        if(item != null) {
            return itemDao.delete(dbConnection, item);
        }
        return false;
    }

    public boolean borrow(BorrowableItem item) {
        if(item != null) {
            if (item.getBorrower() == null) {
                return checkIn(item);
            } else {
                return checkOut(item);
            }
        }
        return false;
    }

    //#TODO Implement and Test checkIn and checkOut routes in frontend
    public boolean checkIn(BorrowableItem item) {
        BorrowableItem before = (BorrowableItem) itemDao.getById(dbConnection, item.getId());
        if (before.getBorrower() == null) {
            System.out.println("Item id: " + item.getId() + " is not borrowed out");
        } else {
            try {
                stmt = dbConnection.prepareStatement(Queries.SET_BORROWED);
                stmt.setString(1, null);
                stmt.setDate(2, null);
                stmt.setInt(3, item.getId());
                int res = stmt.executeUpdate();
                return res >= 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean checkOut(BorrowableItem item) {
        BorrowableItem before = (BorrowableItem) itemDao.getById(dbConnection, item.getId());
        if (before.getBorrower() != null) {
            System.out.println("Item id: " + item.getId() + " is already checked out");
        } else {
            try {
                System.out.println("check out");
                stmt = dbConnection.prepareStatement(Queries.SET_BORROWED);
                stmt.setString(1, item.getBorrower());
                stmt.setDate(2, item.getBorrowDate());
                stmt.setInt(3, item.getId());
                int res = stmt.executeUpdate();
                return res >= 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
