package Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;

public class TestStrings {
    public static final String BOOK = "{\"author\":\"D.C. Parlov\",\"pages\":855,\"borrower\":null,\"borrowDate\":null,\"id\":11,\"category\":{\"id\":3,\"categoryName\":\"Fantasy\"},\"title\":\"The Skyfire Cycle\",\"isBorrowable\":true,\"type\":\"Book\"}";
    public static final String DVD = "{\"runTimeMinutes\":134,\"borrower\":null,\"borrowDate\":null,\"id\":9,\"category\":{\"id\":1,\"categoryName\":\"Comedy\"},\"title\":\"Live in Concert\",\"isBorrowable\":true,\"type\":\"DVD\"}";
    public static final String AUDIO_BOOK = "{\"runTimeMinutes\":220,\"borrower\":null,\"borrowDate\":null,\"id\":17,\"category\":{\"id\":3,\"categoryName\":\"Fantasy\"},\"title\":\"Lord of the Rings\",\"isBorrowable\":true,\"type\":\"AudioBook\"}";
    public static final String REFERENCE_BOOK = "{\"author\":\"Matt Lennon\",\"pages\":250,\"id\":10,\"category\":{\"id\":4,\"categoryName\":\"Adventure\"},\"title\":\"Plants in America\",\"isBorrowable\":false,\"type\":\"ReferenceBook\"}";
    public static final String CATEGORIES = "[{\"id\":1,\"categoryName\":\"Comedy\"},{\"id\":2,\"categoryName\":\"Drama\"},{\"id\":3,\"categoryName\":\"Fantasy\"},{\"id\":4,\"categoryName\":\"Adventure\"},{\"id\":5,\"categoryName\":\"Documentary\"}]";
    public static final String CATEGORY = "{\"id\":1,\"categoryName\":\"Comedy\"}";
    private final String s = "[{\"author\":\"Peter Pan\",\"pages\":345,\"borrower\":null,\"borrowDate\":null,\"id\":6,\"category\":{\"id\":2,\"categoryName\":\"Drama\"},\"title\":\"So Much Drama\",\"isBorrowable\":true,\"type\":\"Book\"}";

    public static void main(String[] args) {
        Date date = new Date(System.currentTimeMillis());
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        System.out.println(date);
        System.out.println(gson.toJson(date));
    }
}
