public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){

        this.merger = merger;

        data = (E[])new Object[arr.length];
        for(int i = 0 ; i < arr.length ; i ++)
            data[i] = arr[i];

        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r){

        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // int mid = (l + r) / 2;
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2*index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return 2*index + 2;
    }

    /**
     * 搜素区间区间[queryL, queryR]的值  左右两个区间
     */
    public E query(int queryL, int queryR){

        if(queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值

    /**
     * 搜素queryL 到queryR 的区间的值
     * @param treeIndex  当前节点索引
     * @param l 当前节点左区间信息
     * @param r 当前节点右区间信息
     * @param queryL 用户输入区间
     * @param queryR  用户输入区间
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR){

        if(l == queryL && r == queryR) // 直接就是节点 不是区间
            return tree[treeIndex];

        int mid = l + (r - l) / 2; // 中间值
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分

        int leftTreeIndex = leftChild(treeIndex); // 当前节点左右索引
        int rightTreeIndex = rightChild(treeIndex);
        if(queryL >= mid + 1) // 坐边界大于中间值 在右子树  直接查找右边子树
            return query(rightTreeIndex, mid + 1, r, queryL, queryR); // 递归
        else if(queryR <= mid) // 如果右边界 比中间值小  直接去左子树找
            return query(leftTreeIndex, l, mid, queryL, queryR);
        /**
         * 如果两种情况都不是，那就有点小麻烦，左右两个节点都要查询
         * 先查左边界到mid
         * 再查mid到右边界
         * 然后再融合
         */
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
