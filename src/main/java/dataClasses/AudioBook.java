package dataClasses;

public class AudioBook extends BorrowableItem {
    private int runTimeMinutes;

    public AudioBook() {
        super(Type.AudioBook);
    }

    public AudioBook(Category category, String title, int runTimeMinutes) {
        super(category, title, Type.AudioBook);
        this.runTimeMinutes = runTimeMinutes;
    }

    public int getRunTimeMinutes() {
        return runTimeMinutes;
    }

    public void setRunTimeMinutes(int runTimeMinutes) {
        this.runTimeMinutes = runTimeMinutes;
    }
}
