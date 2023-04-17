package org.example.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.example.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


import static org.example.stepDefinitions.Hooks.driver;

public class D02_FilterAndSort {
    P01_Homepage homepage = new P01_Homepage();
    P03_VideoGames videoGames = new P03_VideoGames();
    P04_Results results = new P04_Results();
    P05_SelectedITem selectedITem = new P05_SelectedITem();
    P06_AddToCart addToCart=new P06_AddToCart();
    P07_NewShippingAddress newShippingAddress=new P07_NewShippingAddress();
    SoftAssert softAssert=new SoftAssert();
    Utilities util = new Utilities();

    @Given("the user clicks on the 'All' button in the navigation bar")
    public void selectAllInNavigationBar() {
        homepage.navigateAll.click();

    }

    @And("the user selects 'See All'")
    public void userSelectSeeAll() {
        util.scrollToElement(homepage.seeAll);
        homepage.seeAll.click();
    }

    @And("the user selects 'Video Games' and then selects 'All Video Games'")
    public void userSelectVideoGamesThenSelectAllVideoGames() {
        util.clickableFluentWait(homepage.videoGames, 10, 3);
        homepage.videoGames.click();
        util.clickableFluentWait(homepage.allVideoGames, 10, 3);
        homepage.allVideoGames.click();

    }

    @When("the user applies the 'Free Shipping' filter and adds the 'Condition: New' filter")
    public void userSelectFreeShippingFilterAndAddTheFilterOfConditionNew() {
        util.visabilityFluentWait(videoGames.newConditionFilter, 10, 3);
        util.selectCheckbox(videoGames.freeShippingCheckBox);
        util.clickableFluentWait(videoGames.newConditionFilter, 10, 3);
        videoGames.newConditionFilter.click();
    }

    @When("the user selects the 'Sort By' list and selects a type of sorting")
    public void userSelectTheSortByListAndSelectTypeOfSorting() throws InterruptedException {
        util.sortByDropdownList(results.sortByList,"price-desc-rank");
    }

