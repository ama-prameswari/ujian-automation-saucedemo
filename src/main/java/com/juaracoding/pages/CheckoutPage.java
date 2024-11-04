package com.juaracoding.pages;

import com.juaracoding.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    private WebDriver driver;

    public CheckoutPage(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id='checkout']")
    private WebElement btnCheckout;

    @FindBy(xpath = "//span[@class='title']")
    private WebElement pageTitle;

    // Checkout: Your Information
    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement postalCode;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement btnContinue;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    // Checkout: Overview
    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    private WebElement itemTotal;

    @FindBy(xpath = "//div[@class='summary_tax_label']")
    private WebElement taxTotal; // Tax 0.08

    @FindBy(xpath = "//div[@class='summary_total_label']")
    private WebElement finalTotal;

    @FindBy(xpath = "//button[@id='finish']")
    private WebElement btnFinish;

    // Checkout: Complete!
    @FindBy(xpath = "//h2[@class='complete-header']")
    private WebElement headerCompleteCheckout;

    @FindBy(xpath = "//div[@class='complete-text']")
    private WebElement txtCompleteCheckout;

    public void setBtnCheckout() {
        btnCheckout.click();
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public void insertInformation(String firstName, String lastName, String postalCode) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.postalCode.sendKeys(postalCode);
    }

    public void setBtnContinue() {
        btnContinue.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getItemTotal() {
        return itemTotal.getText();
    }

    public String getTaxTotal() {
        return taxTotal.getText();
    }

    public String getFinalTotal() {
        return finalTotal.getText();
    }

    public void setBtnFinishCheckout() {
        btnFinish.click();
    }

    public String getHeaderCompleteCheckout() {
        return headerCompleteCheckout.getText();
    }

    public String getTxtCompleteCheckout() {
        return txtCompleteCheckout.getText();
    }

}
