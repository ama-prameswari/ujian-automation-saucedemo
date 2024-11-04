package com.juaracoding;

import com.juaracoding.pages.LoginPage;
import com.juaracoding.pages.ProductsPage;
import com.juaracoding.pages.CheckoutPage;
import com.juaracoding.utils.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutTest {

    private static WebDriver driver;
    private ExtentTest extentTest;
    private static LoginPage loginPage = new LoginPage();
    private static ProductsPage productsPage = new ProductsPage();
    private static CheckoutPage checkoutPage = new CheckoutPage();

    public CheckoutTest(){
        driver = Hooks.driver;
        extentTest = Hooks.extentTest;
    }

    @When("I click checkout button")
    public void i_click_checkout_button(){
        checkoutPage.setBtnCheckout();
        extentTest.log(LogStatus.PASS, "I click checkout button");
    }

    @And("I enter firstname {string}, lastname {string} and postal code {string}")
    public void i_enter_firstname_lastname_and_postal_code(String firstName, String lastName, String postalCode){
        checkoutPage.insertInformation(firstName, lastName, postalCode);
        Assert.assertTrue(checkoutPage.getPageTitle().contains("Your Information"));
        extentTest.log(
                LogStatus.PASS,
                "I enter firstname "+firstName+", lastname "+lastName+" and postal code "+postalCode
        );
    }

    @And("I click continue button")
    public void i_click_continue_button(){
        checkoutPage.setBtnContinue();
        extentTest.log(LogStatus.PASS, "I click continue button");
    }

    @And("I am on the overview page")
    public void i_am_on_the_overview_page(){
        Assert.assertTrue(checkoutPage.getPageTitle().contains("Overview"));
        Assert.assertTrue(checkoutPage.getItemTotal().contains("39.98"));
        Assert.assertTrue(checkoutPage.getTaxTotal().contains("3.20"));
        Assert.assertTrue(checkoutPage.getFinalTotal().contains("43.18"));
        extentTest.log(LogStatus.PASS, "I am on the overview page");
    }

    @And("I click finish button")
    public void i_click_finish_button(){
        checkoutPage.setBtnFinishCheckout();
        extentTest.log(LogStatus.PASS, "I click finish button");
    }

    @Then("I should see the checkout process complete")
    public void i_should_see_the_checkout_process_complete(){
        Assert.assertTrue(checkoutPage.getPageTitle().contains("Complete!"));
        Assert.assertTrue(checkoutPage.getHeaderCompleteCheckout().contains("Thank you"));
        Assert.assertTrue(checkoutPage.getTxtCompleteCheckout().contains("Your order has been dispatched"));
        extentTest.log(LogStatus.PASS, "I should see the checkout process complete");
    }

    @Then("Finish button is not clickable")
    public void finish_button_is_not_clickable() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertTrue(checkoutPage.getPageTitle().contains("Overview"));
        extentTest.log(LogStatus.PASS, "Finish button is not clickable");
    }

}
