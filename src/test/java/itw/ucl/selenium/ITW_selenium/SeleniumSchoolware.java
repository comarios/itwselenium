package itw.ucl.selenium.ITW_selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import itw.ucl.selenium.ITW_selenium.src.AppID;
import itw.ucl.selenium.ITW_selenium.src.Apps;
import itw.ucl.selenium.ITW_selenium.src.Categories;
import itw.ucl.selenium.ITW_selenium.src.Tests;
import itw.ucl.selenium.ITW_selenium.src.Types;
import itw.ucl.selenium.ITW_selenium.src.UQuestions;
import itw.ucl.selenium.ITW_selenium.src.UserTests;
import itw.ucl.selenium.ITW_selenium.src.UserUQuestions;

import com.google.gson.Gson;

public class SeleniumSchoolware {
	
	@Test
	public void testCategories() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/categories");
		String rawJson = driver.getPageSource();
		Gson gson = new Gson();
		Categories categories = gson.fromJson(rawJson, Categories.class);
		Assert.assertTrue("category title match", categories.getCategories()
				.get(0).getCategType().equals("Mathematics"));
		Assert.assertTrue("category id match", categories.getCategories()
				.get(0).getCategId() == 1);
	}

	@Test
	public void testTypes() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/types");
		String rawJson = driver.getPageSource();
		Gson gson = new Gson();
		Types types = gson.fromJson(rawJson, Types.class);

		Assert.assertEquals((long) 1, (long) types.getTypes().get(0)
				.getTypeId());
		Assert.assertEquals(".exe", types.getTypes().get(1).getAppExtention());
	}

	@Test
	public void testGetApps() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/applications/1");
		String rawJson = driver.getPageSource();
		Gson gson = new Gson();
		Apps apps = gson.fromJson(rawJson, Apps.class);

		Assert.assertEquals("50", apps.getApps().get(3).getSize());
		Assert.assertEquals("BeautifulMaths", apps.getApps().get(0).getName());
	}

	@Test
	public void testAppDetails() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/application/1");
		String rawJson = driver.getPageSource();
		Gson gson = new Gson();

		AppID appIDs = gson.fromJson(rawJson, AppID.class);
		System.out.println(appIDs.getName());
		Assert.assertEquals("BeautifulMaths", appIDs.getName());
	}

	@Test
	public void testGetTests() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/tests/1");
		String rawJson = driver.getPageSource();
		System.out.println(rawJson);
		Gson gson = new Gson();
		Tests tests = gson.fromJson(rawJson, Tests.class);

		Assert.assertTrue("application id match", tests.getTest().get(0)
				.getTestId() == 1);
		Assert.assertTrue("application name match", tests.getTest().get(0)
				.getTestName().equals("BeautifulTest1"));
	}

	@Test
	public void testGetTestDetails() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/test/1");
		String rawJson = driver.getPageSource();
		System.out.println(rawJson);
		Gson gson = new Gson();
		UserTests userTests = gson.fromJson(rawJson, UserTests.class);

		Assert.assertTrue("test details id match", userTests.getUserTests()
				.get(0).getTestId() == 1);
		Assert.assertTrue("test details score match", userTests.getUserTests()
				.get(0).getScore() == 45.0);
		Assert.assertTrue("test details questions attended match", userTests
				.getUserTests().get(0).getQuesAttented() == 3);
		Assert.assertTrue("test details time match", userTests.getUserTests()
				.get(0).getTime() == 12.0);

	}

	@Test
	public void testGetTestQuestions() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/questions/1");
		String rawJson = driver.getPageSource();
		System.out.println(rawJson);
		Gson gson = new Gson();
		UQuestions uQuestions = gson.fromJson(rawJson, UQuestions.class);
		Assert.assertTrue("test questions id match", uQuestions.getUquestion()
				.get(0).getUQuestionId() == 1);
	}

	@Test
	public void testGetQuestionDetails() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/question/1");
		String rawJson = driver.getPageSource();
		System.out.println(rawJson);
		Gson gson = new Gson();
		UserUQuestions userUQuestions = gson.fromJson(rawJson,
				UserUQuestions.class);
		Assert.assertTrue(
				"question details number of clicks match",
				userUQuestions.getUserUQuestion().get(0).getNumberOfClicks() == 1);
		Assert.assertTrue("question details time match", userUQuestions
				.getUserUQuestion().get(0).getTime() == 0);
	}

}
