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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationClass that = (StationClass) o;
        return name.equals(that.name) && lines.equals(that.lines);
    }
}
