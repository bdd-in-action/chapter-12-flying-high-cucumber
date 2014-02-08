@status-points
Feature: Frequent Flyer status is calculated based on points
  As a Frequent Flyer member
  I want my status to be upgraded as soon as I earn enough points
  So that I can benefit from my higher status sooner

Scenario Outline:
  Given Joe is a <initialStatus> Frequent Flyer member
  And he has <initialStatusPoints> status points
  When he earns <extraPoints> extra status points
  Then he should have a status of <finalStatus>

  Examples: Status points required for each level
    | initialStatus | initialStatusPoints | extraPoints | finalStatus |
    | Bronze        | 0                   | 300         | Silver      |
    | Silver        | 0                   | 700         | Gold        |
    | Gold          | 0                   | 1500        | Platinum    |

  Examples: Earning status in small chunks
    | initialStatus | initialStatusPoints | extraPoints | finalStatus |
    | Bronze        | 200                 | 99          | Bronze      |
    | Bronze        | 200                 | 100         | Silver      |
    | Silver        | 600                 | 99          | Silver      |
    | Silver        | 600                 | 100         | Gold        |
    | Gold          | 1400                | 99          | Gold        |
    | Gold          | 1400                | 100         | Platinum    |
    | Platinum      | 2000                | 100         | Platinum    |
