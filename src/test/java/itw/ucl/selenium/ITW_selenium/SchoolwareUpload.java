package itw.ucl.selenium.ITW_selenium;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import itw.ucl.selenium.ITW_selenium.src.TestForUpload;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.google.gson.Gson;

public class SchoolwareUpload {

	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private static final String pathToFile = "C:\\Users\\MConstantinides\\git\\GS03_ITW\\TestData\\storeApp\\Group4.zip\\";
	
	@Before
	public void setUp() throws Exception {
		driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_10); // set FireFox
																// version 10
																// emulation
																// mode
		((HtmlUnitDriver) driver).setJavascriptEnabled(true); // enable
																// JavaScript
																// execution
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testStoreApp() throws Exception {
		driver.get("http://schoolware.cs.ucl.ac.uk/schoolware/web/");
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("wang");
		driver.findElement(By.id("appCategory")).click();
		new Select(driver.findElement(By.id("appCategory")))
				.selectByVisibleText("Physics");
		driver.findElement(By.id("appCategory")).click();
		driver.findElement(By.id("appType")).click();
		new Select(driver.findElement(By.id("appType")))
				.selectByVisibleText(".jar");
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys("wang");
		driver.findElement(By.id("developer")).clear();
		driver.findElement(By.id("developer")).sendKeys("awng");
		driver.findElement(By.id("file")).clear();
		driver.findElement(By.id("file"))
				.sendKeys(pathToFile);
		driver.findElement(By.id("btnUpload")).click();

		String mwh = driver.getWindowHandle();
		Set s = driver.getWindowHandles();
		// this method will you handle of all opened windows
		Iterator ite = s.iterator();

		while (ite.hasNext()) {
			String popupHandle = ite.next().toString();
			if (!popupHandle.contains(mwh)) {
				driver.switchTo().window(popupHandle);
				// After finished your operation in pop-up just select the main
				// window again
				// driver.switchTo().window(mwh);
				String rawJson = driver.getPageSource();
				System.out.println(rawJson);
				Gson gson = new Gson();
				TestForUpload testForUpload = gson.fromJson(rawJson,
						TestForUpload.class);
				Assert.assertEquals("wang",  testForUpload.getAppName());
				Assert.assertEquals("wang",  testForUpload.getDescrption());
			}

		}

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alert.getText();
		} finally {
			acceptNextAlert = true;
		}
	}
}
