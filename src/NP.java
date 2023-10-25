//Name: Eliya Rabia.
//ID: 318771052.

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The interface NP.
 * this class is about the NP.
 */
public interface NP {
    /**
     * get the kind of the relation.
     *
     * @return the string
     */
    String getNpString();

    /**
     * return the regax.
     *
     * @return the regex
     */
    Pattern getNpRegex();

    /**
     * Add to data.
     *
     * @param data the database we need to add to
     * @param matcher  the matcher that we got.
     * @param check    check which type of tag is it.
     */
    void addToData(Data data, Matcher matcher, boolean check);
}
