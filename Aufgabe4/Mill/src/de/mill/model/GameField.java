package de.mill.model;

import de.mill.enums.MillColor;
import de.mill.exceptions.AlreadyAquiredException;
import de.mill.exceptions.MoveNotAllowedException;

import java.util.*;

/**
 * Created by abq329 on 18.06.2015.
 */
public class GameField {

    private static Map<Integer, List<List<Integer>>> millMap = new HashMap<>();
    private static final List<Integer>[] neighbours = new ArrayList[24];
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

        initNeighbours();

    }

    private List<Integer> nonStoneList;

    private static void initNeighbours(){
        for (int i = 0; i < 24; i++ ){
            neighbours[i] = new ArrayList<>();
        }
        neighbours[0].add(1);
        neighbours[0].add(9);

        neighbours[1].add(0);
        neighbours[1].add(2);
        neighbours[1].add(4);

        neighbours[2].add(1);
        neighbours[2].add(14);

        neighbours[3].add(4);
        neighbours[3].add(10);

        neighbours[4].add(1);
        neighbours[4].add(3);
        neighbours[4].add(5);
        neighbours[4].add(7);

        neighbours[5].add(4);
        neighbours[5].add(13);

        neighbours[6].add(7);
        neighbours[6].add(11);

        neighbours[7].add(4);
        neighbours[7].add(6);
        neighbours[7].add(8);

        neighbours[8].add(7);
        neighbours[8].add(12);

        neighbours[9].add(0);
        neighbours[9].add(10);
        neighbours[9].add(21);

        neighbours[10].add(3);
        neighbours[10].add(9);
        neighbours[10].add(11);
        neighbours[10].add(18);

        neighbours[11].add(6);
        neighbours[11].add(10);
        neighbours[11].add(15);

        neighbours[12].add(8);
        neighbours[12].add(13);
        neighbours[12].add(17);

        neighbours[13].add(5);
        neighbours[13].add(12);
        neighbours[13].add(14);
        neighbours[13].add(20);

        neighbours[14].add(2);
        neighbours[14].add(13);
        neighbours[14].add(23);

        neighbours[15].add(11);
        neighbours[15].add(16);

        neighbours[16].add(15);
        neighbours[16].add(17);
        neighbours[16].add(19);

        neighbours[17].add(12);
        neighbours[17].add(16);

        neighbours[18].add(10);
        neighbours[18].add(19);

        neighbours[19].add(16);
        neighbours[19].add(18);
        neighbours[19].add(20);
        neighbours[19].add(22);

        neighbours[20].add(13);
        neighbours[20].add(19);

        neighbours[21].add(9);
        neighbours[21].add(22);

        neighbours[22].add(19);
        neighbours[22].add(21);
        neighbours[22].add(23);

        neighbours[23].add(14);
        neighbours[23].add(22);

    }

    public static boolean isNeighbour(int pos1, int pos2){
        return neighbours[pos1].contains(pos2);
    }


    private final MillColor[] gameField;

    public GameField(){
        gameField = new MillColor[24];
        initGameField();
    }

    public GameField(GameField gameField){
        this.gameField = Arrays.copyOf(gameField.gameField, gameField.gameField.length);
    }

    private void initGameField() {
        for(int i = 0; i < 24; i++){
            gameField[i] = MillColor.Non;
        }
    }

    public void setStone(int pos, MillColor stone) throws AlreadyAquiredException {
        if(gameField[pos] == MillColor.Non || stone == MillColor.Non) {
            gameField[pos] = stone;
        }else {
            throw new AlreadyAquiredException();
        }
    }

    public List<MillColor> getFieldStatus(){
        return Arrays.asList(gameField);
    }

    /*
    * returns true if all of the elements of one of the elements of iList
    * have the same color
    * */
    public boolean isMill(int pos){
        List<List<Integer>> iList = millMap.get(pos);
        // first element of a value of millMap
        List<Integer> first = iList.get(0);
        // second element of a value of millMap
        List<Integer> second = iList.get(1);

        // checks if all elements of one of the two lists have the same color
        // if so it returns true
        return ((gameField[first.get(0)] ==  gameField[first.get(1)]
              && gameField[first.get(1)] == gameField[first.get(2)])
              ||(gameField[second.get(0)] == gameField[second.get(1)]
              && gameField[second.get(2)]== gameField[second.get(1)]));
    }


    public void removeStone(int pos) {
        try {
            setStone(pos, MillColor.Non);
        } catch (AlreadyAquiredException e) {

        }
    }

    public boolean isOneStoneNotInMill(List<Integer> stones){
        for (Integer stone: stones){
            if (!isMill(stone)){
                return true;
            }
        }
        return false;
    }
    public MillColor getColorFor(int pos){
        return gameField[pos];
    }

    public void moveStone(int from, int to) throws MoveNotAllowedException {
        try {
            setStone(to, gameField[from]);
            setStone(from,MillColor.Non);
        } catch (AlreadyAquiredException e) {
            throw new MoveNotAllowedException("From: " + from + ", To" + to);
        }

    }


    public boolean hasMoves(List<Integer> stonesOnField) {
        for(int pos : stonesOnField){
            for(int p : neighbours[pos]){
                if (gameField[p] == MillColor.Non){
                    return true;
                }
            }
        }
        return false;
    }

    public List<Integer> getNonStoneList() {
        List<Integer> nonStoneList = new ArrayList<>();
         for (int i=0; i<gameField.length; i++){
             if (gameField[i] == MillColor.Non){
                 nonStoneList.add(i);
             }
         }
        return nonStoneList;
    }

    public List<Integer> getPossibleMovesFor(int pos) {
        List<Integer> possibleMoves = new ArrayList<>();
        for(int possiblePos : neighbours[pos]){
            if(gameField[possiblePos] == MillColor.Non){
                possibleMoves.add(possiblePos);
            }
        }
        return possibleMoves;
    }
}