    @And("the user adds all products that are less than {int} EGP")
    public void userAddAllProductsLessThanEGP(int price) {
        while (true) {
            List<Integer> prices = new ArrayList<>();
            List<WebElement> priceList = results.priceList();
            int priceListSize = priceList.size();
            for (int i = 0; i < priceListSize; i++) {
                String itemPrice = priceList.get(i).getText().replace(",", "");
                try {
                    int priceValue = Integer.parseInt(itemPrice);
                    prices.add(priceValue);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
            boolean hasLessThanPrice = false;
            for (int priceValue : prices) {
                if (priceValue < price) {
                    hasLessThanPrice = true;
                    break;
                }
            }
            if (hasLessThanPrice) {
                List<WebElement> items = priceList;
                for (int i = 0; i < items.size(); i++) {
                    int priceValue = prices.get(i);
                    if (priceValue < price) {
                        WebElement item = items.get(i);
                        util.visabilityFluentWait(item, 10, 3);
                        item.click();
                        util.clickableFluentWait(selectedITem.addToCartBTN, 10, 3);
                        selectedITem.addToCartBTN.click();
                        String expectedResult=" Added to Cart";
                        String actualResult=addToCart.addToCartLabel.getText();
                        softAssert.assertTrue(expectedResult.contains(actualResult));
                        softAssert.assertAll();
                        driver.navigate().back();
                        driver.navigate().back();
                    }
                }
            } else {
                boolean isNextPagePresent = false;
                try {
                    WebElement nextPage = results.nextPage;
                    if (nextPage.isDisplayed()) {
                        nextPage.click();
                        isNextPagePresent = true;
                    }
                } catch (StaleElementReferenceException | NoSuchElementException e) {
                    WebElement nextPage = driver.findElement(By.cssSelector("a.next"));
                    if (nextPage.isDisplayed()) {
                        nextPage.click();
                        isNextPagePresent = true;
                    }
                }
                if (!isNextPagePresent) {
                    break;
                }
            }
            prices.clear();
        }
    }
    @When("the user fills in the shipping address and payment method details as follows:")
    public void fillInShippingAddressAndPaymentMethodDetails(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String name = data.get(0).get("Full Name");
        String phone = data.get(0).get("Phone");
        String streetAddress = data.get(0).get("Street Address");
        String buildingNumber = data.get(0).get("Building Number");
        String city = data.get(0).get("City");
        String district = data.get(0).get("District");
        String landMark = data.get(0).get("Land Mark");
        String cityLocator="//li[contains(text(), 'Nasr City')]";
        String districtLocator="//ul[@class='autoCompleteResult']//li[text()='Al Manteqah as Sadesah']\n";
        addToCart.proceedToBuyBTN.click();
        newShippingAddress.fullName.sendKeys(name);
        newShippingAddress.mobileNo.sendKeys(phone);
        newShippingAddress.streetName.sendKeys(streetAddress);
        newShippingAddress.buildingNo.sendKeys(buildingNumber);
        util.dynamicDropdownList(newShippingAddress.cityDropDownList,city,By.xpath(cityLocator),1);
        util.dynamicDropdownList(newShippingAddress.districtDropDown,district,By.xpath(districtLocator),5);
        newShippingAddress.landMark.sendKeys(landMark);
        newShippingAddress.radioBTN.click();
        newShippingAddress.addAddressBTN.click();
        util.visabilityFluentWait(newShippingAddress.addCard,10,3);
        newShippingAddress.addCard.click();
        newShippingAddress.cancelBTN.click();
    }

//        public void userAddAllProductsLessThanEGP(int price) {
//
//        while (true) {
//            List<Integer> prices = new ArrayList<>();
//
//            // Get all the prices on the current page and add them to the list
//            List<WebElement> priceList = results.priceList();
//            int priceListSize = priceList.size();
//            for (int i = 0; i < priceListSize; i++) {
//                String itemPrice = priceList.get(i).getText().replace(",", "");
//
//                try {
//                    int priceValue = Integer.parseInt(itemPrice);
//                    prices.add(priceValue);
//                    System.out.println(prices + "prices");
//                } catch (NumberFormatException e) {
//                    // Skip this item if it cannot be parsed as an integer
//                    continue;
//                }
//            }
//
//            // Check if any of the prices on the current page are less than 15000 EGP
//            boolean hasLessThanPrice = false;
//            for (int priceValue : prices) {
//                if (priceValue < 15000) {
//                    hasLessThanPrice = true;
//                    System.out.println(prices + "less than 15000");
//                    break;
//                }
//            }
//
//            // Add all the items to cart if their price is less than 15000 EGP
//            if (hasLessThanPrice) {
//                System.out.println(priceList);
//                List<WebElement> items = priceList;
//                for (int i = 0; i < items.size(); i++) {
//                    int priceValue = prices.get(i);
//                    if (priceValue < 15000) {
//                        WebElement item = items.get(i);System.out.println(priceValue + "selected items");
//                        util.visabilityFluentWait(item, 10, 3);
//                        item.click();
//                        util.clickableFluentWait(selectedITem.addToCartBTN, 10, 3);
//                        selectedITem.addToCartBTN.click();
//
//                        //softassert
//                        String expectedResult=" Added to Cart";
//                        String actualResult=addToCart.addToCartLabel.getText();
//                        softAssert.assertTrue(expectedResult.contains(actualResult));
//                        softAssert.assertAll();
//
//                        driver.navigate().back();
//                        driver.navigate().back();
//                        break; // Exit the loop and proceed to the next item
//                    }
//                }
//
//            } else {
//                // There are no items below 15000 EGP on this page, so break out of the loop and proceed to the next page
//                break;
//            }
//
//            // Move on to the next page if there are more pages
//            try {
//                WebElement nextPage = results.nextPage;
//                if (nextPage.isDisplayed()) {
//                    nextPage.click();
//                } else {
//                    break; // Stop searching if there are no more pages
//                }
//            } catch (StaleElementReferenceException e) {
//                // Find the next page element again if it becomes stale
//                WebElement nextPage = driver.findElement(By.cssSelector("a.next"));
//                if (nextPage.isDisplayed()) {
//                    nextPage.click();
//                } else {
//                    break; // Stop searching if there are no more pages
//                }
//            }
//
//            // Clear the list of prices for the next page
//            prices.clear();
//
////            while (true) {
////                List<Integer> prices = new ArrayList<>();
////
////                // Get all the prices on the current page and add them to the list
////                List<WebElement> priceList = results.priceList();
////                int priceListSize = priceList.size();
////                for (int i = 0; i < priceListSize; i++) {
////                    String itemPrice = priceList.get(i).getText().replace(",", "");
////
////                    try {
////                        int priceValue = Integer.parseInt(itemPrice);
////                        prices.add(priceValue);
////                    } catch (NumberFormatException e) {
////                        // Skip this item if it cannot be parsed as an integer
////                        continue;
////                    }
////                }
////
////                // Check if any of the prices on the current page are less than the given price
////                boolean hasLessThanPrice = false;
////                for (int priceValue : prices) {
////                    if (priceValue < price) {
////                        hasLessThanPrice = true;
////                        break;
////                    }
////                }
////
////                // Add all the items to cart if their price is less than the given price
////                if (hasLessThanPrice) {
////                    List<WebElement> items = priceList;
////                    for (int i = 0; i < items.size(); i++) {
////                        int priceValue = prices.get(i);
////                        if (priceValue < price) {
////                            WebElement item = items.get(i);
////                            util.visabilityFluentWait(item, 10, 3);
////                            item.click();
////                            util.clickableFluentWait(selectedITem.addToCartBTN, 10, 3);
////                            selectedITem.addToCartBTN.click();
////                            driver.navigate().back();
////                            driver.navigate().back();
////                        }
////                    }
////                }
////
////                // Move on to the next page if there are more pages
////                try {
////                    WebElement nextPage = results.nextPage;
////                    if (nextPage.isDisplayed()) {
////                        nextPage.click();
////                    } else {
////                        break; // Stop searching if there are no more pages
////                    }
////                } catch (StaleElementReferenceException e) {
////                    // Find the next page element again if it becomes stale
////                    WebElement nextPage = driver.findElement(By.cssSelector("a.next"));
////                    if (nextPage.isDisplayed()) {
////                        nextPage.click();
////                    } else {
////                        break; // Stop searching if there are no more pages
////                    }
////                }
////
////                // Clear the list of prices for the next page
////                prices.clear();
////            }
////        }
//        }
//    }


}




//        while (true) {
//            List<Integer> prices = new ArrayList<>();
//
//            // Get all the prices on the current page and add them to the list
//            List<WebElement> priceList = results.priceList();
//            int priceListSize = priceList.size();
//            for (int i = 0; i < priceListSize; i++) {
//                String itemPrice = priceList.get(i).getText().replace(",", "");
//
//                try {
//                    int priceValue = Integer.parseInt(itemPrice);
//                    prices.add(priceValue);
//                    System.out.println(prices + "prices");
//                } catch (NumberFormatException e) {
//                    // Skip this item if it cannot be parsed as an integer
//                    continue;
//                }
//            }
//
//            // Check if any of the prices on the current page are less than 15000 EGP
//            boolean hasLessThanPrice = false;
//            for (int priceValue : prices) {
//                if (priceValue < 15000) {
//                    hasLessThanPrice = true;
//                    break;
//                }
//            }
//
//            // Add all the items to cart if their price is less than 15000 EGP
//            if (hasLessThanPrice) {
//                List<WebElement> items = priceList;
//                for (int i = 0; i < items.size(); i++) {
//                    int priceValue = prices.get(i);
//                    if (priceValue < 15000) {
//                        WebElement item = items.get(i);
//                        System.out.println(item);
//                        util.visabilityFluentWait(item, 10, 3);
//                        item.click();
//                        util.clickableFluentWait(selectedITem.addToCartBTN, 10, 3);
//                        selectedITem.addToCartBTN.click();
//                        driver.navigate().back();
//                        driver.navigate().back();
//                    }
//                }
//            }
//
//            // Move on to the next page if there are more pages and no items less than 15000 EGP on the current page
//            if (!hasLessThanPrice) {
//                try {
//                    WebElement nextPage = results.nextPage;
//                    if (nextPage.isDisplayed()) {
//                        nextPage.click();
//                    } else {
//                        break; // Stop searching if there are no more pages
//                    }
//                } catch (StaleElementReferenceException e) {
//                    // Find the next page element again if it becomes stale
//                    WebElement nextPage = driver.findElement(By.cssSelector("a.next"));
//                    if (nextPage.isDisplayed()) {
//                        nextPage.click();
//                    } else {
//                        break; // Stop searching if there are no more pages
//                    }
//                }
//            } else {
//                // There are items less than 15000 EGP on the current page, so don't proceed to the next page
//                break;
//            }
//
//            // Clear the list of prices for the next page
//            prices.clear();
//        }
//    }


//    @And("user add all products less than {int} EGP")
//    public void userAddAllProductsLessThanEGP(int price) {
//        while (true) {
//            List<Integer> prices = new ArrayList<>();
//
//            // Get all the prices on the current page and add them to the list
//            List<WebElement> priceList = results.priceList();
//            int priceListSize = priceList.size();
//            for (int i = 0; i < priceListSize; i++) {
//                String itemPrice = priceList.get(i).getText().replace(",", "");
//
//                try {
//                    int priceValue = Integer.parseInt(itemPrice);
//                    prices.add(priceValue);
//                    System.out.println(prices +"prices");
//                } catch (NumberFormatException e) {
//                    // Skip this item if it cannot be parsed as an integer
//                    continue;
//                }
//            }
//
//            // Check if any of the prices on the current page are less than 15000 EGP
//            boolean hasLessThanPrice = false;
//            for (int priceValue : prices) {
//                if (priceValue < 15000) {
//                    hasLessThanPrice = true;
//                    System.out.println(prices+"less than 15000");
//                    break;
//                }
//            }
//
//            // Add all the items to cart if their price is less than 15000 EGP
//            if (hasLessThanPrice) {
//                System.out.println(priceList);
//                List<WebElement> items = priceList;
//                for (int i = 0; i < items.size(); i++) {
//                    int priceValue = prices.get(i);
//                    if (priceValue < 15000) {
//                        WebElement item = items.get(i);
//                        System.out.println(priceValue+"selected items");
//                        util.visabilityFluentWait(item, 10, 3);
//                        item.click();
//                        util.clickableFluentWait(selectedITem.addToCartBTN, 10, 3);
//                        selectedITem.addToCartBTN.click();
//                        driver.navigate().back();
//                        driver.navigate().back();
//                        break; // Exit the loop and proceed to the next item
//                    }
//                }
//
//            } else {
//                // There are no items below 15000 EGP on this page, so break out of the loop and proceed to the next page
//                break;
//            }
//
//            // Move on to the next page if there are more pages
//            try {
//                WebElement nextPage = results.nextPage;
//                if (nextPage.isDisplayed()) {
//                    nextPage.click();
//                } else {
//                    break; // Stop searching if there are no more pages
//                }
//            } catch (StaleElementReferenceException e) {
//                // Find the next page element again if it becomes stale
//                WebElement nextPage = driver.findElement(By.cssSelector("a.next"));
//                if (nextPage.isDisplayed()) {
//                    nextPage.click();
//                } else {
//                    break; // Stop searching if there are no more pages
//                }
//            }
//
//            // Clear the list of prices for the next page
//            prices.clear();
//        }
//    }



//    @And("user add all products less than {int} EGP")
//
//    public void userAddAllProductsLessThanKEGp(int price) {
//        List<Integer> prices = new ArrayList<>();
//        while (true) {
//            // Get all the prices on the current page and add them to the list
//            int priceListSize = results.priceList().size();
//            for (int i = 0; i < priceListSize; i++) {
//                String itemPrice = results.priceList().get(i).getText().replace(",", "");
//
//                try {
//                    int priceValue = Integer.parseInt(itemPrice);
//                    prices.add(priceValue);
//                } catch (NumberFormatException e) {
//                    // Skip this item if it cannot be parsed as an integer
//                    continue;
//                }
//            }
//
//            // Check if any of the prices on the current page are less than the given price
//            boolean hasLessThanPrice = false;
//            for (int priceValue : prices) {
//                if (priceValue < price) {
//                    hasLessThanPrice = true;
//                    break;
//                }
//            }
//
//            // Add all the prices if there are any less than the given price to cart
//            if (hasLessThanPrice) {
//                System.out.println(results.priceList());
////                List<WebElement> items = results.priceList(); // Assuming each item has a div with class "item" and text that includes the price value
////                for (int i = 0; i < items.size(); i++) {
////                    int priceValue = prices.get(i);
////                    if (priceValue < price) {
////                        util.visabilityFluentWait(items.get(i), 10, 3);
////                        items.get(i).click();
////                        util.clickableFluentWait(selectedITem.addToCartBTN, 10, 3);
////                        selectedITem.addToCartBTN.click();
////                    }
////                }
//            }
//
//            break; // Stop searching once we've found all the items less than the given price
//        }
//
//        // Move on to the next page if there are more pages
//
//        if (results.nextPage.isDisplayed()) {
//            results.nextPage.click();
//            prices.clear(); // Clear the list of prices for the next page
//        } else {
//           // break; // Stop searching if there are no more pages
//        }
//    }
