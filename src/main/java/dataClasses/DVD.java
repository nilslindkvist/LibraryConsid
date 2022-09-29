package dataClasses;

public class DVD extends BorrowableItem {
    private int runTimeMinutes;

    public DVD() {
        super(Type.DVD);
    }

    public DVD(Category category, String title, int runTimeMinutes) {
        super(category, title, Type.DVD);
        this.runTimeMinutes = runTimeMinutes;
    }

    public int getRunTimeMinutes() {
        return runTimeMinutes;
    }

    public void setRunTimeMinutes(int runTimeMinutes) {
        this.runTimeMinutes = runTimeMinutes;
    }
}
