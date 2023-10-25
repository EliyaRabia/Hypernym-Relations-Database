//Name: Eliya Rabia.
//ID: 318771052.

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Which is.
 * this class is about which is pattern.
 */
public class WhichIs extends NPStructure {
    @Override
    public String getNpString() {
        return "which is";
    }

    @Override
    public Pattern getNpRegex() {
        return Pattern.compile(NP + "( , )?" + " " + this.getNpString()
                + " " + "((an example|a kind|a class)? of )?" + NP);
    }

    @Override
    public void addToData(Data data, Matcher matcher, boolean check) {
        check = false;
        String string = matcher.group();
        // represent <np> NP <\np> to be group 1.
        Pattern npPattern = Pattern.compile("<np>(.*?)</np>");
        Matcher npMatcher = npPattern.matcher(string);
        findMatcher(data, npMatcher, string, check);
    }
}
