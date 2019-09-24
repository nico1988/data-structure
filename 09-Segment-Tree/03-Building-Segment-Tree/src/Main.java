public class Main {

    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};
//        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
//                new Merger<Integer>() {
//                    @Override
//                    public Integer merge(Integer a, Integer b) {
//                        return a + b;
//                    }
//                });
        /**
         * 这个东西和前端的箭头函数怎么那么像？ 和prioriytREE的comparator右异曲同工之处
         */
        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
                (a, b) -> a + b);
        System.out.println(segTree);
    }
}
