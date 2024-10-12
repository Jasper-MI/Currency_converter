import java.awt.event.*;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class MyJFrame extends JFrame implements ActionListener {
    JLabel text1;
    JLabel text2;
    JTextField firstNumber;
    JButton submitButton;
    JComboBox firstCurrency;

    JComboBox secondCurrency;
    JLabel resultLabel;
    double calcResult = 0;

    MyJFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        text1 = new JLabel("Amount: ");

        firstNumber = new JTextField(5);

        String[] currency = {"AED (United Arab Emirates Dirham)", "AFN (Afghan Afghani)", "ALL (Albanian Lek)", "AMD (Armenian Dram)", "ANG (Netherlands Antillean Guilder)", "AOA (Angolan Kwanza)", "ARS (Argentine Peso)", "AUD (Australian Dollar)", "AWG (Aruban Florin)", "AZN (Azerbaijani Manat)", "BAM (Bosnia-Herzegovina Convertible Mark)", "BBD (Barbadian Dollar)", "BDT (Bangladeshi Taka)", "BGN (Bulgarian Lev)", "BHD (Bahraini Dinar)", "BIF (Burundian Franc)", "BMD (Bermudan Dollar)", "BND (Brunei Dollar)", "BOB (Bolivian Boliviano)", "BRL (Brazilian Real)", "BSD (Bahamian Dollar)", "BTC (Bitcoin)", "BTN (Bhutanese Ngultrum)", "BWP (Botswanan Pula)", "BYN (Belarusian Ruble)", "BYR (Belarusian Ruble, pre-2016)", "BZD (Belize Dollar)", "CAD (Canadian Dollar)", "CDF (Congolese Franc)", "CHF (Swiss Franc)", "CLF (Chilean Unit of Account (UF))", "CLP (Chilean Peso)", "CNH (Chinese Yuan (Offshore))", "CNY (Chinese Yuan)", "COP (Colombian Peso)", "CRC (Costa Rican Colón)", "CUC (Cuban Convertible Peso)", "CUP (Cuban Peso)", "CVE (Cape Verdean Escudo)", "CZK (Czech Republic Koruna)", "DJF (Djiboutian Franc)", "DKK (Danish Krone)", "DOP (Dominican Peso)", "DZD (Algerian Dinar)", "EEK (Estonian Kroon)", "EGP (Egyptian Pound)", "ERN (Eritrean Nakfa)", "ETB (Ethiopian Birr)", "EUR (Euro)", "FJD (Fijian Dollar)", "FKP (Falkland Islands Pound)", "GBP (British Pound Sterling)", "GEL (Georgian Lari)", "GGP (Guernsey Pound)", "GHS (Ghanaian Cedi)", "GIP (Gibraltar Pound)", "GMD (Gambian Dalasi)", "GNF (Guinean Franc)", "GTQ (Guatemalan Quetzal)", "GYD (Guyanaese Dollar)", "HKD (Hong Kong Dollar)", "HNL (Honduran Lempira)", "HRK (Croatian Kuna)", "HTG (Haitian Gourde)", "HUF (Hungarian Forint)", "IDR (Indonesian Rupiah)", "ILS (Israeli New Sheqel)", "IMP (Manx pound)", "INR (Indian Rupee)", "IQD (Iraqi Dinar)", "IRR (Iranian Rial)", "ISK (Icelandic Króna)", "JEP (Jersey Pound)", "JMD (Jamaican Dollar)", "JOD (Jordanian Dinar)", "JPY (Japanese Yen)", "KES (Kenyan Shilling)", "KGS (Kyrgystani Som)", "KHR (Cambodian Riel)", "KMF (Comorian Franc)", "KPW (North Korean Won)", "KRW (South Korean Won)", "KWD (Kuwaiti Dinar)", "KYD (Cayman Islands Dollar)", "KZT (Kazakhstani Tenge)", "LAK (Laotian Kip)", "LBP (Lebanese Pound)", "LKR (Sri Lankan Rupee)", "LRD (Liberian Dollar)", "LSL (Lesotho Loti)", "LYD (Libyan Dinar)", "MAD (Moroccan Dirham)", "MDL (Moldovan Leu)", "MGA (Malagasy Ariary)", "MKD (Macedonian Denar)", "MMK (Myanma Kyat)", "MNT (Mongolian Tugrik)", "MOP (Macanese Pataca)", "MRO (Mauritanian Ouguiya, pre-2018)", "MRU (Mauritanian Ouguiya)", "MTL (Maltese Lira)", "MUR (Mauritian Rupee)", "MVR (Maldivian Rufiyaa)", "MWK (Malawian Kwacha)", "MXN (Mexican Peso)", "MYR (Malaysian Ringgit)", "MZN (Mozambican Metical)", "NAD (Namibian Dollar)", "NGN (Nigerian Naira)", "NIO (Nicaraguan Córdoba)", "NOK (Norwegian Krone)", "NPR (Nepalese Rupee)", "NZD (New Zealand Dollar)", "OMR (Omani Rial)", "PAB (Panamanian Balboa)", "PEN (Peruvian Nuevo Sol)", "PGK (Papua New Guinean Kina)", "PHP (Philippine Peso)", "PKR (Pakistani Rupee)", "PLN (Polish Zloty)", "PYG (Paraguayan Guarani)", "QAR (Qatari Rial)", "RON (Romanian Leu)", "RSD (Serbian Dinar)", "RUB (Russian Ruble)", "RWF (Rwandan Franc)", "SAR (Saudi Riyal)", "SBD (Solomon Islands Dollar)", "SCR (Seychellois Rupee)", "SDG (Sudanese Pound)", "SEK (Swedish Krona)", "SGD (Singapore Dollar)", "SHP (Saint Helena Pound)", "SLL (Sierra Leonean Leone)", "SOS (Somali Shilling)", "SRD (Surinamese Dollar)", "SSP (South Sudanese Pound)", "STD (São Tomé and Príncipe Dobra, pre-2018)", "STN (São Tomé and Príncipe Dobra)", "SVC (Salvadoran Colón)", "SYP (Syrian Pound)", "SZL (Swazi Lilangeni)", "THB (Thai Baht)", "TJS (Tajikistani Somoni)", "TMT (Turkmenistani Manat)", "TND (Tunisian Dinar)", "TOP (Tongan Paʻanga)", "TRY (Turkish Lira)", "TTD (Trinidad and Tobago Dollar)", "TWD (New Taiwan Dollar)", "TZS (Tanzanian Shilling)", "UAH (Ukrainian Hryvnia)", "UGX (Ugandan Shilling)", "USD (United States Dollar)", "UYU (Uruguayan Peso)", "UZS (Uzbekistan Som)", "VES (Venezuelan Bolívar Soberano)", "VND (Vietnamese Dong)", "VUV (Vanuatu Vatu)", "WST (Samoan Tala)", "XAF (CFA Franc BEAC)", "XAG (Silver (troy ounce))", "XAU (Gold (troy ounce))", "XCD (East Caribbean Dollar)", "XDR (Special Drawing Rights)", "XOF (CFA Franc BCEAO)", "XPD (Palladium Ounce)", "XPF (CFP Franc)", "XPT (Platinum Ounce)", "YER (Yemeni Rial)", "ZAR (South African Rand)", "ZMK (Zambian Kwacha, pre-2013)", "ZMW (Zambian Kwacha)"};
        firstCurrency = new JComboBox(currency);
        firstCurrency.addActionListener(this);

        text2 = new JLabel(" to ");

        secondCurrency = new JComboBox(currency);
        secondCurrency.addActionListener(this);

        submitButton = new JButton("convert");
        submitButton.addActionListener(this);

        resultLabel = new JLabel("Result: " + calcResult);


        this.add(text1);
        this.add(firstNumber);
        this.add(firstCurrency);
        this.add(text2);
        this.add(secondCurrency);
        this.add(submitButton);
        this.add(resultLabel);
        this.pack();
        this.setVisible(true);

    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitButton) { //when commit-butten gets pressed and textField is not empty
            System.out.println("button pressed");
            System.out.println("First Currency: " + firstCurrency.getSelectedItem());
            System.out.println("Second Currency: " + secondCurrency.getSelectedItem());
            System.out.println("First Number: " + firstNumber.getText());
            try {
                resultLabel.setText("Result: " +
                        calc(Double.parseDouble(firstNumber.getText()),
                                firstCurrency.getSelectedItem().toString().substring(0,3),
                                secondCurrency.getSelectedItem().toString().substring(0,3)));
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

        System.out.println("To Rate: " + toRate);
        System.out.println("From Rate: " + fromRate);

        return toRate / fromRate;
    }

    public static double calc(double firstNumber, String firstCurrancy, String secondCurrancy) throws Exception {
        System.out.println("Result: " + firstNumber * getExchangeRate(firstCurrancy, secondCurrancy));
        return firstNumber * getExchangeRate(firstCurrancy, secondCurrancy);
    }




}
