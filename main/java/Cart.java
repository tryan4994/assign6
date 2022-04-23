package main.java;

import java.lang.invoke.MethodHandles.Lookup.ClassOption;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    protected int userAge;
    public List<Product> cart;
    public int cartStorage;

    /**
     * Calculates the final cost after all savings and tax has been applied. Also checks
     * that the user is of age to purchase alcohol if it is in their cart at checkout. Sales tax is always AZ tax.
     *
     * Calculation is based off of the following prices and deals:
     * Dairy -> $3
     * Meat -> $10
     * Produce -> $2 or 3 for $5
     * Alcohol -> $8
     * Frozen Food -> $5
     * Alcohol + Frozen Food -> $10
     *
     * If there is an alcohol product in the cart and the user is under 21, then an
     * UnderAgeException should be thrown.
     *
     * @return double totalCost
     * @throws UnderAgeException
     */
    private int getQty(String className) {
    	int qty=0;
    	for(int i=0;i<cart.size();i++)
    	{
    		if(className.equals(cart.get(i).getClass().toString()))
    			qty++;
    		
    		
    	}
    	
		return qty;
	}
    
    public double calcCost() throws UnderAgeException {
        double totalPrice=0;        	
    	          int diaryCount=getQty(Dairy.class.toString());
    	           int meatCount= getQty(Meat.class.toString());
    	          int produceCount = getQty(Produce.class.toString());
                 int alcohalCount= getQty(Alcohol.class.toString());
    	         int frozenCount= getQty(FrozenFood.class.toString()); 
    	          if(diaryCount>0)
    		{
    		
    	        
    			totalPrice+=(3.0*diaryCount);
    			
    		}    		
    		 if(meatCount>0)
    		{
    			totalPrice+=(10.0*meatCount);    			
    		}     		
    		
    	 if(produceCount>0)
    		{
    			
    		           int rem = produceCount%3;
    		           int div= produceCount/3;
    		           
    		           if(rem>0)
    		           {
    		        	   totalPrice+=(rem*2);
    		        	   
    		           }
    		           if(div>0)
    		           {
    		        	   
    		        	   totalPrice+=div*5;
    		        	   
    		           }   		 
    		} 
    		   	 
    	 if(alcohalCount>0  && frozenCount>0)
    	 {
    		 if(this.userAge<21)
    		 {
    			 throw new UnderAgeException("under Age Exception ");    			 
    		 }    		 
    		 else 
    		 {
    			 if(alcohalCount== frozenCount)
    			 {
    				 
    				 totalPrice+=10*alcohalCount;
    				 
    			 }    			 
    			 else if(alcohalCount>frozenCount)
    			 {    				 
    				 int dif= alcohalCount-frozenCount;
    				 
    				 totalPrice+= (10*frozenCount);
    				 totalPrice+= (8*dif);   				 
    			 }    		    			 
    			 
    			 else if(frozenCount>alcohalCount)
    			 {
    				 

    				 int dif= frozenCount-alcohalCount;
    				 
    				 totalPrice+= (10*alcohalCount);
    				 totalPrice+= (5*dif);
    				 
    			 }
 

				 totalPrice+= getTax(((totalPrice)), "AZ");
    	
    		 }
    		 
    		 
    	 }
    	 
    	 else if(alcohalCount>0)
    	 {
    		 
    		 if(userAge<21)
    		 {
    			 throw new UnderAgeException("Under Age Exception ");
    			 
    		 }
    		 else 
    		 {
    			     			 
    			 totalPrice+=(8*alcohalCount);

				 totalPrice+= getTax(totalPrice, "AZ");
    			 
    		 }    		     		 
    	 }
    	 else if(frozenCount>0)
    	 {
    		 
    	  totalPrice+=(frozenCount*5);
    		     		     		 
    	 }    		    	 
    	     	     	     	
        return totalPrice; //implement me, will be important for assignment 4 (nothing to do here for assignment 3)
    }

    // calculates how much was saved in the current shopping cart based on the deals, returns the saved amount
    // throws exception if alcohol is bought from underage person
    // TODO: Create node graph for this method in assign 4: create white box tests and fix the method, reach at least 98% coverage
    public int Amount_saved() throws UnderAgeException {
        int subTotal = 0;
        int costAfterSavings = 0;

        double produce_counter = 0;
        int alcoholCounter = 0;
        int frozenFoodCounter = 0;
        int dairyCounter = 0;

        for(int i = 0; i < cart.size(); i++) {
            subTotal += cart.get(i).getCost();
            costAfterSavings =costAfterSavings+cart.get(i).getCost();

            if (cart.get(i).getClass().toString() == Produce.class.toString()) {
                produce_counter++;

                if (produce_counter >= 3) {
                    costAfterSavings -= 1;
                    produce_counter = 0;
                }
            }
            else if (cart.get(i).getClass().toString()==Alcohol.class.toString()) {
                alcoholCounter++;
                if (userAge < 21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }
            }
            else if (cart.get(i).getClass().toString() == FrozenFood.class.toString()) {
                frozenFoodCounter++;
            }
            else if (cart.get(i).getClass().toString() == FrozenFood.class.toString())
                dairyCounter++;

            if (alcoholCounter >= 1 && frozenFoodCounter >= 1) {
                 costAfterSavings = costAfterSavings + 3;
                 alcoholCounter--;
                 frozenFoodCounter--;
            }
        }

        return subTotal - costAfterSavings;
    }

    // Gets the tax based on state and the total
    public double getTax(double totalBT, String twoLetterUSStateAbbreviation) {
        double newTotal = 0;
        switch (twoLetterUSStateAbbreviation) {
            case "AZ":
                newTotal = totalBT * .08;
                break;
            case "CA":
                newTotal = totalBT * .09;
                break;
            case "NY":
                newTotal = totalBT * .1;
            case "CO":
                newTotal = totalBT * .07;
                break;
            default:
                return totalBT;
        }
        return newTotal;
    }

    public void addItem(Product np) {
      cart.add(np);
    }

    public boolean RemoveItem(Product productToRemove)
    {
    		boolean test = false;
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i) == productToRemove) {
                 cart.remove(i);
                 test = true;
                 return test;
            }
        }
        return false;
    }

    public Cart(int age) {
        userAge = age;
        cart = new ArrayList<Product>();
    }
}
