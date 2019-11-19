package model;

import java.time.DayOfWeek;

public enum NumberLesson {
    one(1),
    two(2),
    three(3),
    four(4),
    five(5),
    six(6),
    seven(7),
    eight(8);

    private int number;
    NumberLesson(int i) {
        number = i;
    }

    public static NumberLesson getNumberLesson(int i){
        return values()[i-1];
    }

    public int getNumber() {
        return number;
    }


}

