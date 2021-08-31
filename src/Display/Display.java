package Display;
import java.util.*;
import Summary.Group;
import Summary.Metric;
import Summary.Results;
import Data.*;
import java.util.ArrayList;
public class Display {
    public static void tabular(Group group, Metric metric, Results results) {

        ArrayList<Integer> displayValue = query(group, metric, results);

        ArrayList<String> range = groupRange(group);

        interface tableInterface {
            String cellWidthFirstCol(int width, String value);

            String cellWidth(int width, String value);

            String horizontalBorder(int width);

            String horizontalBorderFirstCol(int width);
        }
        tableInterface table = new tableInterface() {
            public String cellWidth(int width, String value) {
                String cell = " ";
                int padding = (width - value.length());
                cell = " " + value + cell.repeat(padding - 1) + "|";
                return cell;
            }

            public String cellWidthFirstCol(int width, String value) {
                String cell = cellWidth(width, value);
                cell = "|" + cell;
                return cell;
            }

            public String horizontalBorder(int width) {
                String border = "-";
                border = border.repeat(width);
                border += "+";
                return border;
            }

            public String horizontalBorderFirstCol(int width) {
                String border = horizontalBorder(width);
                border = "+" + border;
                return border;
            }
        };

        int rangeColWidth = 35;
        int valueColWidth = 15;

        System.out.println(
                table.horizontalBorderFirstCol(rangeColWidth) + table.horizontalBorder(valueColWidth));
        System.out.println(
                table.cellWidthFirstCol(rangeColWidth, "Range") + table.cellWidth(valueColWidth, "Value"));
        System.out.println(
                table.horizontalBorderFirstCol(rangeColWidth) + table.horizontalBorder(valueColWidth));

        for (int i = 0; i < range.size(); i++) {
            System.out.println(
                    table.cellWidthFirstCol(rangeColWidth, range.get(i)) + table.cellWidth(valueColWidth,
                            displayValue.get(i).toString()));
        }
        System.out.println(
                table.horizontalBorderFirstCol(rangeColWidth) + table.horizontalBorder(valueColWidth));


    }

    public static void chart(Group group, Metric metric, Results results) {
        int vertical = 24;
        int horizontal = 80;
        int spacing;
        Character[][] displayChart = new Character[vertical][horizontal];
        ArrayList<Integer> displayValue = query(group, metric, results);
        ArrayList<Integer> chartPositionValue = new ArrayList<>();

        if (group.getGrouping().size() > 79) {
            System.out.println("Cannot perform chart with more than 79 groups.");
            return;
        }

        int max = 0;
        int zeroCount = 0;
        for (Integer i : displayValue) {
            if (max < i) {
                max = i;
            }
            if (i == 0) {
                zeroCount += 1;
            }
        }

        for (Integer value : displayValue) {
            int position = vertical - 2;
            if (zeroCount != displayValue.size()) {
                position = Math.round((vertical - 2) - (((float) value / max)) * (vertical - 2));
            }
            chartPositionValue.add(position);
        }

        spacing = (int) Math.floor((float) (horizontal - 1) / displayValue.size());
        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < horizontal; j++) {
                displayChart[i][j] = ' ';
            }
        }

        int index = 0;
        for (int i = 1; i <= chartPositionValue.size() * spacing; i += spacing) {
            displayChart[chartPositionValue.get(index)][i] = '*';
            index += 1;
        }

        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < horizontal; j++) {
                if (i == vertical - 1) {
                    displayChart[i][j] = '_';
                }
                if (j == 0) {
                    displayChart[i][j] = '|';
                }
            }
        }
        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < horizontal; j++) {
                if (j != horizontal - 1) {
                    System.out.print(displayChart[i][j]);
                } else {
                    System.out.print(displayChart[i][j]);
                }
            }
        }
    }


    public static ArrayList<Integer> query(Group group, Metric metric, Results results) {

        ArrayList<ArrayList<Integer>> value = new ArrayList<>();
        for (int i = 0; i < group.getGrouping().size(); i++) {
            ArrayList<Integer> valueGroup = new ArrayList<>();
            for (int j = 0; j < group.getGrouping().get(i).size(); i++) {
                switch (metric) {
                    case "a" -> {
                        int k = group.getGrouping().get(i).get(j).getNewCases();
                        valueGroup.add(k);
                    }
                    case "b" -> {
                        int k = group.getGrouping().get(i).get(j).getNewDeaths();
                        valueGroup.add(k);
                    }
                    case "c" -> {
                        int k = group.getGrouping().get(i).get(j).getVaccinated();
                        valueGroup.add(k);
                    }
                }
            }
            value.add(valueGroup);
        }
        ArrayList<Integer> displayValue = new ArrayList<>();
        int groupValue = 0;
        for (ArrayList<Integer> integers : value) {
            for (int j = 0; j < integers.size(); j++) {
                groupValue += integers.get(j);
                switch (results) {
                    //NEW TOTAL
                    case "a" -> {
                        if (j == integers.size() - 1) {
                            displayValue.add(groupValue);
                        }
                    }
                    //UP TO
                    case "b" -> {
                        if (j == integers.size() - 1) {
                            displayValue.add(groupValue);
                            groupValue = 0;
                        }
                    }
                }
            }
        }
        return displayValue;
    }
    public static ArrayList<String> groupRange(Group groupObject) {
        ArrayList<ArrayList<Data>> groups = groupObject.getGrouping();
        ArrayList<String> listOfRange = new ArrayList<>();
        for (ArrayList<Data> group : groups) {
            String startDay = group.get(0).getLocalDate();
            String endDay = group.get(group.size() - 1).getLocalDate();
            if (group.size() > 1) {
                listOfRange.add(startDay + "-" + endDay);
            } else {
                listOfRange.add(startDay);
            }
        }
        return listOfRange;
    }
}
