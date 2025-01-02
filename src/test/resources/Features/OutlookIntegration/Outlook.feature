Feature: Outlook automation

  @LoginFunctionality
  Scenario: Login to Outlook
  Then User opens Url Outlook "https://outlook.office365.com/mail/"
  Given I am on the Outlook Pick an account page
  Then I pick the email account

  @FetchEmail
  Scenario Outline: Search with Subject and open email
    Given I navigate to Inbox from the Home page
    Then I fetch latest email in Inbox and open mail with "<emailSubject>" and download attachment
    Then I fetch Emails in Inbox with "<subject>" and open mail with "<mailSearch>"

    Examples:
      |emailSubject                 | subject                    | mailSearch |
      |The Enterprise App Bulletin  | Work From Home request     | Work       |



