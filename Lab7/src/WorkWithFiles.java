import java.io.*;
import java.net.Proxy;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.asm.Type;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class WorkWithFiles {


    public void save(String filename, ArrayList list) throws IOException {
        String tm2 = getNameCopyFile();
        File f1 = new File(filename);
        FileWriter outStreamf = new FileWriter(tm2 + filename);
        File f2 = new File(tm2 + filename);
        FileWriter outStream = new FileWriter(filename);
        copyFile(f1, f2);
        BufferedWriter bw = new BufferedWriter(outStream);
        if ((ArrayList<Square>) list instanceof ArrayList<Square>) {
            for (Square square : (ArrayList<Square>) list) {
                try {
                    bw.write(String.valueOf(square.getSideOfSquare()));
                    bw.write(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if ((ArrayList<Prism>) list instanceof ArrayList<Prism>) {
            for (Prism prism : (ArrayList<Prism>) list) {
                try {
                    bw.write(String.valueOf(prism.getSideOfSquare()));
                    bw.write(System.lineSeparator());
                    bw.write(String.valueOf(prism.getHeight()));
                    bw.write(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        bw.close();
        tm2 = getNameCopyFile();
        f2 = new File(tm2 + filename);
        outStreamf = new FileWriter(tm2 + filename);
        outStreamf.close();
        copyFile(f1, f2);
        outStream.close();
    }

    public void load(String filename, ArrayList list) throws IOException {
        this.Clear(list);
        Scanner scanner = new Scanner(new FileReader(filename));
        double height = -1;
        double sideOfSquare = -1;
        while (scanner.hasNextLine()) {
            height = Double.parseDouble(scanner.nextLine());
            sideOfSquare = Double.parseDouble(scanner.nextLine());
            if ((ArrayList<Square>) list instanceof ArrayList<Square>) {
                list.add(new Square(sideOfSquare));
            }
            if ((ArrayList<Prism>) list instanceof ArrayList<Prism>) {
                list.add(new Prism(height, sideOfSquare));
            }

        }
        scanner.close();
    }

    public void Clear(ArrayList list) {
        list.clear();
    }

    public void serialize(String filename, ArrayList list) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void deserialize(String filename, ArrayList list) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            if ((ArrayList<Square>)list instanceof ArrayList<Square>){
                list = (ArrayList<Square>) in.readObject();
            }
            else if ((ArrayList<Prism>)list instanceof ArrayList<Prism>){
                list = (ArrayList<Prism>) in.readObject();
            }
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }



    public void sFastJSON(String filename, ArrayList list) throws IOException {
        FileWriter outStream = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(outStream);
        bw.write(JSON.toJSONString(list));
        bw.close();
        outStream.close();
    }


    public void dFastJSON(String filename, ArrayList list) throws IOException {
        Scanner scanner = new Scanner(new FileReader(filename));
        this.Clear(list);
        ArrayList<JSONObject> JSONlist = JSON.parseObject(scanner.nextLine(), ArrayList.class);
        for (JSONObject st : JSONlist) {
            if ((ArrayList<Square>)list instanceof ArrayList<Square>){
                SquareTest c = new SquareTest();
                c.addSquare(new Square(st.getDoubleValue("sideOfSquare")));
            }
            else if ((ArrayList<Prism>)list instanceof ArrayList<Prism>){
                PrismTest c = new PrismTest();
                c.addPrism(new Prism( st.getDoubleValue("height"), st.getDoubleValue("sideOfSquare")));
            }
        }
        scanner.close();
    }

    public String getNameCopyFile() {
        LocalDateTime tm = LocalDateTime.now();
        String tm2 = "" + tm;
        tm2 = tm2.replace(".", "_");
        tm2 = tm2.replace(":", "-");
        return tm2;
    }

    private static void copyFile(File source, File dest) throws IOException {
        FileChannel sourceChannel;
        FileChannel destChannel;
        sourceChannel = new FileInputStream(source).getChannel();
        destChannel = new FileOutputStream(dest).getChannel();
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

    }

}
