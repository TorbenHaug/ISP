package de.mill.model;

import de.mill.exceptions.AlreadyAquiredException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abq329 on 18.06.2015.
 */
public class GameField {

    private final Position[] gameField;

    public GameField(){
        gameField = new Position[24];
        initGameField();
    }

    private void initGameField() {
        for(int i = 0; i < 24; i++){
            gameField[i] = new Position();
        }
        gameField[0].addNeighbour(gameField[1]);
        gameField[0].addNeighbour(gameField[9]);

        gameField[1].addNeighbour(gameField[0]);
        gameField[1].addNeighbour(gameField[2]);
        gameField[1].addNeighbour(gameField[4]);

        gameField[2].addNeighbour(gameField[1]);
        gameField[2].addNeighbour(gameField[14]);

        gameField[3].addNeighbour(gameField[4]);
        gameField[3].addNeighbour(gameField[10]);

        gameField[4].addNeighbour(gameField[1]);
        gameField[4].addNeighbour(gameField[3]);
        gameField[4].addNeighbour(gameField[5]);
        gameField[4].addNeighbour(gameField[7]);

        gameField[5].addNeighbour(gameField[4]);
        gameField[5].addNeighbour(gameField[13]);

        gameField[6].addNeighbour(gameField[7]);
        gameField[6].addNeighbour(gameField[11]);

        gameField[7].addNeighbour(gameField[4]);
        gameField[7].addNeighbour(gameField[6]);
        gameField[7].addNeighbour(gameField[8]);

        gameField[8].addNeighbour(gameField[7]);
        gameField[8].addNeighbour(gameField[12]);

        gameField[9].addNeighbour(gameField[0]);
        gameField[9].addNeighbour(gameField[10]);
        gameField[9].addNeighbour(gameField[21]);

        gameField[10].addNeighbour(gameField[3]);
        gameField[10].addNeighbour(gameField[9]);
        gameField[10].addNeighbour(gameField[11]);
        gameField[10].addNeighbour(gameField[18]);

        gameField[11].addNeighbour(gameField[6]);
        gameField[11].addNeighbour(gameField[10]);
        gameField[11].addNeighbour(gameField[15]);

        gameField[12].addNeighbour(gameField[8]);
        gameField[12].addNeighbour(gameField[13]);
        gameField[12].addNeighbour(gameField[17]);

        gameField[13].addNeighbour(gameField[5]);
        gameField[13].addNeighbour(gameField[12]);
        gameField[13].addNeighbour(gameField[14]);
        gameField[13].addNeighbour(gameField[20]);

        gameField[14].addNeighbour(gameField[2]);
        gameField[14].addNeighbour(gameField[13]);
        gameField[14].addNeighbour(gameField[23]);

        gameField[15].addNeighbour(gameField[11]);
        gameField[15].addNeighbour(gameField[16]);

        gameField[16].addNeighbour(gameField[15]);
        gameField[16].addNeighbour(gameField[17]);
        gameField[16].addNeighbour(gameField[19]);

        gameField[17].addNeighbour(gameField[12]);
        gameField[17].addNeighbour(gameField[16]);

        gameField[18].addNeighbour(gameField[10]);
        gameField[18].addNeighbour(gameField[19]);

        gameField[19].addNeighbour(gameField[16]);
        gameField[19].addNeighbour(gameField[18]);
        gameField[19].addNeighbour(gameField[20]);
        gameField[19].addNeighbour(gameField[22]);

        gameField[20].addNeighbour(gameField[13]);
        gameField[20].addNeighbour(gameField[19]);

        gameField[21].addNeighbour(gameField[9]);
        gameField[21].addNeighbour(gameField[22]);

        gameField[22].addNeighbour(gameField[19]);
        gameField[22].addNeighbour(gameField[21]);
        gameField[22].addNeighbour(gameField[23]);

        gameField[23].addNeighbour(gameField[14]);
        gameField[23].addNeighbour(gameField[22]);
    }

    public void setStone(int pos, Stone stone) throws AlreadyAquiredException {
        gameField[pos].setAquiringStone(stone);
    }

    public List<MillColor> getFieldStatus(){
        List<MillColor> retVal = new ArrayList<>();
        for(Position position: gameField){
            retVal.add(position.getColor());
        }
        return retVal;
    }
}
