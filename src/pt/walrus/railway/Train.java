package pt.walrus.railway;

import java.io.Serializable;

/**
 * The Train interface represents an interface of a Train Class
 */
public interface Train extends Serializable {
    /**
     * Returns the direction of the train.
     *
     * @return the direction
     */
    Direction getDirection();
    /**
     * Returns the number of the train.
     *
     * @return the number of the train.
     */
    int getNumber();

}
