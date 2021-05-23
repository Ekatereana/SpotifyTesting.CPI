Feature: Spotify
  As a user
  I want to use spotify api to add album

  Scenario: when a user checks a existent album on spotify, spotify would respond '200'
    Given temporary vars
    When I get album by "12ViHXhXTAxPEDM1TYCxvF" from spotify api
    Then spotify responds with status 200 and name is equals "The Wrong Kind of War"

  Scenario: when a user checks try to add song into play back
    Given client credentials
    When I add new track to queue with url "https://open.spotify.com/track/0ZBC6Eg6DjZRhgUmFdtzGH?si=bda424b075994711"
    Then spotify responds with status 200
