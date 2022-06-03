package com.example.planegame;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class HelloApplication extends Application {

    SequentialTransition sequentialTransition1;
    SequentialTransition sequentialTransition2;
    SequentialTransition sequentialTransition3;
    SequentialTransition sequentialTransition4;
    Label labelScore;
    Rectangle helicopter;
    Rectangle heliRec;
    Rectangle cloud1;
    Rectangle cloud2;
    Rectangle cloud3;
    Rectangle cloud4;
    @Override
    public void start(Stage stage) {

        Pane root = new Pane();
        Scene scene = new Scene(root,1000, 680);

        helicopter = createPlain(scene);
        cloud1 = cloudOne();
        cloud2 = cloudTwo();
        cloud3 = cloudThree();
        cloud4 = cloudFour();
        labelScore = createScore();

        //helicopter controls
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            double x = helicopter.getX();
            double y = helicopter.getY();

            switch (event.getCode()){
                case RIGHT -> helicopter.setX(x + 10);
                case LEFT -> helicopter.setX(x - 10);
                case UP -> helicopter.setY(y - 10);
                case DOWN -> helicopter.setY(y + 10);
                case SPACE -> Pause();
                case ENTER -> Play();
            }
        });

        //Adding objects to the scene
        root.getChildren().addAll(helicopter, cloud1, cloud2, cloud3, cloud4);

        //background
        stage.setTitle("Helicopter Game Simulation");
        stage.setScene(scene);
        root.setStyle("-fx-background-image: url('nightSky.jpg');" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: 1350 680;");
        stage.show();
    }

    //Creating helicopter
    private Rectangle createPlain(Scene scene) {
        String helicopter = getClass().getResource("/helicopter.png").toExternalForm();
        Image image = new Image(helicopter);

        heliRec = new Rectangle(130, 130);
        heliRec.setFill(new ImagePattern(image));
        heliRec.setY(scene.getHeight()/4);
        return heliRec;
    }

    //methods for creating clouds
    private Rectangle cloudOne() {
        String cloud = getClass().getResource("/cloud.png").toExternalForm();
        Image image = new Image(cloud);

        Rectangle rect1 = new Rectangle(1300,25,100,100);
        rect1.setTranslateY(220);

        rect1.setFill(new ImagePattern(image));
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(10000), rect1);
        translateTransition.setToX(-1500);//move from right to left
        translateTransition.setToY(100);

        sequentialTransition1 = new SequentialTransition();
        sequentialTransition1.getChildren().add(translateTransition);
        sequentialTransition1.setCycleCount(Timeline.INDEFINITE);

        sequentialTransition1.play();
        return rect1;
    }

    private Rectangle cloudTwo() {
        String cloud = getClass().getResource("/clouds.png").toExternalForm();
        Image image = new Image(cloud);

        Rectangle rect2 = new Rectangle(1300,25,200,200);
        rect2.setTranslateY(320);

        rect2.setFill(new ImagePattern(image));
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(15000), rect2);
        translateTransition.setToX(-2000);//move from right to left
        translateTransition.setToY(-50);

        sequentialTransition2 = new SequentialTransition();
        sequentialTransition2.getChildren().add(translateTransition);
        sequentialTransition2.setCycleCount(Timeline.INDEFINITE);

        sequentialTransition2.play();
        return rect2;
    }

    private Rectangle cloudThree() {
        String cloud = getClass().getResource("/cloudy.png").toExternalForm();
        Image image = new Image(cloud);

        Rectangle rect3 = new Rectangle(1300,25,150,150);
        rect3.setTranslateY(0);

        rect3.setFill(new ImagePattern(image));
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(20000), rect3);
        translateTransition.setToX(-2000);//move from right to left
        translateTransition.setToY(-10);

        sequentialTransition3 = new SequentialTransition();
        sequentialTransition3.getChildren().add(translateTransition);
        sequentialTransition3.setCycleCount(Timeline.INDEFINITE);

        sequentialTransition3.play();
        return rect3;
    }

    private Rectangle cloudFour() {
        String cloud = getClass().getResource("/cloudy.png").toExternalForm();
        Image image = new Image(cloud);

        Rectangle rect4 = new Rectangle(1300,25,200,200);
        rect4.setTranslateY(300);

        rect4.setFill(new ImagePattern(image));
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(25000), rect4);
        translateTransition.setToX(-2000);//move from right to left
        translateTransition.setToY(400);

        sequentialTransition4 = new SequentialTransition();
        sequentialTransition4.getChildren().add(translateTransition);
        sequentialTransition4.setCycleCount(Timeline.INDEFINITE);

        sequentialTransition4.play();
        return rect4;
    }

    //collision and score
    private Label createScore() {

        cloud1.translateXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if (heliRec.getBoundsInParent().intersects(cloud1.getBoundsInParent())){

                    String icon= getClass().getResource("/game-over.png").toExternalForm();
                    Image imageB= new Image(icon);
                    heliRec.setFill(new ImagePattern(imageB));
                    Pause();
                }
                else if (heliRec.getBoundsInParent().intersects(cloud2.getBoundsInParent())) {

                    String icon= getClass().getResource("/game-over.png").toExternalForm();
                    Image imageJet= new Image(icon);
                    heliRec.setFill(new ImagePattern(imageJet));
                    Pause();
                }
                else if (heliRec.getBoundsInParent().intersects(cloud3.getBoundsInParent())) {

                    String icon= getClass().getResource("/game-over.png").toExternalForm();
                    Image imageB= new Image(icon);
                    heliRec.setFill(new ImagePattern(imageB));
                    Pause();
                }
                else if (heliRec.getBoundsInParent().intersects(cloud4.getBoundsInParent())) {

                    String icon= getClass().getResource("/game-over.png").toExternalForm();
                    Image imageB= new Image(icon);
                    heliRec.setFill(new ImagePattern(imageB));
                    Pause();
                }
            }
        });
        return labelScore;
    }

    public void Pause(){
        sequentialTransition1.pause();
        sequentialTransition2.pause();
        sequentialTransition3.pause();
        sequentialTransition4.pause();

    }

    public void Play(){
        sequentialTransition1.play();
        sequentialTransition2.play();
        sequentialTransition3.play();
        sequentialTransition4.play();

    }

    public static void main(String[] args) {
        launch();
    }
}