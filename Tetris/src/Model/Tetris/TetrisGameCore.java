/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Tetris;

import ModuleGestionPlateau.Plateau;
import ModuleGestionPlateau.Tetrimino;
import java.util.Observable;

/**
 *
 * @author markk
 */
public class TetrisGameCore extends Observable {

    
    static boolean moveDownFinished = false;
    private static boolean gameOver = false;
    private static boolean launched = false;
    private static boolean paused = false;
    private static Tetrimino[] nextPieces = new Tetrimino[3];

    public static void jeu(PlateauTetris plateau, Tetrimino.Couleur[][] rectPlateau, Tetrimino.Couleur[][] rectSuivant, NextTetriminoPanel nextTetriminoPanel) {      
        Tetrimino currentPiece;
        boolean b;
        if (launched == false) {
            nextPieces[0] = plateau.creePiece();
            nextPieces[1] = plateau.creePiece();
            nextPieces[2] = plateau.creePiece();
            copyRectSuivantColors(nextPieces[0], rectSuivant, 1);
            nextTetriminoPanel.displayNextTetriminoOne(nextPieces[0]);
            copyRectSuivantColors(nextPieces[1], rectSuivant, 2);
            nextTetriminoPanel.displayNextTetriminoTwo(nextPieces[1]);
            copyRectSuivantColors(nextPieces[2], rectSuivant, 3);
            nextTetriminoPanel.displayNextTetriminoThree(nextPieces[2]);
            launched = true;
        }
        if (gameOver == false) {
            if (moveDownFinished == false) {
                currentPiece = (Tetrimino) nextPieces[0].clone();
                plateau.setCurrentTetrimino(currentPiece);
                copyRectPlateauColors(plateau.getCurrentTetrimino(), rectPlateau);
                b = plateau.displayPiece(plateau.getCurrentTetrimino());
                setMoveDownFinished(true);
                nextPieces[0] = (Tetrimino) nextPieces[1].clone();
                copyRectSuivantColors(nextPieces[0], rectSuivant, 1);
                nextTetriminoPanel.displayNextTetriminoOne(nextPieces[0]);
                nextPieces[1] = (Tetrimino) nextPieces[2].clone();
                copyRectSuivantColors(nextPieces[1], rectSuivant, 2);
                nextTetriminoPanel.displayNextTetriminoTwo(nextPieces[1]);
                nextPieces[2] = plateau.creePiece();
                copyRectSuivantColors(nextPieces[2], rectSuivant, 3);
                nextTetriminoPanel.displayNextTetriminoThree(nextPieces[2]);
                if (b == false) {
                    setGameOver(true);
                }
            } else {
                plateau.effaceTracePiece(plateau.getCurrentTetrimino());
                if (plateau.getCurrentTetrimino().verifMoveDown(plateau) == true) {
                    plateau.getCurrentTetrimino().moveDown(plateau);
                    copyRectPlateauColors(plateau.getCurrentTetrimino(), rectPlateau);
                    b = plateau.displayPiece(plateau.getCurrentTetrimino());
                    if (b == false) {
                        setGameOver(true);
                    }
                } else {
                    copyRectPlateauColors(plateau.getCurrentTetrimino(), rectPlateau);
                    b = plateau.displayPiece(plateau.getCurrentTetrimino());
                    if (b == false) {
                        setGameOver(true);
                    } else {
                        plateau.removeFullLines(plateau);
                        setMoveDownFinished(false);
                    }
                }
            }
        }else if(gameOver == true){
        
        plateau.gameOver();
        
        }
        


        /*Runnable task = new Runnable() {
            boolean tmp = true;
            @Override
            public void run() {
                while (tmp == true) {
                    if (gameOver == false) {
                        timeline.stop();
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText("Look, an Information Dialog");
                        alert.setContentText("I have a great message for you!");
                        alert.showAndWait();
                        tmp = false;
                    }
                }
            }

        };*/
 /* Task<Void> task = new Task<Void>() {
            boolean tmp = true;
            public Void call() throws Exception {
                while (tmp == true) {
                    if (gameOver == true) {
                        timeline.stop();
                        tmp = false;
                    }
                }
                return null;
            }

            protected void succeeded() {
                super.succeeded();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Look, an Information Dialog");
                alert.setContentText("I have a great message for you!");
                alert.showAndWait();
            }
        };*/
 /*
        task.setOnSucceeded(e -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("I have a great message for you!");
            alert.showAndWait();
        });*/

 /*Thread t = new Thread(task);
        t.start();*/
        if (gameOver == true) {
//                        timeline.stop();
            //tmp = false;
        }
    }
    //  }}

