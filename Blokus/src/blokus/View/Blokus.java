/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.View;

import ModuleGestionPlateau.Case;
import ModuleGestionPlateau.Tetrimino;
import ModuleGestionPlateau.VuePlateau;
import blokus.Model.BlokusGameCore;

import blokus.Model.BlokusPieceF;
import blokus.Model.BlokusPieceI1;
import blokus.Model.BlokusPieceI4;
import blokus.Model.BlokusPieceL4;
import blokus.Model.BlokusPieceU;
import blokus.Model.BlokusPlayer;
import blokus.Model.PlateauBlokus;
import java.io.File;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author markk
 */
public class Blokus extends Application {

    VuePlateau vuePlateauBlokus = new VuePlateau(20, 20);
    PlateauBlokus plateau = new PlateauBlokus(20, 20, 5, 5);
    boolean isStarted = false;
    BlokusPlayer[] players = BlokusGameCore.createPlayers();

    @Override
    public void start(Stage primaryStage) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mauvais Placement");
        alert.setHeaderText("Alerte !");
        alert.setContentText("La pièce doit toucher le bord d'une pièce");
        Alert alert2 = new Alert(AlertType.INFORMATION);
        alert2.setTitle("Mauvais Placement");
        alert2.setHeaderText("Alerte !");
        alert2.setContentText("La pièce doit être entièrement sur le plateau .");
        Alert alert3 = new Alert(AlertType.INFORMATION);
        alert3.setTitle("Mauvais Placement");
        alert3.setHeaderText("Alerte !");
        alert3.setContentText("La pièce doit être sur votre corner .");
        //
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(720, 100);
        //
        Image image = new Image(new File("ressources\\blokus.png").toURI().toString());
        ImageView iv1 = new ImageView();
        HBox box = new HBox();
        box.setPrefSize(720, 100);
        box.setAlignment(Pos.CENTER);
        iv1.setImage(image);
        box.getChildren().add(iv1);
        scrollPane.setContent(box);
        //Creation du menu
        MenuBar menuBar = new MenuBar();
        // --- Menu Jeu
        Menu menuJeu = new Menu("Jouer");
        MenuItem commencer = new Menu("Commencer");
        MenuItem sauvegarder = new Menu("Sauvegarder");
        MenuItem charger = new Menu("Charger");
        MenuItem quitter = new Menu("Quitter");
        menuJeu.getItems().addAll(commencer, sauvegarder, charger, quitter);
        // --- Menu Score
        Menu menuScore = new Menu("Meilleurs Scores");
        // --- Menu Aide
        Menu menuAide = new Menu("Aide");
        MenuItem regleJeu = new Menu("Règles de jeu");
        MenuItem aPropos = new Menu("A propos");
        menuAide.getItems().addAll(regleJeu, aPropos);
        menuBar.getMenus().addAll(menuJeu, menuScore, menuAide);
        //*********//
        //Plateau de jeu//

