/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Tetris;

import ModuleGestionPlateau.Tetrimino;


/**
 *
 * @author markk
 */
public class TetriminoI extends Tetrimino{
    private static final boolean[][] TABTETRIMINO = new boolean[][]{
            {false,false,false,false,true,true,true,true,false,false,false,false,false,false,false,false}, 
            {false,false,true,false,false,false,true,false,false,false,true,false,false,false,true,false}, 
            {false,false,false,false,false,false,false,false,true,true,true,true,false,false,false,false}, 
            {false,true,false,false,false,true,false,false,false,true,false,false,false,true,false,false}
        };
    public static final Couleur color = Couleur.CYAN; 
    
    public TetriminoI() {
         super(1,"TetriminoI",0,TABTETRIMINO,4,4,color);
    }

}
