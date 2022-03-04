Feature: Demoblaze-Cart

  @test
  Scenario:Check Cart value
    Given The navigation through product category is working
    When I select 'Sony vaio i5' and click add to cart
    And I select 'Dell i7 8gb' and click add to cart
    And I delete "Dell i7 8gb" from cart
    Then I place order by filling form and clicking purchase
    And I capture purchase Id and Amount
    And I validate purchase amount equals expected.


