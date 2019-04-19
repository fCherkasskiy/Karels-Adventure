package mapLoader;

import java.io.File;
import java.util.Scanner;

class ColorReader {

    private String[][] pictureList;
    private int length;
    private int height;
    private int size;

    ColorReader(String path) throws Exception{
        File map = new File(path);
        Scanner sc = new Scanner(map);
        length = sc.nextInt();
        height = sc.nextInt();
        size = length * height;
        pictureList = new String[height][length];
        for(int a = 0; a < height; a++){
            for(int b = 0; b < length; b++){
                pictureList[a][b] = ColorList.COLOR_LIST[sc.nextInt()];
            }
        }
    }

    String getColor(int x, int y){ return pictureList[x][y];}

    int getLength(){ return length;}

    int getHeight(){ return height;}

    int getSize(){ return size;}
}
