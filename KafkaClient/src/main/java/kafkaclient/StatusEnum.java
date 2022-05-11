package kafkaclient;

import java.util.Random;

public enum StatusEnum {
    RETIRED,
    SALARY,
    HOURLY,
    PART_TIME;

    public static StatusEnum getRandomStatus(){
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}
