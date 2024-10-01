package com.project;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.application.Platform;

public class AppData {

    private static AppData instance = null;

    public boolean readyConsoles = false;
    public boolean readyJocs = false;
    public boolean readyPersonatges = false;

    public JSONArray dataConsoles = null;
    public JSONArray dataJocs = null;
    public JSONArray dataPersonatges = null;

    private AppData() { }

    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }

    public void loadData(String dataFile, Consumer<String> callBack) {
        new Thread(() -> {
            try {
                Thread.sleep(1000);  
                InputStream is = getClass().getResourceAsStream(dataFile);
                Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
                StringBuilder content = new StringBuilder();
                char[] buffer = new char[1024];
                int bytesRead;
                while ((bytesRead = reader.read(buffer)) != -1) {
                    content.append(buffer, 0, bytesRead);
                }

                Platform.runLater(()->{
                    callBack.accept(content.toString());
                });

            } catch (InterruptedException e) {
                callBack.accept(null);
                e.printStackTrace();
            } catch (IOException e) {
                callBack.accept(null);
                e.printStackTrace();
            }
        }).start();
    }

    boolean dataReady (String type) {
        switch (type) {
            case "Consoles": return readyConsoles;
            case "Jocs": return readyJocs;
            case "Personatges": return readyPersonatges;
        }
        return false;
    }

    JSONArray getData (String type) {
        switch (type) {
            case "Consoles": return dataConsoles;
            case "Jocs": return dataJocs;
            case "Personatges": return dataPersonatges;
        }
        return null;
    }

    public JSONObject getItemData(String type, int index) {
        if (dataReady(type)) {
            JSONArray dataArray = getData(type);
            if (dataArray != null && index >= 0 && index < dataArray.length()) {
                return dataArray.getJSONObject(index);
            }
        }
        return null;
    }

    public void load(String type, Consumer<String> callBack) {
        if (dataReady(type)) {
          callBack.accept("OK");
          return;
        }
      
        String arxiu = "";
        switch (type) {
          case "Consoles": arxiu = "/assets/data/consoles.json"; break;
          case "Jocs": arxiu = "/assets/data/jocs.json"; break;
          case "Personatges": arxiu = "/assets/data/personatges.json"; break;
          default: throw new IllegalArgumentException("Tipus desconegut: " + type);
        }
        
        loadData(arxiu, (receivedData) -> {
            if (receivedData == null) {
              callBack.accept(null);
              return;
            } else {
              JSONArray dadesArxiu = new JSONArray(receivedData);
              switch (type) {
                case "Consoles":
                  readyConsoles = true;
                  dataConsoles = dadesArxiu;
                  break;
                case "Jocs":
                  readyJocs = true;
                  dataJocs = dadesArxiu;
                  break;
                case "Personatges":
                  readyPersonatges = true;
                  dataPersonatges = dadesArxiu;
                  break;
              }
              callBack.accept("OK");
            }
        });
    }
        
}
