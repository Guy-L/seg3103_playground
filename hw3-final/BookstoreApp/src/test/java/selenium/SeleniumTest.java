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
  static String uniqueID;

  @BeforeAll
  public static void setUpBeforeClass() throws Exception {
    ProcessBuilder pb = new ProcessBuilder("java", "-jar", "bookstore5.jar").inheritIO();
    server = pb.start();
	Random r = new Random();
	uniqueID = "" + (char)(r.nextInt(26) + 'a') + (char)(r.nextInt(26) + 'a') + (char)(r.nextInt(26) + 'a') + (char)(r.nextInt(26) + 'a') + (char)(r.nextInt(26) + 'a');
  }

  @BeforeEach
  void setUp() {
    // Pick your browser
    // driver = new FirefoxDriver();
    // driver = new SafariDriver();
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get("http://localhost:8080/");
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
  public void F1PosTest(){
      F8PosTest(); //login as admin
	  driver.findElement(By.id("addBook-category")).sendKeys("a");
	  driver.findElement(By.id("addBook-id")).sendKeys(uniqueID);
	  driver.findElement(By.id("addBook-title")).sendKeys(uniqueID);
	  driver.findElement(By.id("addBook-authors")).sendKeys("a");
	  driver.findElement(By.id("longDescription")).sendKeys("a");
	  driver.findElement(By.id("cost")).sendKeys("1");
	  driver.findElement(By.name("addBook")).click();
	  assertEquals(true, driver.getPageSource().contains("Successfully added book"));
  }
  
  @Test
  public void F1NegTest(){
	  F8PosTest(); //login as admin
	  driver.findElement(By.name("addBook")).click();
	  assertEquals(true, driver.getPageSource().contains("A title is mandatory"));
	  assertEquals(true, driver.getPageSource().contains("Missing author(s)"));
	  assertEquals(true, driver.getPageSource().contains("The Book Id must be between 5 and 8 character long"));
	  assertEquals(true, driver.getPageSource().contains("A category is mandatory"));
	  assertEquals(true, driver.getPageSource().contains("A cost is mandatory and must be greater or equal to 0"));
  }
  
  @Test
  public void F21PosTest(){
	  driver.findElement(By.id("searchBtn")).click();
	  if(!driver.getPageSource().contains(uniqueID)) F1PosTest(); //add the uniqueID book since it hasn't been added
	  
	  driver.findElement(By.id("search")).sendKeys("a");
	  driver.findElement(By.id("searchBtn")).click();
	  assertEquals("We currently have the following items in category 'a'", driver.findElement(By.tagName("h1")).getText());
	  
	  //F1PosTest() requires its book not to already exist to pass, so we must delete it
	  F8PosTest(); //login as admin	  
	  driver.findElement(By.id("searchBtn")).click();
	  driver.findElement(By.id("del-"+uniqueID)).click();
	  assertEquals(false, driver.getPageSource().contains(uniqueID));
  }
  
  @Test
  public void F21NegTest(){
	  driver.findElement(By.id("search")).sendKeys("b");
	  driver.findElement(By.id("searchBtn")).click();
	  assertEquals("Sorry we do not have any item matching category 'b' at this moment", driver.findElement(By.tagName("h1")).getText());
  }
  
  @Test
  public void F22PosTest(){
	  driver.findElement(By.id("searchBtn")).click(); //the input box will be empty
	  assertEquals("We currently have the following items in category ''", driver.findElement(By.tagName("h1")).getText());
  }
  
  @Test
  public void F22NegTest(){
      F8PosTest(); //login as admin
	  driver.findElement(By.id("searchBtn")).click();
	  for (WebElement element : driver.findElements(By.name("deleteItem"))) 
	  	element.click(); //delete every book :(
      
	  driver.findElement(By.id("searchBtn")).click(); //the input box will be empty
	  assertEquals(true, driver.getPageSource().contains("Sorry we do not have any item matching category '' at this moment"));
	  
	  //Other tests assume Hall001 to exist, so I have to add it back
	  driver.get("http://localhost:8080/admin");
	  driver.findElement(By.id("addBook-category")).sendKeys("default");
	  driver.findElement(By.id("addBook-id")).sendKeys("hall001");
	  driver.findElement(By.id("addBook-title")).sendKeys("Core Servlets and JavaServer Pages 2nd Edition (Volume 1)");
	  driver.findElement(By.id("addBook-authors")).sendKeys("Marty Hall and Larry Brown");
	  driver.findElement(By.id("longDescription")).sendKeys("The definitive reference on servlets and JSP from Prentice Hall and Sun Microsystems Press.Nominated for the Nobel Prize in Literature.");
	  driver.findElement(By.id("cost")).sendKeys("39.95");
	  driver.findElement(By.name("addBook")).click();
	  assertEquals(true, driver.getPageSource().contains("Successfully added book"));
  }
  
  @Test
  public void F3PosTest(){
	  driver.findElement(By.id("searchBtn")).click();
	  driver.findElement(By.id("order-hall001")).click();
	  //No assert: see Implementation Issues
  }
  
  //@Test
  //public void F3NegTest(){
	//Untestable
  //}
  
  @Test
  public void F4PosTest(){
	  F3PosTest(); //order a book
	  driver.findElement(By.id("cartLink")).click();
	  assertEquals(true, driver.getPageSource().contains("hall001"));
  }
  
  @Test
  public void F4NegTest(){
	  driver.findElement(By.id("searchBtn")).click();
	  driver.findElement(By.id("cartLink")).click();
	  assertEquals(true, driver.findElements(By.name("checkout")).isEmpty()); //check that checkout button isn't offered (will fail)
  }
  
  @Test
  public void F5PosTest(){
	  F4PosTest(); //order a book and view order
	  driver.findElement(By.id("hall001")).sendKeys("\b3" + Keys.TAB + Keys.ENTER);
	  assertEquals("$119.85", driver.findElement(By.id("tothall001")).getText());
  }
  
  @Test
  public void F5NegTest(){
	  F4PosTest(); //order a book and view order
	  String oldValue = driver.findElement(By.id("tothall001")).getText();
	  driver.findElement(By.id("hall001")).sendKeys("\bNaN" + Keys.TAB + Keys.ENTER);
	  assertEquals(oldValue, driver.findElement(By.id("tothall001")).getText()); 
	  //nothing was changed after invalid number of copies was given
  }
  
  @Test
  public void F51PosTest(){
	  F4PosTest(); //order a book and view order
	  driver.findElement(By.id("hall001")).sendKeys("\b0" + Keys.TAB + Keys.ENTER);
	  assertEquals("$0.00", driver.findElement(By.id("tothall001")).getText());
	  //Note: this is not really desirable; see Implementation Issues
  }
  
  @Test
  public void F51NegTest(){
	  F4PosTest(); //order a book and view order
	  driver.findElement(By.id("hall001")).sendKeys("\b0" + Keys.TAB + Keys.ENTER);
	  assertEquals("$0.00", driver.findElement(By.id("tothall001")).getText());
	  driver.findElement(By.id("hall001")).sendKeys("\b5" + Keys.TAB + Keys.ENTER);
	  assertEquals("$0.00", driver.findElement(By.id("tothall001")).getText());
	  //Note: this is not really desirable; see Implementation Issues
  }
  
  @Test
  public void F61PosTest(){
	  F4PosTest(); //order a book and view order
	  driver.findElement(By.name("checkout")).click();
	  assertEquals("$5.19", driver.findElement(By.id("order_taxes")).getText());
  }
  
  @Test
  public void F61NegTest(){
	  F51PosTest(); //oder a book then remove it in the order view page
	  driver.findElement(By.name("checkout")).click();
	  assertEquals(true, driver.findElements(By.id("order_total")).isEmpty()); //check that total isn't offered (will fail)
  }
  
  @Test
  public void F62PosTest(){
	  F4PosTest(); //order a book and view order
	  driver.findElement(By.name("checkout")).click();
	  assertEquals("$12.00", driver.findElement(By.id("order_shipping")).getText());
  }
  
  @Test
  public void F62NegTest(){
      //Since we expect not to see checkout information like in the F6.1 negative, we can just repeat that test.
	  F61NegTest();
  }
  
  @Test
  public void F7PosTest(){
	  driver.findElement(By.id("searchBtn")).click();
	  if(driver.getPageSource().contains(uniqueID)) F8PosTest(); //login as admin
	  else F1PosTest(); //add the uniqueID book since it hasn't been added
	  
	  driver.findElement(By.id("searchBtn")).click();
	  driver.findElement(By.id("del-"+uniqueID)).click();
	  assertEquals(false, driver.getPageSource().contains(uniqueID));
  }
  
  @Test
  public void F7NegTest(){
      F8PosTest(); //login as admin
	  driver.findElement(By.id("searchBtn")).click();
	  for (WebElement element : driver.findElements(By.name("deleteItem"))) 
	  	element.click(); //delete every book :(
      
	  assertEquals(true, driver.findElements(By.name("deleteItem")).isEmpty()); //now check there is no deleteItem buttons -- yes, this is a VERY redundant test
	  
	  //Other tests assume Hall001 to exist, so I have to add it back
	  driver.get("http://localhost:8080/admin");
	  driver.findElement(By.id("addBook-category")).sendKeys("default");
	  driver.findElement(By.id("addBook-id")).sendKeys("hall001");
	  driver.findElement(By.id("addBook-title")).sendKeys("Core Servlets and JavaServer Pages 2nd Edition (Volume 1)");
	  driver.findElement(By.id("addBook-authors")).sendKeys("Marty Hall and Larry Brown");
	  driver.findElement(By.id("longDescription")).sendKeys("The definitive reference on servlets and JSP from Prentice Hall and Sun Microsystems Press.Nominated for the Nobel Prize in Literature.");
	  driver.findElement(By.id("cost")).sendKeys("39.95");
	  driver.findElement(By.name("addBook")).click();
	  assertEquals(true, driver.getPageSource().contains("Successfully added book"));
  }
  
  @Test
  public void F8PosTest(){
	  driver.get("http://localhost:8080/admin");
	  driver.findElement(By.id("loginId")).sendKeys("admin");
	  driver.findElement(By.id("loginPasswd")).sendKeys("password");
	  driver.findElement(By.id("loginBtn")).click();
	  assertEquals("http://localhost:8080/admin", driver.getCurrentUrl());
  }
  
  @Test
  public void F8NegTest(){
	  driver.get("http://localhost:8080/admin");
	  driver.findElement(By.id("loginId")).sendKeys("foo");
	  driver.findElement(By.id("loginPasswd")).sendKeys("bar");
	  driver.findElement(By.id("loginBtn")).click();
	  assertEquals(true, driver.getPageSource().contains("Invalid username and/or password"));
  }
  
  ////------------------------------------------------------------
  ////---------------------- USE CASE TESTS ----------------------
  ////------------------------------------------------------------
  
  @Test
  public void GSignInTC1(){
	//  
  }
  
//  @Test
//  public void GSignInTC2(){
//	//  
//  }
//  
//  @Test
//  public void GLogOutTC1(){
//	//  
//  }
//  
//  @Test
//  public void GAddBookTC1(){
//	//  
//  }
//  
//  @Test
//  public void GAddBookTC2(){
//	//  
//  }
//  
//  @Test
//  public void GAddBookTC3(){
//	//  
//  }
//  
//  @Test
//  public void GAddBookTC4(){
//	//  
//  }
//  
//  @Test
//  public void GrowseTC1(){
//	//  
//  }
//  
//  @Test
//  public void GrowseTC2(){
//	//  
//  }
//  
//  @Test
//  public void GBrowseTC3(){
//	//  
//  }
//  
//  @Test
//  public void GOrderBookTC1(){
//	//  
//  }
//  
//  @Test
//  public void GOrderBookTC2(){
//	//  
//  }
//  
//  @Test
//  public void GUpdateOrderTC1(){
//	//  
//  }
//  
//  @Test
//  public void GUpdateOrderTC2(){
//	//  
//  }
//  
//  @Test
//  public void GCheckOutTC1(){
//	//  
//  }
//  
//  @Test
//  public void GSelectLanguageTC1(){
//	//  
//  }
}
