package mapLoader;

import java.io.File;
import java.util.Scanner;

public class colors_mapLoader {

    public int length;
    public int height;
    public int size;
    public int[] colorlist;

    public void getFile() throws Exception {
        File map = new File("E:\\TJHSST\\Karels-Adventure\\Java\\titanic.txt");
        Scanner sc = new Scanner(map);
        length = sc.nextInt();
        height = sc.nextInt();
        size = length * height;
        for(int i = 2; i < size; i++)
            colorlist.
    }



}
