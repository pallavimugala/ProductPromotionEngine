**Product Promotion Engine**

 * Create a Product Object

 * Create Cart object with count of Products (Input is taken as character array as per the problem description)

 * Create a generic Promotion Interface which can be leveraged to create multiple Promotion Types . _For example, we can create another class
 for Percentage Promotion in future_
   

** Scenarios considered while developing this
  
  * Single Item Promotions 
  * Combo Item Promotions
     > The model is generic to be able to add more single/combo item promotions in future

     > Once the promotion is applied on a specific item , cart is updated to make sure that a promotion will be mutually
      exclusive on a product
    
     > Best possible promotion is calculated by analyzing the best discountable amount

** FEW SAMPLE TEST SCENARIOS COVERED
  
CASE 1: A,1 , 
        B,1,
        C,1
       NO PROMOTION APPLIED (TOTAL AMOUNT: 50+30+20 = 100)

CASE 2: A,5,
        B,5,
        C,1
        PROMOTIONS APPLICABLE: 3A, 2B, CD (In this case, best promotion is calculated by the recursive logic)
        TOTAL AMOUNT (130(3A) + 45(2B) + 2*50(2A) + 30(1B) +15(1C) == 370 )

** Run the gradle task test to run all the tests. The test summary will be as below**

CartItemsTest > Check if the cart items are valid. PASSED
CartItemsTest > Check if exception is thrown if invalid CART item is passed. PASSED
CartItemsTest > Check if the cart is empty PASSED
CartItemsTest > Count SKU occurences PASSED
CaseStudyExampleTests > Scenario A PASSED
CaseStudyExampleTests > [1] [A, B, C, A, A, A, A, B, B, B, B] PASSED
CaseStudyExampleTests > Scenario C PASSED
PromotionEngineImplTest > Calculate price when no Promotions are applied PASSED
PromotionEngineImplTest > Calculate price when single Promotions are applied PASSED
PromotionEngineImplTest > Calculate price when Combo Promotions are applied PASSED
PromotionEngineImplTest > Calculate price when multiple promotions are applied and they should be mutually exclusive PASSED
  

_Assumptions_
 * Inputs are sent in character arrays
 * Inorder for the code to be more efficient, greedy algorithm needs to be applied if there are
   100's of promotions on SKU's . This solution is simplistic approach which can be achieved in few hours of time
 

 

 
