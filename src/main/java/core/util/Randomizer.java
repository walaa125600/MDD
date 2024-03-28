package core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Randomizer {
    private static final Random RANDOM = new Random();
    public static String dateTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    public static String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
    public static String timestamp = new SimpleDateFormat("hhmmssss").format(new Date());

    public static int getInt() {
        return RANDOM.nextInt(999999999);
    }
}