        vuePlateauBlokus.getgPane2().setPrefWidth(300);
        vuePlateauBlokus.getgPane2().setPrefHeight(520);
        Circle[] c = new Circle[4];
        for (int i = 0; i < 4; i++) {
            int a = 0, b = 0;
            c[i] = new Circle();
            c[i].setRadius(5);
            switch (i) {
                case 0:
                    a = 0;
                    b = 0;
                    c[i].setCenterX(b);
                    c[i].setCenterY(a);
                    c[i].setFill(Color.BLUE);
                    break;
                case 1:
                    a = 19;
                    b = 0;
                    c[i].setCenterX(b);
                    c[i].setCenterY(a);
                    c[i].setFill(Color.YELLOW);
                    break;
                case 2:
                    a = 0;
                    b = 19;
                    c[i].setCenterX(b);
                    c[i].setCenterY(a);
                    c[i].setFill(Color.GREEN);
                    break;
                case 3:
                    a = 19;
                    b = 19;
                    c[i].setCenterX(b);
                    c[i].setCenterY(a);
                    c[i].setFill(Color.RED);
                    break;
                default:
                    break;
            }
            vuePlateauBlokus.getgPane2().add(c[i], a, b);
            vuePlateauBlokus.getgPane2().setAlignment(Pos.CENTER);
            GridPane.setHalignment(c[i], HPos.CENTER);
            GridPane.setValignment(c[i], VPos.CENTER);
        }
        vuePlateauBlokus.getgPane2().requestFocus();
        addMouseListener(vuePlateauBlokus.getRectPlateau());
        //GridPane Droite
        GridPane gPaneRight = new GridPane();
        gPaneRight.setPrefWidth(100);
        gPaneRight.setPrefHeight(520);
        gPaneRight.setStyle("-fx-background-color: #008000;");
        Button jouer = new Button("Jouer");
        Button passer = new Button("Passer son tour");
        passer.setOnAction((ActionEvent e) -> {
            plateau.getPlayer().setLost();
            BlokusGameCore.pass(plateau.getPlayer(), players, plateau);
        });
        jouer.setOnAction((ActionEvent e) -> {
            BlokusGameCore.start();
            scrollPane.setContent(drawPiecePanel(players[0].getLeftPieces(), convertColor(players[0].getColor())));
        });
        VBox btnBox = new VBox(10);
        gPaneRight.setAlignment(Pos.CENTER);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.getChildren().add(jouer);
        btnBox.getChildren().add(passer);
        gPaneRight.add(btnBox, 0, 0, 10, 10);
        GridPane.setHalignment(btnBox, HPos.CENTER);
        //*********//
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(vuePlateauBlokus.getgPane2());
        root.setBottom(scrollPane);
        root.setRight(gPaneRight);
        Scene scene = new Scene(root, 720, 645);
        primaryStage.setTitle("Blokus");
        primaryStage.getIcons().add(new Image(new File("ressources\\blokus-logo.png").toURI().toString()));
        primaryStage.setScene(scene);
        primaryStage.show();
        plateau.addObserver((Observable o, Object arg) -> {
            if (arg instanceof Case) {
                Case tmp = (Case) arg;
                if (vuePlateauBlokus.getColouredRectPlateau()[tmp.getCoord().getX()][tmp.getCoord().getY()] == false) {
                    vuePlateauBlokus.getRectPlateau()[tmp.getCoord().getX()][tmp.getCoord().getY()].setFill(convertColor(vuePlateauBlokus.getCouleursRectPlateau()[tmp.getCoord().getX()][tmp.getCoord().getY()]));
                    vuePlateauBlokus.getColouredRectPlateau()[tmp.getCoord().getX()][tmp.getCoord().getY()] = true;
                } else if (vuePlateauBlokus.getColouredRectPlateau()[tmp.getCoord().getX()][tmp.getCoord().getY()] == true) {
                    vuePlateauBlokus.getRectPlateau()[tmp.getCoord().getX()][tmp.getCoord().getY()].setFill(Color.BLACK);
                    vuePlateauBlokus.getColouredRectPlateau()[tmp.getCoord().getX()][tmp.getCoord().getY()] = false;
                }
            }
        }
        );
        //observeur des joueurs
        players[0].addObserver((Observable o, Object arg) -> {
            int tmp = (int) arg;
            switch (tmp) {
                case 1:
                    scrollPane.setContent(drawPiecePanel(players[0].getLeftPieces(), convertColor(players[0].getColor())));
                    break;
                case 2:
                    alert.showAndWait();
                    break;
                case 3:
                    alert2.showAndWait();
                    break;
                case 4:
                    alert3.showAndWait();
                    break;
                default:
                    break;
            }
        });
        players[1].addObserver((Observable o, Object arg) -> {
            int tmp = (int) arg;
            switch (tmp) {
                case 1:
                    scrollPane.setContent(drawPiecePanel(players[1].getLeftPieces(), convertColor(players[1].getColor())));
                    break;
                case 2:
                    alert.showAndWait();
                    break;
                case 3:
                    alert2.showAndWait();
                    break;
                case 4:
                    alert3.showAndWait();
                    break;
                default:
                    break;
            }
        });
        players[2].addObserver((Observable o, Object arg) -> {
            int tmp = (int) arg;
            switch (tmp) {
                case 1:
                    scrollPane.setContent(drawPiecePanel(players[2].getLeftPieces(), convertColor(players[2].getColor())));
                    break;
                case 2:
                    alert.showAndWait();
                    break;
                case 3:
                    alert2.showAndWait();
                    break;
                case 4:
                    alert3.showAndWait();
                    break;
                default:
                    break;
            }
        });
        players[3].addObserver((Observable o, Object arg) -> {
            int tmp = (int) arg;
            switch (tmp) {
                case 1:
                    scrollPane.setContent(drawPiecePanel(players[3].getLeftPieces(), convertColor(players[3].getColor())));
                    break;
                case 2:
                    alert.showAndWait();
                    break;
                case 3:
                    alert2.showAndWait();
                    break;
                case 4:
                    alert3.showAndWait();
                    break;
                default:
                    break;
            }
        });
        //observeur jeu

