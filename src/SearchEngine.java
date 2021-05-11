/*
 * Name: Lohit Geddam
 * PID:  A16374851
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Search Engine implementation.
 * 
 * @author Lohit Geddam
 * @since  05/10/21
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    private static final int ACTORS_MOVIES = 0;
    private static final int STUDIOS_MOVIES = 1;
    private static final int ACTORS_RATINGS = 2;
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String[] cast = scanner.nextLine().split(" ");
                String[] studios = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                // populate movieTree
                for (String c: cast) {
                    movieTree.insert(c.toLowerCase());
                    if (!movieTree.findDataList(c.toLowerCase()).contains(movie)) {
                        movieTree.insertData(c.toLowerCase(), movie.toLowerCase());
                    }
                }

                // populate ratingTree
                for (String c: cast) {
                    ratingTree.insert(c.toLowerCase());
                    if (!ratingTree.findDataList(c.toLowerCase()).contains(rating)) {
                        ratingTree.insertData(c.toLowerCase(), rating);
                    }
                }

                // populate studioTree
                for (String s: studios) {
                    studioTree.insert(s.toLowerCase());
                    if (!studioTree.findDataList(s.toLowerCase()).contains(movie)) {
                        studioTree.insertData(s.toLowerCase(), movie.toLowerCase());
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {
        // process query
        String[] keys = query.toLowerCase().split(" ");
        LinkedList<String> allReferences = new LinkedList<String>();
        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful

        //populate allReferences
        for (String k: keys) {
            if (!searchTree.findKey(k)) {
                continue;
            }
            LinkedList<String> temp = searchTree.findDataList(k);
            for (int i = 0; i < temp.size(); i++) {
                if (!allReferences.contains(temp.get(i))) {
                    allReferences.add(temp.get(i));
                }
            }
        }
        // refine allReferences and remove any items not shared
        for (String k: keys) {
            if (!searchTree.findKey(k)) {
                allReferences.clear();
                break;
            }
            LinkedList<String> temp = searchTree.findDataList(k);
            allReferences.retainAll(temp);
        }
        print(query, allReferences);
        if (keys.length == 1) {
            return;
        }
        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful
        ArrayList<String> usedTerms = new ArrayList<String>();
        // look for any unique items for each key
        for (String k: keys) {
            LinkedList<String> temp = new LinkedList<String>();
            if (!searchTree.findKey(k)) {
                print(k, temp);
                continue;
            }
            LinkedList<String> keyData = searchTree.findDataList(k);
            // keep track of unique items
            for (int i = 0; i < keyData.size(); i++) {
                if (!allReferences.contains(keyData.get(i))
                        && !usedTerms.contains(keyData.get(i))) {
                    temp.add(keyData.get(i));
                    usedTerms.add(keyData.get(i));
                }
            }
            // avoid printing unnecessary messages
            if (!temp.isEmpty() || allReferences.isEmpty()) {
                print(k, temp);
            }
        }
    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // initialize search trees
        BSTree<String> movieTree = new BSTree<String>();
        BSTree<String> ratingTree = new BSTree<String>();
        BSTree<String> studioTree = new BSTree<String>();

        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);
        String query = "";
        for (int i = 2; i < args.length; i++) {
            query += " " + args[i];
        }
        query = query.substring(1);
        // populate search trees
        populateSearchTrees(movieTree, studioTree, ratingTree, fileName);
        // choose the right tree to query
        if (searchKind == ACTORS_MOVIES) {
            searchMyQuery(movieTree, query);
        } else if (searchKind == STUDIOS_MOVIES) {
            searchMyQuery(studioTree, query);
        } else if (searchKind == ACTORS_RATINGS) {
            searchMyQuery(ratingTree, query);
        }
    }
}
