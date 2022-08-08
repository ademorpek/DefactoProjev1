package com.thoughtworks.gauge.maven;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepImplementation extends BaseTest {



    @Step("<Key> saniye bekle")
    public void waitBySeconds(int Key) {
        try {
            Thread.sleep(Key * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Step("<element> Elementine tıkla")
    public void clickElement(String element) {
       driver.findElement(By.cssSelector(element)).click();
        }

    @Step("<element> Elementine tıkla1")
    public void clickElement1(String element1) {
        driver.findElement(By.xpath(element1)).click();
    }

    @Step("<key> elementin üstünde bekle")
    public void hover(String key) {
        WebElement ele = driver.findElement(By.cssSelector(key));
        Actions action = new Actions(driver);
        action.moveToElement(ele).perform();

    }

    @Step({"<key> elementi <text> değerini içeriyor mu kontrol et"})
    public void checkElementContainsText(String key, String text) {
        driver.findElement(By.cssSelector(key));
        Boolean containsText = driver.findElement(By.cssSelector(key)).getText().contains(text);
        assertTrue(containsText, "Expected text is not contained");
    }

    @Step({"<key> alanına kaydır"})
    public void scrollToElement(String key) {
        WebElement element = driver.findElement(By.cssSelector(key));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
    @Step({"<key1> alanına kaydır2"})
        public void scrollElement (String key1){
        WebElement element =  driver.findElement(By.xpath(key1));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
        }

    @Step({"<key> rastgele seç"})
    public void randomPick(String key) {
        List<WebElement> elements = driver.findElements(By.cssSelector(key));
        Random random = new Random();
        int index = random.nextInt(elements.size());
        elements.get(index).click();
    }


    @Step("<key> elementini kontrol et")
    public void checkElement(String key)
    {
        WebElement element = driver.findElement(By.xpath(key));
        assertTrue((element).isDisplayed(), "Sayfa Açılamadı");
    }

}
