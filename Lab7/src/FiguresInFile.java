import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;


public class FiguresInFile {

    public ArrayList<Prism> list;
    public ArrayList<Square> list2;

    public ArrayList<Prism> getList() {
        return list;
    }

    public void setList(ArrayList<Prism> list) {
        this.list = list;
    }

    public ArrayList<Square> getList2() {
        return list2;
    }

    public void setList2(ArrayList<Square> list2) {
        this.list2 = list2;
    }

    public FiguresInFile() {
        list = new ArrayList<>();
    }

    public FiguresInFile(ArrayList<Square> list2) {
        this.list2 = list2;
    }

    /**
     * Реализация метода Save для Prisms
     */
    public void savePrisms(String filename) throws IOException {
        FileWriter outStream = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(outStream);
        for (Prism prism : list) {
            try {
                bw.write(String.valueOf(prism.getHeight()));
                bw.write(System.lineSeparator());
                bw.write(String.valueOf(prism.getSideOfSquare()));
                bw.write(System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        bw.close();
        outStream.close();
    }

    /**
     * Реализация метода load для Prisms
     */

    public void loadPrisms(String filename) throws IOException {
        this.clearPrisms();
        Scanner scanner = new Scanner(new FileReader(filename));
        double height = -1;
        double sideOfSquare = -1;
        while (scanner.hasNextLine()) {
            height = Double.parseDouble(scanner.nextLine());
            sideOfSquare = Double.parseDouble(scanner.nextLine());
            this.list.add(new Prism(height, sideOfSquare));
        }
        scanner.close();
    }

    public void clearPrisms() {
        this.list.clear();
    }

    /**
     * реализация метода save для Squares
     **/
    public void saveSquares(String filename) throws IOException {
        FileWriter outStream = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(outStream);
        for (Square square : list2) {
            try {
                bw.write(String.valueOf(square.getSideOfSquare()));
                bw.write(System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        bw.close();
        outStream.close();
    }

    /**
     * Реализация метода load для Squares
     */
    public void loadSquares(String filename) throws IOException {
        this.clearSquares();
        Scanner scanner = new Scanner(new FileReader(filename));
        double sideOfSquare = -1;
        while (scanner.hasNextLine()) {
            sideOfSquare = Double.parseDouble(scanner.nextLine());
            this.list2.add(new Square(sideOfSquare));
        }
        scanner.close();
    }

    public void clearSquares() {
        this.list2.clear();
    }

    /**
     * сериализация для Prisms
     */
    public void serializePrisms(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.list);
            out.close();
            fileOut.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * десериализация для Prisms
     */
    public void deserializePrisms(String filename) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.list = (ArrayList<Prism>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Prism class not found");
            c.printStackTrace();
        }
    }

    public void serializeSquares(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.list2);
            out.close();
            fileOut.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deserializeSquares(String filename) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.list2 = (ArrayList<Square>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Prism class not found");
            c.printStackTrace();
        }
    }

    /**
     * серилизация при помощи внешней библиотеки
     */
    public void serializeFastJSONPrisms(String filename) throws IOException {
        FileWriter outStream = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(outStream);
        bw.write(JSON.toJSONString(this.list));
        bw.close();
        outStream.close();
    }

    /**
     * десерилизация при помощи внешней библиотеки
     */
    public void deserializeFastJSONPrisms(String filename) throws IOException {
        Scanner scanner = new Scanner(new FileReader(filename));
        this.clearPrisms();
        ArrayList<JSONObject> JSONlist = JSON.parseObject(scanner.nextLine(), ArrayList.class);
        for (JSONObject st : JSONlist) {
            this.addPrism(new Prism(st.getDoubleValue("height"), st.getDoubleValue("sideOfSquare")));
        }
        scanner.close();
    }

    public void addPrism(Prism prism) {
        this.list.add(prism);
    }

    public void serializeFastJSONSquares(String filename) throws IOException {
        FileWriter outStream = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(outStream);
        bw.write(JSON.toJSONString(this.list2));
        bw.close();
        outStream.close();
    }

    public void deserializeFastJSONSquares(String filename) throws IOException {
        Scanner scanner = new Scanner(new FileReader(filename));
        this.clearSquares();
        ArrayList<JSONObject> JSONlist = JSON.parseObject(scanner.nextLine(), ArrayList.class);
        for (JSONObject st : JSONlist) {
            this.addSquare(new Square(st.getDoubleValue("sideOfSquare")));
        }
        scanner.close();
    }

    public void addSquare(Square square) {
        this.list2.add(square);
    }
}
