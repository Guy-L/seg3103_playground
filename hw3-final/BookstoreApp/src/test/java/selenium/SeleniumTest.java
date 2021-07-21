package selenium;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Random;

class ExampleSeleniumTest {

  static Process server;
  private WebDriver driver;
  String uniqueID;

  @BeforeAll
  public static void setUpBeforeClass() throws Exception {
    ProcessBuilder pb = new ProcessBuilder("java", "-jar", "bookstore5.jar").inheritIO();
    server = pb.start();
  }

  @BeforeEach
  void setUp() {
    // Pick your browser
    // driver = new FirefoxDriver();
    // driver = new SafariDriver();
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
	Random r = new Random();
	uniqueID = "" + (char)(r.nextInt(26) + 'a') + (char)(r.nextInt(26) + 'a') + (char)(r.nextInt(26) + 'a') + (char)(r.nextInt(26) + 'a') + (char)(r.nextInt(26) + 'a');

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get("http://localhost:8080/admin");
    // wait to make sure Selenium is done loading the page
    WebDriverWait wait = new WebDriverWait(driver, 60);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
  }

  @AfterEach
  public void tearDown() {
    driver.close();
  }

  @AfterAll
  public static void tearDownAfterClass() throws Exception {
    server.destroy();
  }

  //@Test
  //void test1() {
  //  WebElement element = driver.findElement(By.id("title"));
  //  String expected = "YAMAZONE BookStore";
  //  String actual = element.getText();
  //  assertEquals(expected, actual);
  //}
  //
  //@Test
  //public void test2() {
  //  WebElement welcome = driver.findElement(By.cssSelector("p"));
  //  String expected = "Welcome";
  //  String actual = welcome.getText();
  //  assertEquals(expected, getWords(actual)[0]);
  //  WebElement langSelector = driver.findElement(By.id("locales"));
  //  langSelector.click();
  //  WebElement frSelector = driver.findElement(By.cssSelector("option:nth-child(3)"));
  //  frSelector.click();
  //  welcome = driver.findElement(By.cssSelector("p"));
  //  expected = "Bienvenu";
  //  actual = welcome.getText();
  //  assertEquals(expected, getWords(actual)[0]);
  //}
  //
  //private String[] getWords(String s) {
  //  return s.split("\\s+");
  //}*/
  //
  //@Test
  //public void orderingUpdatingTest(){
  //  driver.findElement(By.id("searchBtn")).click();
  //  driver.findElement(By.id("order-hall001")).click();
  //  driver.findElement(By.id("order-hall002")).click(); //we will remove this item before checkout
  //  driver.findElement(By.id("order-lewis001")).click(); //we will order two of this item
  //  driver.findElement(By.id("cartLink")).click();
  //  driver.findElement(By.id("hall002")).sendKeys("\b0" + Keys.TAB + Keys.ENTER);
  //  driver.findElement(By.id("lewis001")).sendKeys("\b2" + Keys.TAB + Keys.ENTER + Keys.TAB + Keys.ENTER);
  //  assertEquals("$104.22", driver.findElement(By.id("order_total")).getText());
  //  //(expected value obtained from going through the user story manually beforehand
  //}
  
  
  
  //------------------------------------------------------------
  //-------------------- FUNCTIONALITY TESTS -------------------
  //------------------------------------------------------------
  
  @Test
  public void F8NegTest(){
	  driver.findElement(By.id("loginId")).sendKeys("foo");
	  driver.findElement(By.id("loginPasswd")).sendKeys("bar");
	  driver.findElement(By.id("loginBtn")).click();
	  assertEquals(true, driver.getPageSource().contains("Invalid username and/or password"));
  }
  
  @Test
  public void F8PosTest(){
	  driver.findElement(By.id("loginId")).sendKeys("admin");
	  driver.findElement(By.id("loginPasswd")).sendKeys("password");
	  driver.findElement(By.id("loginBtn")).click();
	  assertEquals("http://localhost:8080/admin", driver.getCurrentUrl());

  }
  
  @Test
  public void F1NegTest(){
		F8PosTest();
		driver.findElement(By.name("addBook")).click();
	    assertEquals(true, driver.getPageSource().contains("A title is mandatory"));
	    assertEquals(true, driver.getPageSource().contains("Missing author(s)"));
	    assertEquals(true, driver.getPageSource().contains("The Book Id must be between 5 and 8 character long"));
	    assertEquals(true, driver.getPageSource().contains("A category is mandatory"));
	    assertEquals(true, driver.getPageSource().contains("A cost is mandatory and must be greater or equal to 0"));
  }
  
