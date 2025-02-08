import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.Key;
import java.util.Random;

public class Main {
    private static final String url = "https://peakme.in/getRandAgent.php";
    public static Robot robot;
    private static final Runtime p = Runtime.getRuntime();
    public static final Random rand = new Random();
    private static final int mouse = InputEvent.BUTTON1_DOWN_MASK;
    private static final int delay = 600;
    public static void main(String[] args)  {
        try {
            Thread.sleep(5000);
            robot = new Robot();
            for (int i = 0;i<100;i++){
                int click = rand.nextInt(100);
                boolean changeAgent = rand.nextBoolean();
                nextWindow();
                clickIn();
                nextWindow();
                if (changeAgent){
                    setFile(loadData());
                    close();
                    Thread.sleep(1000);
                    open();
                    Thread.sleep(7000);
                } else {
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.delay(600);
                    robot.keyPress(KeyEvent.VK_N);
                    robot.delay(600);
                    robot.keyRelease(KeyEvent.VK_N);
                    robot.delay(600);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    Thread.sleep(5000);
                }
                Thread.sleep(5000);
                System.out.println(i);
                if (!changeAgent){
                    close();
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void setFile(String data) throws IOException{
        File file = new File("C:\\Users\\sanjiv\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\xa7mydw8.default-release\\user.js");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        if (!data.equals("")) writer.write("user_pref(\"general.useragent.override\", \""+ data +"\");");
        writer.close();
        fileWriter.close();
    }
    private static String loadData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder().GET().header("accept","application/json")
                .uri(URI.create(url)).build();
        HttpResponse resp;
        resp = client.send(req,HttpResponse.BodyHandlers.ofString());
        return resp.body().toString();
    }
    public static void click(){
        robot.mouseMove(506,623);
        robot.delay(1000);
        robot.mousePress(mouse);
        robot.delay(573);
        robot.mouseRelease(mouse);
    }
    private static void nextWindow() throws InterruptedException{
        robot.keyPress(KeyEvent.VK_ALT);
        robot.delay(delay);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_ALT);
        Thread.sleep(1000);
    }
    public static void clickIn() throws InterruptedException{
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(delay);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(delay);
        robot.keyPress(KeyEvent.VK_L);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_L);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(delay);
        Thread.sleep(4000);
    }
    private static void open() throws IOException{
            p.exec("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
    }
    private static void close(){
        robot.mouseMove(1510,10);
        robot.delay(900);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(delay);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
