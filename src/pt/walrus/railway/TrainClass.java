package pt.walrus.railway;

/**
 * The Train class represents a train with a train number and direction
 */
public class TrainClass implements Train {
    /**
     * Serializable class a version number
     */
    private static final long serialVersionUID = 0L;
    private final int number;
    private final Direction direction;

    /**
     * Instantiates a new Train.
     *
     * @param number    the number
     * @param direction the direction
     */
    public TrainClass(int number, Direction direction) {
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
