package backend;

import Util.TypeCastJSON;
import com.google.gson.GsonBuilder;
import dataClasses.*;
import com.google.gson.Gson;

import static spark.Spark.*;

public class APIServer {
    private final Gson gson;
    private final DBController db;


    public APIServer() {
        ipAddress("localhost");
        port(4040);
        staticFiles.location("/public");
        gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").create();
        db = new DBController();
        if(!db.openConnection())
            return;

        /*
         * Landing page redirect
         */
        get("/", (req, res) -> {
            System.out.println(req.body());
            System.out.println(res.status());
            System.out.println("hello");
            res.redirect("/html/index.html");
            return true;
        });

        /*
         * Routes for Category management
         */
        get("/categories", (req, res) -> {
            System.out.println(req.body());
            System.out.println(res.status());
            return gson.toJson(db.getAllCategories());
        });

        get("/categories/:id", (req, res) -> {
            System.out.println(req.body());
            System.out.println(res.status());
            return gson.toJson(db.getCategoryById(Integer.parseInt(req.params(":id"))), Category.class);
        });

        post("/categories", (req, res) -> {
            System.out.println(req.body());
            System.out.println(res.status());
            Category category = gson.fromJson(req.body(), Category.class);
            return db.addCategory(category);
        });

        put("/categories/:id", (req, res) -> {
            System.out.println(req.body());
            System.out.println(res.status());
            Category category = gson.fromJson(req.body(), Category.class);
            if (category.getId() == Integer.parseInt(req.params(":id"))) {
                return db.updateCategory(category);
            } else {
                return false;
            }
        });

        delete("/categories/:id", (req, res) -> {
            System.out.println(req.body());
            System.out.println(res.status());
            Category category = gson.fromJson(req.body(), Category.class);
            if (category.getId() == Integer.parseInt(req.params(":id"))) {
                return db.deleteCategory(category);
            } else {
                return false;
            }
        });

        /*
         * Routes for management of LibraryItems
         */
        get("/items", (req, res) -> {
            System.out.println(req.body());
            System.out.println(res.status());
            return gson.toJsonTree(db.getAllItems());
        });

        get("/items/:id", (req, res) -> {
            System.out.println(req.body());
            System.out.println(res.status());
            return gson.toJson(db.getItemById(Integer.parseInt(req.params(":id"))));
        });

        post("/items", (req, res) -> {
            System.out.println(req.body());
            System.out.println(res.status());
            LibraryItem item = TypeCastJSON.cast(req.body());
            if (item == null) return false;
            return db.addItem(item);
        });

        put("/items/:id", (req, res) -> {
            System.out.println(req.body());
            System.out.println(res.status());
            LibraryItem item = TypeCastJSON.cast(req.body());
            if (item == null) return false;
            if (item.getId() == Integer.parseInt(req.params(":id"))) {
                return db.updateItem(item);
            } else {
                return false;
            }
        });

        put("/items/:id/borrow", (req, res) -> {
            System.out.println(req.body());
            System.out.println(res.status());
            LibraryItem item = TypeCastJSON.cast(req.body());
            if (item == null) return false;
            if (item.isBorrowable()) {
                if (item.getId() == Integer.parseInt(req.params(":id"))) {
                    return db.borrow((BorrowableItem) item);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        });

        delete("/items/:id", (req, res) -> {
            System.out.println(req.body());
            System.out.println(res.status());
            LibraryItem item = TypeCastJSON.cast(req.body());
            if (item == null) return false;
            if (item.getId() == Integer.parseInt(req.params(":id"))) {
                return db.deleteItem(item);
            } else {
                return false;
            }
        });
    }

    public static void main(String[] args) {
        new APIServer();
    }
}