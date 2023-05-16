package org.example;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {

    LoadProp loadProp = new LoadProp();
    protected static WebDriver driver;
    String browsername = "chrome";
    @Before

    public void setUp() {
        if(browsername.equalsIgnoreCase("Chrome")) {
            //open the browser
            driver = new ChromeDriver();
        }else if(browsername.equalsIgnoreCase("Edge")) {
            //open the browser
            driver = new EdgeDriver();
        }else  if(browsername.equalsIgnoreCase("Firefox")) {
            //open the browser
            driver = new FirefoxDriver();}
        //open the url
        driver.get("https:/demo.nopcommerce.com/");
        //maximize the window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(src,"image/png","screenshot");
        }driver.close();
    }

}
