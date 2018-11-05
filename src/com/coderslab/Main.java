package com.coderslab;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main extends Application {

    AlertBox alertBox = new AlertBox();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage stage = primaryStage;
        stage.setTitle("Alarm clock");

        // Close with ESC
        closeShortCut(stage);


        //Title label
        Label titleLabel = new Label("Current time:");
        titleLabel.setPadding(new Insets(30, 20, 0, 20));
        titleLabel.setAlignment(Pos.TOP_CENTER);
        titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        //Time label
        Date d = new Date(new Date().getTime());
        String s = new SimpleDateFormat("HH:mm:ss").format(d);
        Label timelabel = new Label(s);
        timelabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        //Alarm label
        Label alarmLabel = new Label("Alarm time:");
        alarmLabel.setPadding(new Insets(10, 0, 0, 0));
        alarmLabel.setAlignment(Pos.TOP_CENTER);
        alarmLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        //Alarm value
        Label alarmValueLabel = new Label("");
        alarmValueLabel.setPadding(new Insets(10, 0, 10, 0));
        alarmValueLabel.setAlignment(Pos.TOP_CENTER);
        alarmValueLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));


        //Alarm text field
        TextField alarmTextField = new TextField();
        alarmTextField.setAlignment(Pos.CENTER_LEFT);
        alarmTextField.setPromptText("HH:mm:ss");
        alarmTextField.setMinHeight(30);


        // Set alarm time button
        Button setAlarmButton = new Button("Set alarm");
        setAlarmButton.setStyle("-fx-alignment: CENTER;");
        setAlarmButton.setAlignment(Pos.CENTER_RIGHT);
        setAlarmButton.setMinSize(70, 30);
        setAlarmButton.setOnAction(event -> {
            Pattern pattern = Pattern.compile("(([0-1]?[0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]");
            Matcher matcher = pattern.matcher(alarmTextField.getText());
            if(matcher.matches()) {
                System.out.println("matches");
                alarmValueLabel.setText(alarmTextField.getText());
                alarmTextField.clear();
            } else {
                System.out.println("Wrong time format.");
                alarmValueLabel.setText("Wrong time format.");
            }
        });

        //Dynamic time change
        final Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(1000), // refresh every ... milisec
                        event -> {
                            timelabel.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
                            if (timelabel.getText().equals(alarmValueLabel.getText())) {
                                alertBox.display();
                                System.out.println("It works ! WHOAAH");
                            }
                        }
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // Add exit button
        Button exitButton = new Button("Exit");
        exitButton.setAlignment(Pos.CENTER);
        exitButton.setMinSize(70, 30);
        exitButton.setOnAction(event -> exitButtonAction(stage));

        //Exit app tip
        Label infoLabel = new Label("Press exit button or ESC to exit");
        infoLabel.setAlignment(Pos.BOTTOM_CENTER);


        //Set alarm HBox
        HBox alarmHBox = new HBox(20);
        alarmHBox.setPadding(new Insets(0,10,0,10));
        alarmHBox.getChildren().addAll(alarmTextField, setAlarmButton);

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setStyle("-fx-background-color: #b3d7ff;");
        vBox.getChildren().addAll(titleLabel, timelabel, alarmLabel, alarmValueLabel, alarmHBox, exitButton, infoLabel);


        Scene scene = new Scene(vBox, 300, 300);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public void closeShortCut(Stage stage) {
        stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent evt) {
                if (evt.getCode().equals(KeyCode.ESCAPE)) {
                    stage.close();
                }
            }
        });
    }

    public void exitButtonAction(Stage stage) {
        stage.close();
    }



}



