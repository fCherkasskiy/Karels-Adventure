public class Main implements Runnable {
   Editor editor = new Editor();
   public static void main(String[] args){
       new Thread(new Main()).start();
   }
   @Override
   public void run() {
       while(true){
           editor.repaint();
       }

   }
}

