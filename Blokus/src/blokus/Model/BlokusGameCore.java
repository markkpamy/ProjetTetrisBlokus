/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.Model;

import ModuleGestionPlateau.Coordonnees;
import ModuleGestionPlateau.Plateau;
import ModuleGestionPlateau.Tetrimino;
import ModuleGestionPlateau.Tetrimino.Couleur;

/**
 *
 * @author markk
 */
public class BlokusGameCore {

    private static int turn = 0;
    static boolean isStarted;
    static boolean launched;
    static boolean gamefinished = false;

    public static void onClick(BlokusPlayer[] players, PlateauBlokus plateau, int x, int y, Couleur[][] couleurs) {

        if (isStarted == true && launched == true && gamefinished==false) {
            boolean b1 = true;
            //gamefinished = true;
            boolean b2 = validatePiece(players[getTurn()], plateau, plateau.getCurrentTetrimino());
            if (players[getTurn()].getTurnNumber() == 0) {
                b1 = validateFirstMove(players[getTurn()], plateau, plateau.getCurrentTetrimino());
                b2 = true;
            }
            if (b2 == true && isEntirelyOnBoard(plateau, plateau.getCurrentTetrimino()) == true && b1 == true) {
                BlokusGameCore.updateCoord(plateau.getCurrentTetrimino(), x, y);
                BlokusGameCore.copyRectPlateauColors(players, plateau, plateau.getCurrentTetrimino(), couleurs);
                plateau.displayPiece(plateau.getCurrentTetrimino());
                players[getTurn()].removePiece(plateau.getCurrentTetrimino());
                players[getTurn()].setTurnNumber(players[getTurn()].getTurnNumber() + 1);
                setTurn(getTurn() + 1);
                /*while (players[getTurn()].isLost() == true) {
                    setTurn(getTurn() + 1);
                    if (getTurn() > 3) {
                        setTurn(0);
                    }
                    gamefinished = false;
                }*/
                //if (gamefinished == false) { 
                    if (getTurn() > 3) {
                        setTurn(0);
                    }
                    players[getTurn()].setTurn(true);
                    plateau.setTurn(plateau.getTurn() + 1);
                    plateau.setPlayer(players[getTurn()]);
                    launched = false;
                    //cheker validité position*/
               // }
            }
            if (b2 == false) {
                players[getTurn()].notPossibleToMove();
            }
            if (isEntirelyOnBoard(plateau, plateau.getCurrentTetrimino()) == false) {
                players[getTurn()].notEntirelyOnBoard();
            }
            if (b1 == false) {
                players[getTurn()].notValidCorner();
            }
        }
    }

    public static void pass(BlokusPlayer player,BlokusPlayer[] players, PlateauBlokus plateau) {
        player.setTurnNumber(player.getTurnNumber() + 1);
        setTurn(getTurn() + 1);
        if (getTurn() > 3) {
            setTurn(0);
        }
        players[getTurn()].setTurn(true);
        plateau.setTurn(plateau.getTurn() + 1);
        plateau.setPlayer(players[getTurn()]);
        launched = false;
    }

    public static void onMoved(BlokusPlayer[] players, Plateau plateau, int x, int y, Couleur[][] couleurs) {
        if (isStarted == true && launched == true) {
            //vérifier si les cases a coté sont vides avt de bouger
//&& verifMove(plateau,x,y,plateau.getCurrentTetrimino()) ==true
            if (x >= 0 && x < plateau.getNbLignes() && y >= 0 && y < plateau.getNbCols() && verifMove(plateau, x, y, plateau.getCurrentTetrimino()) == true) {
                plateau.effaceTracePiece(plateau.getCurrentTetrimino());
                //deleteColor à implémenter
                BlokusGameCore.deleteColors(players, plateau, plateau.getCurrentTetrimino(), couleurs);
                BlokusGameCore.updateCoord(plateau.getCurrentTetrimino(), x, y);
                BlokusGameCore.copyRectPlateauColors(players, plateau, plateau.getCurrentTetrimino(), couleurs);
                plateau.displayPiece(plateau.getCurrentTetrimino());
            }
        }
    }

    public static boolean verifMove(Plateau plateau, int x, int y, Tetrimino t) {
        plateau.effaceTracePiece(t);
        boolean result = true;
        int compt = 0;
        //int compt1 = 0;
        for (int i = x - 2; i < x - 2 + t.getnBlignesGrille(); i++) {
            // int compt2 = 0;
            for (int j = y - 2; j < y - 2 + t.getnBcolsGrille(); j++) {
                if (t.getTABTETRIMINO()[0][compt] == true && i >= 0 && i < plateau.getNbLignes() && j >= 0 && j < plateau.getNbCols()) {
                    //if (t.getCoordCaseTetriminos()[compt1][compt2].getCoord().getX() >= 0 && t.getCoordCaseTetriminos()[compt1][compt2].getCoord().getX() < plateau.getNbLignes() && t.getCoordCaseTetriminos()[compt1][compt2].getCoord().getY() >= 0 && t.getCoordCaseTetriminos()[compt1][compt2].getCoord().getY() < plateau.getNbCols()) {
                    if (plateau.getGrille()[i][j] == true) {
                        return false;
                    }
                    //}
                }
                compt++;
            }
        }
        return result;
    }

