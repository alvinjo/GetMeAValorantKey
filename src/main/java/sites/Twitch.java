package sites;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.Constants;

public class Twitch {

    public void login(WebDriver driver){
        pressLoginButton(driver);
        enterLoginDetails(driver);
        submitLoginDetails(driver);
    }

    public String getStreamUrl(WebDriver driver){
        String streamXPath = "//*[@id=\"root\"]/div/div[2]/div/main/div[1]/div[3]/div/div/div/div[2]/div[3]/div[2]/div[1]/div[1]/div[2]/div/div/div/div/article/div[2]/div/div[5]/a";

        WebElement streamLink = driver.findElement(By.xpath(streamXPath));
        return streamLink.getAttribute("href");
    }

    private void pressLoginButton(WebDriver driver){
        String loginButtonXPath = "/html/body/div[1]/div/div[2]/nav/div/div[3]/div[3]/div/div[1]/div[1]/button";
        WebElement loginButton = driver.findElement(By.xpath(loginButtonXPath));
        loginButton.click();
    }

    private void enterLoginDetails(WebDriver driver){
        String usernameXPath = "//*[@id=\"login-username\"]";
        String passwordXPath = "//*[@id=\"password-input\"]";

        WebElement usernameInput = driver.findElement(By.xpath(usernameXPath));
        WebElement passwordInput = driver.findElement(By.xpath(passwordXPath));

        usernameInput.sendKeys(Constants.U);
        passwordInput.sendKeys(Constants.P);
    }

    private void submitLoginDetails(WebDriver driver){
        String submitButtonXPath = "/html/body/div[2]/div/div/div/div/div/div[1]/div/div/div[3]/form/div/div[3]/button/div/div";
        WebElement submitButton = driver.findElement(By.xpath(submitButtonXPath));
        submitButton.click();
    }
}
