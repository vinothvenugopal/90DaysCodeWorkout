Feature: CarWale test case day 1

Scenario: TC001_CarWale Test Case
Given open carwale site
And Click on Used
And Select the City as Chennai
And Select budget min 8L and max 12L and Click Search
And Select Cars with Photos under Only Show Cars With
And Select Manufacturer as Hyundai Creta
And Select Best Match as km low to high
And Validate the Cars are listed with KMs Low to High
And Add the least KM ran car to Wishlist
When Go to Wishlist and Click on More Details
Then Print all the details under Overview

 
