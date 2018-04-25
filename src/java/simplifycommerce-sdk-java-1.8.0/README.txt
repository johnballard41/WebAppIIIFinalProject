
                Java SDK for Simplify Commerce


  What is it?
  ------------

  A Java API to the Simplify Commerce payments platform.   If you have
  not already got an account sign up at https://www.simplify.com/commerce.


  Installation
  ------------

  The SDK is contained in the JAR file:

    lib/payments-sdk-java-1.8.0.jar

  All dependent JARs are contained in the lib directory.

  To use the SDK add all the Jars in the lib directory to your
  classpath.  The API is in the package com.simplify.package.

  Using the SDK
  --------------

  To run a payment though Simplify Commerce use the following
  code substituting your public and private API keys:

      PaymentsApi.PRIVATE_KEY = "YOUR_PRIVATE_API_KEY";
      PaymentsApi.PUBLIC_KEY = "YOUR_PUBLIC_API_KEY";

      Payment payment = Payment.create(new PaymentsMap()
                .set("currency", "USD")
                .set("card.cvc", "123")
                .set("card.expMonth", 11)
                .set("card.expYear", 13)
                .set("card.number", "5555555555554444")
                .set("amount", 1000) // In cents e.g. $10.00
                .set("description", "prod description"));
      System.out.println(payment);

  For more examples see https://www.simplify.com/commerce/docs/sdk/java.

  Version
  -------

  This is version 1.8.0 of the SDK.  For an up-to-date
  version check at https://www.simplify.com/commerce/docs/sdk/java.

  Licensing
  ---------

  Please see LICENSE.txt for details.

  Documentation
  -------------

  API documentation is available in the docs directory in HTML.  For more
  detailed information on the API with examples visit the online 
  documentation at https://www.simplify.com/commerce/docs/sdk/java.

  Support
  -------

  Please see https://www.simplify.com/commerce/docs/support for information.
  
  Copyright
  ---------

  Copyright (c) 2013 - 2018 MasterCard International Incorporated
  All rights reserved.

