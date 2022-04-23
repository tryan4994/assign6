package test.java;

import main.java.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.lang.ClassNotFoundException;

@RunWith(Parameterized.class)
public class costTest {

    private Class<Cart> classUnderTest;

    @SuppressWarnings("unchecked")
    public costTest(Object classUnderTest) {
        this.classUnderTest = (Class<Cart>) classUnderTest;
    }

    // Define all classes to be tested
    @Parameterized.Parameters
    public static Collection<Object[]> cartClassUnderTest() {
        Object[][] classes = {
            {Cart.class}
            
            
        };
        return Arrays.asList(classes);
    }

    private Cart createCart(int age) throws Exception {
        Constructor<Cart> constructor = classUnderTest.getConstructor(Integer.TYPE);
        return constructor.newInstance(age);
    }

    // A sample Cart

    Cart cart1;
    Product product1;
    Produce produce1;
    Produce produce2;
    double cart1Expected;
    double cart1Expected2;
    int cart1Saved;
    double cartExpectedZero;
    double cart1Taxes;
    double cartAge;
    String arizona= "AZ";
    String california= "CA";
    String newyork= "NY";
    String colorado= "CO";
    double produce_counter;
    List<Product> cart2;
    



    
    @org.junit.Before
    public void setUp() throws Exception {

        // all carts should be set up like this

        // cart created with an age 40 shopper
        cart1 = createCart(40);
        for (int i = 0; i < 2; i++) {
            cart1.addItem(new Alcohol());
        }
        for(int i = 0; i < 3; i++) {
            cart1.addItem(new Dairy());
        }
        for(int i = 0; i < 4; i++) {
            cart1.addItem(new Meat());
        }

        cart1Expected = 70.2;
        cartExpectedZero= 0.0;
        cartAge= 0.0;
        /*
        cart2= createCart(25);
        for (int i = 0; i < 1; i++) {
        	cart2.addItem(new FrozenFood());
        	*/
        }
    
    

    // sample test
    @Test
    public void calcCostCart1() throws UnderAgeException {
        double amount = cart1.calcCost();
        assertEquals(cart1Expected, amount, 0.1);
        
        
        
    }
    
    @Test
    public void calcException() throws UnderAgeException {
    	assertThrows(UnderAgeException.class, () -> {                   
    		throw new UnderAgeException("The User is not of age!");
    	});
    }
    
    @Test
    public void calcAlcoholCost() throws UnderAgeException {

    	Alcohol alcohol1= new Alcohol();
    	int alcoholCost1= alcohol1.getCost();
    	
    	assertEquals(8, alcoholCost1);
    }
    
    @Test
    public void calcProduceCost() throws UnderAgeException {

    	Produce produce1= new Produce();
    	int produceCost1= produce1.getCost();
    	assertEquals(2, produceCost1);
    }
    
    @Test
    public void calcDairyCost() throws UnderAgeException {

    	Dairy dairy1= new Dairy();
    	int dairyCost1= dairy1.getCost();
    	assertEquals(3, dairyCost1);
    	
    }
    
    @Test
    public void calcFrozenFoodCost() throws UnderAgeException {

    	FrozenFood frozen1= new FrozenFood();
    	int frozenCost1= frozen1.getCost();
    	assertEquals(5,frozenCost1);
    }
    
    @Test
    public void calcMeatCost() throws UnderAgeException {

    	Meat meat1= new Meat();
    	int meatCost1= meat1.getCost();
    	assertEquals(10, meatCost1);
    }
    
    @Test
    public void calcProduceDiscount() throws UnderAgeException {
    	////System.out.println("Size = "+cart1.size());
    	cart2 = new ArrayList<Product>();
    	for(int i=0;i<10;i++)
    	{
    		cart2.add(new Produce());
    		
    	}
    	
    	for(int i=0;i<3;i++)
    	{
    		cart2.add(new Dairy());
    		
    	}
    	
    	
    	
    	
    	
    	double amount5= cart1.calcCost();
    	for(int i = 0; i < cart2.size(); i++) {
    		
    	if (cart2.get(i).getClass().toString().equals(Produce.class.toString())) {
            produce_counter++;

            if (produce_counter >= 3) {
            	amount5 -= 1;
                produce_counter = 0;
            	}
        	}
    	}
        
    }
    
