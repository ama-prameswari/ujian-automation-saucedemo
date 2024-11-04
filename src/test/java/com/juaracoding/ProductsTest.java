package com.juaracoding;

import com.juaracoding.pages.LoginPage;
import com.juaracoding.pages.ProductsPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProductsTest {

    private static WebDriver driver;
    private ExtentTest extentTest;
    private static LoginPage loginPage = new LoginPage();
    private static ProductsPage productsPage = new ProductsPage();

    public ProductsTest(){
        driver = Hooks.driver;
        extentTest = Hooks.extentTest;
    }

    @Given("I am logged in with valid {string} and {string}")
    public void i_am_logged_in_with_valid_username_and_password(String username, String password) {
        loginPage.loginUser(username, password);
        loginPage.setBtnLogin();
        extentTest.log(LogStatus.PASS, "I am logged in with valid username and password");
    }

    @And("I am on the products page")
    public void i_am_on_the_products_page() {
        Assert.assertEquals(loginPage.getTxtTitle(), "Products");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        extentTest.log(LogStatus.PASS, "I am on the products page");
    }

    @When("I click add to cart button for two products")
    public void i_click_add_to_cart_button_for_two_products() {
        for (int i = 0; i < 2; i++) {
            productsPage.clickProductButton(i);
            extentTest.log(LogStatus.INFO, "Product " + (i + 1) + " added to cart.");
        }
        extentTest.log(LogStatus.PASS, "I click add to cart button for two products");
    }

    @And("I click cart button")
    public void i_click_cart_button() {
        productsPage.setCartButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertEquals(loginPage.getTxtTitle(), "Your Cart");
        extentTest.log(LogStatus.PASS, "I click cart button");
    }

    @Then("I should see two products in my cart")
    public void I_should_see_two_products_in_my_cart() {
        Assert.assertEquals(productsPage.getCartTotalItem(), 2);
        extentTest.log(LogStatus.PASS, "I should see two products in my cart");
    }

    @And("I am logout")
    public void i_am_logout() {
        productsPage.logout();
        extentTest.log(LogStatus.PASS, "I am logout");
    }

}
