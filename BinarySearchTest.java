import java.security.SecureRandom;
import java.util.Arrays;

public class BinarySearchTest {
    public static void main(String[] args) {
        SecureRandom oRand = new SecureRandom();
        String[] asWords = new String[10];
        asWords[0] = "Monkey";
        asWords[1] = "Elephant";
        asWords[2] = "Turtle";
        asWords[3] = "Dog";
        asWords[4] = "Penguin";
        asWords[5] = "Duck";
        asWords[6] = "Cat";
        asWords[7] = "Giraffe";
        asWords[8] = "Polar Bear";
        asWords[9] = "Koala";

        Arrays.sort(asWords);

        // *************************************
        // *** BEGIN Test Binary Search Call ***
        BinarySearchTest oTest = new BinarySearchTest();
        String sTarget = asWords[oRand.nextInt(asWords.length)];
        System.out.println("Binary Search: " + sTarget);
        // *** END Test Binary Search Call ***
        // ***********************************

        long startTime;
        long elapsedTime;
        int iReturnIndex;

        // ********************************
        // *** BEGIN Time Binary search ***
        startTime = System.nanoTime();
        iReturnIndex = oTest.findNumberBinarySearch(asWords, sTarget, 0, asWords.length - 1);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("Binary Search Time: " + elapsedTime);
        // *** END Time Binary search ***
        // ******************************
    }

    //Find target number's index using recursive binary search.
    private int findNumberBinarySearch(String[] asWords, String sTarget, int sLowIndex, int sHighIndex) {
        //Get middle index.
        int sMiddleIndex = (sLowIndex + sHighIndex) / 2;

        //Check if number at middle index equals target.
        if (asWords[sMiddleIndex].equals(sTarget)) {
            return sMiddleIndex;
        }
        //Check if target number is greater than number at middle index.
        else if (sTarget.compareTo(asWords[sMiddleIndex]) > 0) {
            //Recursively call this method with new limits.
            return findNumberBinarySearch(asWords, sTarget, sMiddleIndex + 1, sHighIndex);
        }
        //Else the target is in lower half, so recursively call this method.
        else {
            return findNumberBinarySearch(asWords, sTarget, sLowIndex, sMiddleIndex - 1);
        }
    }
}