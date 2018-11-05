package com.coderslab;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class AlertBox {

    public static void display() {

        Stage stage = new Stage();
        stage.setTitle("ALARM TIME");
        stage.initModality(Modality.APPLICATION_MODAL);

        // Play sound
        String musicFile = "AlarmSound.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.7);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setStartTime(Duration.seconds(0));
        mediaPlayer.setStopTime(Duration.seconds(10));
        mediaPlayer.play();

        // Alert label
        Label alertLabel = new Label("Wake up!");
        alertLabel.setPadding(new Insets(15,0,0,0));
        alertLabel.setStyle("-fx-alignment: CENTER;");
        alertLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        // Ok button
        Button closeButton = new Button("Got it!");
        closeButton.setMinSize(100,30);
        closeButton.setPadding(new Insets(0,0,0,0));
        // Stop sound with click
        closeButton.setOnAction(event -> {
            mediaPlayer.stop();
            stage.close();
        });


        VBox vBox = new VBox(50);
        vBox.setStyle("-fx-background-color: #b3d7ff;");
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(alertLabel, closeButton);


        Scene scene = new Scene(vBox, 300, 160);
        stage.setScene(scene);
        stage.show();
    }

}
