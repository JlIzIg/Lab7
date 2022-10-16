import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("***Демонстрация класса Square***");
        Square a = new Square(-5);
        System.out.println("Area is " + a.getArea());
        System.out.println("Perimeter is " + a.getPerimeter());
        System.out.println("Diagonal is " + a.getDiagonal());
        System.out.println("***Демонстрация класса SquareTest***");
        Square[] arr = new Square[10];
        SquareTest test = new SquareTest();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Square(Math.random() * 200 - 100);
            System.out.println("" + i + " " + arr[i].toString());
            test.addSquare(arr[i]);
        }

        System.out.println(test.getMaxAreaSquare());
        System.out.println("***Демонстрация класса Prism***");
        Prism p = new Prism(4, 5);
        System.out.println(p);
        System.out.println("***Демонстрация класса PrismTest***");
        Prism[] arr2 = new Prism[10];
        PrismTest p2 = new PrismTest();
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = new Prism(Math.random() * 80 - 40, Math.random() * 200 - 100);
            System.out.println("" + i + " " + arr2[i].toString());
            p2.addPrism(arr2[i]);
        }
        System.out.println("Prism with max diagonal is " + p2.getMaxDiagonal() + "\n" + p2.prisms.get(p2.getMaxDiagonal()));
        p2.removeOnIndex(p2.getMaxDiagonal());
        System.out.println("Prism with max diagonal after removing (of index) is " + p2.getMaxDiagonal() + "\n" + p2.prisms.get(p2.getMaxDiagonal()).toString());
        p2.removeOnElement(p2.prisms.get(p2.getMaxDiagonal()));
        System.out.println("Prism with max diagonal after removing (of element) is " + p2.getMaxDiagonal() + "\n" + p2.prisms.get(p2.getMaxDiagonal()).toString());


    }
}
