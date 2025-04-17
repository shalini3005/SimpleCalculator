package src;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SimpleCalculator extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Input fields
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();

        // Dropdown for operation
        ComboBox<String> operatorBox = new ComboBox<>();
        operatorBox.getItems().addAll("+", "-", "*", "/");
        operatorBox.setValue("+");

        // Result label
        Label resultLabel = new Label("Result: ");

        // Calculate button
        Button calculateButton = new Button("Calculate");

        calculateButton.setOnAction(e -> {
            try {
                double num1 = Double.parseDouble(textField1.getText());
                double num2 = Double.parseDouble(textField2.getText());
                String operator = operatorBox.getValue();
                double result;

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("Cannot divide by zero");
                        }
                        result = num1 / num2;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operator");
                }

                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Enter valid numbers");
            } catch (ArithmeticException | IllegalArgumentException ex) {
                resultLabel.setText(ex.getMessage());
            }
        });

        // Layout
        VBox root = new VBox(10,
                new Label("Number 1:"), textField1,
                new Label("Number 2:"), textField2,
                new Label("Operator:"), operatorBox,
                calculateButton, resultLabel
        );
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Simple Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
