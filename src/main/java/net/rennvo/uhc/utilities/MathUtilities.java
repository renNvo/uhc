package net.rennvo.uhc.utilities;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtilities {

    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

}
