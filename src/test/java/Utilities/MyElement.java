package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public abstract class MyElement extends ConfigReader implements WebElement
{
    static WebDriver driver = WebDriverSingleton.getDriver();
    static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    public static boolean isElementVisible(WebElement element)
    {
//        boolean isEleDisplayed = (boolean) js.executeScript("return (arguments[0].offsetParent !== null) && "+"(arguments[0].offsetWidth > 0 || arguments[0].offsetHeight > 0);", element);
        boolean isEleDisplayed = element.isDisplayed();
        if(isEleDisplayed)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static void click(String element, String page) throws InterruptedException, IOException {
        if(isElementVisible(getElement(element, page)))
        {
            MyElement.getElement(element, page).click();
            Thread.sleep(5000);
        }
    }

    public static void sendKeys(String value, String element, String page) throws InterruptedException, IOException {
        if(isElementVisible(getElement(element, page)))
        {
            wait.until(ExpectedConditions.elementToBeClickable(getElement(element, page)));
            MyElement.getElement(element, page).sendKeys(value);
            Thread.sleep(5000);
        }
    }

    public static void select_value_from_dropdown(String value, String element, String page) throws InterruptedException, IOException {
        if(isElementVisible(getElement(element, page)))
        {
            Thread.sleep(5000);
            Select select = new Select(MyElement.getElement(element, page));
            wait.until(ExpectedConditions.elementToBeClickable(getElement(element, page)));
            MyElement.getElement(element, page).click();
            Thread.sleep(2000);
            select.selectByVisibleText(value);
        }
    }

    public static void validate_text(String expected, String element, String page) throws IOException {
        if(isElementVisible(getElement(element, page)));
        {
            String text = MyElement.getElement(element, page).getText();
            if(text!=null && text.length()>0 && text.equals(expected))
            {
                System.out.println("The text "+text+" is displayed on "+page+". (Validated)");
            }
            else
            {
                System.out.println("Could not validate "+text+" element!");
            }
        }
    }
}
