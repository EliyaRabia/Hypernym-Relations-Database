//Name: Eliya Rabia.
//ID: 318771052.

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.nio.file.Files;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.Comparator;


/**
 * The type Database.
 * this class is about the data.
 */
public class Data {
    private final TreeMap<String, TreeMap<String, Integer>> dataMap;
    private final TreeMap<String, Integer> hypoMap;
    //represent adding one of hypo or hypernym.
    public static final int ADD = 1;

    /**
     * Instantiates a new Database.
     */
    public Data() {
        this.dataMap = new TreeMap<>();
        this.hypoMap = new TreeMap<>();
    }

    /**
     * read files
     * the function read the corpus, and add them to the map.
     *
     * @param address the address of the corpus.
     */
    public void readFiles(String address) {
        File folder = new File(address);
        File[] files = folder.listFiles();
        if (files != null) {
            findMatcher(files);
        }
    }

    /**
     * Find matcher.
     * this function will find all the matchers.
     *
     * @param files the files
     */
    public void findMatcher(File[] files) {
        BufferedReader in;
        NPStructure[] npStructures = {new As(), new Especially(),
                new Including(), new SuchAs(), new WhichIs()};
        for (File file : files) {
            for (NPStructure npStructure : npStructures) {
                try {
                    in = new BufferedReader(new InputStreamReader(new
                            FileInputStream(file)));
                    //read by lines.
                    List<String> lines = Files.readAllLines(file.toPath());
                    for (String line : lines) {
                        Matcher matcher =
                                npStructure.getNpRegex().matcher(line);
                        // this while loop find all the matchers.
                        while (matcher.find()) {
                            npStructure.addToData(this, matcher,
                                    true);
                        }
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * add hyper or hypernym.
     * add a new hypernym and hypernym to the database.
     * at the begging check if the hypernym is already exists.
     *
     * @param hypernym - the hypernym.
     * @param hypo     - the hyponym.
     */
    public void addHyperOrHypernym(String hypernym, String hypo) {
        // if the current hypernym is not in the database
        if (!this.dataMap.containsKey(hypernym)) {
            this.dataMap.put(hypernym, new TreeMap<>());
            this.dataMap.get(hypernym).put(hypo, ADD);
            return;
        }
        // if the current hypernym is in the database
        if (this.dataMap.get(hypernym).containsKey(hypo)) {
            int current = this.dataMap.get(hypernym).get(hypo);
            // add one to the base
            this.dataMap.get(hypernym).put(hypo, current + ADD);
            return;
        }
        // create new hypernym and hyponym.
        this.dataMap.get(hypernym).put(hypo, ADD);
    }

    /**
     * is existed.
     * Returns true if the received hyponym exist in the database.
     *
     * @param find - the hyponym we need to find.
     * @return true if existed, false otherwise.
     */
    public boolean isExist(String find) {
        // this for loop runs on all the hypernym
        for (String hyper : this.dataMap.keySet()) {
            // this for loop runs on all the hyponym that in the hypernyms.
            for (String hyponym : this.dataMap.get(hyper).keySet()) {
                if (hyponym.equals(find)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * find appearances.
     * the method find all the appearances and insert them to dataHypoMap.
     *
     * @param hypo the hypo we need to find
     */
    public void findAppearances(String hypo) {
        // check if the hypo is existing.
        if (!isExist(hypo)) {
            System.out.println("The lemma doesn't appear in the corpus.");
        } else {
            //this for loop found all the hypernym.
            for (String hypernym : this.dataMap.keySet()) {
                if (this.dataMap.get(hypernym).containsKey(hypo)) {
                    this.hypoMap.put(hypernym,
                            this.dataMap.get(hypernym).get(hypo));
                }
            }
        }
    }

    /**
     * print appearances.
     * Print all appearances of the hypernyms.
     */
    public void printAppearances() {
        LinkedHashMap<String, Integer> sortedMap;
        // sort all the hyponym in decreasing order.
        sortedMap = sortTreeMap(this.hypoMap);
        // this for loop prints the map.
        for (String string : sortedMap.keySet()) {
            System.out.println(string + ": (" + this.hypoMap.get(string)
                    + ")");
        }
    }

    /**
     * linked hash map.
     * the method sort the tree map with LinkedHashMap.
     *
     * @param map the map we need to sort.
     * @return the sorted linked hashmap
     */
    public LinkedHashMap<String, Integer> sortTreeMap(TreeMap<String,
            Integer> map) {
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        // sort the LinkedHashMap by decreasing order
        map.entrySet().stream().sorted(
                Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(
                hyponym -> sortedMap.put(hyponym.getKey(), hyponym.getValue()));
        return sortedMap;
    }
}
