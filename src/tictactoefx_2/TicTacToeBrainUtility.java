/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoefx_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author blj0011
 */
public class TicTacToeBrainUtility
{

    public final static String X_ICON_PATH = "xIcon.png";
    public final static String O_ICON_PATH = "oIcon.png";

    private final static String[] WINNING_COMBINATIONS =
    {
        "123", "456", "789", "147", "258", "369", "159", "357"
    };

    private TicTacToeBrainUtility()
    {
//        System.out.println(getTriples("_xo_x_o__") + ": " + iCanWin(getTriples("_xo_x_o__"), 'x'));
//        System.out.println(getTriples("xx_x_o___") + ": " + iCanWin(getTriples("xx_x_o___"), 'x'));
//        System.out.println(getTriples("_xo_x_o__") + ": " + iCanWin(getTriples("_xo_x_o__"), 'o'));
//        System.out.println(getTriples("xx_x_ooo_") + ": " + iCanWin(getTriples("xx_x_ooo_"), 'o'));
//
//        System.out.println(getTriples("_xo_x_o__") + ": " + opponentCanWin(getTriples("_xo_x_o__"), 'x'));
//        System.out.println(getTriples("xx_x_o___") + ": " + opponentCanWin(getTriples("xx_x_o___"), 'x'));
//        System.out.println(getTriples("_xo_x_o__") + ": " + opponentCanWin(getTriples("_xo_x_o__"), 'o'));
//        System.out.println(getTriples("xx_x_ooo_") + ": " + opponentCanWin(getTriples("xx_x_ooo_"), 'o'));

        //System.out.println(iCanFork(getTriples("xo__x___o"), 'x'));
    }

    public static List<String> iCanFork(List<String> triples, char c)
    {
        System.out.println(Arrays.toString(triples.toArray()));
        List<String> tempList = new ArrayList();

        switch (c)
        {
            case 'x':
                for (String triple : triples)
                {
                    if (triple.matches("\\d\\dx") || triple.matches("x\\d\\d") || triple.matches("\\dx\\d"))
                    {
                        tempList.add(triple);
                    }
                }
                break;
            case 'o':
                for (String triple : triples)
                {
                    if (triple.matches("\\d\\do") || triple.matches("o\\d\\d") || triple.matches("\\do\\d"))
                    {
                        tempList.add(triple);
                    }
                }
                break;
        }

        String combine = String.join("", tempList);
        String[] splitString = combine.split("");
        Arrays.sort(splitString);
        System.out.println(Arrays.toString(splitString));

        System.out.println(splitString.length + "  length");
        if(splitString.length > 0 && splitString[0].length() > 0)
        {
            Map<String, Integer> map = new HashMap();

            for (int i = 1; i <= 9; i++)
            {
                for (String tempString : splitString)
                {
                    if (Character.isDigit(tempString.charAt(0)) && i == Integer.parseInt(tempString))
                    {
                        if (map.containsKey(Integer.toString(i)))
                        {
                            int count = map.get(Integer.toString(i));
                            map.put(Integer.toString(i), count + 1);
                        }
                        else
                        {
                            map.put(Integer.toString(i), 1);
                        }

                        System.out.println("found: " + Integer.toString(i) + " " + map.get(Integer.toString(i)));

                    }
                }
            }

            System.out.println("map: " + Arrays.toString(map.values().toArray()));
            List<String> repeatedNumbers = new ArrayList();
            for (Map.Entry<String, Integer> entry : map.entrySet())
            {
                if (entry.getValue() > 1)
                {
                    repeatedNumbers.add(entry.getKey());
                }
            }

            return repeatedNumbers;
        }
        
        return tempList;
    }

    public static List<String> opponentCanFork(List<String> triples, char c)
    {
        return iCanFork(triples, c);
    }

    public static int iCanWin(List<String> triples, char c)
    {
        switch (c)
        {
            case 'x':
                for (String triple : triples)
                {
                    if (triple.matches("\\dxx") || triple.matches("x\\dx") || triple.matches("xx\\d"))
                    {
                        System.out.print("I can win: " + triple + " - ");
                        for (int i = 0; i < triple.length(); i++)
                        {
                            if (Character.isDigit(triple.charAt(i)))
                            {
                                System.out.println("winning qrid: " + triple.charAt(i));
                                return Integer.parseInt(Character.toString(triple.charAt(i)));
                            }
                        }
                    }
                }
            case 'o':
                for (String triple : triples)
                {
                    if (triple.matches("\\doo") || triple.matches("o\\do") || triple.matches("oo\\d"))
                    {
                        System.out.print("opp can win: " + triple + " - ");
                        for (int i = 0; i < triple.length(); i++)
                        {
                            if (Character.isDigit(triple.charAt(i)))
                            {
                                System.out.println("winning qrid: " + triple.charAt(i));
                                return Integer.parseInt(Character.toString(triple.charAt(i)));
                            }
                        }
                    }
                }
        }

        return -1;
    }

    public static int opponentCanWin(List<String> triples, char c)
    {
        return iCanWin(triples, c);
    }

    public static List<String> getTriples(String position)
    {
        if (position.length() != 9)
        {
            System.out.println(position + " lenght does not equal 9");
            return null;
        }
        List<String> tempTriples = new ArrayList();

        for (String winningCombination : WINNING_COMBINATIONS)
        {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < winningCombination.length(); i++)
            {
                int currentWinningComboIndex = Integer.parseInt(Character.toString(winningCombination.charAt(i % 3)));
                switch (position.charAt(currentWinningComboIndex - 1))
                {
                    case 'x':
                        stringBuilder.append("x");
                        break;
                    case 'o':
                        stringBuilder.append("o");
                        break;
                    default:
                        stringBuilder.append(Integer.toString(currentWinningComboIndex));
                        break;
                }
            }

            tempTriples.add(stringBuilder.toString());
        }

        return tempTriples;
    }
    
    public static int playCenter()
    {
        return 5;
    }
    
    public static int playCorner(String currentState)
    {
        Integer[] cornerNums = {1, 3, 7, 9};
        List<Integer> list = Arrays.asList(cornerNums);
        Collections.shuffle(list);
        
        for(Integer num : list)
        {
            if(currentSquareOpen(Integer.toString(num), currentState))
            {
                return num;
            }
        }
        
        return -1;
    }
    
    public static boolean currentSquareOpen(String location, String currentState)
    {
        return currentState.contains(location);
    }
}
