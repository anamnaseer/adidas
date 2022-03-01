Feature: Pet

  @test
  Scenario:Get "available" pets. Assert expected result
    Given I send get request to fetch 'available' type pets
    Then We validate the 200 response was send

  @test
  Scenario:Post a new available pet to the store. Assert new pet added
    Given I send post request to add new 'available' type pet with id '4321'
    Then We validate the added pet response

  @test
  Scenario:Update this pet status to "sold". Assert status updated.
    Given I send put request to update pet with id '4321' to 'sold'
    Then We validate the added pet response updated to 'sold' with id '4321'

  @test
  Scenario:Delete this pet. Assert deletion
    Given I send delete request to delete pet with id '4321'
    Then  We validate the 404 response was send






