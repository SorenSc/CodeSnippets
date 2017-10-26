import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Creates a tree structure based on a list of elements. The elements are an observation from a tree
 * which is ordered in preorder, which means that each element knows its position in the preorder list and its
 * level in the tree.
 */ 
public class TreeReconstruction
{

    /**
     * Simulating a reconstruction of the tree. After creating the list of elements in positioned  in preorder, the
     * tree is reconstructed and its root element is returned.
     *
     * The tree original tree looks like the following
     *
     *              E1
     *            /   \
     *           E2    E3
     *         /   \
     *       E4     E5
     *
     * @param args
     */
    public static void main(String[] args) {
        Element E1 = new Element(1, 0);
        Element E2 = new Element(2, 1);
        Element E3 = new Element(5, 1);
        Element E4 = new Element(3, 2);
        Element E5 = new Element(4, 2);
        List<Element> elements = Arrays.asList(E1, E2, E3, E4, E5);

        Node root = createTree(elements);
    }

    /**
     * The algorithm for reconstructing the tree.
     * @param components
     * @return
     */
    private static Node createTree(List<Element> components)
    {
        Stack<Node> stack = new Stack<>();

        for(Element c: components){
            Integer level = c.level;
            Integer position = c.positionInPreOrder;

            Node node = transformElementToNode(c);
            if(stack.empty()){
                stack.push(node);
            }else{
                Node nfs = stack.peek();
                if(nfs.level < node.level){
                    nfs.add(node);
                    stack.push(node);
                }else if(nfs.level == node.level) {
                    stack.pop();
                    stack.peek().add(node);
                    stack.push(node);
                }else{
                    while(nfs.level >= node.level){
                        stack.pop();
                        nfs = stack.peek();
                    }
                    nfs.add(node);
                    stack.push(node);
                }
            }
        }
        return stack.firstElement();
    }

    /**
     * Convert an element to a node.
     * @param element
     * @return
     */
    private static Node transformElementToNode(Element element)
    {
        return new Node(element.positionInPreOrder, element.level);
    }

    private static class Element
    {
        public Integer positionInPreOrder;
        public Integer level;

        public Element(Integer positionInPreOrder, Integer level)
        {
            this.positionInPreOrder = positionInPreOrder;
            this.level = level;
        }
    }

    /**
     * The variables positionInInorder and level are not necessary, but they might be useful for debugging.
     */
    private static class Node
    {
        public Integer positionInPreorder;
        public Integer level;
        public List<Node> children;

        public Node(Integer positionInPreorder, Integer level)
        {
            this.positionInPreorder = positionInPreorder;
            this.level = level;
        }

        public void add(Node node){
            if (children == null)
                children = new ArrayList<Node>();
            children.add(node);
        }
    }
}