package de.mill.model;

import de.mill.exceptions.AlreadyAquiredException;

import java.util.*;

/**
 * Created by abq329 on 18.06.2015.
 */
public class GameField {

    private static Map<Integer, List<List<Integer>>> millMap = new HashMap<>();
    static{
        List<Integer> list1 = new ArrayList<>(Arrays.asList(0,1,2));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(3,4,5));
        List<Integer> list3 = new ArrayList<>(Arrays.asList(6,7,8));
        List<Integer> list4 = new ArrayList<>(Arrays.asList(9,10,11));
        List<Integer> list5 = new ArrayList<>(Arrays.asList(12,13,14));
        List<Integer> list6 = new ArrayList<>(Arrays.asList(15,16,17));
        List<Integer> list7 = new ArrayList<>(Arrays.asList(18,19,20));
        List<Integer> list8 = new ArrayList<>(Arrays.asList(21,22,23));
        List<Integer> list9 = new ArrayList<>(Arrays.asList(0,9,21));
        List<Integer> list10 = new ArrayList<>(Arrays.asList(3,10,18));
        List<Integer> list11 = new ArrayList<>(Arrays.asList(6,11,15));
        List<Integer> list12 = new ArrayList<>(Arrays.asList(1,4,7));
        List<Integer> list13 = new ArrayList<>(Arrays.asList(16,19,22));
        List<Integer> list14 = new ArrayList<>(Arrays.asList(8,12,17));
        List<Integer> list15 = new ArrayList<>(Arrays.asList(5,13,20));
        List<Integer> list16 = new ArrayList<>(Arrays.asList(2,14,23));

        millMap.put(0, new ArrayList<>(Arrays.asList(list1, list9)));
        millMap.put(1, new ArrayList<>(Arrays.asList(list1, list12)));
        millMap.put(2, new ArrayList<>(Arrays.asList(list1, list16)));
        millMap.put(3, new ArrayList<>(Arrays.asList(list2, list10)));
        millMap.put(4, new ArrayList<>(Arrays.asList(list2, list12)));
        millMap.put(5, new ArrayList<>(Arrays.asList(list2, list15)));
        millMap.put(6, new ArrayList<>(Arrays.asList(list3, list11)));
        millMap.put(7, new ArrayList<>(Arrays.asList(list3, list12)));
        millMap.put(8, new ArrayList<>(Arrays.asList(list3, list14)));
        millMap.put(9, new ArrayList<>(Arrays.asList(list4, list9)));
        millMap.put(10, new ArrayList<>(Arrays.asList(list4, list10)));
        millMap.put(11, new ArrayList<>(Arrays.asList(list4, list11)));
        millMap.put(12, new ArrayList<>(Arrays.asList(list5, list14)));
        millMap.put(13, new ArrayList<>(Arrays.asList(list5, list15)));
        millMap.put(14, new ArrayList<>(Arrays.asList(list5, list16)));
        millMap.put(15, new ArrayList<>(Arrays.asList(list6, list11)));
        millMap.put(16, new ArrayList<>(Arrays.asList(list6, list13)));
        millMap.put(17, new ArrayList<>(Arrays.asList(list6, list14)));
        millMap.put(18, new ArrayList<>(Arrays.asList(list7, list10)));
        millMap.put(19, new ArrayList<>(Arrays.asList(list7, list13)));
        millMap.put(20, new ArrayList<>(Arrays.asList(list7, list15)));
        millMap.put(21, new ArrayList<>(Arrays.asList(list8, list9)));
        millMap.put(22, new ArrayList<>(Arrays.asList(list8, list13)));
        millMap.put(23, new ArrayList<>(Arrays.asList(list8, list16)));

    }

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


    public boolean setStone(int pos, Stone stone) throws AlreadyAquiredException {
        gameField[pos].setAquiringStone(stone);
        MillColor color = stone.COLOR;

        boolean mill = isMill(millMap.get(pos), color);
        System.out.println("Gibt es eine Muehle? " + mill);
        return mill;

    }

    public List<MillColor> getFieldStatus(){
        List<MillColor> retVal = new ArrayList<>();
        for(Position position: gameField){
            retVal.add(position.getColor());
        }
        return retVal;
    }

    /*
    * returns true if all of the elements of one of the elements of iList
    * have the same color
    * */
    private boolean isMill(List<List<Integer>> iList, MillColor color){
        // first element of a value of millMap
        List<Integer> first = iList.get(0);
        // second element of a value of millMap
        List<Integer> second = iList.get(1);

        // checks if all elements of one of the two lists have the same color
        // if so it returns true
        return (gameField[first.get(0)].getColor() == color
             && gameField[first.get(1)].getColor() == color
             && gameField[first.get(2)].getColor() == color)
                ||  (gameField[second.get(0)].getColor() == color
                  && gameField[second.get(1)].getColor() == color
                  && gameField[second.get(2)].getColor() == color);
    }


}
