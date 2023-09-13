package com.providio.paymentProccess;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.providio.pageObjects.paymentpPage;
import com.providio.pageObjects.reviewOrderPage;
import com.providio.testcases.baseClass;

public class tc__CreditCardPaymentProcess extends baseClass{
	
	public void paymentByCreditCard() throws InterruptedException {
		
	

		 Thread.sleep(5000);
		 
		List<WebElement> minicartcount = driver.findElements(By.xpath("//span[@class ='minicart-quantity ml-1']"));		
		if(minicartcount.size()==0) {
			
			//validating the payment page
			validatePaymentButtonClk();
			
	        List<WebElement> continueasAGuest = driver.findElements(By.xpath("//button[contains(text(),'Guest Checkout')]"));
	        logger.info(continueasAGuest.size());
	        
	        paymentpPage pp = new paymentpPage(driver);
	        
			//brain tree
			List<WebElement> creditcardscheck = driver.findElements(By.xpath("//a[@class ='nav-link creditcard-tab active']"));
		    System.out.println(creditcardscheck.size());
		    
		    //creditcard salesfornce

			List<WebElement> creditcardsSalesForce = driver.findElements(By.xpath("//div[@class='sfpp-payment-method-header sfpp-payment-method-header-card']"));
		    System.out.println(creditcardsSalesForce.size());
		    
			    if(creditcardscheck.size()>0) {
			    	
			    	List<WebElement> savedCardsBrainTree = driver.findElements(By.xpath("//option[@class ='js_stored_card']"));
			    	System.out.println(savedCardsBrainTree.size());
			    	if(savedCardsBrainTree.size()>0) {
			    		
			    		logger.info("Saved cards are there selected one of them");
			    		
			    	}else {
			    		
			    		Thread.sleep(5000);
			    		if(continueasAGuest.size()>0) {
			    			//guest cc
			    			guestUserCCpayment();			    			
		
		                }else {
		                	//new card
		                	registerUserCC();
		                }
			    		    		
			    	}
			   	
			    }else if(creditcardsSalesForce.size()>0) {
			    	
			    	
		    		if(continueasAGuest.size()>0) {
		    			//guest cc
		    			salesforcePaymentProcessguest();			    			
	
	                }else {
	                	//new card
				    	//new card salesforce
				    	//salesforcePaymentProcess();
	                }

			    	
			    	
			    }else {
		
			    	List<WebElement> savedCardsCyberSourece = driver.findElements(By.xpath("//option[@class ='js_stored_card']"));
			    	System.out.println(savedCardsCyberSourece.size());
			    	
			    	if(savedCardsCyberSourece.size()>0) {
			    		//select one and send the cvv number of that card
			    		logger.info("Saved cards are there for cyber source");
			    		
			    	}else {
			             // cyber source new card
			    		cyberSourceNewcard();
			    		
			    	}

			    }
			    
			    
			    //Salesforce payment integration place the order
			    if(creditcardsSalesForce.size()>0) {
			    	
			    	logger.info("clicking the salesforce place the order");
			    	
			    	//click the place order button
			    	pp.placetheOrder(driver);
			    	
			    }else {
			    	
			    	logger.info("excuting another clicking the salesforce place the order");
			    	
			    	pp.clickonrevieworder(driver);
					logger.info("ckicked on the review oreder");
					Thread.sleep(10000);
					validatethebuttonisclicked();
					
					//revieworderpage
					
					reviewOrderPage rop = new reviewOrderPage(driver);
					Thread.sleep(15000);
					rop.clickonplaceorderwithJsExuter(driver);
					logger.info("successfully click on the place order button");
			    }
	

			Thread.sleep(20000);
			
			//validate the final place the order page
			validatePlacetheOrderPage();
			
            //ordernumberandOrderdat
			ordernumberandOrderdat();
			
			
		}else {
                logger.info("The cart value is empty");
                test.fail("The cart value is empty");
		}
	}
	
	//payment of cyber source 
	private void cyberSourceNewcard() throws InterruptedException {
		
		paymentpPage pp = new paymentpPage(driver);
		
//		pp.addPaymentButton(driver);
//    	logger.info("click on the add payment button");
    	
        pp.latestcardnumber(driver);
        logger.info("entered card number");
        pp.latestExpDate(driver);
        logger.info("entered exp month");
        pp.latestExpYear(driver);
        logger.info("entered exp year");
        pp.latestSceuritycode(driver);
        logger.info("entered scecode");
		
	}
	//payment of salesforce integeration new one
	private void salesforcePaymentProcess() throws InterruptedException {
		paymentpPage pp = new paymentpPage(driver);
		
		//pp.radiobutton(driver);
		
		pp.cardNumber(driver);
    	
    	logger.info("entered card number");
    	pp.expiryDate(driver);
    	
    	logger.info("entered cvv");
    	pp.cvc(driver);
    	logger.info("entered exp");
	}
	
