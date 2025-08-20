
package practica0;
/*
 * Nodo para Árbol Binario de Búsqueda (BST)
 * Guarda un Patient y referencias a hijos izquierdo y derecho.
 */
public class TreeNode {
    private Patient patient;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Patient patient) {
        this.patient = patient;
        this.left = null;
        this.right = null;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return patient.toString();
    }
}