    @Test
    public void calcProduceCount() throws UnderAgeException {
        
    	produce1 = new Produce();
        while(produce_counter<3) {
        	
        	produce1.getCost();
            produce_counter++;
        }     	       
        System.out.println("Produce count: "+ produce_counter);
    }
    
    @Test
    public void calcProduceExists() throws UnderAgeException {
    	try {
    		Class.forName("main.java.Produce");
    	}catch (ClassNotFoundException e) {
    		Assert.fail("Fail Produce does't exist");
    	}
    }
    
    /**
     *                When Cart is Empty
     * @throws UnderAgeException
     */
    
    @Test
    public void calcCostCartZero() throws UnderAgeException {
       
     	Cart newCart = new Cart(33);
    	double amount1 =newCart.calcCost();
        
        assertEquals(cartExpectedZero, amount1, 0.1);
        
    }
    
    
    /**
     *              Calculate Saved Amount 
     * @throws UnderAgeException
     */
    @Test
    public void cartSavings() throws UnderAgeException{
   
    	int amount2= cart1.Amount_saved();
    	assertEquals(cartExpectedZero, amount2, 0.1);
    }
    
    
    /**
     *   test  for calculating Tax Newyork
     * 
     * @throws UnderAgeException
     */
    @Test
    public void cartTaxesNewyork() throws UnderAgeException{
    	
    	
    	assertEquals(8.4, cart1.getTax(120, newyork),0.1);
    }
    
    

    /**
     *   test  for calculating Tax arizona
     * 
     * @throws UnderAgeException
     */
    @Test
    public void cartTaxesArizona() throws UnderAgeException{
    	
    	
    	assertEquals(800, cart1.getTax(10000, arizona),0.1);
    }
    
  
    
    /**
     *   test  for calculating Tax California
     * 
     * @throws UnderAgeException
     */
    @Test
    public void cartTaxesCalifornia() throws UnderAgeException{
    	
    	
    	assertEquals(72, cart1.getTax(800, california),0.1);
    }
    
    
    
    
    /**
     *   test  for calculating Tax Coloradio
     * 
     * @throws UnderAgeException
     */
    @Test
    public void cartTaxesColoradio() throws UnderAgeException{
    	
    	
    	assertEquals(21, cart1.getTax(300, colorado),0.1);
    }
    
    
    
    
    /**
     *                     Calculate the price of Meat 500 Quantity
     * @throws UnderAgeException
     */
    
    @Test
    public void  calulateMeatPrice() throws UnderAgeException{
    
    Cart cart = new Cart(30);
    
    for(int i=0;i<500;i++)
        cart.addItem(new Meat());
    
        	assertEquals(5000, cart.calcCost(),0.1);
    
    
    
    }
    
    
    
    
    /**
     *           Calculate Price Of alcohol 120 Quantity
     *            age Greater than 21
     *           
     * 
     * @throws UnderAgeException
     */
    @Test
    public void  calulateAlcohol120Productus() throws UnderAgeException{
    
    Cart cart = new Cart(30);
    
    for(int i=0;i<120;i++)
        cart.addItem(new Alcohol());
    
        	assertEquals(1036.8, cart.calcCost(),0.1);
    
    
    
    }
    
    
    
    /**
     *          Calculate Price of Frozen Food
     *          
     * @throws UnderAgeException
     */
    @Test
    public void  calulateFrozenFoodPrice() throws UnderAgeException{
    
    Cart cart = new Cart(30);
    
    for(int i=0;i<70;i++)
        cart.addItem(new FrozenFood());
    
        	assertEquals(350, cart.calcCost(),0.1);
    
    
    
    }
    
    
    
