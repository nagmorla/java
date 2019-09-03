
import java.util.Arrays;

/**
 * It identifies maximum mirror of an array. Mirror: is a group of contiguous
 * elements such that somewhere in the array, the same group appears in reverse
 * order
 *
 * @author nmorla
 * @since Aug 27, 2019
 */
public class MaxMirror {

    public static void main(String[] args) {
        int[] sarr = {1, 2, 3, 4, 2, 1};
//        int[] sarr = {7, 1, 2, 9, 7, 2, 1};
        //                      1 2 7 9 2 1 7
        int[] darr = new int[sarr.length];
        System.arraycopy(sarr, 0, darr, 0, sarr.length);
        reverse(darr);
        System.out.println("SRC:" + Arrays.toString(sarr));
        System.out.println("DST:" + Arrays.toString(darr));
        int nMaxMatrix = getMax(sarr, darr);
        System.out.println("Max Matrix:" + nMaxMatrix);
    }

    static int getMax(int[] sarr, int[] darr) {
        int tmpMaxMatrix = 0;
        int nMaxMatrix = 0;
        int nSrcArryIndx;
        outer:
        for (int i = 0; i < sarr.length; i++) {
            nSrcArryIndx = i;
            for (int j = 0; j < darr.length; j++) {
                if (sarr[nSrcArryIndx] == darr[j]) {
                    ++nSrcArryIndx;
                    ++tmpMaxMatrix;
                    if (nSrcArryIndx >= sarr.length) {
                        return nMaxMatrix < tmpMaxMatrix ? tmpMaxMatrix : nMaxMatrix;
                    }
                } else {
                    System.out.print("");
                    //If atleast one match occured
                    if (tmpMaxMatrix > 0) {
                        nMaxMatrix = nMaxMatrix < tmpMaxMatrix ? tmpMaxMatrix : nMaxMatrix;
                        tmpMaxMatrix = 0;
                        break;
                    }
                }
                if (j == darr.length - 1) {
                    if (tmpMaxMatrix > 0) {
                        nMaxMatrix = nMaxMatrix < tmpMaxMatrix ? tmpMaxMatrix : nMaxMatrix;
                        tmpMaxMatrix = 0;
                    }
                }
            }
        }
        return nMaxMatrix;
    }

    static void reverse(int a[]) {
        int n = a.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = temp;
        }
    }

}
