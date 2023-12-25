package generate;

public class Id {
    private static long counter = 1;
    public static long generateId(){
        return counter++;
    }

}
