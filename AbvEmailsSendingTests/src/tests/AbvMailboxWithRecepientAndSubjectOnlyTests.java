package tests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import abv.AbvMailboxWithAllDetails;
import abv.AbvMailboxWithRecepientAndSubjectOnly;;

public class AbvMailboxWithRecepientAndSubjectOnlyTests {

	@Test
	public void shouldOpenAndMaximizeWebSite() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		AbvMailboxWithRecepientAndSubjectOnly.navigateToSite(driver, "http://abv.bg");
		driver.manage().window().maximize();
		
		WebElement initialMessage = driver.findElement(By.cssSelector("#leftSide>h2"));
		String initialMessageStr = initialMessage.getText();
		
		assertEquals("���� � ��� ����", initialMessageStr);
		
		driver.close();
		driver.quit();
	}
	
	@Test
	public void shouldOpenMaximizeAndLogin() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		AbvMailboxWithRecepientAndSubjectOnly.navigateToSite(driver, "http://abv.bg");
		driver.manage().window().maximize();
		
		AbvMailboxWithRecepientAndSubjectOnly.loginToSite(driver);
		
		WebElement loggedInMessage = driver.findElement(By.cssSelector("#middlePagePanel>.h1"));
		String loggedInMessageStr = loggedInMessage.getText();
		String[] loggedInMessageArray = loggedInMessageStr.split(",");
		String loggedInMessageHello = loggedInMessageArray[0];
		//System.out.println(loggedInMessageStr);
		
		assertEquals("���������", loggedInMessageHello);
		
		driver.close();
        driver.quit();
	}
	
	@Test
	public void shouldOpenMaximizeLoginAndSendEmailWithSubjectOnly() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		AbvMailboxWithAllDetails.navigateToSite(driver, "http://abv.bg");
		driver.manage().window().maximize();
		
		AbvMailboxWithAllDetails.loginToSite(driver);
		
		AbvMailboxWithAllDetails.sendMail(driver);
		
		String confirmationForSendMailMessage = AbvMailboxWithAllDetails.confirmSending(driver);
		
		assertEquals("������� ���� ��������� �������!", confirmationForSendMailMessage);
		
		driver.close();
        driver.quit();
	}

}