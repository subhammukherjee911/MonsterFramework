package Utilities;

import org.openqa.selenium.devtools.v122.dom.model.BoxModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Configuration
{
    Properties prop = new Properties();

    public Configuration()
    {

    }
    public void Config() throws IOException {
        try {
            prop.load(new FileInputStream("src/test/resources/config.properties"));
        }
        catch (FileNotFoundException e)
        {
            throw new FileNotFoundException(e.getMessage());
        }
        String BrowserOption = prop.getProperty("BrowserOption");
        String BrowserSetSize = prop.getProperty("BrowserSetSize");
        String ClearCookies = prop.getProperty("ClearCookies");
        MonstorLogo();
        System.out.println("The selected browser category is "+BrowserOption);
        if(BrowserOption!= null && BrowserSetSize!=null && BrowserOption.length()>0 && BrowserSetSize.length()>0)
        {
            WebDriverSingleton.setBrowser(BrowserOption, BrowserSetSize, ClearCookies);
        }
        else
        {
            System.out.println("-----Invalid options given-----");
        }
    }

    public String getTags()
    {
            String tags = prop.getProperty("Tags");
            System.out.println("Tags -> "+tags);
            return tags;
    }

    public static void MonstorLogo()
    {
        String colorRed = "\u001B[31m";
        String colorGreen = "\u001B[32m";
        String colorYellow = "\u001B[33m";
        String colorBlue = "\u001B[34m";
        String colorMagenta = "\u001B[35m";
        String colorCyan = "\u001B[36m";
        String boldOn = "\u001B[1m";
        String resetFormat = "\u001B[0m";
        System.out.println(colorBlue+boldOn+"M      F"+resetFormat);
        System.out.println(colorGreen+boldOn+"O      R"+resetFormat);
        System.out.println(colorCyan+boldOn+"N      A"+resetFormat);
        System.out.println(colorYellow+boldOn+"S      M"+resetFormat);
        System.out.println(colorMagenta+boldOn+"T      E"+resetFormat);
        System.out.println(colorRed+boldOn+"E      W"+resetFormat);
        System.out.println(colorYellow+boldOn+"R      O"+resetFormat);
        System.out.println(colorBlue+boldOn+"       R"+resetFormat);
        System.out.println(colorCyan+boldOn+"       K"+resetFormat);
        System.out.println("For any queries related to framework design and structure please reach out to Subham Mukherjee - subham.a.mukherjee@capgemini.com");
    }
}
