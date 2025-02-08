import java.awt.*;
import java.awt.event.KeyEvent;

public class TMain {
    public static void main(String[] args) {
        try {
            Main.robot = new Robot();
            Thread.sleep(5000);
            while (true){
                Main.clickIn();
                int a = Main.rand.nextInt(100);
                Thread.sleep(5000);
                if (a < 20){
                    Main.click();
                    Thread.sleep(20000);
                    Main.robot.keyPress(KeyEvent.VK_CONTROL);
                    Main.robot.delay(600);
                    Main.robot.keyPress(KeyEvent.VK_W);
                    Main.robot.delay(700);
                    Main.robot.keyRelease(KeyEvent.VK_W);
                    Main.robot.delay(600);
                    Main.robot.keyRelease(KeyEvent.VK_CONTROL);
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException | AWTException e){
            e.printStackTrace();
        }
    }
}
