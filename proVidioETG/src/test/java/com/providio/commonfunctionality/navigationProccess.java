package com.providio.commonfunctionality;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.providio.pageObjects.bundleProductAddAllToCart;
import com.providio.pageObjects.navigationPage;
import com.providio.pageObjects.productListingPage;
import com.providio.payments.size;
import com.providio.testcases.baseClass;

public class navigationProccess extends baseClass {
	
	int minicartCountValue;
	
	public void commonNavigationProccess() throws InterruptedException {
		
		for(int i=1;i<=1; i++) {
	         navigationPage navPage =new navigationPage(driver);
	    	 navPage.selectRandomMenu(driver);
	    	// to know the category entered 
	    	 List<WebElement> pageTitle = driver.findElements(By.cssSelector("h1.page-title"));
	    	 if(pageTitle.size()>0)
	    	 {
	    		 WebElement pageTitle1 = driver.findElement(By.cssSelector("h1.page-title"));
	    		 test.info("Entered into random menu " + pageTitle1.getText());
	    	 }
	    //selected a random product 
	    	 productListingPage plp = new productListingPage(driver);
	   	     plp.selectProductRandom(driver);	    	         
	   	     List<WebElement> productName1 = driver.findElements(By.xpath("//h1[@class='product-name hidden-sm-down']"));
	   	     if(productName1.size()>0) {
	   	    	 WebElement productName = driver.findElement(By.xpath("//h1[@class='product-name hidden-sm-down']"));
	   	    	 test.info("productName is  [" + i + "] " +productName.getText());
	   	     }	
	   	  //if selected product is bundle them
	   	     List<WebElement> bundleProduct = driver.findElements(By.cssSelector(".bundle-item"));
	   	     if(bundleProduct.size()>0){
	   	    	bundleProductAddAllToCart bundle= new bundleProductAddAllToCart();
	   	    	bundle.addAllToCart(driver);
	   	     }    
	   	//The cart value before adding the product to cart
	   	     Thread.sleep(2000);
	   		 List<WebElement> minicartcountList = driver.findElements(By.cssSelector(".minicart-quantity"));
	   		 if(minicartcountList.size()>0) {
	   			 WebElement minicartcount = driver.findElement(By.cssSelector(".minicart-quantity"));
	   			 String countOfMinicart = minicartcount.getText();
	
	            // Check if the string is not empty and contains only digits
	            if (!countOfMinicart.isEmpty() && countOfMinicart.matches("\\d+")) {
	                minicartCountValue = Integer.parseInt(countOfMinicart);
	                System.out.println("The minicart count before adding the product is " + minicartCountValue);    		
	             }
	   		 }
	   	// size selection
	   		 List<WebElement> pdpPage = driver.findElements(By.xpath("//button[contains(@class,'add-to-cart btn btn-primary')]"));
	   		 if( pdpPage.size()>0) {
			          size s = new size();			        
			          s.selectSize(driver);
		    		 }

	   		 if(minicartcountList.size()>0) {
	   			 Thread.sleep(1000);
		          WebElement minicartcountafteradding = driver.findElement(By.xpath("//span[@class ='minicart-quantity ml-1']"));
		          String countOfMinicartafteradding = minicartcountafteradding.getText();
		          int minicartCountValueafteradding = Integer.parseInt(countOfMinicartafteradding);	
			      logger.info("The minicart count after adding the product is"+minicartCountValueafteradding);
		
			       //validation for product add to cart
			        test.info("Verifying the product is added to cart or not ");
		
			        if( minicartCountValueafteradding!= minicartCountValue) {
			            test.pass("Product added to cart");
			            logger.info("Product is  added to cart");
			        }else {
			            test.fail("Product is not added to cart");
			            logger.info("Product is not added to cart");
			        }
   		 		}
	    }			
	}
}
