import java.io.IOException;
import java.util.ArrayList;

public class Main2 {

    public static SquareTest getSquaresArray(int n) {
        Square[] arr = new Square[n];
        SquareTest test = new SquareTest();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Square(Math.random() * 200 - 100);
            System.out.println("" + i + " " + arr[i].toString());
            test.addSquare(arr[i]);
        }
        return test;
    }

    public static PrismTest getPrismsArray(int n) {
        Prism[] arr2 = new Prism[n];
        PrismTest p2 = new PrismTest();
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = new Prism(Math.random() * 80 - 40, Math.random() * 200 - 100);
            System.out.println("" + i + " " + arr2[i].toString());
            p2.addPrism(arr2[i]);
        }
        return p2;

    }

    public static void main(String[] args) throws IOException {

        FiguresInFile fg = new FiguresInFile();
        fg.setList((ArrayList<Prism>) getPrismsArray(15).getPrisms());
        long timeStart = System.currentTimeMillis(), t1, t2, t3, t4;
        t1 = System.currentTimeMillis() - timeStart;
        timeStart = System.currentTimeMillis();
        fg.savePrisms("filePrisms.txt");
        fg.clearPrisms();
        fg.loadPrisms("filePrisms.txt");
        t2 = System.currentTimeMillis() - timeStart;
        timeStart = System.currentTimeMillis();
        fg.serializePrisms("filePrismsSerialize.txt");
        fg.clearPrisms();
        fg.deserializePrisms("filePrismsSerialize.txt");
        t3 = System.currentTimeMillis() - timeStart;
        timeStart = System.currentTimeMillis();
        fg.serializeFastJSONPrisms("prisms2.json");
        fg.clearPrisms();
        fg.deserializeFastJSONPrisms("prisms2.json");
        t4 = System.currentTimeMillis() - timeStart;
        System.out.println("Initial Data Generation:	" + t1 + " ms");
        System.out.println("Text format Save/load:		" + t2 + " ms");
        System.out.println("Java serialization/des:		" + t3 + " ms");
        System.out.println("FastJackson serialization/des:	" + t4 + " ms");
        FiguresInFile fg2 = new FiguresInFile();
        fg2.setList2((ArrayList<Square>) getSquaresArray(15).getSquares());
        fg2.saveSquares("fileSquares.txt");
        fg2.clearSquares();
        fg2.loadSquares("fileSquares.txt");
        fg2.serializeSquares("fileSquaresSerialize.txt");
        fg2.clearSquares();
        fg2.deserializeSquares("fileSquaresSerialize.txt");
        fg2.serializeFastJSONSquares("squares2.json");
        fg2.clearSquares();
        fg2.deserializeFastJSONSquares("squares2.json");
    }

}
