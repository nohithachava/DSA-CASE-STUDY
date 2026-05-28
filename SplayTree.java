class SplayTree {
    class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
        }
    }

    Node root;

    Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    Node splay(Node root, int key) {
        if (root == null || root.key == key)
            return root;

        if (key < root.key) {
            if (root.left == null)
                return root;

            if (key < root.left.key) {
                root.left.left = splay(root.left.left, key);
                root = rightRotate(root);
            } 
            else if (key > root.left.key) {
                root.left.right = splay(root.left.right, key);
                if (root.left.right != null)
                    root.left = leftRotate(root.left);
            }

            return (root.left == null) ? root : rightRotate(root);
        } 
        else {
            if (root.right == null)
                return root;

            if (key > root.right.key) {
                root.right.right = splay(root.right.right, key);
                root = leftRotate(root);
            } 
            else if (key < root.right.key) {
                root.right.left = splay(root.right.left, key);
                if (root.right.left != null)
                    root.right = rightRotate(root.right);
            }

            return (root.right == null) ? root : leftRotate(root);
        }
    }

    void insert(int key) {
        if (root == null) {
            root = new Node(key);
            return;
        }

        Node temp = root;
        Node parent = null;

        while (temp != null) {
            parent = temp;
            if (key < temp.key)
                temp = temp.left;
            else
                temp = temp.right;
        }

        if (key < parent.key)
            parent.left = new Node(key);
        else
            parent.right = new Node(key);
    }

    void search(int key) {
        root = splay(root, key);
        System.out.println("Accessed key: " + key + ", New Root: " + root.key);
    }

    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        SplayTree tree = new SplayTree();

        int keys[] = {30, 15, 50, 10, 20, 40, 60, 5, 25};

        for (int key : keys) {
            tree.insert(key);
        }

        System.out.println("Initial BST Inorder:");
        tree.inorder(tree.root);

        System.out.println("\n\nAccessing hot keys:");
        int access[] = {40, 5, 40, 25, 5, 40, 5};

        for (int key : access) {
            tree.search(key);
        }

        System.out.println("\nFinal Tree Inorder:");
        tree.inorder(tree.root);
    }
}