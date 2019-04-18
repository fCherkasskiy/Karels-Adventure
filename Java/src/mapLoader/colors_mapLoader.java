package mapLoader;

import java.io.File;
import java.util.Scanner;

class colors_mapLoader {

    private String[] pictureList;
    private int length;
    private int height;
    private int size;

    colors_mapLoader(String path) throws Exception{
        File map = new File(path);
        Scanner sc = new Scanner(map);
        length = sc.nextInt();
        height = sc.nextInt();
        size = length * height;
        String[] pictureList = new String[size];
        for(int i = 0; i < size; i++) {
            int a = sc.nextInt();
            System.out.print(a);
//            pictureList[i] = driver_mapLoader.COLOR_LIST[a];
//            System.out.println(pictureList[i]);
            String g = driver_mapLoader.COLOR_LIST[a];
            System.out.println(g);
        }
    }

    String getColor(int i){return this.pictureList[i];}

    int getLength(){ return length;}

    int getHeight(){ return height;}

    int getSize(){ return size;}
}
