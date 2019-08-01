package com.ggx.datastructure.tree;

/**
 * 红黑树,规则:
 * 1. 一个节点不是红色就是黑色
 * 2. 根节点总是黑色
 * 3. 新插入的节点总是红色
 * 4. 不能有两个连续的红色节点
 * 5. 从任一节点到其每个叶子的简单路径都包含相同数目的黑色节点
 * 6. 空节点总是黑色节点
 * 新插入节点导致违反规则时，rotate or color flip:
 * 1. 如果叔节点是黑色时，rotate,得到父的黑色节点+两个红色子节点
 * 2. 如果叔节点时红色时，color flip，得到红色父节点+两个黑色子节点
 *
 * 树构建时，大小规则依赖K的值
 * 树的深度及高度：
 * 深度：对于任意节点n,n的深度为从根到n的唯一路径长，根的深度为0；
 * 高度：对于任意节点n,n的高度为从n到一片树叶的最长路径长，所有树叶的高度为0；
 */
public class RedBlackTree<K, V> {

    private Node<K,V> root;
    private int size;

    /**=================================插入方法、插入后的规则检查及违反规则后的rotate or color flip=========================*/
    /**
     * 插入方法
     * @param key
     * @param value
     */
    public void add(K key, V value){
        Node<K, V> node = new Node<>(key, value);
        //树为空时，新节点为根节点
        if(root == null){
            root = node;
            root.isBlack = true;//根节点总是黑色节点
            size++;
            return;
        }
        add(root, node);
        size++;
    }

    private void add(Node<K, V> parent, Node<K,V> newNode){

        if(((Comparable<K>) newNode.key).compareTo(parent.key) > 0){
            //新节点的Key大于父节点的，新节点插入父节点的右子树
            if(parent.right == null){
                parent.right = newNode;
                newNode.parent = parent;
                newNode.isLeftChild = false;
            }else{
                add(parent.right, newNode);
            }
        }else{
            //新节点插入父节点的左子树
            if(parent.left == null){
                parent.left = newNode;
                newNode.parent = parent;
                newNode.isLeftChild = true;
            }else{
                add(parent.left, newNode);
            }
        }
        //判断新插入的节点是否违反了红黑树的规则，违反时进行rotate or color flip
        checkColor(newNode);
    }

    /**
     * 检查节点是否违反规则
     * 违反时进行rotate or color flip
     * @param node
     */
    private void checkColor(Node<K, V> node) {

        //根节点时直接返回
        if(root == node){
            return;
        }
        //两个连续的红色节点
        if(!node.isBlack && !node.parent.isBlack){
            //纠正树
            correctTree(node);
            //纠正后有可能使得父节点及其祖父节点双红，需递归检查
            checkColor(node.parent);
        }
    }

    /**
     * 纠正数使其符合红黑树的规则
     * 进入本方法的前提是两个红色节点，根节点永远是黑色节点，所以父节点总不是根节点，祖父节点总不是null
     * @param node
     */
    private void correctTree(Node<K, V> node) {

        if(node.parent.isLeftChild){
            //父节点是左节点,叔节点是祖父节点的右节点
            if(node.parent.parent.right == null || node.parent.parent.right.isBlack){
                //叔节点是黑色节点时，rotate
                rotate(node);
                return;
            }
            //叔节点是红色节点时，color flip,祖父节点为红色节点，父节点及叔节点为黑色节点
            node.parent.parent.isBlack = false;
            node.parent.isBlack = true;
            node.parent.parent.right.isBlack = true;
        }else{
            //父节点是右节点，叔节点是祖父节点的左节点
            if(node.parent.parent.left == null || node.parent.parent.left.isBlack){
                //叔节点是黑色节点时，rotate
                rotate(node);
                return;
            }
            //叔节点是红色节点时，color flip,祖父节点为红色节点，父节点及叔节点为黑色节点
            node.parent.parent.isBlack = false;
            node.parent.isBlack = true;
            node.parent.parent.left.isBlack = true;
        }
    }

    /**
     * 翻转以使树符合规则
     * @param node
     */
    private void rotate(Node<K, V> node) {

        if(node.isLeftChild){
            if(node.parent.isLeftChild){
                //自身左节点，父节点为左节点时右旋
                rightRotate(node.parent.parent);
                node.parent.isBlack = true;
                node.isBlack = false;
                node.parent.right.isBlack = false;
            }else{
                //自身左节点，父节点为右节点时右左旋
                rightLeftRotate(node.parent.parent);
                node.isBlack = true;
                node.right.isBlack = false;
                node.left.isBlack = false;
            }
        }else{
            if(!node.parent.isLeftChild){
                //自身右节点，父节点为右节点时左旋
                leftRotate(node.parent.parent);
                node.parent.isBlack = true;
                node.parent.left.isBlack = false;
                node.isBlack = false;
            }else{
                //自身右节点，父节点为左节点时左右旋
                leftRightRotate(node.parent.parent);
                node.isBlack = true;
                node.left.isBlack = false;
                node.right.isBlack = false;
            }
        }
    }