    private static void setMoveDownFinished(boolean par) {
        moveDownFinished = par;
    }

    /**
     * @param aGameOver the gameOver to set
     */
    public static void setGameOver(boolean aGameOver) {
        gameOver = aGameOver;
    }

    public static void copyRectPlateauColors(Tetrimino t, Tetrimino.Couleur[][] rectPlateau) {
        int compt = 0;
        for (int i = 0; i < t.getnBlignesGrille(); i++) {
            for (int j = 0; j < t.getnBcolsGrille(); j++) {
                if (t.getTABTETRIMINO()[t.getPositionCourante()][compt] == true) {
                    rectPlateau[t.getCoordCaseTetriminos()[i][j].getCoord().getX()][t.getCoordCaseTetriminos()[i][j].getCoord().getY()] = t.getColor();
                }
                compt++;
            }
        }
    }

    public static void copyRectSuivantColors(Tetrimino t, Tetrimino.Couleur[][] rectSuivant, int numeroRect) {
        int a = 0, b = 0, c = 0, d = 0;
        switch (numeroRect) {
            case 1:
                a = 0;
                b = 2;
                c = 0;
                d = 4;
                break;
            case 2:
                a = 2;
                b = 4;
                c = 0;
                d = 4;
                break;
            case 3:
                a = 4;
                b = 6;
                c = 0;
                d = 4;
                break;
            default:
                break;
        }
        int compt = 0;
        for (int i = a; i < b; i++) {
            for (int j = c; j < d; j++) {
                if (t.getTABTETRIMINO()[0][compt] == true) {
                    rectSuivant[i][j] = t.getColor();
                }
                compt++;
            }
        }
    }

    public static void moveLeft(Plateau plateau, Tetrimino.Couleur[][] couleursRectPlateau) {
        if (launched == true && paused == false && moveDownFinished == true) {
            plateau.effaceTracePiece(plateau.getCurrentTetrimino());
            plateau.getCurrentTetrimino().moveLeft(plateau);
            TetrisGameCore.copyRectPlateauColors(plateau.getCurrentTetrimino(), couleursRectPlateau);
            plateau.displayPiece(plateau.getCurrentTetrimino());
        }
    }

    public static void moveRight(Plateau plateau, Tetrimino.Couleur[][] couleursRectPlateau) {
        if (launched == true && paused == false && moveDownFinished == true) {
            plateau.effaceTracePiece(plateau.getCurrentTetrimino());
            plateau.getCurrentTetrimino().moveRight(plateau);
            TetrisGameCore.copyRectPlateauColors(plateau.getCurrentTetrimino(), couleursRectPlateau);
            plateau.displayPiece(plateau.getCurrentTetrimino());
        }
    }

    public static void rotate(Plateau plateau, Tetrimino.Couleur[][] couleursRectPlateau) {
        if (launched == true && paused == false && moveDownFinished == true) {
            plateau.effaceTracePiece(plateau.getCurrentTetrimino());
            plateau.getCurrentTetrimino().rotate(plateau);
            TetrisGameCore.copyRectPlateauColors(plateau.getCurrentTetrimino(), couleursRectPlateau);
            plateau.displayPiece(plateau.getCurrentTetrimino());
        }
    }

    public static void moveDown(Plateau plateau, Tetrimino.Couleur[][] couleursRectPlateau) {
        if (launched == true && paused == false) {
            plateau.effaceTracePiece(plateau.getCurrentTetrimino());
            plateau.getCurrentTetrimino().moveDown(plateau);
            TetrisGameCore.copyRectPlateauColors(plateau.getCurrentTetrimino(), couleursRectPlateau);
            plateau.displayPiece(plateau.getCurrentTetrimino());
        }

    }

    public static void pauseGame() {
        if (gameOver == false && launched == true) {
            paused = true;
//            timeline.pause();
        }
    }

    public static void continueGame() {
        if (gameOver == false && launched == true) {
            paused = false;
            //           timeline.play();
        }
    }

}
