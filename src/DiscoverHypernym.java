//Name: Eliya Rabia
//ID: 318771052

/**
 * The type Discover hypernym.
 * this is class is about discover hypernym.
 */
public class DiscoverHypernym {
    // represent the first place in args.
    public static final int FILE_ADDRESS = 0;
    //represent the second place in args.
    public static final int LEMMA = 1;
    //represent length equal to 2.
    public static final int LENGTH = 2;
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        StringBuilder lemma = null;
        Data data = new Data();
        // in case the lemma is only one word.
        if (args.length == LENGTH) {
            data.readFiles(args[FILE_ADDRESS]);
            lemma = new StringBuilder(args[LEMMA]);
            // in case the lemma is more than one word
        } else if (args.length > LENGTH) {
            data.readFiles(args[FILE_ADDRESS]);
            lemma = new StringBuilder(args[LEMMA]);
            // this for loop create a one string that include the lemma .
            for (int i = LENGTH; i < args.length; i++) {
                lemma.append(" ").append(args[i]);
            }
        } else {
            System.out.println("ERROR");
        }
        data.findAppearances(String.valueOf(lemma));
        data.printAppearances();
    }
}
