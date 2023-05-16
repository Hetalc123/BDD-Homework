package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.testng.Assert;

import static org.example.Hooks.*;
import static org.example.Utils.*;

public class Stepdefs {
    LoadProp loadProp = new LoadProp();

    @Given("I am on registration page")
    public void i_am_on_registration_page() {
        clickOnElement(By.className("ico-register"));
    }

    @When("I enter required registration details")
    public void i_enter_required_registration_details() {
//click on female radio button
        clickOnElement(By.xpath(("(//input[@type='radio'])[2]")));
//type the firstname
        typeText(By.id("FirstName"),loadProp.getProperty("firstname"));
        //type the lastname
        typeText(By.id("LastName"),loadProp.getProperty("lastname"));
        //select day of birth
        selectElementByIndex(By.xpath("//select[@name='DateOfBirthDay']"), 4);
        //select month of birth)
        selectElementByText(By.xpath("//select[@name='DateOfBirthMonth']"),loadProp.getProperty("monthOfBirth"));
        //select year of birth
        selectElementValue(By.xpath("//select[@name='DateOfBirthYear']"), "1989");
        //type the email
        typeText(By.name("Email"), "myfirstauto" + datestamp() + "@gmail.com");
        //type password
        typeText(By.name("Password"),loadProp.getProperty("password"));
        //confirm password
        typeText(By.id("ConfirmPassword"),loadProp.getProperty("password"));
        //click on register
        clickOnElement(By.id("register-button"));
    }

    @Then("I should able to register successfully")
    public void i_should_able_to_register_successfully() {
        String expectedResult =loadProp.getProperty("expectedResult");
        //get text and print
        String actualResult = getTextFromElement(By.xpath("//div[@class='result']"));
        System.out.println("Message on screen is =>" + (actualResult));
        //check if actual result is same as expected
        Assert.assertEquals(actualResult, expectedResult, "Registration not completed");
    }

    @Given("I am On Login Page")
    public void i_am_on_login_page() {
        clickOnElement(By.className("ico-login"));
    }

    @When("I enter login details and I successfully logged in")
    public void i_enter_login_details() {
//type email
        typeText(By.xpath("//input[@name='Email']"), "myfirstauto" + datestamp() + "@gmail.com");
        //type password
        typeText(By.xpath("//input[@class='password']"),loadProp.getProperty("password"));
        //click on login
        clickOnElement(By.xpath("//button[@class='button-1 login-button']"));
    }

    @When("I click on product and click on email")
    public void i_click_on_product_and_click_on_email() {
        //click on product
        clickOnElement(By.xpath("(//button[@class='button-2 product-box-add-to-cart-button'])[2]"));
        //click on email
        clickOnElement(By.xpath("//button[@class='button-2 email-a-friend-button']"));
    }

    @Then("I should be able to email a friend successfully")
    public void i_should_be_able_to_email_a_friend_successfully() {
        //type friend's email
        typeText(By.className("friend-email"),loadProp.getProperty("friendEmail"));
        //clear emai field
        WebElement email = driver.findElement(By.className("your-email"));
        email.clear();
        //type email
        typeText(By.className("your-email"), "myfirstauto" + datestamp() + "@gmail.com");
        clickOnElement(By.name("send-email"));
        String expectedResult = loadProp.getProperty("emailSent");
//Get the message
        String actualResult = getTextFromElement(By.xpath("//div[@class='result']"));
        System.out.println(actualResult);
        //check if actual result is same as expected
        Assert.assertEquals(actualResult, expectedResult, "Refered a friend");
    }

}
