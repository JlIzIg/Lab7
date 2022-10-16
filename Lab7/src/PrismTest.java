import java.util.ArrayList;
import java.util.List;

public class PrismTest {


    public List<Prism> getPrisms() {
        return prisms;
    }

    public void setPrisms(List<Prism> prisms) {
        this.prisms = prisms;
    }

    public List<Prism> prisms;

    public PrismTest() {
        this.prisms = new ArrayList<>();
    }

    public boolean addPrism(Prism prism) {
        return prisms.add(prism);
    }

    public void removeOnIndex(int index) {
        if (index < prisms.size())
            prisms.remove(index);
    }

    public void removeOnElement(Prism prism) {
        prisms.remove(prism);
    }

    public int getMaxDiagonal() {
        int maxIndex = 0;
        for (int i = 0; i < prisms.size() - 1; i++) {
            if (prisms.get(maxIndex).getDiagonal() < prisms.get(i + 1).getDiagonal()) {
                maxIndex = i + 1;
            }
        }
        return maxIndex;
    }
}

