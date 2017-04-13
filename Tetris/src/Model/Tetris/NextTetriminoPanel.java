/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Tetris;

import ModuleGestionPlateau.Case;
import ModuleGestionPlateau.Coordonnees;
import ModuleGestionPlateau.Tetrimino;
import java.util.Observable;

/**
 *
 * @author markk
 */
public class NextTetriminoPanel extends Observable{
     
    private boolean[][] nextTetriminoPanel;
    private final Case[][] coordCasesGrille;
    public NextTetriminoPanel(){
        this.coordCasesGrille = new Case[6][4];
        this.nextTetriminoPanel = new boolean[6][4];
    for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                this.nextTetriminoPanel[i][j] = false;
                this.coordCasesGrille[i][j] = new Case(new Coordonnees(i, j));
            }
        }
    }
    public void displayNextTetriminoOne(Tetrimino nextTetrimino){
        clear(0,2,0,4);
        int compt=0;
        for(int i=0;i<2;i++){           
            for(int j=0;j<4;j++){              
            if(nextTetrimino.getTABTETRIMINO()[0][compt] == true ){
                    if(this.nextTetriminoPanel[i][j] == false)
                    setGrilleCaseTrue(i, j);              
                }  
            compt++;
            }
        }
    
    }
    public void displayNextTetriminoTwo(Tetrimino nextTetrimino){
        int compt=0;
        clear(2,4,0,4);
        for(int i=2;i<4;i++){
            for(int j=0;j<4;j++){              
                if(nextTetrimino.getTABTETRIMINO()[0][compt] == true ){
                    if(this.nextTetriminoPanel[i][j] == false)
                    setGrilleCaseTrue(i, j);              
                }
                compt++;
            }
        }
    
    }
    public void displayNextTetriminoThree(Tetrimino nextTetrimino){
        int compt=0;
        clear(4,6,0,4);
        for(int i=4;i<6;i++){           
            for(int j=0;j<4;j++){               
            if(nextTetrimino.getTABTETRIMINO()[0][compt] == true ){
                    if(this.nextTetriminoPanel[i][j] == false)
                    setGrilleCaseTrue(i, j);              
                }
            compt++;
            }
        }
    
    }
    private void setGrilleCaseTrue(int i, int j) {
        if (this.nextTetriminoPanel[i][j] != true) {
            this.nextTetriminoPanel[i][j] = true;
            setChanged();
            notifyObservers(getCoordCasesGrille()[i][j]);
        }
    }

    //public void displayPiece(Tetrimino t){
    private void setGrilleCaseFalse(int i, int j) {
        if (this.nextTetriminoPanel[i][j] != false) {
            this.nextTetriminoPanel[i][j] = false;
            setChanged();
            notifyObservers(getCoordCasesGrille()[i][j]);
        }
    }
    
    private void clear(int a,int b,int c,int d){
        for(int i=a;i<b;i++){
            for(int j=c;j<d;j++){
            setGrilleCaseFalse( i,  j);
            }
        }
    }

    /**
     * @return the coordCasesGrille
     */
    public Case[][] getCoordCasesGrille() {
        return coordCasesGrille;
    }
}
