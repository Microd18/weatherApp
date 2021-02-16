package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text feel;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    private Text presure;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if(!getUserCity.equals("")){
                String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q="+ getUserCity +"&appid=5322d56ce73f8bea7e431e0b5882934d&units=metric");
                if (!output.isEmpty()){
                    JSONObject obj = new JSONObject(output);
                    temp_info.setText("ТЕМПЕРАТУРА:   " + obj.getJSONObject("main").getDouble("temp"));
                    feel.setText("ОЩУЩАЕТСЯ:     " + obj.getJSONObject("main").getDouble("feels_like"));
                    temp_max.setText("МАКСИМУМ:      " + obj.getJSONObject("main").getDouble("temp_max"));
                    temp_min.setText("МИНИМУМ:        " + obj.getJSONObject("main").getDouble("temp_min"));
                    presure.setText("ДАВЛЕНИЕ:         " + obj.getJSONObject("main").getDouble("pressure"));
                }
            }
        });
    }
    private static String getUrlContent(String urlAdress){
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null){
                content.append(line + "\n");
            }
            reader.close();
         } catch (Exception e) {
            System.out.println("Такой город не найден!");
        }
        return content.toString();
    }
}