        scene.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                BlokusGameCore.start();
                scrollPane.setContent(drawPiecePanel(players[0].getLeftPieces(), convertColor(players[0].getColor())));
            }
            if (e.getCode() == KeyCode.R) {
                BlokusGameCore.rotate(players, plateau, plateau.getCurrentTetrimino(), vuePlateauBlokus.getCouleursRectPlateau());
            }
            if (e.getCode() == KeyCode.F) {
                BlokusGameCore.flipPiece(players, plateau, plateau.getCurrentTetrimino(), vuePlateauBlokus.getCouleursRectPlateau());
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * @param t prend une pièce en paramètre
     * @param size
     * @param color
     * @return retourne un objet de type canvas utilisé dans le scroll bar
     */
    public Canvas drawPiece(Tetrimino t, int size, Color color) {
        Canvas canvas = new Canvas(80, 80);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int compt = 0;
        for (int i = 0; i < t.getnBlignesGrille(); i++) {
            for (int j = 0; j < t.getnBcolsGrille(); j++) {
                if (t.getTABTETRIMINO()[t.getPositionCourante()][compt] == true) {
                    gc.setFill(color);
                    gc.fillRect(i * size, j * size, size, size);
                } else if (t.getTABTETRIMINO()[t.getPositionCourante()][compt] == false) {
                    gc.setFill(Color.WHITE);
                    gc.fillRect(i * size, j * size, size, size);
                }
                canvas.setOnMouseClicked((MouseEvent event) -> {
                    //test
                    BlokusGameCore.setLaunchedTrue();
                    BlokusGameCore.copyRectPlateauColors(players, plateau, t, vuePlateauBlokus.getCouleursRectPlateau());
                    plateau.setCurrentTetrimino(t);
                });
                compt++;
            }

        }
        return canvas;
    }

    private HBox drawPiecePanel(ArrayList<Tetrimino> pieces, Color color) {
        HBox hbox = new HBox(5);
        for (int i = 0; i < pieces.size(); i++) {
            hbox.getChildren().add(drawPiece(pieces.get(i), 15, color));
        }
        return hbox;
    }

    public Color convertColor(Tetrimino.Couleur couleur) {
        switch (couleur) {
            case CYAN:
                return Color.CYAN;
            case RED:
                return Color.RED;
            case GREEN:
                return Color.GREEN;
            case PURPLE:
                return Color.PURPLE;
            case ORANGE:
                return Color.ORANGE;
            case BLUE:
                return Color.BLUE;
            case YELLOW:
                return Color.YELLOW;
        }
        return Color.BLANCHEDALMOND;
    }

    public void addMouseListener(Rectangle[][] rectPlateau) {
        //final BlokusPieceF b= new BlokusPieceF();
        for (int i = 0; i < plateau.getNbLignes(); i++) {
            for (int j = 0; j < plateau.getNbCols(); j++) {
                int x = plateau.getCoordCasesGrille()[i][j].getCoord().getX();
                int y = plateau.getCoordCasesGrille()[i][j].getCoord().getY();
                rectPlateau[i][j].setOnMouseMoved((MouseEvent e) -> {
                    BlokusGameCore.onMoved(players, plateau, x, y, vuePlateauBlokus.getCouleursRectPlateau());
                });
                rectPlateau[i][j].setOnMouseClicked((MouseEvent e) -> {
                    //cheker validité position
                    BlokusGameCore.onClick(players, plateau, x, y, vuePlateauBlokus.getCouleursRectPlateau());
                });
            }
        }
    }
    

}
