package Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

    public static String host = "http://localhost";

    public static JsonNode getRequestParam(String urlReqString, Map<String, Object> parameters) throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get(urlReqString)
                .header("Content-Type", "application/json")
                .queryString(parameters)
                .asJson();
        return response.getBody();
    }

    public static JsonNode postRequestParam(String urlReqString, Object obj) throws UnirestException {
        Gson gson = new Gson();
        String json = gson.toJson(obj);

        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.post(urlReqString)
                .header("Content-Type", "application/json")
                .body(json)
                .asJson();
        return response.getBody();
    }

    public static JsonNode postRequest(String urlReqString, Object obj) throws UnirestException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json="";
        
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.post(urlReqString)
                .header("Content-Type", "application/json")
                .body(json)
                .asJson();
        return response.getBody();
    }

    public static void setImage(JLabel icon, String imageUrl) {
        try {
            URL url = new URL(imageUrl);
//            url = getClass().getResource("/test.jpg");
            BufferedImage image = ImageIO.read(url);

            Image im = image.getScaledInstance(icon.getWidth(), icon.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon i = new ImageIcon(im);

            icon.setIcon(i);
        } catch (IOException ignored) {
        }
    }

    public static String formatMoney(double money) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return formatter.format(money);
    }

    public static <T> List<T> parseJsonToList(String json, Class<T> clazz) {
        Gson gson = new Gson();
        Type type = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(json, type);
    }

    public static <T> T parseJsonToObject(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    public static String formatTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public static Date parseToDate(String time) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(time);
        return date;
    }
}
