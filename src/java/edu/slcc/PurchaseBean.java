package edu.slcc;

import com.simplify.payments.PaymentsApi;
import javax.inject.Named;
import com.simplify.payments.PaymentsMap;
import com.simplify.payments.domain.CardToken;
import com.simplify.payments.domain.Payment;
import com.simplify.payments.exception.ApiException;
import com.simplify.payments.exception.InvalidRequestException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;

@Named(value = "pb")
@SessionScoped
public class PurchaseBean implements Serializable {

    
    final static String PUBLIC_KEY = "sbpb_YWJiNjdkYmEtMTVhYS00MDUwLWIzNjktYmRiMjJmYjM1Mzdm";
    final static String PRIVATE_KEY = "IRoWsZGvk2KNkchahYM+QpJDawBD11cfMXq9GQJdU/Z5YFFQL0ODSXAOkNtXTToq";
    
    private String ccNumber = "";
    private int CVV;
    private int experationMonth;
    private int experationYear;
    private String ccName = "";
    private int ccZip;
    private int chargeAmount;
    private String result = "";
    
    public PurchaseBean()
    {
        ccNumber = "";
        CVV = 0;
        experationMonth = 0;
        experationYear = 0;
        ccName = "";
        ccZip = 0;
        chargeAmount = 0;
        result = "";
    }

    public void processPayment() {
        try {
            PaymentsApi.PUBLIC_KEY = PUBLIC_KEY;
            PaymentsApi.PRIVATE_KEY = PRIVATE_KEY;

            Payment payment = Payment.create(new PaymentsMap()
                    .set("currency", "USD")
                    .set("token", generateToken()) // returned by JavaScript call
                    .set("amount", chargeAmount) // In cents e.g. $10.00
                    .set("description", "prod description"));

            if ("APPROVED".equals(payment.get("paymentStatus"))) {
                System.out.println("Payment approved");
                result = "<p style=\"color:green\";>Payment Approved</p>";
            } else {
                System.out.println("Payment Failed" + payment.toString());
                result = "<p style=\"color:red\">Payment Failed</p>";
            }
        } catch (ApiException e) {
            System.out.println("Message:    " + e.getMessage());
            System.out.println("Reference:  " + e.getReference());
            System.out.println("Error code: " + e.getErrorCode());
            if (e instanceof InvalidRequestException) {
                InvalidRequestException e2 = (InvalidRequestException) e;
                if (e2.hasFieldErrors()) {
                    for (InvalidRequestException.FieldError fe : e2.getFieldErrors()) {
                        System.out.println(fe.getFieldName()
                                + ": '" + fe.getMessage()
                                + "' (" + fe.getErrorCode() + ")");
                    }
                }
            }
            result = "<p style=\"color:red\">Payment Failed</p>";
        }
    }

    public String generateToken() {
        try {
            PaymentsApi.PUBLIC_KEY = PUBLIC_KEY;
            PaymentsApi.PRIVATE_KEY = PRIVATE_KEY;
            
            CardToken cardToken = CardToken.create(new PaymentsMap()
                    .set("card.name", ccName)
                    .set("card.zip", ccZip)
                    .set("card.cvc", CVV)
                    .set("card.expMonth", experationMonth)
                    .set("card.expYear", experationYear)
                    .set("card.number", ccNumber));
            return cardToken.get("id").toString();
        } catch (ApiException e) {
            System.out.println("Message:    " + e.getMessage());
            System.out.println("Reference:  " + e.getReference());
            System.out.println("Error code: " + e.getErrorCode());
            if (e instanceof InvalidRequestException) {
                InvalidRequestException e2 = (InvalidRequestException) e;
                if (e2.hasFieldErrors()) {
                    for (InvalidRequestException.FieldError fe : e2.getFieldErrors()) {
                        System.out.println(fe.getFieldName()
                                + ": '" + fe.getMessage()
                                + "' (" + fe.getErrorCode() + ")");
                    }
                }
            }
            return null;
        }
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public int getExperationMonth() {
        return experationMonth;
    }

    public void setExperationMonth(int experationMonth) {
        this.experationMonth = experationMonth;
    }

    public int getExperationYear() {
        return experationYear;
    }

    public void setExperationYear(int experationYear) {
        this.experationYear = experationYear;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public int getCcZip() {
        return ccZip;
    }

    public void setCcZip(int ccZip) {
        this.ccZip = ccZip;
    }
    
    public int getChargeAmount()
    {
        return chargeAmount;
    }
    
    public void setChargeAmount(int chargeAmount)
    {
        this.chargeAmount = chargeAmount;
    }
    
    public String chargeAmountToString()
    {
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
        String s = n.format(chargeAmount / 100.0);
        return s;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
