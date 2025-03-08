// imports
package src;
import java.util.*;
import java.io.*;
import java.util.Comparator;

// comparator
class compare implements Comparator<Member> {
    public int compare(Member first, Member second) {
        // compares adjacent elements and checks if they are equal or one is greater/less than the other
        if (first.getIDNumber() == second.getIDNumber()) {
            return 0;
        } else if (first.getIDNumber() > second.getIDNumber()) {
            return 1;
        } else {
            return -1;
        }
    }
}