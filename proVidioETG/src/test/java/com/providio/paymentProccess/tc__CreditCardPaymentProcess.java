package com.providio.paymentProccess;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.providio.Validations.Checkout_Validation;
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
		    
		    List<WebElement> stripePayment = driver.findElements(By.xpath("(//div[contains(@class,'StripeElement')])[1]"));
		    System.out.println("The stripe paymnet is " +stripePayment.size());
		    
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

			    	
			    	
			    }else if(stripePayment.size()>0) {
			    	test.info("Stripe payment activated");
			    	stripePayment();
			    	
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
	

			Thread.sleep(5000);
			 //Checkout_Validation checkout= new Checkout_Validation();
    		if(driver.getTitle().endsWith("Order Confirmation | Providio")) {
    			
    			 Checkout_Validation checkout= new Checkout_Validation();
    		 //validate the final place the order page
    			 checkout.validatePlacetheOrderPage();
    		
    	     //ordernumberandOrderdate
    			 checkout.ordernumberandOrderdate();
    			 Thread.sleep(5000);
    			}
			
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
	
	public void stripePayment() {
		paymentpPage pp = new paymentpPage(driver);
		
		pp.cardNum(driver);
		test.info("entered card number");
		pp. expDate(driver);
		test.info("entered exp date");		
		pp.cvv(driver);
		test.info("entered cvv");
		pp.postalCode();
		test.info("entered postal code");
	}
	   
	//validate the payment page
	private void validatePaymentButtonClk() {
		
		//validate the payment page
	    WebElement paymentPage = driver.findElement(By.xpath("//label[contains(text(), 'Payment Method')]"));
	    if( paymentPage.isDisplayed()) {
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
        List<WebElement> button = driver.findElements(By.xpath("//button[@name='submit' and @value='place-order']"));
        if(button.size()>0) {
        	 WebElement button1 = driver.findElement(By.xpath("//button[@name='submit' and @value='place-order']"));
	        // Check if the button is displayed on the page.
	        if (button1.isDisplayed()) {
	            System.out.println("Button is displayed.");
	        } else {
	            System.out.println("Button is not displayed.");
	        }
	}
	}
}