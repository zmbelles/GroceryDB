# GroceryDB

### 1. Project Description
 This Database simulates a grocery store admin database that keeps track of the store's employees, inventory, discounts, shipment status, reward members, and departments.
### 2. Assumptions 
Assumption 1: This database assumes that the database will be tracked by corporate, eliminating the need to connect users to anything else 
Assumption 2: No employee will ever be paid more than $999,999.99/year  
Assumption 3: administrator will always type the correct query  
Assumption 4: User is running MySQL with a schema named 'term' created  
Assumption 5: User has set a localhost password on mysql to '!Fall2022' (to be fixed)  
### 3. Limitations 
1. further abstraction to apply in multiple database contexts  
2. the ability for administrators to make their own queries  
3. permission types when accessing the GUI  
4. Employees cannot add or edit inventory  
### 4. Cardinality and Relationships  
1. Employee works in Department:              Many to One  
2. Department has stock of Inventory:         One to Many  
3. Inventory is on Discount:                  One to One  
4. Inventory has a status of Shipment_Status: One to One  
5. Employee is a Member:                      One to One
### 5. How to Run  
1. Download this repository  
2. Create a new localhost with a password of !Fall2022 on MySQL Workbench  
3. Download MySQL Connector/J from https://dev.mysql.com/downloads/connector/j/
4. Add Connector/J to the projects build path, replacing what is currently there
4. run login.java using 'Admin' as username and 'Admin' as password
