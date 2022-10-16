import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SquareTest {
    public List<Square> getSquares() {
        return squares;
    }

    public void setSquares(List<Square> squares) {
        this.squares = squares;
    }

    public List<Square> squares;

    public SquareTest() {
        this.squares = new ArrayList<>();
    }

    public boolean addSquare(Square square) {
        return squares.add(square);
    }

    public void removeOnIndex(int index) {
        if (index < squares.size())
            squares.remove(index);
    }

    public void removeOnElement(Square square) {
        squares.remove(square);
    }
    public String getMaxAreaSquare() {
        int maxIndex = 0;
        for (int i = 0; i < squares.size() - 1; i++) {
            if (squares.get(maxIndex).getArea() < squares.get(i + 1).getArea()) {
                maxIndex = i + 1;
            }
        }
        String maxAreaSquare = "The square with maximum area is " + maxIndex + ". It's area is " + squares.get(maxIndex).getArea() + ".";
        return maxAreaSquare;
    }

}
