/* 
 * Catalogue.java
 * @author Ethan Ly
 * @author Chandan Reddy
 * CIS 36B, Lab 7.2
 */

import java.io.IOException;
import java.util.Scanner;

public interface Catalogue {
    /**
     * Reads from a file and populates the catalogue
     * @param input the Scanner used to read in the data
     */
    void populateCatalogue(Scanner input) throws IOException;
    
    /**
     * Searches for Media m in the catalogue
     * @param m the piece of media to locate
     * @return the location of the media in 
     * the catalogue
     */
    int binarySearch(Media m);
    
    /** 
     * Sorts the catalogue into
     * ascending order
     */
    void bubbleSort();
    
    /**
     * Prints a menu of options to interact
     * with the catalogue
     */
    void printMenu();
    
    /**
     * Prints out the current catalogue
     */
    void printStock();
}