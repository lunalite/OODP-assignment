####OODP-assignment
Remember to save project/package name as CZ2002_Assignment.
Presently, the IDE we are using is Netbeans.

---
####Edit 08/04/16 - HD, J
- Changed all roomStatus to enum type
- Created a guestMgr to handle all guest details
- Added food items

Required
- Adding the storing of data from file array to XML
- Change roomType to enum instead of a class
- Converting randomised room status to XML file upload
- ~~Need to implement the creation of order code for each room service order under RoomServiceOrder.java~~
- ~~Still require for more food items and better description~~
- Payment method for checking in/out not implemented yet
- ~~Create~~/Update/~~Search~~ guests detail (Search by name using keyword/s) 
- Create/Update/Remove/Print reservation
- Create/Update rooms details (include setting status like ‘Under Maintenance”,
- ~~Create/Update/Remove room service menu items.~~
- Check room availability
- Room Check-in (for ~~walk-in~~ or reservation)
- Room Check-out and print bill invoice (with breakdowns on days of stay, room service order items and its total, tax and total amount)
- ~~Probably include enum usage for roomStatus so that it will induce less error (Can someone implement this?)~~
- ~~Entering room service orders~~ 
- ~~list menu items for selection~~
- ~~Required to change the room numbers to 4 int digits instead of a string according to requirements from assignment~~
- ~~Print Room Status statistic report by~~
- ~~room type occupancy rate~~
- ~~room status (status type and list the room number)~~



####Edit 07/04/16 -HD
- Added create for guest details while saving to .XML file
- Created a XMLMgr to deal with getting and storing data to .XML file.
- Added creation/update and removal for room service items

Required
- Adding the storing of data from file array to XML
- Converting randomised room status to XML file upload
- ~~Need to implement the creation of order code for each room service order under RoomServiceOrder.java~~
- Still require for more food items and better description
- Payment method for checking in/out not implemented yet
- ~~Create~~/Update/~~Search~~ guests detail (Search by name using keyword/s) 
- Create/Update/Remove/Print reservation
- Create/Update rooms details (include setting status like ‘Under Maintenance”,
- ~~Create/Update/Remove room service menu items.~~
- Check room availability
- Room Check-in (for ~~walk-in~~ or reservation)
- Room Check-out and print bill invoice (with breakdowns on days of stay, room service order items and its total, tax and total amount)
- Probably include enum usage for roomStatus so that it will induce less error (Can someone implement this?)
- ~~Entering room service orders~~ 
- ~~list menu items for selection~~
- ~~Required to change the room numbers to 4 int digits instead of a string according to requirements from assignment~~
- ~~Print Room Status statistic report by~~
- ~~room type occupancy rate~~
- ~~room status (status type and list the room number)~~




####Edit 06/04/16 - HD
- Changed roomNo string to 0203 instead of 02-03 according to requirements.
- Added payment class instantiation for those already occupied in currentDay
- Added order code implementation for room service
- Added checking of orders for room service
- Added removing of orders for room service
- Added enum type for roomServiceOrderStatus for safety reason
- Added XML parser for guest details
- implemented searching for guest name method

Required
- ~~Need to implement the creation of order code for each room service order under RoomServiceOrder.java~~
- Still require for more food items and better description
- Payment method for checking in/out not implemented yet
- Create/Update/~~Search guests~~ detail (Search by name using keyword/s) 
- Create/Update/Remove/Print reservation
- Create/Update rooms details (include setting status like ‘Under Maintenance”,
- Create/Update/Remove room service menu items.
- Check room availability
- Room Check-in (for walk-in or reservation)
- Room Check-out and print bill invoice (with breakdowns on days of stay, room service order items and its total, tax and total amount)
- Probably include enum usage for roomStatus so that it will induce less error (Can someone implement this?)
- ~~Entering room service orders~~ 
- ~~list menu items for selection~~
- ~~Required to change the room numbers to 4 int digits instead of a string according to requirements from assignment~~
- ~~Print Room Status statistic report by~~
- ~~room type occupancy rate~~
- ~~room status (status type and list the room number)~~

####Edit 05/04/16 - HD
- Added error checking for the scanner for room number for check in such that it is within system (Added function for that)
- Added error checking for room Service for room number such that it is occupied within current day.

Required
- Need to implement the creation of order code for each room service order under RoomServiceOrder.java
- Still require for more food items and better description
- Payment method for checking in/out not implemented yet
- Probably include enum usage for roomStatus so that it will induce less error
- Required to change the room numbers to 4 int digits instead of a string according to requirements from assignment
- Create/Update/Search guests detail (Search by name using keyword/s) 
- Create/Update/Remove/Print reservation
- Create/Update rooms details (include setting status like ‘Under Maintenance”,
- Entering room service orders - list menu items for selection
- Create/Update/Remove room service menu items.
- Check room availability
- Room Check-in (for walk-in or reservation)
- Room Check-out and print bill invoice (with breakdowns on days of stay, room service order items and its total, tax and total amount)
- ~~Print Room Status statistic report by~~
- ~~room type occupancy rate~~
- ~~room status (status type and list the room number)~~


####Edit 04/04/16 - HD
- Changed methods to static methods for MenuMgr.java
- showMenu() and getItemDescription() methods of MenuMgr are now called in roomServiceMgr.java
- Change date type for dateTime to Date instead of int for RoomServiceOrder.java
- Payment method is implemented for room service (payment object list stored under roomMgr.java for all rooms)
- Basket for items is added for ordering of items in main class
- implemented the string to int converter for ease of usage (room numbers) within RoomMgr.java

Required
- Need to implement room checking in for certain room number under Room.java
- Need to implement the creation of order code for each room service order under RoomServiceOrder.java
- Still require for more food items and better description
- Check out not implemented yet
- Payment method for checking in/out not implemented yet


####Edit 03/04/16 - HD
Under CZ2002_Assignment.java - added for case 3 menuMgr.
  
1. Still missing payment method
2. Missing the adding to basket for items ordered.
3. Still require for more food items and better description.

Updated files: MenuMgr.java, CZ2002_Assignment.java, Item.java

####Edit 02/04/16 - HD
Uploaded to github. Easier for us to work on the codes and commit releases.

Base codes are from Sam.

