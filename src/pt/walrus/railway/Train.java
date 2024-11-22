package pt.walrus.railway;

public class Train {
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
