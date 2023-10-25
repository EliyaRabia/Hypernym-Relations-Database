//Name: Eliya Rabia.
//ID: 318771052.

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type As.
 *this class is about as pattern.
 */
public class As extends NPStructure {
    @Override
    public String getNpString() {
        return "as";
    }

    @Override
    public Pattern getNpRegex() {
        return Pattern.compile("such " + NP + "( , )? "
                + this.getNpString() + " (" + NP + "( , )?)+( ?or | ?and )?("
                + NP + ")?");
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
