package Pages;

import Utilities.ConfigReader;
import Utilities.MyElement;
import Utilities.WebDriverSingleton;
import io.cucumber.java.et.Ja;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.Hooks;

import java.io.IOException;
import java.time.Duration;
import java.util.Locale;

public class CommonMethods
{
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    ConfigReader cr;
    String browserSizeOption;

    public CommonMethods() {
        this.driver = WebDriverSingleton.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        this.cr = new ConfigReader();
    }

    public void launchApp(String appName) throws InterruptedException {
        if(appName!=null && appName.length()>0)
        {
            if(appName.toUpperCase().equals("MORGAN STANLEY"))
            {
                driver.get("https://tier2.mydesk.morganstanley.com/logon/LogonPoint/mydesk.html#/login");
                browserSizeOption = WebDriverSingleton.getBrowserSize();
                WebDriverSingleton.SetBrowserSize(browserSizeOption);
                pageTitleValidation();
            }
            else if(appName.toUpperCase().equals("FACEBOOK"))
            {
                driver.get("https://www.google.com");
                browserSizeOption = WebDriverSingleton.getBrowserSize();
                WebDriverSingleton.SetBrowserSize(browserSizeOption);
                pageTitleValidation();
            }
            else if(appName.toUpperCase().equals("AMAZON"))
            {
                driver.get("https://www.amazon.in");
                browserSizeOption = WebDriverSingleton.getBrowserSize();
                WebDriverSingleton.SetBrowserSize(browserSizeOption);
                pageTitleValidation();
            }
            else
            {
                System.out.println("Invalid application name!");
                driver.close();
            }
        }
    }

    public void scrollToElement(WebElement element)
    {
        js.executeScript("return arguments[0].scrollIntoView(true);", element);
    }
    public void click_on_element(String element, String page) throws IOException
    {
        try
        {
            scrollToElement(MyElement.getElement(element, page));
            wait.until(ExpectedConditions.elementToBeClickable(MyElement.getElement(element, page)));
            MyElement.click(element, page);
        }
        catch (TimeoutException | InterruptedException e)
        {
            System.out.println("TimeOutException: " +e.getMessage());
            driver.close();
        }
        catch (ElementClickInterceptedException e)
        {
            System.out.println("ElementClickInterceptedException: " +e.getMessage());
            driver.close();
        }
        catch (ElementNotInteractableException e)
        {
            System.out.println("ElementNotInteractableException: " +e.getMessage());
            driver.close();
        }
    }
    public void enter_input_element(String value, String element, String page) throws IOException, InterruptedException {
        try
        {
            Thread.sleep(5000);
            MyElement.sendKeys(value, element, page);
        }
        catch (TimeoutException e)
        {
            System.out.println("TimeOutException: " +e.getMessage());
            driver.close();
        }
        catch (ElementNotInteractableException e)
        {
            System.out.println("ElementNotInteractableException: " +e.getMessage());
            driver.close();
        }
    }
    public void select_from_dropdown(String value, String element, String page) throws IOException, InterruptedException {
        try
        {
            MyElement.select_value_from_dropdown(value, element, page);
        }
        catch (TimeoutException e)
        {
            System.out.println("TimeOutException: " +e.getMessage());
            driver.close();
        }
        catch (ElementNotInteractableException e)
        {
            System.out.println("ElementNotInteractableException: " +e.getMessage());
            driver.close();
        }
    }
    public void pageTitleValidation() throws InterruptedException {
        String title = this.driver.getTitle();
        Thread.sleep(5000);
        if(title!=null && title.length()>0)
        {
            System.out.println("Page Title = "+title);
        }
        else
        {
            throw new InterruptedException("Cannot validate page title");
        }
    }

    public void validateText(String expected, String element, String page) throws IOException {
        try
        {
            MyElement.validate_text(expected, element, page);
        }
        catch (TimeoutException e)
        {
            System.out.println("TimeOutException: " +e.getMessage());
            driver.close();
        }
        catch (ElementNotInteractableException e)
        {
            System.out.println("ElementNotInteractableException: " +e.getMessage());
            driver.close();
        }
    }
}
