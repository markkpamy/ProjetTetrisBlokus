/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Tetris;

import ModuleGestionPlateau.Case;
import ModuleGestionPlateau.Coordonnees;
import ModuleGestionPlateau.Plateau;
import ModuleGestionPlateau.Tetrimino;
import java.util.Random;


/**
 *
 * @author markk
 */
public class PlateauTetris extends Plateau{

    
    public PlateauTetris(int nbLignes, int nbCols, int nBlignesGrille, int nBcolsGrille) {
        super(nbLignes, nbCols, nBlignesGrille, nBcolsGrille);
    }
    public Tetrimino creePiece() {
        //cree un tableau pour la pièce puis l'initialiser avec les coordonnes vrai des cases dans le tableau
        Random rnd = new Random();
        int valAlea = rnd.nextInt(7) + 1;
        Case[][] c = new Case[4][4];
        int h = 0, v = 3, i, j;
        switch (valAlea) {
            case 1:
                TetriminoI I = new TetriminoI();
                for (i = 0; i < 4; i++) {
                    for (j = 0; j < 4; j++) {
                        c[i][j] = new Case();
                        c[i][j].setCoord(new Coordonnees(h, v));
                        v++;
                    }
                    h++;
                    v = 3;
                }
                I.setCoordCaseTetriminos(c);
                //this.currentTetrimino = I;
                return I;
            case 2:
                TetriminoO O = new TetriminoO();
                for (i = 0; i < 4; i++) {
                    for (j = 0; j < 4; j++) {
                        c[i][j] = new Case();
                        c[i][j].setCoord(new Coordonnees(h, v));
                        v++;
                    }
                    h++;
                    v = 3;
                }
                O.setCoordCaseTetriminos(c);
                //this.currentTetrimino = O;
                return O;
            case 3:
                TetriminoT T = new TetriminoT();
                for (i = 0; i < 4; i++) {
                    for (j = 0; j < 4; j++) {
                        c[i][j] = new Case();
                        c[i][j].setCoord(new Coordonnees(h, v));
                        v++;
                    }
                    h++;
                    v = 3;
                }
                T.setCoordCaseTetriminos(c);
                //this.currentTetrimino = T;
                return T;
            case 4:
                TetriminoL L = new TetriminoL();
                for (i = 0; i < 4; i++) {
                    for (j = 0; j < 4; j++) {
                        c[i][j] = new Case();
                        c[i][j].setCoord(new Coordonnees(h, v));
                        v++;
                    }
                    h++;
                    v = 3;
                }
                L.setCoordCaseTetriminos(c);
                return L;
            case 5:
                TetriminoJ J = new TetriminoJ();
                for (i = 0; i < 4; i++) {
                    for (j = 0; j < 4; j++) {
                        c[i][j] = new Case();
                        c[i][j].setCoord(new Coordonnees(h, v));
                        v++;
                    }
                    h++;
                    v = 3;
                }
                J.setCoordCaseTetriminos(c);
                //this.currentTetrimino = J;
                return J;
            case 6:
                TetriminoZ Z = new TetriminoZ();
                for (i = 0; i < 4; i++) {
                    for (j = 0; j < 4; j++) {
                        c[i][j] = new Case();
                        c[i][j].setCoord(new Coordonnees(h, v));
                        v++;
                    }
                    h++;
                    v = 3;
                }
                Z.setCoordCaseTetriminos(c);
                //this.currentTetrimino = Z;
                return Z;
            case 7:
                TetriminoS S = new TetriminoS();
                for (i = 0; i < 4; i++) {
                    for (j = 0; j < 4; j++) {
                        c[i][j] = new Case();
                        c[i][j].setCoord(new Coordonnees(h, v));
                        v++;
                    }
                    h++;
                    v = 3;
                }
                S.setCoordCaseTetriminos(c);
                //this.currentTetrimino = S;
                return S;
            default:
                return (new Tetrimino(this.getnBlignesGrille(), this.getnBcolsGrille()));
        }

    }
    public void gameOver(){
        setChanged();
        notifyObservers(5);
    }
    
}