    /**
     *                    Calculate Price With Frozen Food With Alcohol 
     *                     Frozen Food Quantity Greater Than Alcohol
     *                     
     *                     
     * @throws UnderAgeException
     */
    
    @Test
    public void  calulateFrozenFoodWithAlcohol() throws UnderAgeException{
    
    Cart cart = new Cart(30);
    
    for(int i=0;i<12;i++)
    cart.addItem(new FrozenFood());
    for(int i=0;i<7;i++)
        cart.addItem(new Alcohol());
    
        	assertEquals(102.6, cart.calcCost(),0.1);
    
    }
    
    
    
    /**
     *            Here Alcohol Quantity is greater than Frozen Food
     * @throws UnderAgeException
     */
    @Test
    public void  calculateAlcoholWithFrozen() throws UnderAgeException{
    
    Cart cart = new Cart(30);
    
    for(int i=0;i<52;i++)
    cart.addItem(new FrozenFood());
    for(int i=0;i<77;i++)
        cart.addItem(new Alcohol());
    
        	assertEquals(777.6, cart.calcCost(),0.1);
    
    
    
    }
    
   
    
    
   
    
    
    /**
     *                                Here We Check Multiple products At Once 
     * @throws UnderAgeException
     */
    @Test
    public void  calulateMultipleProductsPriceAtOnce() throws UnderAgeException{
    
    Cart cart = new Cart(30);
    for(int i=0;i<10;i++)
    cart.addItem(new Produce());
    for(int i=0;i<10;i++)
        cart.addItem(new Alcohol());
    for(int i=0;i<10;i++)
        cart.addItem(new FrozenFood());
    
    for(int i=0;i<10;i++)
        cart.addItem(new Dairy());
    
    for(int i=0;i<10;i++)
        cart.addItem(new Meat());
    
        	assertEquals(266.76, cart.calcCost(),0.1);
    
    
    
    }
    
    
    /**
     *                       When Frozen food and Alcohol Quantity Same
     * @throws UnderAgeException
     */

    @Test
    public void  calulatePriceBothFrozenFoodAndAlcohoSame() throws UnderAgeException{
    
    Cart cart = new Cart(44);
    
    for(int i=0;i<10;i++)
        cart.addItem(new Alcohol());
    for(int i=0;i<10;i++)
        cart.addItem(new FrozenFood());

assertEquals(108.0, cart.calcCost(),0.1);
    
    
    }
    
    
    /**
     *             To Check Frozen food With Alcohol underAge 
     * 
     * @throws UnderAgeException
     */
    
    @Test
    public void  checkAlcohoWithFrozenFoodUnderAge() throws UnderAgeException{
    
    Cart cart = new Cart(18);
    
    for(int i=0;i<10;i++)
        cart.addItem(new Alcohol());
    for(int i=0;i<10;i++)
        cart.addItem(new FrozenFood());

    UnderAgeException thrown = Assertions.assertThrows(UnderAgeException.class, () -> {
        cart.calcCost();
  });

  Assertions.assertEquals("under Age Exception ", thrown.getMessage());

    
    
    }
    

  /**
   *      To check Alcohol Under age Exception
   */
    @Test
   public void testAlcoholUnderAge() {

    	Cart cart = new Cart(10);
    	cart.addItem(new Alcohol());
      UnderAgeException thrown = Assertions.assertThrows(UnderAgeException.class, () -> {
            cart.calcCost();
      });

      Assertions.assertEquals("Under Age Exception ", thrown.getMessage());
    }

    
    
    
    