	//payment of salesforce integeration new one
	private void salesforcePaymentProcessguest() throws InterruptedException {
		paymentpPage pp = new paymentpPage(driver);
		
		//pp.radiobutton(driver);
		
		pp.cardNumber(driver);
    	
    	logger.info("entered card number");
    	pp.expiryDate(driver);
    	
    	logger.info("entered cvv");
    	pp.cvc(driver);
    	logger.info("entered exp");
	}
	//payment method of reg new card details
	private void registerUserCC() throws InterruptedException {
		
		paymentpPage pp = new paymentpPage(driver);

    	pp.selectnewcardindropdown();  
		logger.info("Selectd the new card");
		
		pp.setcardholdername(driver);
		logger.info("entered card name");
		
		pp.setcardnumber(driver);
		logger.info("entered card number");
		
		pp.setcardcvv(driver);
		logger.info("entered cvv");
		
		pp.setcardexp(driver);
		logger.info("entered exp");
	}
	
	//payment method of guest user cc
	private void guestUserCCpayment() throws InterruptedException {
		
		paymentpPage pp = new paymentpPage(driver);
		
		pp.setcardholdername(driver);
		logger.info("entered card name");
		
		pp.setcardnumber(driver);
		logger.info("entered card number");
		
		pp.setcardcvv(driver);
		logger.info("entered cvv");
		
		pp.setcardexp(driver);
		logger.info("entered exp");
		
	}
	
	
	//validate the order number and date of order
	private void ordernumberandOrderdat() {
		
		//displayordernumberandplaceddate
	    
	    WebElement orderNumeber = driver.findElement(By.xpath("//span[@class ='summary-details order-number']"));
	    String Ordernumber = orderNumeber.getText();
        test.pass("Successfully Order is Placed and the Order number is "+ Ordernumber);
        logger.info("Successfully Order is Placed and the Order number is "+ Ordernumber);
	    
	    //order date
	    WebElement OrderDate = driver.findElement(By.xpath("//span[@class ='summary-details order-date']"));
        String Orderdate = OrderDate.getText();
        test.pass("Successfully Order is Placed and the Ordered date is "+ Orderdate);
		
	}
	
	
	//validate the place the order page
    private void validatePlacetheOrderPage() throws InterruptedException {
    	
    	test.info("verify that order is placed");
		
		//validate the orderstatus
	    WebElement PlacetheOrder = driver.findElement(By.xpath("//h2[@class ='order-thank-you-msg']"));
	    String ActualTitleofPlacetheOrder = PlacetheOrder.getText();
	    String ExpectedTitlePlacetheOrder = "Thank you for your order.";
	    logger.info(PlacetheOrder.getText());
	    
	    if (ActualTitleofPlacetheOrder.equals(ExpectedTitlePlacetheOrder)) {
	        test.pass("Successfully Order is Placed");
	        logger.info("Successfully Order is Placed");
	    } else {
	        //test.fail( "The order is not placed");
	        logger.info("Click failed");
	        relickReviewOrederButton();
	        
	        
	    }
	    
	    Thread.sleep(5000);
    	
    }
    
    //reclick the review ordeer buttton
    
    private void relickReviewOrederButton() {
    	
    	 WebElement PlacetheOrder = driver.findElement(By.xpath("//h2[@class ='order-thank-you-msg']"));
 	    String ActualTitleofPlacetheOrder = PlacetheOrder.getText();
 	    String ExpectedTitlePlacetheOrder = "Thank you for your order.";
 	    logger.info(PlacetheOrder.getText());
 	    
 	    if (ActualTitleofPlacetheOrder.equals(ExpectedTitlePlacetheOrder)) {
 	        test.pass("Successfully Order is Placed");
 	        logger.info("Successfully Order is Placed");
 	    } else {
 	        test.fail( "The order is not placed");
 	        logger.info("Click failed");
 	        
 	        
 	    }
    	
    }
	
	//validate the payment page
	private void validatePaymentButtonClk() {
		
		//validate the payment page
	    WebElement paymentPage = driver.findElement(By.xpath("//label[contains(text(), 'Payment Method')]"));
	    String ActualTitleofpaymentPage = paymentPage.getText();
	    String ExpectedTitlepaymentPage = "Payment Method";
	    logger.info(paymentPage.getText());
	    
	    if (ActualTitleofpaymentPage.equals(ExpectedTitlepaymentPage)) {
	    	test.info("Verify that shipping address added");
	    	 test.pass("Successfully added the shipping address");
	    	 test.info("Verify the payment button is clicked");
	        test.pass("Successfully clicked on the Payment button");
	        logger.info("Successfully clicked on the Payment button");
	    } else {
	        test.fail( "The page Title does not match expected " + ExpectedTitlepaymentPage + " " + "  but found" + " " + ActualTitleofpaymentPage + " ");
	        logger.info("Click failed");
	    }
	    
	    
	//  //soft assertions payment page
	//  
	//  softAssert.assertEquals(ActualTitleofpaymentPage, ExpectedTitlepaymentPage, "Page title does not match expected value");
	//  
	//  //Hard assertions payment page
	//  
	//  Assert.assertEquals(ActualTitleofpaymentPage, ExpectedTitlepaymentPage, "Page title does not match expected value");
	
	   
		
	}
	
	//validate after clicking the review oreder page
	
	
	private void validatethebuttonisclicked() {
		
		// Find the button using the XPath expression.
        WebElement button = driver.findElement(By.xpath("//button[@name='submit' and @value='place-order']"));

        // Check if the button is displayed on the page.
        if (button.isDisplayed()) {
            System.out.println("Button is displayed.");
        } else {
            System.out.println("Button is not displayed.");
        }
	}

}