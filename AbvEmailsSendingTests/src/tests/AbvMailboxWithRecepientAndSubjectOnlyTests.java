package tests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import abv.AbvMailboxWithAllDetails;
import abv.AbvMailboxWithRecepientAndSubjectOnly;;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AbvMailboxWithRecepientAndSubjectOnlyTests {

	@Test
	public void stage1_shouldOpenAndMaximizeWebSite() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		AbvMailboxWithRecepientAndSubjectOnly.navigateToSite(driver, "http://abv.bg");
		driver.manage().window().maximize();
		
		WebElement initialMessage = driver.findElement(By.cssSelector("#leftSide>h2"));
		String initialMessageStr = initialMessage.getText();
		
		assertEquals("Вход в АБВ Поща", initialMessageStr);
		
		driver.close();
		driver.quit();
	}
	
	@Test
	public void stage2_shouldOpenMaximizeAndLogin() {
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
		
		assertEquals("Здравейте", loggedInMessageHello);
		
		driver.close();
        driver.quit();
	}
	
	@Test
	public void stage3_shouldOpenMaximizeLoginAndSendEmailWithSubjectOnly() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		AbvMailboxWithAllDetails.navigateToSite(driver, "http://abv.bg");
		driver.manage().window().maximize();
		
		AbvMailboxWithAllDetails.loginToSite(driver);
		
		AbvMailboxWithAllDetails.sendMail(driver);
		
		String confirmationForSendMailMessage = AbvMailboxWithAllDetails.confirmSending(driver);
		
		assertEquals("Писмото беше изпратено успешно!", confirmationForSendMailMessage);
		
		driver.close();
        driver.quit();
	}

}
