import java.awt.event.*;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class MyJFrame extends JFrame implements ActionListener {
    JTextField firstNumber;
    JButton submitButton;
    JComboBox firstCurrency;

    JComboBox secondCurrency;
    JLabel resultLabel;
    double calcResult = 0;

    MyJFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        firstNumber = new JTextField(16);

        String[] currency = {"USD","AMD"};
        firstCurrency = new JComboBox(currency);
        firstCurrency.addActionListener(this);

        secondCurrency = new JComboBox(currency);
        secondCurrency.addActionListener(this);

        submitButton = new JButton("convert");
        submitButton.addActionListener(this);

        resultLabel = new JLabel("Result: " + calcResult);


        this.add(firstNumber);
        this.add(firstCurrency);
        this.add(secondCurrency);
        this.add(submitButton);
        this.add(resultLabel);
        this.setSize(400,400);
        this.pack();
        this.setVisible(true);

    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitButton) { //when comboBox triggers an event
            System.out.println("button pressed");
            System.out.println(firstCurrency.getSelectedItem());
            System.out.println(secondCurrency.getSelectedItem());
            System.out.println(firstNumber.getText());
            try {
                resultLabel.setText("Result: " + calc(Double.parseDouble(firstNumber.getText()), firstCurrency.getSelectedItem().toString(), secondCurrency.getSelectedItem().toString()));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }

    }

    static String API_KEY = "f7b054e08f9045c68e3766fd2c461692";
    static String BASE_URL = "https://openexchangerates.org/api/latest.json";

    public static double getExchangeRate(String from, String to) throws Exception {
        String urlString = BASE_URL + "?app_id=" + API_KEY + "&symbols=" + from + "," + to;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        JSONObject jsonResponse = new JSONObject(content.toString());
        JSONObject rates = jsonResponse.getJSONObject("rates");
        double fromRate = rates.getDouble(from);
        double toRate = rates.getDouble(to);

        System.out.println(toRate);
        System.out.println(fromRate);

        return toRate;
    }

    public static double calc(double firstNumber, String firstCurrancy, String secondCurrancy) throws Exception {

        return firstNumber * getExchangeRate(firstCurrancy, secondCurrancy);
    }




}
