package abv;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbvMailboxWithAllDetails {
	
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
	      // 10 seconds before throwing exception
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      
	      navigateToSite(driver, "http://abv.bg/");
	      
	      // Maximize the browser
	      driver.manage().window().maximize();
	      
	      loginToSite(driver);
	      
	      sendMail(driver);
	      
	      confirmSending(driver);
	      // assertEquals(sendingMessageResult, "������� ���� ��������� �������!"); 
	      
	      // Close the Browser.
	      driver.close();
	}

	public static String confirmSending(WebDriver driver) {
		  WebElement sendingConfirmationMessage = driver.findElement(By.xpath("//*[contains(text(), '������� ���� ��������� �������!')]"));
	      String sendingMessageResult = sendingConfirmationMessage.getText();
	      System.out.println(sendingMessageResult);
	      
	      return sendingMessageResult;
	}

	public static void sendMail(WebDriver driver) {
		WebElement composeMailButton = driver.findElement(By.xpath(
	    		  ".//*[@id='main']/div/div[4]/div/div[4]/div/div[2]/div/div[2]/div/div[3]/div"));
	      WebDriverWait wait = new WebDriverWait(driver, 3);
	      wait.until(ExpectedConditions.elementToBeClickable(composeMailButton));
	      
	      composeMailButton.click();
	      
	      WebElement receipients = driver.findElement(By.xpath(
	    		  ".//*[@id='main']/div/div[4]/div/div[4]/div/div[4]/div/div[2]/div/div[2]/div/div[2]/div[1]/table/tbody/tr[2]/td[2]/div/input"));
	      receipients.sendKeys("testingbox@abv.bg");
	      
	      WebElement subjectTextbox = driver.findElement(By.xpath(
	    		  ".//*[@id='main']/div/div[4]/div/div[4]/div/div[4]/div/div[2]/div/div[2]/div/div[2]/div[1]/table/tbody/tr[5]/td[2]/div/input"));
	      subjectTextbox.click(); // click to resolve the recipients addresses 
	      subjectTextbox.sendKeys("Test Subject 1");
	      
	      WebElement messageBody = driver.findElement(By.className("gwt-RichTextArea"));
	      messageBody.sendKeys("Test message body");
	      
	      WebElement sendMailButton = driver.findElement(By.className("abv-button"));
	      sendMailButton.click();
	}

	public static void loginToSite(WebDriver driver) {
		WebElement usernameTextBox = driver.findElement(By.xpath(".//*[@id='username']"));
	      WebElement passwordTextBox = driver.findElement(By.xpath(".//*[@id='password']"));
	      String username = "testingbox";
	      String password = "111333a";
	      usernameTextBox.sendKeys(username);
	      passwordTextBox.sendKeys(password);
	      WebElement loginButton = driver.findElement(By.xpath(".//*[@id='loginBut']"));
	      loginButton.click();
	}

	public static void navigateToSite(WebDriver driver, String site) {
	      driver.navigate().to(site);
	}
}