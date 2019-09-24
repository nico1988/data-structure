public class Main {

    public static void main(String[] args) {

        int[] arr = new int[10];
        for(int i = 0 ; i < arr.length ; i ++)
            arr[i] = i;

        int[] scores = new int[]{100, 99, 66};
        int[] scores1 = new int[]{1,2,3,6,4,5};
        for(int i = 0 ; i < scores.length ; i ++)
            System.out.println(scores[i]);
        for (int i : scores1) {
            System.out.println(scores[i]);
        }
        for(int score: scores)
            System.out.println(score);

        scores[0] = 96;

        for(int i = 0 ; i < scores.length ; i ++)
            System.out.println(scores[i]);
        System.out.println(1);
    }
}
