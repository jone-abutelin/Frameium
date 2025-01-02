@PageValidations @ArabicText
Feature: Verify Items Under Menu - Arabic

  @ArabicText
  @VerifyTextinArabic
  Scenario: Verify that items under Our Market > Main Market Section appears correctly - Arabic
    Then User opens the Url "https://www.saudiexchange.sa/wps/portal/saudiexchange/home/"
    Then Click on the Arabic Convert button
    Then Click on the Arabic Our Markets dropdown menu
    Then Verify Items under Our Market Section > Main Market Section in Arabic:
      | السوق الرئيسية           |
      | جميع أسهم السوق الرئيسية |
      | ملخص السوق               |
      | أداء السوق               |
      | مؤشرات السوق             |
      | الأسعار الافتراضية       |
      | معلومات التداول للمصدرين |


  @ArabicText
  @VerifyTextinArabicNeg
  Scenario: Verify that items under Our Market > Main Market Section appears correctly - Arabic (Negative)
    Then User opens the Url "https://www.saudiexchange.sa/wps/portal/saudiexchange/home/"
    Then Click on the Arabic Convert button
    Then Click on the Arabic Our Markets dropdown menu
    Then Verify Items under Our Market Section > Main Market Section in Arabic:
      | السوق الرئيسية           |
      | جميع أسهم السوق الرئيسية |
      | ملخص السولسوق            |
      | أداء السوق               |
      | مؤشرات السوق             |
      | الأسعار الافتراضية       |
      | معلومات التداول للمصدرين |