package Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataClasses.*;

/**
 * Helper class to check for Type and cast to correct Item class
 */
public class TypeCastJSON {
    /** #TODO Byta namn till något mer konkret?
     * Casts JSON String of LibraryItem to correct type of subclass
     * @param json JSON of LibraryItem in String representation
     * @return LibraryItem cast in correct type, or NULL if no type in json
     */
    public static LibraryItem cast(String json) {
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").create();
        LibraryItem item = gson.fromJson(json, LibraryItem.class);
        if (item == null || item.getType() == null)
            return null;
        switch (item.getType()) {
            case Book:
                item = gson.fromJson(json, Book.class);
                item.setBorrowable(true);
                return item;
            case DVD:
                item = gson.fromJson(json, DVD.class);
                item.setBorrowable(true);
                return item;
            case AudioBook:
                item = gson.fromJson(json, AudioBook.class);
                item.setBorrowable(true);
                return item;
            case ReferenceBook:
                item = gson.fromJson(json, ReferenceBook.class);
                item.setBorrowable(false);
                return item;
            default:
                return null;
        }
    }
}
