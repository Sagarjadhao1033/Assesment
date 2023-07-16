import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonTestScripts {
  WebDriver driver;
        @BeforeClass
        public void setUp() {
            // Set up WebDriver and launch the browser
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sagar Jadho\\Downloads\\chromedriver.exe");
            driver = new ChromeDriver();


            driver.get("https://www.amazon.com");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        @Test(priority = 1)
        public void loginTest() {

            // Test step 1: Click on Sign in Link
           driver.findElement(By.id("nav-link-accountList"));
            // Test step 2: Login to an e-commerce website

            driver.findElement(By.id("ap_email")).sendKeys("Abc1234@gmail.com");
            driver.findElement(By.id("continue")).click();
            driver.findElement(By.id("ap_password")).sendKeys("Xyz1234");
            driver.findElement(By.id("signInSubmit")).click();

            // Assert that the login was successful
            WebElement loggedInUserElement = driver.findElement(By.id("loggedInUser"));
            Assert.assertEquals(loggedInUserElement.getText(), "Welcome, ABC1234");
        }

        @Test( priority =2 )
        public void searchItemTest() {
            // Test step 2: Search an item

            driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Acer Laptop");
            driver.findElement(By.id("nav-search-submit-button")).click();


        }

        @Test( priority = 3)
        public void printProductsTest() {

            // Wait for the page to load
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();

                // Test step 3: Find all product elements using XPath and Print all the products on the first page
                List<WebElement> productElements = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));

                // Iterate over the product elements and print their titles
                for (WebElement productElement : productElements) {
                    WebElement titleElement = productElement.findElement(By.xpath(".//h2/a/span"));
                    String title = titleElement.getText();
                    System.out.println(title);
                }

            }
        }
            @Test( priority = 4)
            public void addProduct()
        {
                // Add a product to the cart
                driver.findElement(By.xpath("span[contains(text(), 'Acer Aspire Lite 11th Gen')]"));
                 driver.findElement(By.xpath("//input[@value='Add to Cart']")).click();


            }

        @Test(priority = 5)
        public void updateProductTest() {
            // Test step 5: Update a product
                WebElement quantityDropdown = driver.findElement(By.id("a-autoid-0-announce"));
            Select select = new Select(quantityDropdown);
                select.selectByVisibleText("3");
        }

        @Test( priority = 6)
        public void deleteProductTest() {
                // Test step 6: Delete the product
                driver.findElement(By.xpath("//input[@value='Delete']")).click();
            }

        @Test(priority = 7)
        public void logoutTest() {
            // Test step 7: Logout
            driver.findElement(By.id("logoutButton")).click();

                // Confirm the deletion
                driver.switchTo().alert().accept();
        }

        @Test(priority = 8)
        public void tearDown() {
            // Close the browser and perform cleanup
            driver.quit();
        }
    }




