### Problem 1
> ---
> Given that you have a bare minimum Netty Server available with you, configure it to better leverage the event notification capabilities provided by the underlying OS.
> 
> **Note:** *While doing the above, keep in mind that your server's program should run seamlessly on any given OS and not just a specific OS.*    
> 
### Problem 2
> ---
> Given that you have a _Flux_ of _100_ items with you, _throttle_ it in such a way that it should emit only _10_ items every _10_ seconds.  

### Problem 3
> ---
> You have been recently hired as an SDE at Amazon and are currently assigned to **Kindle Team.**
> 
> Your team caters to Amazon's customers who buy & read books online.
> 
> As part of a product revamp, your team will be building the following new features into Kindle:
> 
> 1. Allow the users to view all the books that they have **purchased** online.
>
> 2. Allow the users to temporarily **checkout** books and read them without having to buy them.
> 
> As an SDE, your job is create all the necessary APIs to support the above features.
 
### Problem 4
>---
> We have static functions getGoodsProducts, getPantryProducts and defaultProducts in the Product Factory, which return Flux of type ProductModel.
> 
> Complete the searchProducts function in Products Service.
> 
> It takes a choice of productType as input
> and returns all products available from getGoodsProducts and getPantryProducts that are of the same productType. 
> 
> In case none of such products exist it returns the values from defaultProducts.