//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.GuestUserWith_CreditCard;

import com.providio.pageObjects.navigationPage;
import com.providio.pageObjects.productDescriptionPage;
import com.providio.pageObjects.productListingPage;
import com.providio.paymentProccess.tc__CheckOutProcess;
import com.providio.paymentProccess.tc__CreditCardPaymentProcess;
import com.providio.payments.size;
import com.providio.testcases.baseClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class tc__PdpPagewithGuestuser extends baseClass {

	SoftAssert softAssert = new SoftAssert();

	@Test
	public void PDPpage() throws InterruptedException {
		
		test.info("Open browser");
        // Test Case 0: Open browser
        driver.get("https://zzqi-002.dx.commercecloud.salesforce.com/s/RefArch/electronics/gps%20navigation/garmin-nuvi-255M.html?lang=en_US");
        logger.info("Opened browser");
	    // Validate if the user is logged in or not
	 /*
	        // Step 1: Navigate to Women's New Arrivals
        	navigateRandomMenu();
        	
	        // Step 2: Select a Product from the Listing Page
	        selectProductFromListingPage();
*/
	        // Step 3: Perform actions on Product Description Page
	        performActionsOnProductDescriptionPage();

	        // Step 4: Write a Review for the Product
	        writeReviewForProduct();

	        // Step 5: Add the Product to Cart
	        addProductToCart();

	        // Step 6: Proceed to Checkout
	        tc__CheckOutProcess cp = new tc__CheckOutProcess();
	        cp.checkoutprocess();
            //payment process
            
            tc__CreditCardPaymentProcess cc = new tc__CreditCardPaymentProcess();          
            cc.paymentByCreditCard(); 

	    // Assert all the soft assertions
	    softAssert.assertAll();
	}

	// Step 1: Navigate to Women's New Arrivals
	private void navigateRandomMenu() throws InterruptedException {
		navigationPage navPage =new navigationPage(driver);
  	  	navPage.selectRandomMenu(driver);
  	  	test.info("Randomly selected a Menu");
  	  List<WebElement> pageTitle = driver.findElements(By.cssSelector("h1.page-title"));
 	 if(pageTitle.size()>0)
 	 {
 		 WebElement pageTitle1 = driver.findElement(By.cssSelector("h1.page-title"));
 		 test.info("Entered into random menu" + pageTitle1.getText());
 	 }
	}

	// Step 2: Select a Product from the Listing Page
	private void selectProductFromListingPage() throws InterruptedException {
	    productListingPage plp = new productListingPage(driver);
	    plp.selectProductRandom(driver);
	    test.info("Clicked on a product from the listing page");
	    List<WebElement> productName1 = driver.findElements(By.xpath("//h1[contains(@class,'product-name')]"));
  	     if(productName1.size()>0) {
  	    	 WebElement productName = driver.findElement(By.xpath("//h1[contains(@class,'product-name')]"));
  	    	 test.info("productName is " +productName.getText());
  	     }
	    
	    //validate the product selected
	    validateproductselect();
	}

	// Step 3: Perform actions on Product Description Page
	private void performActionsOnProductDescriptionPage() throws InterruptedException {
	 	// size selection
  		 List<WebElement> pdpPage = driver.findElements(By.xpath("//button[contains(@class,'add-to-cart btn btn-primary')]"));
  		 if( pdpPage.size()>0) {
		          size s = new size();			        
		          s.selectSize(driver);
	    		 }
  		productDescriptionPage pdp = new productDescriptionPage(driver);
	    pdp.clickOnLeftCarousel(driver);
	    logger.info("Clicked on Left Carousel");
	    pdp.clickOnRightCarousel(driver);
	    logger.info("Clicked on Right Carousel");
	    // Add any additional actions on the Product Description Page
	}

	// Step 4: Write a Review for the Product
	public void writeReviewForProduct() throws InterruptedException {
		List<WebElement> bopis =driver.findElements(By.xpath("(//span[contains(@class,'write-question-review-button-text')])[1]"));		
		List<WebElement> powerReviews =driver.findElements(By.cssSelector("a.pr-snippet-write-review-link"));
		
		if(powerReviews.size()>0 || bopis.size()>0) {
		//WebElement bopisDisplay =driver.findElement(By.xpath("(//span[contains(@class,'write-question-review-button-text')])[1]"));
	//	WebElement powerReviews1 =driver.findElement(By.cssSelector("a.pr-snippet-write-review-link"));
		if(((powerReviews.size()>0))) {
			productDescriptionPage pdp = new productDescriptionPage(driver);
			    pdp.clickOnWriteAReviewAtTop(driver);
			    logger.info("Clicked on Write a Review at the top");
			    pdp.clickOnRating(driver);
			    logger.info("Clicked on Rating");
			    pdp.clickOnReviewHeadline(driver, headline);
			    logger.info("Entered Review Headline");
			    Thread.sleep(1000);
			    pdp.clickOnComments(comment);
			    logger.info("Entered Comments");
			    pdp.clickOnYes();
			    logger.info("Clicked on Yes");
			    pdp.clicknickName(nickName);
			    logger.info("Entered Nickname");
			    pdp.clickOnLoc(location);
			    logger.info("Entered Location");
			    pdp.clickOnSubmitReview(driver);
			    logger.info("Clicked on Submit Review");
			    // Add any additional steps for writing a review
			    validateReviewProduct();
			    pdp.clickOncontinueShoping(driver);
			    logger.info("clicked on the clickOncontinueShoping button");
			    
		}else {
			logger.info("Another review is activated");
			test.info("Another review is activated");
		}
	    
	  }   
	}

	// Step 5: Add the Product to Cart
	private void addProductToCart() throws InterruptedException {
	     List<WebElement> pdpPage = driver.findElements(By.xpath("//button[contains(@class,'add-to-cart btn btn-primary')]"));
   		 if( pdpPage.size()>0) {	    			 
		          size s = new size();			          
		          s.selectSize(driver);
	    		 };
	    // Add any additional steps for adding the product to the cart
	}
	

	
	private void validateproductselect() {
		
		//validate the product is selected
		test.info("verify that product is selected");		
        List<WebElement> pdpHeader = driver.findElements(By.xpath("//h1[contains(@class,'product-name')]"));          
        if (pdpHeader.size()>0) {
        	WebElement pdpHeader1 = driver.findElement(By.xpath("//h1[contains(@class,'product-name')]"));
        	System.out.println("The product name is " +pdpHeader1);
            test.pass("Successfully clicked on the product from the product listing page");
            logger.info("Successfully clicked on the product from the product listing page");
        } else {
            test.fail("Product is not selected from the product listing page");
            logger.info("Product is not selected from the product listing page");
        }		 
	}
	
	private void validateReviewProduct() {
		List<WebElement> thankYouText1 = driver.findElements(By.cssSelector("h1.title"));
		test.info("validate the Review of the product");
		if(thankYouText1.size()>0) {
		WebElement thankYouText = driver.findElement(By.cssSelector("h1.title"));
		// Get the text value of the element
				String actualText = thankYouText.getText();
				// Define the expected text
				String expectedText = "Thank you!";
				// Validate if the text is displayed using a basic if condition
				if (actualText.equals(expectedText)) {
				    logger.info("The 'Thank you!' text is displayed on the page.");
				    test.pass("successfully writen the review");
				} else {
				    logger.info("The 'Thank you!' text is not displayed on the page.");
				    test.fail("Review is not done");
				}

		}
	}

}