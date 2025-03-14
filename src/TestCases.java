import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases {
	
	WebDriver driver = new ChromeDriver();
	String WebSite1 = "https://automationteststore.com/";

	String[] FNames = { "ahmad", "ali", "anas", "omar", "ayat", "alaa", "sawsan", "Rama" };

	String[] LNames = { "Khaled", "mustafa", "Mohammad", "abdullah", "malek", "omar" };

	Random rand = new Random();
	String Password1 = "soso123@";
	String GlobalUserName = "";
	String GlobalLogIn = "";

	@BeforeTest
	public void SetUp() {
		driver.manage().window().maximize();

		driver.get(WebSite1);
	}

	@Test(priority = 1)
	public void SignUp() throws InterruptedException {

		driver.findElement(By.linkText("Login or register")).click();

		WebElement SignUpButton = driver.findElement(By.xpath("//button[@title='Continue']"));
		SignUpButton.click();

		int RandomForIndexFN = rand.nextInt(FNames.length);
		int RandomForIndexLN = rand.nextInt(LNames.length);
		String UserFN = FNames[RandomForIndexFN];
		String UserLN = LNames[RandomForIndexLN];
		int NumberOfRandom = rand.nextInt(45612);
		String DomainName = "@gmail.com";
		String Email = UserFN + UserLN + NumberOfRandom + DomainName;

		WebElement FNInput = driver.findElement(By.id("AccountFrm_firstname"));
		FNInput.sendKeys(UserFN);

		GlobalUserName = UserFN;

		WebElement LNInput = driver.findElement(By.id("AccountFrm_lastname"));
		LNInput.sendKeys(UserLN);

		WebElement ConfirmEmail = driver.findElement(By.id("AccountFrm_email"));
		ConfirmEmail.sendKeys(Email);

		WebElement Adressinput = driver.findElement(By.id("AccountFrm_address_1"));
		Adressinput.sendKeys("AmmanCity");

		WebElement CityInput = driver.findElement(By.id("AccountFrm_city"));
		CityInput.sendKeys("CapitalCity");

		WebElement CountryInput = driver.findElement(By.id("AccountFrm_country_id"));
		Select selector = new Select(CountryInput);
		int RandomCountry = rand.nextInt(1, 240);
		selector.selectByIndex(RandomCountry);
		Thread.sleep(3000);

		WebElement RegionInput = driver.findElement(By.id("AccountFrm_zone_id"));
		Select selector1 = new Select(RegionInput);
		int RandomRegion = rand.nextInt(1, 7);
		selector1.selectByIndex(RandomRegion);

		WebElement PostalInput = driver.findElement(By.id("AccountFrm_postcode"));
		PostalInput.sendKeys("13310");

		WebElement LOGInput = driver.findElement(By.id("AccountFrm_loginname"));
		LOGInput.sendKeys(UserFN + UserLN + NumberOfRandom);

		String UserEmail = UserFN + UserLN + NumberOfRandom;

		GlobalLogIn = UserEmail;

		WebElement PasswordInput = driver.findElement(By.id("AccountFrm_password"));
		PasswordInput.sendKeys("soso123@");

		WebElement ConfirmPassInput = driver.findElement(By.id("AccountFrm_confirm"));
		ConfirmPassInput.sendKeys("soso123@");

		WebElement CheckPolicy = driver.findElement(By.id("AccountFrm_agree"));
		CheckPolicy.click();
		Thread.sleep(2000);
		WebElement COntinueinput = driver.findElement(By.xpath("*//button[@title='Continue']"));
		COntinueinput.click();

	}

	@Test(priority = 2)
	public void LogOut() {

		Actions action = new Actions(driver);
		WebElement WelcomeBack = driver.findElement(By.partialLinkText("Welcome"));
		action.moveToElement(WelcomeBack).perform();

		WebElement LogOff = driver.findElement(By.partialLinkText("Logoff"));
		LogOff.click();
	}

	@Test(priority = 3)
	public void LogIn() throws InterruptedException {

		driver.findElement(By.linkText("Login or register")).click();

		WebElement Loginname = driver.findElement(By.id("loginFrm_loginname"));
		Loginname.sendKeys(GlobalLogIn);

		WebElement Passwordinput2 = driver.findElement(By.id("loginFrm_password"));
		Passwordinput2.sendKeys(Password1);
		Thread.sleep(2000);
		WebElement Login = driver.findElement(By.xpath("//button[@title='Login']"));
		Login.click();
	}

	@Test(priority = 4)
	public void AddItemToTheCart() throws InterruptedException {
		String[] WebSitesForTheItems = { "https://automationteststore.com/index.php?rt=product/category&path=68",
				"https://automationteststore.com/index.php?rt=product/category&path=36",
				"https://automationteststore.com/index.php?rt=product/category&path=43",
				"https://automationteststore.com/index.php?rt=product/category&path=49",
				"https://automationteststore.com/index.php?rt=product/category&path=58",
				"https://automationteststore.com/index.php?rt=product/category&path=52",
				"https://automationteststore.com/index.php?rt=product/category&path=65" };
Thread.sleep(2000);
		int randomIndex1 = rand.nextInt(WebSitesForTheItems.length);
		driver.get(WebSitesForTheItems[randomIndex1]);

		WebElement ListOfItems = driver.findElement(By.cssSelector(".thumbnails.row"));

		int SumProduct = ListOfItems.findElements(By.tagName("li")).size();

		int TotalNumber = rand.nextInt(SumProduct);
		ListOfItems.findElements(By.tagName("li")).get(TotalNumber).click();

		WebElement Container = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));
		int TotalNumOfProduct = Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).size();
		int RandomOfProduct = rand.nextInt(TotalNumOfProduct);
		Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).get(RandomOfProduct).click();

		WebElement ULProduct = driver.findElement(By.className("productpagecart"));
		int liitems = ULProduct.findElements(By.tagName("li")).get(0).findElements(By.tagName("span")).size();

		if (liitems > 0) {
			driver.get(WebSite1);
			System.out.print("SorryTheItem Out Of Stock");

			String ActualResult = "https://automationteststore.com";
			String ExpectedResult = driver.getCurrentUrl();
			Assert.assertEquals(ActualResult, ExpectedResult);

		}

		else {
			driver.findElement(By.className("cart")).click();

			System.out.print("TheItemshavebeenadded");
		}

		Thread.sleep(3000);
		String ActualResult1= driver.findElement(By.className("heading1")).getText();
		String ExpectedResult1= "SHOPPING CART";
		Assert.assertEquals(ActualResult1, ExpectedResult1);

		boolean ActualResult2 = driver.findElement(By.id("cart_checkout1")).isDisplayed();
		boolean ExpectedResult2 = true;
		Assert.assertEquals(ActualResult2, ExpectedResult2);

	}


	
}