    public static void updateCoord(Tetrimino t, int x, int y) {
        int compt1 = 0;
        for (int i = x - 2; i < x - 2 + t.getnBlignesGrille(); i++) {
            int compt2 = 0;
            for (int j = y - 2; j < y - 2 + t.getnBcolsGrille(); j++) {
                t.getCoordCaseTetriminos()[compt1][compt2].setCoord(new Coordonnees(i, j));
                compt2++;
            }
            compt1++;
        }
    }

    public static void start() {
        isStarted = true;
    }

    public static void setLaunchedTrue() {
        if (launched != true) {
            launched = true;
        }
    }

    public static void copyRectPlateauColors(BlokusPlayer[] players, Plateau plateau, Tetrimino t, Tetrimino.Couleur[][] rectPlateau) {
        int compt = 0;
        for (int i = 0; i < t.getnBlignesGrille(); i++) {
            for (int j = 0; j < t.getnBcolsGrille(); j++) {
                if (t.getTABTETRIMINO()[t.getPositionCourante()][compt] == true && t.getCoordCaseTetriminos()[i][j].getCoord().getX() >= 0 && t.getCoordCaseTetriminos()[i][j].getCoord().getX() < plateau.getNbLignes() && t.getCoordCaseTetriminos()[i][j].getCoord().getY() >= 0 && t.getCoordCaseTetriminos()[i][j].getCoord().getY() < plateau.getNbCols()) {
                    rectPlateau[t.getCoordCaseTetriminos()[i][j].getCoord().getX()][t.getCoordCaseTetriminos()[i][j].getCoord().getY()] = players[getTurn()].getColor();
                    plateau.setRectPlateau(rectPlateau);
                }
                compt++;
            }
        }
    }

    public static void deleteColors(BlokusPlayer[] players, Plateau plateau, Tetrimino t, Tetrimino.Couleur[][] rectPlateau) {
        int compt = 0;
        for (int i = 0; i < t.getnBlignesGrille(); i++) {
            for (int j = 0; j < t.getnBcolsGrille(); j++) {
                if (t.getTABTETRIMINO()[t.getPositionCourante()][compt] == true && t.getCoordCaseTetriminos()[i][j].getCoord().getX() >= 0 && t.getCoordCaseTetriminos()[i][j].getCoord().getX() < plateau.getNbLignes() && t.getCoordCaseTetriminos()[i][j].getCoord().getY() >= 0 && t.getCoordCaseTetriminos()[i][j].getCoord().getY() < plateau.getNbCols()) {
                    rectPlateau[t.getCoordCaseTetriminos()[i][j].getCoord().getX()][t.getCoordCaseTetriminos()[i][j].getCoord().getY()] = Couleur.BLACK;
                    plateau.setRectPlateau(rectPlateau);
                }
                compt++;
            }
        }
    }

