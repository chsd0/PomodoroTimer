package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class TableController {
    ExctractData ed= new ExctractData();

    private ObservableList<ExctractData> data = FXCollections.observableArrayList();

    @FXML
    private TableView<ExctractData> historyTable;
    @FXML
    private TableColumn<ExctractData, String> dateColumn = new TableColumn<>("Data");
    @FXML
    private TableColumn<ExctractData, String> workColumn = new TableColumn<>("WorkingCyclecount");
    @FXML
    private TableColumn<ExctractData, String> breakColumn = new TableColumn<>("BreakCycleCount");
    @FXML
    private TableColumn<ExctractData, String> kpdColumn =new TableColumn<>("kpd");
    @FXML
    private Button mButton;

    @FXML
    private Button sButton;

    @FXML
    private Button hButton;



    @FXML
    public void initialize() {
        for(int i =0;i< ed.getDateCount();i++){
            ed.next();
            String d = ed.getDate(), w= ed.getWorkingCycleCount(), b= ed.getBreakCycleCount(), k = ed.getKpd();
            data.add(new ExctractData(d, w,b,k));
        }


        // устанавливаем тип и значение которое должно хранится в колонке
        dateColumn.setCellValueFactory(new PropertyValueFactory<ExctractData, String>("date"));
        workColumn.setCellValueFactory(new PropertyValueFactory<ExctractData, String>("workingCycleCount"));
        breakColumn.setCellValueFactory(new PropertyValueFactory<ExctractData, String>("breakCycleCount"));
        kpdColumn.setCellValueFactory(new PropertyValueFactory<ExctractData, String>("kpd"));

        // заполняем таблицу данными
        historyTable.setItems(data);
    }
    @FXML
    protected void handleSButton() {
        // Обработчик события для кнопки settingsButton
        // Переключение на сцену настроек
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pomodorotimer/settings-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = sButton.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void handleHButton() {
        // Обработчик события для кнопки historyButton
        // Переключение на сцену истории

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pomodorotimer/history-view.fxml"));
            Parent root = fxmlLoader.load();
            TableController tableController = fxmlLoader.getController();

            Scene scene = hButton.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void handleMButton() {
        // Обработчик события для кнопки mainButton
        // Переключение на главную сцену
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pomodorotimer/hello-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = mButton.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<ExctractData> getData() {
        return data;
    }


}
