package org.SeleniumTest1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\MyTools\\chromedriver.exe");
        driver = new ChromeDriver();

        System.out.println("Opening browser and website..");
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement root = driver.findElement(By.xpath("//div[@id='root']"));


        WebElement username = root.findElement(By.id("user-name"));
        WebElement password = root.findElement(By.id("password"));

        System.out.println("Memasukkan username dan password");
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce" + Keys.ENTER);

        System.out.print("Test Login: ");
        String resultSuccessLogin = assertEqualsGetText("//div[contains(@class, 'header_secondary_container')]/span[contains(@class, 'title')]", "Products");
        System.out.println(resultSuccessLogin);

        List<WebElement> itemsAdd = root.findElements(By.xpath("//div[contains(@class, 'inventory_item')]//div[contains(@class, 'pricebar')]/button"));

        System.out.println("Adding 2 items to cart");
        System.out.print("Test Add to Cart: ");

        itemsAdd.get(0).click();
        itemsAdd.get(1).click();

        String cartItemsAdd2 = assertEqualsGetText("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']", "2");
        System.out.println(cartItemsAdd2);

        System.out.println();
        delay(2);

        System.out.println("Adding 2 more items to cart");
        System.out.print("Test Add to Cart: ");
        List<WebElement> itemsAdd4 = root.findElements(By.xpath("//div[contains(@class, 'inventory_item')]//div[contains(@class, 'pricebar')]/button"));
        itemsAdd.get(2).click();
        itemsAdd.get(3).click();

        String cartItemsAdd4 = assertEqualsGetText("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']", "4");
        System.out.println(cartItemsAdd4);

        System.out.println();
        delay(2);

        List<WebElement> itemsRemove = root.findElements(By.xpath("//div[contains(@class, 'inventory_item')]//div[contains(@class, 'pricebar')]/button"));

        System.out.println("Removing 2 items from cart");
        System.out.print("Test Removing 2 items from Cart: ");
        itemsRemove.get(0).click();
        itemsRemove.get(1).click();

        String cartItemsRemove2 = assertEqualsGetText("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']", "2");
        System.out.println(cartItemsRemove2);

        System.out.println();
        delay(2);

        System.out.println("Removing 2 more items from cart");
        System.out.print("Test Removing 2 items from Cart: ");
        itemsRemove.get(2).click();
        itemsRemove.get(3).click();

        String cartItemsRemove0 = assertEqualsGetText("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']", "");
        System.out.println(cartItemsRemove0);

        System.out.println();
        delay(2);

        System.out.print("Logout Test: ");
        WebElement hamburgerBtn = root.findElement(By.xpath("//button[@id='react-burger-menu-btn']"));
        hamburgerBtn.click();
        delay(1);
        WebElement logoutBtn = root.findElement(By.xpath("//nav[@class='bm-item-list']/a[@id='logout_sidebar_link']"));
        logoutBtn.click();
        String textLogin = assertEqualsGetAttribute("//input[@id='login-button']", "value", "Login");
        System.out.println(textLogin);


    }

    static void delay(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    static String assertEqualsGetText(String stringXPath, String expected) {
        try {
            String elementText = driver.findElement(By.xpath(stringXPath)).getText();
            if ( elementText.equals(expected) ) {
                return "pass";
            }else{
                return "fail";
            }
        } catch (WebDriverException e) {
            //String errorMsg = e.getMessage();
            //System.out.print("Element not found...skipping test");
            return "fail";
        }
    }

    static String assertEqualsGetAttribute(String stringXPath, String attribute, String expected) {
        try {
            String elementText = driver.findElement(By.xpath(stringXPath)).getAttribute(attribute);
            if ( elementText.equals(expected) ) {
                return "pass";
            }else{
                return "fail";
            }
        } catch (WebDriverException e) {
            //String errorMsg = e.getMessage();
            //System.out.print("Element not found...skipping test");
            return "fail";
        }
    }


}