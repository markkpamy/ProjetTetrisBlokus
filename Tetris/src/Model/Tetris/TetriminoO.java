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
public class TetriminoO extends Tetrimino{
    private static final boolean[][] TABTETRIMINO=new boolean[][]{
            {false,true,true,false,false,true,true,false,false,false,false,false,false,false,false,false},
            {false,true,true,false,false,true,true,false,false,false,false,false,false,false,false,false}, 
            {false,true,true,false,false,true,true,false,false,false,false,false,false,false,false,false}, 
            {false,true,true,false,false,true,true,false,false,false,false,false,false,false,false,false}
        };
    public static final Couleur color = Couleur.YELLOW;
    public TetriminoO() {
        super(2,"TetriminoO",0,TABTETRIMINO,4,4,color);
    }
}
