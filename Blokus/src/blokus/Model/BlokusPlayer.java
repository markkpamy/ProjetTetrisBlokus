/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.Model;

import ModuleGestionPlateau.Tetrimino;
import ModuleGestionPlateau.Tetrimino.Couleur;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author markk
 */
public class BlokusPlayer extends Observable{

    private int id;
    private String name;
    private ArrayList<Tetrimino> leftPieces = new ArrayList<Tetrimino>();
    private boolean turn;
    private int turnNumber;
    private Couleur color;
    private boolean lost =false;

    public BlokusPlayer(int id, String name,Couleur color) {
        this.id = id;
        this.name = name;
        turn=false;
        this.color=color;
        this.turnNumber=0;
        initList();
    }
    public BlokusPlayer() {
        this.id = 0;
        this.name = "";
        this.turnNumber=0;
        initList();
    }
    public BlokusPlayer(int i,Couleur color) {
        this.id = i;
        this.name = "";
        this.color=color;
        this.turnNumber=0;
        initList();
    }

    public  void initList() {
        getLeftPieces().add(new BlokusPieceF());
        getLeftPieces().add(new BlokusPieceI1());
        getLeftPieces().add(new BlokusPieceI2());
        getLeftPieces().add(new BlokusPieceI3());
        getLeftPieces().add(new BlokusPieceI4());
        getLeftPieces().add(new BlokusPieceI5());
        getLeftPieces().add(new BlokusPieceL4());
        getLeftPieces().add(new BlokusPieceL5());
        getLeftPieces().add(new BlokusPieceN());
        getLeftPieces().add(new BlokusPieceO4());
        getLeftPieces().add(new BlokusPieceP());
        getLeftPieces().add(new BlokusPieceT4());
        getLeftPieces().add(new BlokusPieceT5());
        getLeftPieces().add(new BlokusPieceU());
        getLeftPieces().add(new BlokusPieceV3());
        getLeftPieces().add(new BlokusPieceV5());
        getLeftPieces().add(new BlokusPieceW());
        getLeftPieces().add(new BlokusPieceX());
        getLeftPieces().add(new BlokusPieceY());
        getLeftPieces().add(new BlokusPieceZ4());
        getLeftPieces().add(new BlokusPieceZ5());        
    }

    public void removePiece(Tetrimino t) {
        for (int i = 0; i < getLeftPieces().size(); i++) {
            if (getLeftPieces().get(i).getIdTetrimino() == t.getIdTetrimino()) {
                getLeftPieces().remove(i);
                setChanged();
                notifyObservers(1);
            }
        }
    }

    /**
     * 
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the leftPieces
     */
    public ArrayList<Tetrimino> getLeftPieces() {
        return leftPieces;
    }

    /**
     * @param leftPieces the leftPieces to set
     */
    public void setLeftPieces(ArrayList<Tetrimino> leftPieces) {
        this.leftPieces = leftPieces;
    }

    /**
     * @return the turn
     */
    public boolean isTurn() {
        return turn;
    }

    /**
     * @param turn the turn to set
     */
    public void setTurn(boolean turn) {
        if(turn==true){
        setChanged();
        notifyObservers(1);
        }
        this.turn = turn;
    }

    /**
     * @return the color
     */
    public Couleur getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Couleur color) {
        this.color = color;
    }

    public void notPossibleToMove() {
        setChanged();
        notifyObservers(2);
    }
    public void notEntirelyOnBoard() {
        setChanged();
        notifyObservers(3);
    }

    /**
     * @return the turnNumber
     */
    public int getTurnNumber() {
        return turnNumber;
    }

    /**
     * @param turnNumber the turnNumber to set
     */
    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public void notValidCorner() {
        setChanged();
        notifyObservers(4);    
    }

    /**
     * @return the lost
     */
    public boolean isLost() {
        return lost;
    }

    /**
     * @param lost the lost to set
     */
    public void setLost() {
        this.lost = true;
    }

}
