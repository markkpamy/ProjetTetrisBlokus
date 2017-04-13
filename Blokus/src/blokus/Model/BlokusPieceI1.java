/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.Model;

import ModuleGestionPlateau.Tetrimino;


/**
 *
 * @author markk
 */
public class BlokusPieceI1 extends Tetrimino{

    public BlokusPieceI1() {
        super(2, "PentaminoI1", 0, TABTETRIMINO, 5, 5, color);
    }
    public static Couleur color = Couleur.CYAN;
    
    private static boolean [][] TABTETRIMINO = new boolean[][]{
        {false, false, false, false, false,
         false, false, false, false, false,
        false, false, true, false, false,
        false, false, false, false, false,
        false, false, false, false, false}
    } ;
    
    
}