    /**
     * 右左旋
     * 1. 先将node节点的右节点右旋
     * 2. 再将node节点左旋
     * @param node 问题节点的祖父节点
     */
    private void rightLeftRotate(Node<K, V> node) {
        rightRotate(node.right);
        leftRotate(node);
    }

    /**
     * 左右旋
     * @param node 问题节点的祖父节点
     */
    private void leftRightRotate(Node<K, V> node) {

        leftRotate(node.left);
        rightRotate(node);
    }

    /**
     * 右旋
     * @param node 问题节点的祖父节点
     */
    private void rightRotate(Node<K, V> node) {

        Node<K, V> temp = node.left;
        //1. 将node左节点(temp)的右节点转为node的右节点
        node.left = temp.right;
        if(node.left != null){
            node.left.parent = node;
            node.left.isLeftChild = true;
        }
        //2. 判断node是否有父节点
        if(node.parent == null){
            //node为根节点
            root = temp;
            temp.parent = null;
        }else{
            temp.parent = node.parent;
            if(node.isLeftChild){
                temp.isLeftChild = true;
                temp.parent.left = temp;
            }else{
                temp.isLeftChild = false;
                temp.parent.right = temp;
            }
        }
        //3. 将node转为temp的右节点
        temp.right = node;
        node.parent = temp;
        node.isLeftChild = false;
    }

    /**
     * 左旋
     * @param node 问题节点的祖父节点
     */
    private void leftRotate(Node<K, V> node) {
        Node<K, V> temp = node.right;
        //1. 将node子节点的左节点转为node节点的右节点
        node.right = temp.left;
        if(node.right != null){
            node.right.parent = node;
            node.right.isLeftChild = false;
        }
        //2. 判断node是否有父节点
        if(node.parent == null){
            //node为根节点
            root = temp;
            temp.parent = null;
        }else{
            temp.parent = node.parent;
            if(node.isLeftChild){
                temp.isLeftChild = true;
                temp.parent.left = temp;
            }else{
                temp.isLeftChild = false;
                temp.parent.right = temp;
            }
        }
        //3. 将node转为temp的左节点
        temp.left = node;
        node.isLeftChild = true;
        node.parent = temp;
    }

    /**=====================================================插入方法==========================================================*/

    /**
     * 红黑树的高度
     * @return
     */
    public int height(){
        if(root == null){
            return 0;
        }
        return height(root) - 1;
    }

    /**
     * 计算任一节点的高度 + 1
     * @param node
     * @return
     */
    private int height(Node<K, V> node) {

        if(node == null){
            return 0;
        }

        int leftHeight = height(node.left) + 1;
        int rightHeight = height(node.right) + 1;
        if(leftHeight > rightHeight){
            return leftHeight;
        }
        return rightHeight;
    }

    /**
     * 计算红黑树每条简单路径的黑节点个数
     * @param node
     * @return
     */
    public int blackNode(Node<K, V> node){
        if(node == null){
            return 1;
        }

        int leftBlackNodes = blackNode(node.left);
        int rightBlackNodes = blackNode(node.right);
        //如果黑色节点数不一致，违反规则，抛出异常
        if(leftBlackNodes != rightBlackNodes){
            throw new RuntimeException();
        }
        //自己是黑色节点+1
        if(node.isBlack){
            leftBlackNodes++;
        }
        return leftBlackNodes;
    }

    /**============================================删除方法================================================*/

    /**
     * 删除key值的节点
     * @param key
     */
    public void delete(K key){
        delete(key, root);
    }

    /**
     * 从node节点开始寻找K值为key的节点删除
     * @param key
     * @param node
     */
    private void delete(K key, Node<K, V> node) {
        //未寻找到，返回
        if(node == null){
            return;
        }
        int minus = ((Comparable<K>)node.key).compareTo(key);
        if(minus == 0){
            //删除当前节点
           deleteNode(node);
        }else if(minus > 0){
            //key值小于当前节点的key值，在左子树继续寻找
            delete(key, node.left);
        }else if(minus < 0){
            //key值大于当前节点的key值，在右子树继续寻找
            delete(key, node.right);
        }
    }