  @Test
  public void F1PosTest(){
      F8PosTest();
	  driver.findElement(By.id("addBook-category")).sendKeys("a");
	  driver.findElement(By.id("addBook-id")).sendKeys(uniqueID);
	  driver.findElement(By.id("addBook-title")).sendKeys("a");
	  driver.findElement(By.id("addBook-category")).sendKeys("a");
	  driver.findElement(By.id("addBook-authors")).sendKeys("a");
	  driver.findElement(By.id("longDescription")).sendKeys("a");
	  driver.findElement(By.id("cost")).sendKeys("1");
	  driver.findElement(By.name("addBook")).click();
	  assertEquals(true, driver.getPageSource().contains("Successfully added book"));
  }
  
  @Test
  public void F21PosTest(){
		F8PosTest();
	  driver.findElement(By.id("search")).sendKeys("a");
	  driver.findElement(By.id("searchBtn")).click();
	  assertEquals(true, driver.getPageSource().contains("We currently have the following items in category 'a'"));
  }
  
  @Test
  public void F21NegTest(){
		F8PosTest();
	  driver.findElement(By.id("search")).sendKeys("b");
	  driver.findElement(By.id("searchBtn")).click();
	  assertEquals(true, driver.getPageSource().contains("Sorry we do not have any item matching category 'b' at this moment"));
  }
  
  @Test
  public void F22PosTest(){
		F8PosTest();
	  driver.findElement(By.id("searchBtn")).click();
	  assertEquals(true, driver.getPageSource().contains("We currently have the following items in category 'a'"));
	  
  }
  
  //@Test
  //public void F22NegTest(){
	//  
  //}
  //
  //@Test
  //public void F3PosTest(){
	//  
  //}
  //
  //@Test
  //public void F3NegTest(){
	//  
  //}
  //
  //@Test
  //public void F4PosTest(){
	//  
  //}
  //
  //@Test
  //public void F4NegTest(){
	//  
  //}
  //
  //@Test
  //public void F5PosTest(){
	//  
  //}
  //
  //@Test
  //public void F5NegTest(){
	//  
  //}
  //
  //@Test
  //public void F51PosTest(){
	//  
  //}
  //
  //@Test
  //public void F51NegTest(){
	//  
  //}
  //
  //@Test
  //public void F61PosTest(){
	//  
  //}
  //
  //@Test
  //public void F61NegTest(){
	//  
  //}
  //
  //@Test
  //public void F62PosTest(){
	//  
  //}
  //
  //@Test
  //public void F62NegTest(){
	//  
  //}
  //
  //@Test
  //public void F7PosTest(){
	//  
  //}
  //
  //@Test
  //public void F7NegTest(){
	//  
  //}
  //
  ////------------------------------------------------------------
  ////---------------------- USE CASE TESTS ----------------------
  ////------------------------------------------------------------
  //
  //@Test
  //public void SignInTC1(){
	//  
  //}
  //
  //@Test
  //public void SignInTC2(){
	//  
  //}
  //
  //@Test
  //public void LogOutTC1(){
	//  
  //}
  //
  //@Test
  //public void AddBookTC1(){
	//  
  //}
  //
  //@Test
  //public void AddBookTC2(){
	//  
  //}
  //
  //@Test
  //public void AddBookTC3(){
	//  
  //}
  //
  //@Test
  //public void AddBookTC4(){
	//  
  //}
  //
  //@Test
  //public void BrowseTC1(){
	//  
  //}
  //
  //@Test
  //public void BrowseTC2(){
	//  
  //}
  //
  //@Test
  //public void BrowseTC3(){
	//  
  //}
  //
  //@Test
  //public void OrderBookTC1(){
	//  
  //}
  //
  //@Test
  //public void OrderBookTC2(){
	//  
  //}
  //
  //@Test
  //public void UpdateOrderTC1(){
	//  
  //}
  //
  //@Test
  //public void UpdateOrderTC2(){
	//  
  //}
  //
  //@Test
  //public void CheckOutTC1(){
	//  
  //}
  //
  //@Test
  //public void SelectLanguageTC1(){
	//  
  //}
}
