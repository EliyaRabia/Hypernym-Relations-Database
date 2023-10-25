//Name: Eliya Rabia.
//ID: 318771052.

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Nps.
 * this class is about NPs.
 */
public abstract class NPStructure implements NP {
    //represent the start index of the matcher.
    public static final int START = 4;
    //represent the end index of the matcher.
    public static final int END = 5;

    /**
     * The constant NP.
     *
     */
    public static final String NP = "<np>([^<]+)</np>";

    @Override
    public Pattern getNpRegex() {
        return Pattern.compile(NP + "( ,)? "
                                 + this.getNpString() + " (" + NP
                                 + "( ,)? )+( ?or | ?and )?(" + NP + ")?");
    }

    /**
     * Find matcher.
     * @param data      the data
     * @param npMatcher the np matcher
     * @param string    the string
     * @param check     the check
     */
    public void findMatcher(Data data, Matcher npMatcher, String string,
                       boolean check) {
        String hyper = null, hypernym = null;
        try {
            //first np match will be the hyper, or the hypernym in case it's
            // which is pattern.
            if (npMatcher.find()) {
                if (check) {
                    hyper = string.substring(npMatcher.start() + START,
                            npMatcher.end() - END);
                }
                // mean it's which is , so first will be the hypernym.
                if (!check) {
                    hypernym = string.substring(npMatcher.start() + START,
                            npMatcher.end() - END);
                }
            }
            findHypernym(data, npMatcher, string, check, hyper,
                    hypernym);
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    /**
     * Find hypernym.
     * this function find all the hypernym, or if it's which is, find the
     * hyper.
     *
     * @param data      the database
     * @param npMatcher the np matcher
     * @param string    the string
     * @param check     the check
     * @param hyper     the hyper
     * @param hypernym  the hypernym
     */
    public void findHypernym(Data data, Matcher npMatcher,
                                String string, boolean check, String hyper,
                                String hypernym) {
        try {
            while (npMatcher.find()) {
                if (check) {
                    hypernym = string.substring(npMatcher.start() + START,
                            npMatcher.end() - END);
                    data.addHyperOrHypernym(hyper, hypernym);
                }
                if (!check) {
                    hyper = string.substring(npMatcher.start() + START,
                            npMatcher.end() - END);
                    data.addHyperOrHypernym(hyper, hypernym);
                    // because can be only one hyper.
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}
