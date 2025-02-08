import java.awt.*;
import java.io.*;
import java.util.*;

public class GMain {
    private static Map<Integer,Character> special;
    private static ArrayList<String> name;
    private static ArrayList<String> lasName;
    private static Random rand = new Random();
    private static StringBuilder builder = new StringBuilder();
    private static Robot r;
    public static void main(String[] args) throws AWTException{
        r = new Robot();
        special = new HashMap<>();
        giveValueToSpecial();
        name = new ArrayList<>();
        lasName = new ArrayList<>();
        loadData();
        int date = rand.nextInt(28);
        int month = rand.nextInt(12);
        int year = rand.nextInt(2004-1980)+1980;
        String firstName = name.remove(rand.nextInt(name.size()));
        String lastName = lasName.remove(rand.nextInt(lasName.size()));
        int numeric = rand.nextInt(1_999_000_000) + 999_999;
        String pass = generatePass(firstName,lastName);
        System.out.println(pass);
    }
    private static void list(String user,String pass){
        File f = new File("C:\\Users\\sanjiv\\Desktop\\pass.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f,true))){
            writer.write((user+","+pass));
            writer.newLine();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void giveValueToSpecial(){
        special.put(1,'!');
        special.put(2,'@');
        special.put(3,'#');
        special.put(4,'$');
        special.put(5,'%');
        special.put(6,'^');
        special.put(7,'*');
        special.put(8,'(');
    }
    private static String generatePass(String start,String last){
        int charsFromName = rand.nextInt(3)+1;
        for(int i = 0;i<charsFromName;i++){
            builder.append(start.charAt(i));
            builder.append(last.charAt(i));
        }
        builder.append(special.get(rand.nextInt(8)+1));
        builder.append(special.get(rand.nextInt(8)+1));
        builder.append(rand.nextInt(90000));
        return String.valueOf(builder);
    }
    public static void loadData(){
        try(BufferedReader caste = new BufferedReader(new FileReader("caste.txt"));
            BufferedReader nameFile = new BufferedReader(new FileReader("name.txt"))){
            String n;
            boolean gender = true;
            while ((n = nameFile.readLine()) != null){
                if (n==","){
                    gender = false;
                    continue;
                }
                if(gender) {
                    name.add(n);
                }else {
                    name.add(n);
                }
            }
            while ((n = caste.readLine()) != null){
                lasName.add(n);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