    public static BlokusPlayer[] createPlayers() {
        BlokusPlayer[] players = new BlokusPlayer[4];
        Couleur color = Couleur.BLACK;
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    color = Couleur.BLUE;
                    break;
                case 1:
                    color = Couleur.YELLOW;
                    break;
                case 2:
                    color = Couleur.GREEN;
                    break;
                case 3:
                    color = Couleur.RED;
                    break;
                default:
                    break;
            }
            players[i] = new BlokusPlayer(i, color);
        }
        return players;
    }

    public static void rotate(BlokusPlayer[] players, Plateau plateau, Tetrimino currentTetrimino, Couleur[][] couleurs) {
        if (isStarted == true && launched == true) {
            plateau.effaceTracePiece(currentTetrimino);
            currentTetrimino.rotateWithCoord();
            BlokusGameCore.copyRectPlateauColors(players, plateau, currentTetrimino, couleurs);
            plateau.displayPiece(currentTetrimino);
        }
    }

    public static void flipPiece(BlokusPlayer[] players, PlateauBlokus plateau, Tetrimino currentTetrimino, Couleur[][] couleurs) {
        if (isStarted == true && launched == true) {
            plateau.effaceTracePiece(currentTetrimino);
            currentTetrimino.flipOver();
            BlokusGameCore.copyRectPlateauColors(players, plateau, currentTetrimino, couleurs);
            plateau.displayPiece(currentTetrimino);
        }
    }

    public static boolean validatePiece(BlokusPlayer player, Plateau plateau, Tetrimino t) {
        //a modifier
        boolean result = false;
        for (int i : new int[]{0, 4}) {
            for (int j : new int[]{0, 4}) {
                if (i == 0 && j == 0) {
                    if ((t.getCoordCaseTetriminos()[i][j].getCoord().getX() - 1 >= 0) && (t.getCoordCaseTetriminos()[i][j].getCoord().getX() - 1 < plateau.getNbLignes()) && (t.getCoordCaseTetriminos()[i][j].getCoord().getY() - 1 >= 0) && (t.getCoordCaseTetriminos()[i][j].getCoord().getY() - 1 < plateau.getNbCols())) {
                        if (plateau.getRectPlateau()[t.getCoordCaseTetriminos()[i][j].getCoord().getX() - 1][t.getCoordCaseTetriminos()[i][j].getCoord().getY() - 1] == player.getColor()) {
                            return true;
                        }
                    }
                } else if (i == 0 && j == 4) {
                    if ((t.getCoordCaseTetriminos()[i][j].getCoord().getX() - 1 >= 0) && (t.getCoordCaseTetriminos()[i][j].getCoord().getX() - 1 < plateau.getNbLignes()) && (t.getCoordCaseTetriminos()[i][j].getCoord().getY() + 1 >= 0) && (t.getCoordCaseTetriminos()[i][j].getCoord().getY() + 1 < plateau.getNbCols())) {
                        if (plateau.getRectPlateau()[t.getCoordCaseTetriminos()[i][j].getCoord().getX() - 1][t.getCoordCaseTetriminos()[i][j].getCoord().getY() + 1] == player.getColor()) {
                            return true;
                        }
                    }
                } else if (i == 4 && j == 0) {
                    if ((t.getCoordCaseTetriminos()[i][j].getCoord().getX() + 1) >= 0 && (t.getCoordCaseTetriminos()[i][j].getCoord().getX() + 1) < plateau.getNbLignes() && (t.getCoordCaseTetriminos()[i][j].getCoord().getY() - 1) >= 0 && (t.getCoordCaseTetriminos()[i][j].getCoord().getY() - 1) < plateau.getNbCols()) {
                        if (plateau.getRectPlateau()[t.getCoordCaseTetriminos()[i][j].getCoord().getX() + 1][t.getCoordCaseTetriminos()[i][j].getCoord().getY() - 1] == player.getColor()) {
                            return true;
                        }
                    }
                } else if (i == 4 && j == 4) {
                    if ((t.getCoordCaseTetriminos()[i][j].getCoord().getX() + 1) >= 0 && (t.getCoordCaseTetriminos()[i][j].getCoord().getX() + 1) < plateau.getNbLignes() && (t.getCoordCaseTetriminos()[i][j].getCoord().getY() + 1) >= 0 && (t.getCoordCaseTetriminos()[i][j].getCoord().getY() + 1) < plateau.getNbCols()) {
                        if (plateau.getRectPlateau()[t.getCoordCaseTetriminos()[i][j].getCoord().getX() + 1][t.getCoordCaseTetriminos()[i][j].getCoord().getY() + 1] == player.getColor()) {
                            return true;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static boolean validateFirstMove(BlokusPlayer player, Plateau plateau, Tetrimino t) {
        boolean result = false;
        switch (player.getId()) {
            case 0:
                for (int i = 0; i < t.getnBlignesGrille(); i++) {
                    for (int j = 0; j < t.getnBcolsGrille(); j++) {
                        if (t.getCoordCaseTetriminos()[i][j].getCoord().getX() == 0 && t.getCoordCaseTetriminos()[i][j].getCoord().getY() == 0) {
                            return true;
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < t.getnBlignesGrille(); i++) {
                    for (int j = 0; j < t.getnBcolsGrille(); j++) {
                        if (t.getCoordCaseTetriminos()[i][j].getCoord().getX() == 0 && t.getCoordCaseTetriminos()[i][j].getCoord().getY() == 19) {
                            return true;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < t.getnBlignesGrille(); i++) {
                    for (int j = 0; j < t.getnBcolsGrille(); j++) {
                        if (t.getCoordCaseTetriminos()[i][j].getCoord().getX() == 19 && t.getCoordCaseTetriminos()[i][j].getCoord().getY() == 0) {
                            return true;
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < t.getnBlignesGrille(); i++) {
                    for (int j = 0; j < t.getnBcolsGrille(); j++) {
                        if (t.getCoordCaseTetriminos()[i][j].getCoord().getX() == 19 && t.getCoordCaseTetriminos()[i][j].getCoord().getY() == 19) {
                            return true;
                        }
                    }
                }
                break;
            default:
                break;
        }
        return result;
    }

    public static boolean isEntirelyOnBoard(Plateau plateau, Tetrimino t) {
        boolean result = true;
        int compt = 0;
        for (int i = 0; i < t.getnBlignesGrille(); i++) {
            for (int j = 0; j < t.getnBcolsGrille(); j++) {
                if (t.getTABTETRIMINO()[0][compt] == true) {
                    if ((t.getCoordCaseTetriminos()[i][j].getCoord().getX() < 0 || t.getCoordCaseTetriminos()[i][j].getCoord().getY() < 0 || t.getCoordCaseTetriminos()[i][j].getCoord().getX() >= plateau.getNbLignes() || t.getCoordCaseTetriminos()[i][j].getCoord().getY() >= plateau.getNbCols())) {
                        return false;
                    }
                }
                compt++;
            }
        }

        return result;
    }

    /**
     * @return the turn
     */
    public static int getTurn() {
        return turn;
    }

    /**
     * @param aTurn the turn to set
     */
    public static void setTurn(int aTurn) {
        turn = aTurn;
    }
}
