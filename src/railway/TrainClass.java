package railway;

/**
 * The Train class represents a train with a train number and direction
 */
public class TrainClass implements Train {
    /**
     * Serializable class a version number
     */
    private static final long serialVersionUID = 0L;
    /**
     * Number of the train
     */
    private final int number;
    /**
     * Direction of the train
     */
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

    /**
     * Returns the direction of the train
     * @return the direction of the train
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Returns the number of the train
     * @return the number of the train
     */
    public int getNumber() {
        return number;
    }
}
