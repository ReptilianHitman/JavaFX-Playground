import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        File dir = new File("src/main/java/fun");
        File[] files = dir.listFiles();

        assert files != null;
        for (int i = 0; i < files.length; i++) {
            System.out.println(i + 1 + ": " + files[i].getName());
        }

        int fileIndex = new Scanner(System.in).nextInt() - 1;

        Class.forName("fun." + files[fileIndex].getName().substring(0, files[fileIndex].getName().length() - 5))
                .getMethod("main", String[].class)
                .invoke(null, (Object) new String[]{});
    }
}
