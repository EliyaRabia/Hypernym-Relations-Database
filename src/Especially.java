//Name: Eliya Rabia.
//ID: 318771052.

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Especially.
 * this class is about especially pattern.
 */
public class Especially extends NPStructure {
    @Override
    public String getNpString() {
        return "especially";
    }

    @Override
    public Pattern getNpRegex() {
        return super.getNpRegex();
    }

    @Override
    public void addToData(Data data, Matcher matcher, boolean check) {
        String string = matcher.group();
        // represent <np> NP <\np> to be group 1.
        Pattern npPattern = Pattern.compile("<np>(.*?)</np>");
        Matcher npMatcher = npPattern.matcher(string);
        findMatcher(data, npMatcher, string, check);
    }
}
