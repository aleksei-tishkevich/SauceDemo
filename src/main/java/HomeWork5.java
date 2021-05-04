import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.*;
import java.util.Scanner;


public class HomeWork5 {

    private String xpathOfWebElementWithAllUserNames;
    private String xpathOfWebElementUserNameField;
    private String xpathOfWebElementPasswordField;
    private String xpathOfWebElementLoginButton;
    private String xpathOfWebElementWithPasswordValue;
    private String xpathOfWebElementWithNameOfProduct;
    private String xpathOfWebElementWithPriceOfProduct;
    private WebDriver driver;

    public void readFile() throws FileNotFoundException {
        File file = new File("elementLocation.txt");
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        String[] xpathes = line.split(" ");
        scanner.close();

        xpathOfWebElementWithAllUserNames = xpathes[1];
        xpathOfWebElementUserNameField = xpathes[2];
        xpathOfWebElementPasswordField = xpathes[3];
        xpathOfWebElementLoginButton = xpathes[4];
        xpathOfWebElementWithPasswordValue = xpathes[5];
        xpathOfWebElementWithNameOfProduct = xpathes[6];
        xpathOfWebElementWithPriceOfProduct = xpathes[7];
        //This block of code works with text file. It gets string from the file and assigns values to the strings
    }


    @BeforeMethod
    public void openWebSite()  {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver 5");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        //This block of the code opens website
    }

    @Test
    public void loginInToSystemAndCheckPriceOfBag() throws FileNotFoundException {
        readFile();
        //this is a call to a method that works with the file
        WebElement allUserNames = driver.findElement(By.xpath(xpathOfWebElementWithAllUserNames));
        String stringUsersName = allUserNames.getText();
        //here code finds element with all user names and gets text
        String[] userNames = stringUsersName.split("\\n");
        //here the text is parsed into an array of strings
        String finalUserName = userNames[1];
        //assign the variable to the desired username
        WebElement loginField = driver.findElement(By.xpath(xpathOfWebElementUserNameField));
        Actions action = new Actions(driver);
        action.click(loginField).sendKeys(finalUserName).build().perform();
        //fill username to the field username
        WebElement passwordField = driver.findElement(By.xpath(xpathOfWebElementPasswordField));
        WebElement loginButton = driver.findElement(By.xpath(xpathOfWebElementLoginButton));
        WebElement webElementWithPasswordValue = driver.findElement(By.xpath(xpathOfWebElementWithPasswordValue));
        String pass = webElementWithPasswordValue.getText();
        String[] arrayWithPasswordValue = pass.split("\\n");
        String password = arrayWithPasswordValue[1];
        //find element with password, get text from the element, parse the text and assign to string
        action.click(passwordField).sendKeys(password).build().perform();
        //fill password to the field password
        loginButton.click();
        //login into system
        WebElement bag = driver.findElement(By.xpath(xpathOfWebElementWithNameOfProduct));
        String bagName = bag.getText();
        System.out.println("Name of the first item: " + bagName);
        WebElement bagPrice = driver.findElement(By.xpath(xpathOfWebElementWithPriceOfProduct));
        String bagPriceFinal = bagPrice.getAttribute("innerText");
        System.out.println("Price of the first item: " + bagPriceFinal);
        //take and print out the necessary data in the console
        Assert.assertTrue(bagPriceFinal.contains("29.99"), String.valueOf(bagName.contains("Sauce Labs Backpack")));
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
