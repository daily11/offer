package code.SecondPage;

import util.TreeNode;

import java.util.ArrayList;

public class 二叉树中和为某一值的路径 {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>();
        if (root == null)
            return listAll;
        ArrayList<Integer> list = new ArrayList<Integer>();
        int sum = 0;
        pa(root, target, listAll, list, sum);
        return listAll;
    }

    /**
     *                 1
     *             1        1
     *          1     1   1    1
     *          需求：3
     */
    public void pa(TreeNode root, int target, ArrayList<ArrayList<Integer>> listAll, ArrayList<Integer> list, int sum) {
        if (root == null)
            return;
        sum += root.val;

        if (root.left == null && root.right == null) {
            if (sum == target) {
                list.add(root.val);
                listAll.add(new ArrayList<Integer>(list));
                list.remove(list.size() - 1);
            }
            return;
        }

        list.add(root.val);
        pa(root.left, target, listAll, list, sum);
        pa(root.right, target, listAll, list, sum);
        list.remove(list.size() - 1);
    }
}
