public class Test {
    public static void main(String[] args) {
        Node A = new Node("A");
        Node B = new Node("B",A);
        Node C = new Node("C",A);
        Node D = new Node("D",C);


        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
        System.out.println(D);

    }
}