    /**
     * 删除节点
     * @param node
     */
    public void deleteNode(Node<K, V> node) {

        //node两个子节点时，替换为后继结点，相当于删除后继结点
        if(node.left != null && node.right != null){
            Node<K, V> s = successor(node);
            //将node替换为s节点
            node.key = s.key;
            node.value = s.value;
            //将node指向后继结点
            node = s;
        }

        //node是后继节点或不变。开始修正红黑树
        //获取可能存在的左子树或者右子树，替换后继节点，然后修正红黑树
        Node<K, V> replacement = node.left != null ? node.left : node.right;
        if(replacement != null){
            replacement.parent = node.parent;
            if(node.parent == null){
                root = replacement;
            }else if(node == node.parent.left){
                node.parent.left = replacement;
                replacement.isLeftChild = true;
            }else{
                node.parent.right = replacement;
                replacement.isLeftChild = false;
            }

            //将node删除
            node.parent = node.left = node.right = null;

            //修正红黑树
            fixAfterDeletion(replacement);
        }else if(node.parent == null){
            //node是根,且没有子节点，根被删除，树为空
            root = null;
        }else{
            //node没有子节点
            if(node.isBlack == true){
                //node是黑节点，此时根节点到node父节点这个子树的黑节点可能少1，需要修正
                fixAfterDeletion(node);
            }
            //node为红节点或者已被修正，删除node
            if(node.parent != null){
                if(node == node.parent.left){
                    node.parent.left = null;
                }else if(node == node.parent.right){
                    node.parent.right = null;
                }
                node.parent = null;
            }
        }
    }

    /**
     * 修正树
     * @param node
     */
    private void fixAfterDeletion(Node<K, V> node) {

        while(node != root && node.isBlack){
            Node<K, V> sibling = sibling(node);
            if(node.isLeftChild){
                //case 1:sibling是红色节点，无法借黑色节点，
                //但sibling的子节点是黑色，从父节点左旋，并交换sibling与parent的颜色
                //此时node的sibling是黑色节点，进入case2、case3或case 4
                if(!sibling.isBlack){
                    leftRotate(node.parent);
                    node.parent.isBlack = false;
                    sibling.isBlack = true;
                    sibling = sibling(node);
                }

                //case 2:sibling的左右孩子都是黑色节点，
                //将sibling设置为红色，将node变为node.parent，继续向上调整
                if(sibling.left.isBlack && sibling.right.isBlack){
                    sibling.isBlack = false;
                    node = node.parent;
                }else{
                    //case 3: sibling的左孩子是红色节点
                    //交换sibling与左子树的颜色，并在sibling上做右旋转
                    if(!sibling.left.isBlack){
                        sibling.isBlack = false;
                        sibling.left.isBlack = true;
                        rightRotate(sibling);
                        sibling = sibling(node);
                    }

                    //case 4: sibling的右孩子是红色节点
                    //交换sibling及父节点的颜色，并将node父节点及sibling右子树变黑色
                    //然后在node的父节点上左旋，将node节点设为根节点(已平衡，跳出调整)
                    sibling.isBlack = node.parent.isBlack;
                    node.parent.isBlack = true;
                    sibling.right.isBlack = true;
                    leftRotate(node.parent);
                    node = root;
                }
            }else{
                //右节点，与左节点的对称
                //case 1:兄弟节点红色
                if(sibling.isLeftChild){
                    node.parent.isBlack = false;
                    sibling.isBlack = true;
                    rightRotate(node.parent);
                    sibling = sibling(node);
                }

                //case 2: 兄弟节点的左右节点都是黑色
                if(sibling.left.isBlack && sibling.right.isBlack){
                    sibling.isBlack = false;
                    node = node.parent;
                }else{
                    //case 3:兄弟节点右子树是红色节点
                    if(!sibling.right.isBlack){
                        sibling.right.isBlack = true;
                        sibling.isBlack = false;
                        leftRotate(sibling);
                        sibling = sibling(node);
                    }
                    //case 4:兄弟节点左子树是红色节点
                    sibling.isBlack = node.parent.isBlack;
                    node.parent.isBlack = true;
                    sibling.left.isBlack = true;
                    rightRotate(sibling.parent);
                    node = root; //node节点指向根节点，跳出循环
                }
            }
        }
    }

    /**
     * 获取node的兄弟节点
     * @param node
     * @return
     */
    private Node<K,V> sibling(Node<K, V> node) {

        //空节点或根节点返回null
        if(node == null || node.parent == null){
            return null;
        }
        Node<K, V> parent = node.parent;
        if(node == parent.left){
            return parent.right;
        }else{
            return parent.left;
        }
    }

    /**
     * 寻找node节点的后继节点
     * @param node
     * @return
     */
    private Node<K,V> successor(Node<K, V> node) {

        if(node.left != null){
            //寻找左子树的最大值
            Node<K, V> successor = node.left;
            while(successor.right != null){
                successor = successor.right;
            }
            return successor;
        }else if(node.right != null){
            //寻找右子树的最小值
            Node<K,V> successor = node.right;
            while(successor.left != null){
                successor = successor.left;
            }
            return successor;
        }else{
            return null;
        }
    }

    /**============================================删除方法================================================*/
    private static class Node<K, V>{
        private K key;
        private V value;
        private Node<K,V> left, right, parent;
        boolean isLeftChild, isBlack;

        public Node(K k, V v){
            this.key = k;
            this.value = v;
            left = right = parent = null; //初始化父节点、左节点、右节点为null
            isBlack = false; //新节点总是红色节点
            isLeftChild = false; //随意，只是初始化
        }
    }
}
