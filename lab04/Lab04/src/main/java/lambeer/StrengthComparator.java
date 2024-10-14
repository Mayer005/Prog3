package lambeer;

import java.util.*;

public class StrengthComparator implements Comparator<Beer>{
    @Override
    public int compare(Beer b1, Beer b2) {
        return Double.compare(b1.getStrength(), b2.getStrength());
    }
}