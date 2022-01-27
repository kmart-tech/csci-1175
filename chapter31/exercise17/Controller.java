import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.text.ParseException;

public class Controller {
    public TextField tfInvestmentAmount, tfAnnualInterestRate, tfNumberOfYears, tfFutureValue;

    public void calculateAction(ActionEvent actionEvent) {
        try {
            double amount = Double.parseDouble(tfInvestmentAmount.getText());
            double monthlyRate = Double.parseDouble(tfAnnualInterestRate.getText()) / (12 * 100);
            double years = Double.parseDouble(tfNumberOfYears.getText());

            double futureValue = amount * Math.pow((1 + monthlyRate), (years * 12.0));
            tfFutureValue.setText(String.format("$%.2f", futureValue));
        }
        catch (NumberFormatException ex) {
            tfFutureValue.setText("Invalid Entry");
        }
    }

    public void exitAction(ActionEvent actionEvent) {
        Platform.exit();
    }
}
