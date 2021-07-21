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
import org.openqa.selenium.support.ui.Select;;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Random;

class SeleniumTest {

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
	System.out.println("Unique ID for Session: " + uniqueID);
  }

  @AfterEach
  public void tearDown() {
    driver.close();
  }

  @AfterAll
  public static void tearDownAfterClass() throws Exception {
    server.destroy();
  }
  
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
	  assertEquals("Successfully added book", driver.findElement(By.tagName("h2")).getText());
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
      if(!driver.getPageSource().contains(uniqueID))
	      F1PosTest(); //add the uniqueID book if it wasn't here already
 
	  driver.findElement(By.id("search")).sendKeys("a");
	  driver.findElement(By.id("searchBtn")).click();
	  assertEquals("We currently have the following items in category 'a'", driver.findElement(By.tagName("h1")).getText());
	  
	  //F1PosTest() requires its book not to already exist to pass, so we must delete it
	  if(driver.findElements(By.cssSelector("input[value='Sign out']")).isEmpty()) F8PosTest(); //if not signed in, sign in
	  removeBook(uniqueID);
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
	  while(!driver.findElements(By.name("deleteItem")).isEmpty()){
		  driver.findElement(By.name("deleteItem")).click(); //delete every book :(
		   try{Thread.sleep(1000);} catch(Exception e){}
	  }
      
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
	  try{Thread.sleep(1000);} catch(Exception e){}
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
      if(!driver.getPageSource().contains(uniqueID))
	      F1PosTest(); //add the uniqueID book if it wasn't here already

	  if(driver.findElements(By.cssSelector("input[value='Sign out']")).isEmpty()) F8PosTest(); //if not signed in, sign in
	  removeBook(uniqueID); //abstraction of this functionality for reuse elsewhere
	  assertEquals(false, driver.getPageSource().contains(uniqueID));
  }
  
  @Test
  public void F7NegTest(){
      F8PosTest(); //login as admin
	  driver.findElement(By.id("searchBtn")).click();
	  while(!driver.findElements(By.name("deleteItem")).isEmpty()){
		  driver.findElement(By.name("deleteItem")).click(); //delete every book :(
		  try{Thread.sleep(1000);} catch(Exception e){}
	  }
      
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
  public void SignInTC1(){
	  //N.B.: Functional duplicate of F8PosTest
	  driver.get("http://localhost:8080/admin");
	  driver.findElement(By.id("loginId")).sendKeys("admin");
	  driver.findElement(By.id("loginPasswd")).sendKeys("password");
	  driver.findElement(By.id("loginBtn")).click();
	  assertEquals("http://localhost:8080/admin", driver.getCurrentUrl());
  }

  @Test
  public void SignInTC2(){
	  driver.get("http://localhost:8080/admin");
	  driver.findElement(By.id("loginId")).sendKeys("blah");
	  driver.findElement(By.id("loginBtn")).click();
	  assertEquals(true, driver.getPageSource().contains("Invalid username and/or password"));
	  driver.findElement(By.id("loginId")).sendKeys("foo");
	  driver.findElement(By.id("loginPasswd")).sendKeys("bar");
	  driver.findElement(By.id("loginBtn")).click();
	  assertEquals(true, driver.getPageSource().contains("Invalid username and/or password"));
	  driver.findElement(By.id("loginId")).sendKeys("admin");
	  driver.findElement(By.id("loginPasswd")).sendKeys("password");
	  driver.findElement(By.id("loginBtn")).click();
	  assertEquals("http://localhost:8080/admin", driver.getCurrentUrl());
  }
  
  @Test
  public void LogOutTC1(){
	  SignInTC1(); //login as admin
	  driver.findElement(By.cssSelector("input[value='Sign out']")).click();
	  assertEquals(true, driver.getPageSource().contains("You have been logged out"));
  }
  
  @Test
  public void AddBookTC1(){
	  //N.B.: Functional duplicate of F1PosTest
      SignInTC1(); //login as admin
	  driver.findElement(By.id("addBook-category")).sendKeys("myCoolCategory");
	  driver.findElement(By.id("addBook-id")).sendKeys(uniqueID + "222");
	  driver.findElement(By.id("addBook-title")).sendKeys(uniqueID + "222");
	  driver.findElement(By.id("addBook-authors")).sendKeys("ABC");
	  driver.findElement(By.id("longDescription")).sendKeys("ABC");
	  driver.findElement(By.id("cost")).sendKeys("30");
	  driver.findElement(By.name("addBook")).click();
	  assertEquals(true, driver.getPageSource().contains("Successfully added book"));
  }
  
  @Test
  public void AddBookTC2(){
      SignInTC1(); //login as admin
	  driver.findElement(By.name("addBook")).click();
	  assertEquals(true, driver.getPageSource().contains("A title is mandatory"));
	  assertEquals(true, driver.getPageSource().contains("Missing author(s)"));
	  assertEquals(true, driver.getPageSource().contains("The Book Id must be between 5 and 8 character long"));
	  assertEquals(true, driver.getPageSource().contains("A category is mandatory"));
	  assertEquals(true, driver.getPageSource().contains("A cost is mandatory and must be greater or equal to 0"));
	  
	  driver.findElement(By.id("addBook-category")).sendKeys("z");
	  driver.findElement(By.id("addBook-id")).sendKeys("z");
	  driver.findElement(By.id("addBook-title")).sendKeys("z");
	  driver.findElement(By.id("addBook-authors")).sendKeys("z");
	  driver.findElement(By.id("longDescription")).sendKeys("z");
	  driver.findElement(By.id("cost")).sendKeys("z");
	  driver.findElement(By.name("addBook")).click();
	  assertEquals(true, driver.getPageSource().contains("The Book Id must be between 5 and 8 character long"));
	  assertEquals(true, driver.getPageSource().contains("A cost is mandatory and must be greater or equal to 0"));
	  
	  driver.findElement(By.id("addBook-id")).sendKeys("\bazaza");
	  driver.findElement(By.id("cost")).sendKeys("\b1");
	  driver.findElement(By.name("addBook")).click();
	  assertEquals(true, driver.getPageSource().contains("Successfully added book")); 
	  
	  removeBook("azaza");
  }
  
  @Test
  public void AddBookTC3(){
      SignInTC1(); //login as admin
	  driver.findElement(By.id("addBook-category")).sendKeys("Blah");
	  driver.findElement(By.id("addBook-id")).sendKeys("hall001");
	  driver.findElement(By.id("addBook-title")).sendKeys("Blah");
	  driver.findElement(By.id("addBook-authors")).sendKeys("Blah");
	  driver.findElement(By.id("longDescription")).sendKeys("Blah");
	  driver.findElement(By.id("cost")).sendKeys("39.95");
	  driver.findElement(By.name("addBook")).click();
	  assertEquals(true, driver.getPageSource().contains("Book with same id already exist"));
	  
	  driver.findElement(By.id("addBook-id")).sendKeys("\b\b\b\b\b\b\bbbbbbb");
	  driver.findElement(By.name("addBook")).click();
	  assertEquals(true, driver.getPageSource().contains("Successfully added book")); 
	  
	  removeBook("bbbbbb");
  }
  
  @Test
  public void AddBookTC4(){
      SignInTC1(); //login as admin
	  driver.findElement(By.name("addBook")).click();
	  assertEquals(true, driver.getPageSource().contains("A title is mandatory"));
	  assertEquals(true, driver.getPageSource().contains("Missing author(s)"));
	  assertEquals(true, driver.getPageSource().contains("The Book Id must be between 5 and 8 character long"));
	  assertEquals(true, driver.getPageSource().contains("A category is mandatory"));
	  assertEquals(true, driver.getPageSource().contains("A cost is mandatory and must be greater or equal to 0"));
	  
	  driver.findElement(By.id("addBook-category")).sendKeys("z");
	  driver.findElement(By.id("addBook-id")).sendKeys("hall001");
	  driver.findElement(By.id("addBook-title")).sendKeys("z");
	  driver.findElement(By.id("addBook-authors")).sendKeys("z");
	  driver.findElement(By.id("longDescription")).sendKeys("z");
	  driver.findElement(By.id("cost")).sendKeys("5");
	  driver.findElement(By.name("addBook")).click();
	  assertEquals(true, driver.getPageSource().contains("Book with same id already exist"));
	  
	  driver.findElement(By.id("addBook-id")).sendKeys("\b\b\b\b\b\b\bccccccc");
	  driver.findElement(By.name("addBook")).click();
	  assertEquals(true, driver.getPageSource().contains("Successfully added book")); 
	  
	  removeBook("ccccccc");
  }
  
  @Test
  public void BrowseTC1(){
	  driver.findElement(By.id("searchBtn")).click();
      if(!driver.getPageSource().contains(uniqueID+"222"))
	      AddBookTC1(); //add the uniqueID+222 book if it wasn't here already

	  driver.findElement(By.id("search")).sendKeys("myCoolCategory");
	  driver.findElement(By.id("searchBtn")).click();
	  assertEquals("We currently have the following items in category 'myCoolCategory'", driver.findElement(By.tagName("h1")).getText());
	  
	  //AddBookTC1 requires its book not to already exist to pass, so we must delete it
	  if(driver.findElements(By.cssSelector("input[value='Sign out']")).isEmpty()) SignInTC1(); //if not signed in, sign in
	  removeBook(uniqueID+"222");
  }
  
  @Test
  public void BrowseTC2(){
	  //N.B.: Functional duplicate of F22PosTest
	  driver.findElement(By.id("searchBtn")).click();
	  assertEquals("We currently have the following items in category ''", driver.findElement(By.tagName("h1")).getText());
  }
  
  @Test
  public void BrowseTC3(){
	  driver.findElement(By.id("search")).sendKeys("foobar");
	  driver.findElement(By.id("searchBtn")).click();
	  assertEquals("Sorry we do not have any item matching category 'foobar' at this moment", driver.findElement(By.tagName("h1")).getText());
  }

  @Test
  public void RemoveBookTC1(){
	  //N.B.: Functional duplicate of F7PosTest
	  driver.findElement(By.id("searchBtn")).click();
      if(!driver.getPageSource().contains(uniqueID+"222"))
	      AddBookTC1(); //add the uniqueID+222 book if it wasn't here already

      if(driver.findElements(By.cssSelector("input[value='Sign out']")).isEmpty()) SignInTC1(); //if not signed in, sign in
      removeBook(uniqueID+"222");
      assertEquals(false, driver.getPageSource().contains(uniqueID+"222"));
  }

  @Test
  public void OrderBookTC1(){
	  //N.B.: Functional duplicate of F3PosTest
	  driver.findElement(By.id("searchBtn")).click();
	  driver.findElement(By.id("order-hall001")).click();
	  //No assert: see Implementation Issues
  }
  
  @Test
  public void OrderBookTC2(){
	  driver.findElement(By.id("searchBtn")).click();
	  driver.findElement(By.id("order-hall001")).click();
	  driver.findElement(By.id("order-hall001")).click();
	  //No assert: see Implementation Issues
  }
  
  @Test
  public void ViewOrderTC1(){
	  //N.B.: Functional duplicate of F4PosTest
	  OrderBookTC1(); //order one copy of the hall001 book
	  driver.findElement(By.id("cartLink")).click();
	  assertEquals(true, driver.getPageSource().contains("hall001"));
  }
  
  @Test
  public void UpdateOrderTC1(){
	  //N.B.: Functional duplicate of F5PosTest
	  ViewOrderTC1(); //order one copy of the hall001 book & view order  
	  driver.findElement(By.id("hall001")).sendKeys("\b3" + Keys.TAB + Keys.ENTER);
	  assertEquals("$119.85", driver.findElement(By.id("tothall001")).getText());
  }
  
  @Test
  public void UpdateOrderTC2(){
	  //N.B.: Functional duplicate of F51PosTest
	  ViewOrderTC1(); //order one copy of the hall001 book & view order  
  	  driver.findElement(By.id("hall001")).sendKeys("\b0" + Keys.TAB + Keys.ENTER);
	  assertEquals("$0.00", driver.findElement(By.id("tothall001")).getText());
	  //Note: this is not really desirable; see Implementation Issues
  }
  
  @Test
  public void CheckOutTC1(){
	  UpdateOrderTC1(); //checkout order; three copies of the hall001 book
	  driver.findElement(By.name("checkout")).click();
	  assertEquals("$151.42", driver.findElement(By.id("order_total")).getText());
  }
  
  @Test
  public void SelectLanguageTC1(){
	  Select dropdown = new Select(driver.findElement(By.id("locales")));
	  dropdown.selectByVisibleText("French");
	  assertEquals(true, driver.getPageSource().contains("Librairie Y'AMAZONE")); 

	  //Other tests need the page to be in English, so let's revert
	  dropdown = new Select(driver.findElement(By.id("locales")));
	  dropdown.selectByVisibleText("Anglais");
	  assertEquals(true, driver.getPageSource().contains("YAMAZONE BookStore")); 
  }

  ////------------------------------------------------------------
  ////---------------------- HELPER METHOD -----------------------
  ////------------------------------------------------------------
  
  public void removeBook(String ID){
	  driver.findElement(By.id("searchBtn")).click();
	  driver.findElement(By.id("del-"+ID)).click();
  }
}
