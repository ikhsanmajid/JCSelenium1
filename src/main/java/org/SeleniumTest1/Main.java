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
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        WebElement root = driver.findElement(By.xpath("//div[@id='root']"));


        WebElement username = root.findElement(By.id("user-name"));
        WebElement password = root.findElement(By.id("password"));

        System.out.println("Memasukkan username dan password");
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        delay(2);
        root.findElement(By.id("login-button")).click();

        System.out.print("Test Login: ");
        String resultSuccessLogin = assertEqualsGetText("//div[contains(@class, 'header_secondary_container')]/span[contains(@class, 'title')]", "Products");
        System.out.println(resultSuccessLogin);

        //==============Add 2 items

        List<WebElement> items = root.findElements(By.xpath("//div[contains(@class, 'inventory_item')]//div[contains(@class, 'pricebar')]/button"));

        System.out.println("Adding 2 items to cart");
        System.out.print("Test Add to Cart: ");

        items.get(0).click();
        items.get(1).click();

        String cartItemsAdd2 = assertEqualsGetText("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']", "2");
        System.out.println(cartItemsAdd2);

        System.out.println();
        delay(2);

        //==============Add 2 more items

        System.out.println("Adding 2 more items to cart");
        System.out.print("Test Add to Cart: ");
        List<WebElement> itemsAdd4 = root.findElements(By.xpath("//div[contains(@class, 'inventory_item')]//div[contains(@class, 'pricebar')]/button"));
        items.get(2).click();
        items.get(3).click();

        String cartItemsAdd4 = assertEqualsGetText("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']", "4");
        System.out.println(cartItemsAdd4);

        System.out.println();
        delay(2);

        //==============Remove 2 items

        List<WebElement> itemsRemove = root.findElements(By.xpath("//div[contains(@class, 'inventory_item')]//div[contains(@class, 'pricebar')]/button"));

        System.out.println("Removing 2 items from cart");
        System.out.print("Test Removing 2 items from Cart: ");
        itemsRemove.get(0).click();
        itemsRemove.get(1).click();

        String cartItemsRemove2 = assertEqualsGetText("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']", "2");
        System.out.println(cartItemsRemove2);

        System.out.println();
        delay(2);

        //==============Remove 2 more items

        System.out.println("Removing 2 more items from cart");
        System.out.print("Test Removing 2 items from Cart: ");
        itemsRemove.get(2).click();
        itemsRemove.get(3).click();

        String cartItemsRemove0 = assertEqualsGetText("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']", "");
        System.out.println(cartItemsRemove0);

        System.out.println();
        delay(2);

        //==============Checkout

        List<WebElement> itemsCheckout = root.findElements(By.xpath("//div[contains(@class, 'inventory_item')]//div[contains(@class, 'pricebar')]/button"));

        System.out.print("Checkout Test");
        System.out.println("Adding 3 items to cart");
        System.out.print("Test Add to Cart: ");

        itemsCheckout.get(0).click();
        itemsCheckout.get(1).click();
        itemsCheckout.get(3).click();

        String cartItemsAdd3 = assertEqualsGetText("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']", "3");
        System.out.println(cartItemsAdd3);

        System.out.println();
        delay(2);


        System.out.println("Shopping Cart");
        root.findElement(By.xpath("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']")).click();
        System.out.print("Test Display Checkout: ");
        String btnCheckout = assertEqualsGetText("//button[@id='checkout']", "Checkout");
        System.out.println(btnCheckout);
        root.findElement(By.xpath("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']")).click();
        System.out.print("Test Check Item = 3: ");
        List<WebElement> itemsCount = root.findElements(By.xpath("//div[@class='cart_list']/div[@class='cart_item']"));
        System.out.println(itemsCount.size() == 3 ? "pass" : "fail");

        delay(2);

        root.findElement(By.id("checkout")).click();
        System.out.println("Checkout clicked....");

        WebElement firstName = root.findElement(By.id("first-name"));
        WebElement lastName = root.findElement(By.id("last-name"));
        WebElement postalCode = root.findElement(By.id("postal-code"));


        System.out.println("Memasukkan alamat....");
        firstName.sendKeys("Ikhsan");
        lastName.sendKeys("Majid");
        postalCode.sendKeys("11111");

        delay(2);

        System.out.println("Click continue....");
        root.findElement(By.id("continue")).click();

        System.out.println("Click finish....");
        WebElement finishBtn = root.findElement(By.id("finish"));
        delay(2);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true)", finishBtn);
        delay(2);
        finishBtn.click();

        System.out.println();
        System.out.println("Checkout Success Test");
        System.out.print("Test halaman Finish: ");
        String displayFinished = assertEqualsGetText("//div[@id='checkout_complete_container']//h2[@class='complete-header']", "Thank you for your order!");
        System.out.println(displayFinished);
        System.out.println("BacktoHome clicked....");
        delay(2);
        root.findElement(By.id("back-to-products")).click();

        delay(2);

        System.out.println();
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