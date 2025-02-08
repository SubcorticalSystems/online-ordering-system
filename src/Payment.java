//import java.text.NumberFormat;
//import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {
    public Payment(OrderProcessor.PaymentType paymentType, String accountNumber, String bankOrIssuer, double paymentAmount, Date paymentDate) {
        this.paymentType = paymentType;
        this.accountNumber = accountNumber;
        this.bankOrIssuer = bankOrIssuer;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }
    //NumberFormat formatter = NumberFormat.getCurrencyInstance();
    OrderProcessor.PaymentType paymentType;
    String accountNumber;
    String bankOrIssuer;
    double paymentAmount;
    Date paymentDate;

    public OrderProcessor.PaymentType getPaymentType() {
        return paymentType;
    }

    public String getPartialAccountNumber() {
        return "**** " + accountNumber.substring(accountNumber.length() - 4);
    }



    /*
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy ");

    public String getBankOrIssuer() {
        return bankOrIssuer;
    }

    public String getFormattedPaymentAmount() {
        return formatter.format(paymentAmount);
    }

    public String getFormattedPaymentDate() {
        return dateFormat.format(paymentDate);
    }

     */
}
