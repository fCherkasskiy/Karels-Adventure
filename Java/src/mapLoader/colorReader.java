package mapLoader;

import java.io.File;
import java.util.Scanner;

class colorReader {

    private String[] pictureList;
    private int length;
    private int height;
    private int size;

    colorReader(String path) throws Exception{
        File map = new File(path);
        Scanner sc = new Scanner(map);
        length = sc.nextInt();
        height = sc.nextInt();
        size = length * height;
        pictureList = new String[size];
        for(int i = 0; i < size; i++) {
            pictureList[i] = colorList.COLOR_LIST[sc.nextInt()];
        }

    }

    String getColor(int index){return this.pictureList[index];}

    int getLength(){ return length;}

    int getHeight(){ return height;}

    int getSize(){ return size;}
}
