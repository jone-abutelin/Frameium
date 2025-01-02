@TAG
  Feature: chatwith chatgpt
    @Egypt
    Scenario: Submit and Track your case
      Given  User opens Url "https://www.tamm.abudhabi/"
      Then click on the support option
      Then click on the tammchatbot option
      Then choose the submit and track your case
      Then click the submit case and fill the Application form
      And click the submit option
      And click Confirm and send
      And click the Yes button to exit the chat.
      Then user navigates to the Home page and see the heading 'How Can We Help You?'
