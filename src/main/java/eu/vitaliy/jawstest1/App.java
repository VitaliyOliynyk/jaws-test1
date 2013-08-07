package eu.vitaliy.jawstest1;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        if (args.length == 0) {
            args = new String[]{"hello"};
        }

        if(!System.getProperties().containsKey("wordnet.database.dir")){
            System.setProperty("wordnet.database.dir", "c:\\Program Files (x86)\\WordNet\\2.1\\dict\\");
        }

        //  Concatenate the command-line arguments
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            buffer.append((i > 0 ? " " : "") + args[i]);
        }
        String wordForm = buffer.toString();
        //  Get the synsets containing the wrod form
        WordNetDatabase database = WordNetDatabase.getFileInstance();
        Synset[] synsets = database.getSynsets(wordForm);
        //  Display the word forms and definitions for synsets retrieved
        if (synsets.length > 0) {
            System.out.println("The following synsets contain '" +
                    wordForm + "' or a possible base form " +
                    "of that text:");
            for (int i = 0; i < synsets.length; i++) {
                System.out.println("");
                String[] wordForms = synsets[i].getWordForms();
                for (int j = 0; j < wordForms.length; j++) {
                    System.out.print((j > 0 ? ", " : "") +
                            wordForms[j]);
                }
                System.out.println(": " + synsets[i].getDefinition());
            }
        } else {
            System.err.println("No synsets exist that contain " +
                    "the word form '" + wordForm + "'");
        }

    }
}
