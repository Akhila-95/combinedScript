import org.testng.annotations.Test;

import com.GuestUserWith_CreditCard.tc__PdpPagewithGuestuser;
import com.providio.testcases.baseClass;

public class practise extends baseClass{
@Test
	public void pracrice () throws InterruptedException {
	
	driver.get("https://zzqi-002.dx.commercecloud.salesforce.com/s/RefArch/washable-wool-classic-straight-skirt-/25328702M.html?lang=en_US");
		tc__PdpPagewithGuestuser pdp =new tc__PdpPagewithGuestuser();
		pdp.writeReviewForProduct();
	}
}

