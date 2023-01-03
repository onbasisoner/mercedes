Feature: Task1

  Background: Login
    Given Check page url contains "hepsiburada.com"
    And Click "acceptCookie" element
    And Check "myAccount" field existence on page
    And Hover on "myAccount" element
    And Click "login" element
    And Check page title contains "Üye Giriş Sayfası"
    And Fill "UserNameTextBox" field with "sonerautomation@grr.la"
    And Click "LoginButton" element
    And Fill "PasswordTextBox" field with "Automation123"
    And Click "LoginButtonPasswordPage" element
    And Wait for given seconds 5
    And Check "myAccount" field existence on page


  Scenario: AddToBasket
    And Fill "searchBox" field with "Laptop"
    And Click Enter
    And Check page title contains "Laptop"
    And Hover on "firstProduct" element
    And Click "addToBasketButton" element
    And Check equality of "toastNotif" field inner text with "Ürün sepete eklendi"
    And Wait for given seconds 10
    And Hover on "thirdProduct" element
    And Click "addToBasketButton" element
    And Check equality of "toastNotif" field inner text with "Ürün sepete eklendi"
    And Click "gotoBasket" element
    And Click "increaseFirstProductButton" element
    And Check equality of "quantityofFirstProduct" field with "2"
    And Click "goToMainPage" element
    And Wait for given seconds 5
    And Hover on "myAccount" element
    And Click "logout" element

  Scenario: ChangeBirthDate
    And Hover on "myAccount" element
    And Click "accountInfo" element
    And Wait for given seconds 5
    And Check page title contains "Üyelik Bilgilerim"
    And Fill "birthdaySelection" field value with "04031991"
    And Click "updateButton" element
    And Check equality of "toastNotif" field inner text with "Bilgileriniz başarıyla güncellendi."
    And Click "logoutButton" element