    /*
    @Test
    public void cartTaxes2() throws UnderAgeException{
    	double amount4= cart1.getTax(cartExpectedZero, null);
        assertTrue("Cart taxes is greater than 0: ", amount4>0);
    }
    
    /*
    @Test
    public void cartTaxesArizona() throws UnderAgeException{
    	cart1.getTax(cartExpectedZero, arizona);
    }
    */
    /*
    @Test
    public void cartTaxesCalifornia() throws UnderAgeException{
    	cart1.getTax(cartExpectedZero, california);
    }
    @Test
    public void cartTaxesNewYork() throws UnderAgeException{
    	cart1.getTax(cartExpectedZero, newyork);
    }
    @Test
    public void cartTaxesColorado() throws UnderAgeException{
    	cart1.getTax(cartExpectedZero, colorado);
    }
    
    */
    
    
    /**
     *   cost Saved
     * @throws UnderAgeException
     */
    
    @Test
    public void calculatePriceSaved() throws UnderAgeException{
		
    	Cart cart = new Cart(30);
    	cart.addItem(new Alcohol());
    	cart.addItem(new Dairy());
    	
    	cart.addItem(new FrozenFood());

    	cart.addItem(new FrozenFood());
    	
    	cart.addItem(new Meat());
    	cart.addItem(new Produce());
    	cart.addItem(new Meat());
    	cart.addItem(new Produce());
    	

    	assertEquals(-3, cart.Amount_saved());
	}
    

    
    
    /**
     *   cost Saved  with greater then  3 Produce
     * @throws UnderAgeException
     */
    
    @Test
    public void calculatePriceSavedProduce() throws UnderAgeException{
		Cart cart = new Cart(24);
	cart.addItem(new Produce());

	cart.addItem(new Produce());

	cart.addItem(new Produce());

	cart.addItem(new Produce());

	cart.addItem(new Produce());
    	
    	assertEquals(1, cart.Amount_saved());
    	
	}
    
    
    /**
     *  
     *   Amount Saved For Alcohol Under age Exception
     * @throws UnderAgeException
     */
    
    @Test
    public void calculatePriceSavedUnderAge() throws UnderAgeException{
    	Cart cart = new Cart(10);
    	cart.addItem(new Alcohol());
      UnderAgeException thrown = Assertions.assertThrows(UnderAgeException.class, () -> {
            cart.Amount_saved();
      });

      Assertions.assertEquals("The User is not of age to purchase alcohol!", thrown.getMessage());
    	
	}
    
    
    
    @Test
    public void cartAddItems() throws UnderAgeException{
    	cart1.addItem(product1);
    	
    }
  
  /**
   *                               
   * @throws UnderAgeException
   */
    @Test
    public void cartRemoveItems() throws UnderAgeException{
    	cart1.addItem(new Alcohol());
    	cart1.addItem(new Dairy());
    	
    	boolean ret= cart1.RemoveItem(new Alcohol());
    	assertTrue(ret);
    }
  
    /**
     *                           
     * @throws UnderAgeException
     */
    @Test
    public void cartRemoveItemsNotExist() throws UnderAgeException{
    	cart1.addItem(new Alcohol());
    	cart1.addItem(new Dairy());
    	
    	boolean retBoolean= cart1.RemoveItem(new FrozenFood());
    	
    	assertFalse(retBoolean);
    }
  
    /*
    @Test
    public void calcCostCart2() throws UnderAgeException{
    	double amount3= cart1.Amount_saved();
        assertTrue("Cart cost is greater than 0: ", amount3>0);
    }
    
    @Test
    public void calcCostCart3() throws UnderAgeException{
    	double amount4= Cart.calcCost();
    	//Cost is equal to 0:
    	assertEquals( costExpected,amount4, 0.01);
    }
    
    
    @Test
    public void cartSaved() throws UnderAgeException{
    	int amount1= Cart.Amount_saved();
        assertTrue("Cart amount saved is greater than 0: ", amount1>0);
    }
    
    @Test
    public void cartSaved2() throws UnderAgeException{
    	int amount2= cart1.Amount_saved();
        assertTrue("Cart amount saved is less than 0: ", amount2<0);
    }
    */
}