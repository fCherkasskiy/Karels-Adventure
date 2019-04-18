package mapLoader;

import java.io.File;
import java.util.Scanner;

import static mapLoader.driver_mapLoader.COLOR_LIST;

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
        String[] pictureList = new String[size -2];
        for(int i = 0; i < size; i++)
            pictureList[i] = COLOR_LIST[sc.nextInt()];
    }

    String getColor(int i){ return this.pictureList[i];}

    int getLength(){ return length;}

    int getHeight(){ return height;}

    int getSize(){ return size;}
}
