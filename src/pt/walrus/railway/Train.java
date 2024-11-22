package pt.walrus.railway;

import java.io.Serializable;

public class Train implements Serializable {
    private final int number;
    private final Direction direction;

    public Train(int number, Direction direction) {
        this.number = number;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getNumber() {
        return number;
    }
}