package ru.develgame.codelab.java.stringcollation;

import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        String ruRule = "< 0 < 1 < 2 < 3 < 4 < 5 < 6 < 7 < 8 < 9 " +
                "< a < b < c < d < e < f < g < h < i < j < k < l < m < n < o < p < q < r < s < t < u < v < w < x < y < z " +
                "< а < б < в < г < д < е < ё < ж < з < и < й < к < л < м < н < о < п < р < с < т < у < ф < х < ц < ч < ш < щ < ъ < ы < ь < я < ю < я";
        RuleBasedCollator collator = new RuleBasedCollator(ruRule);

        List<String> list = new ArrayList<>(List.of("зз", "аа", "ее", "бб", "вв", "ёё", "ёа"));
        list.sort(collator);
        System.out.println(list);

        list = new ArrayList<>(List.of("0", "9", "1", "ab", "зз", "аа", "ее", "бб", "вв", "ёё", "ёа", "ff", "fs", "aa", "yj", "qwerty"));
        list.sort(collator);
        System.out.println(list);
    }
}
