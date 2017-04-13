/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.Model;

import ModuleGestionPlateau.Plateau;


/**
 *
 * @author markk
 */
public class PlateauBlokus extends Plateau{
    
    private int turn=0;
    private BlokusPlayer player ;
    public PlateauBlokus(int nbLignes, int nbCols, int nBlignesGrille, int nBcolsGrille) {
        super(nbLignes, nbCols, nBlignesGrille, nBcolsGrille);
    }

    /**
     * @return the turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * @param turn the turn to set
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * @return the player
     */
    public BlokusPlayer getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(BlokusPlayer player) {
        this.player = player;
    }
    
    
    
}
