package backend;

import Util.Queries;
import dataClasses.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CategoryDAO implements Dao<Category> {
    private PreparedStatement stmt;

    @Override
    public Collection<Category> getAll(Connection con) {
        try {
            stmt = con.prepareStatement(Queries.GET_ALL_CATEGORIES);
            ResultSet rs = stmt.executeQuery();
            Collection<Category> collection = new ArrayList<>();
            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                collection.add(new Category(id, name));
            }
            return collection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category getById(Connection con, int id) {
        try {
            stmt = con.prepareStatement(Queries.GET_CATEGORY_BY_ID);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String name = rs.getString(2);
                if (id == rs.getInt(1))
                    return new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(Connection con, Category category) {
        try {
            stmt = con.prepareStatement(Queries.GET_ALL_CATEGORIES);
            ResultSet rs = stmt.executeQuery();
            String newCat = category.getCategoryName().toLowerCase();
            while(rs.next()) {
                String name = rs.getString(2).toLowerCase();
                if (name.equals(newCat)) {
                    System.out.println("Not unique categoryName, already in database id: " + rs.getInt(1));
                    return false;
                }
            }

            stmt = con.prepareStatement(Queries.CREATE_CATEGORY);
            stmt.setString(1, category.getCategoryName());
            int res = stmt.executeUpdate();
//            category.setId(rs.getInt(1));
            return res >= 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Connection con, Category category) {
        try {
            stmt = con.prepareStatement(Queries.UPDATE_CATEGORY);
            stmt.setString(1, category.getCategoryName());
            stmt.setInt(2, category.getId());
            int res = stmt.executeUpdate();
            return res >= 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Connection con, Category category) {
        try {
            //Check for references
            stmt = con.prepareStatement(Queries.CHECK_CATEGORY_REFERENCES);
            stmt.setInt(1, category.getId());
            ResultSet rs = stmt.executeQuery();
            //rs.next() returns false if empty ResultSet because of 0 result query
            if (rs.next()) {
                int categoryReferenced = rs.getInt(1);
                if (categoryReferenced > 0) return false;
            }
            //If no references in LibraryItem, go ahead and delete
            stmt = con.prepareStatement(Queries.DELETE_CATEGORY);
            stmt.setInt(1, category.getId());
            int res = stmt.executeUpdate();
            return res >= 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
