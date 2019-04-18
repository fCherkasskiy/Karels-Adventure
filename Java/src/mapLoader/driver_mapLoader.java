package mapLoader;

class driver_mapLoader {
    static String[] COLOR_LIST = {
            "#808000", "#008000",
            "#A52A2A", "#808080",

            "#0000A0", "#ADD8E6",
            "#800080", "#FF00FF",

            "white", "#FFA500",
            "#FFFF00", "#00FFFF",

            "black", "green",
            "red", "blue"};

    public static void main(String[] args) throws Exception{
        colors_mapLoader loader = new colors_mapLoader("E:\\TJHSST\\Karels-Adventure\\maps\\titanic.txt");

    }

}
