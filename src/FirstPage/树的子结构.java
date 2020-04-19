package FirstPage;

import util.TreeNode;

/**
 * https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/solution/mian-shi-ti-26-shu-de-zi-jie-gou-xian-xu-bian-li-p/
 */
public class 树的子结构 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    boolean recur(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

//    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
//        if (root2 == null)
//            return false;
//        StringBuilder sb1 = new StringBuilder();
//        StringBuilder sb2 = new StringBuilder();
//        pre(root1, sb1);
//        pre(root2, sb2);
//        if (sb1.toString().contains(sb2.toString()))
//            return true;
//        else
//            return false;
//    }
//
//    public void pre(TreeNode node, StringBuilder sb) {
//        if (node != null) {
//            sb.append(node.val);
//            pre(node.left, sb);
//            pre(node.right, sb);
//        }
//    }
}
