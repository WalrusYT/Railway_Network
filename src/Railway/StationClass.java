package Railway;

import Railway.dataStructures.DoubleList;
import Railway.dataStructures.List;

public class StationClass implements Station {
    private final String name;
    private final List<Line> lines = new DoubleList<>();

    public StationClass (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
