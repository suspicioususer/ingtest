Feature: Submit Username

  Scenario Outline: Submit Username Scenario
    Given create main activity
    When input username <username>
    And press submit button
    Then log

    Examples:
      |username      |
      |google        |
      |suspicioususer|