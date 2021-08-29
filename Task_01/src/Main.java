import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.stream(new int[]{-2, -4, -6, 1,2,3,4,5}).filter(a -> a >= 0).count());
    }
}
