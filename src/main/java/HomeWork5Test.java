import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class HomeWork5Test {
//This code creates text file with xpathes from this task
    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        try {
            File elementLocation = new File("elementLocation.txt");
            if (!elementLocation.exists())
                elementLocation.createNewFile();
            PrintWriter pw = new PrintWriter(elementLocation);
            pw.print("//*[@id='login_credentials']/*[text()] ");
            pw.print("//*[@id='login_credentials'] ");
            pw.print("//*[@id='user-name'] ");
            pw.print("//*[@id='password'] ");
            pw.print("//*[@id='login-button'] ");
            pw.print("//*[@id='root']/div/div[2]/div[2]/div/div[2] ");
            pw.print("//*[@id='item_4_title_link']/div ");
            pw.print("//*[@id='inventory_container']/div/div[1]/div[2]/div[2]/div");
            pw.close();
            br = new BufferedReader(new FileReader("elementLocation.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException error) {
            System.out.println("Error: " + error);
        } finally {
            try {
                br.close();
            } catch (IOException error) {
                System.out.println("Error: " + error);
            }
        }

    }

}
