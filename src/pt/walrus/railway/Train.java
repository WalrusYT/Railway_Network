package pt.walrus.railway;

import java.io.Serializable;

/**
 * The type Train.
 */
public class Train implements Serializable {
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
    public Train(int number, Direction direction) {
        this.number = number;
        this.direction = direction;
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public int getNumber() {
        return number;
    }
}
