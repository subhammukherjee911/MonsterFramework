package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverSingleton {
    private static WebDriver driver;
    private static String browser;
    private static String browserSize;
    private static String cookieOption;
    private WebDriverSingleton()
    {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            if (browser == null) {
                throw new IllegalStateException("Browser is not set!");
            }
            if (browser.equalsIgnoreCase("Chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                System.out.println("---Successfully launched Chrome Browser---");
                ClearBrowserCookies(cookieOption);
                driver.get("http://www.google.com");
                SetBrowserSize(browserSize);
            } else if (browser.equalsIgnoreCase("Edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                System.out.println("---Successfully launched Edge Browser---");
                ClearBrowserCookies(cookieOption);
                driver.get("http://www.google.com");
                SetBrowserSize(browserSize);
            } else {
                throw new IllegalStateException("Invalid browser option");
            }
        }
        return driver;
    }
    public static String getBrowserSize()
    {
        return browserSize;
    }

    public static void setBrowser(String browserOption, String browserSizeOption, String CookieOption) {
        if (driver != null)
        {
            throw new IllegalStateException("Driver already initialized, can't change browser type");
        }
        browser = browserOption;
        browserSize = browserSizeOption;
        cookieOption = CookieOption;
    }

    public static void resetDriver()
    {
        driver = null;
    }

    public static void ClearBrowserCookies(String option)
    {
        if(option.equalsIgnoreCase("Yes"))
        {
            WebDriverSingleton.getDriver().manage().deleteAllCookies();
            System.out.println("All cookies deleted successfully!");
        }
    }
    public static void SetBrowserSize(String option)
    {
        if(option.equalsIgnoreCase("Maximized") || option.equalsIgnoreCase("Maximum"))
        {
            WebDriverSingleton.getDriver().manage().window().maximize();
        }
        else if (option.equalsIgnoreCase("Minimized") || option.equalsIgnoreCase("Minimum"))
        {
            WebDriverSingleton.getDriver().manage().window().minimize();
        }
        else if(option.equalsIgnoreCase("Full Screen") || option.equalsIgnoreCase("FullScreen"))
        {
            WebDriverSingleton.getDriver().manage().window().fullscreen();
        }
    }

}

