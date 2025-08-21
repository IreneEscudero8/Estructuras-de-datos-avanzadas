
package practica0;

public class PatientBST {
    private TreeNode root;

    public PatientBST() {
        root = null;
    }

    // Insertar paciente según prioridad
    public void insert(Patient p) {
        root = insertRec(root, p);
    }

    private TreeNode insertRec(TreeNode current, Patient p) {
        if (current == null) return new TreeNode(p);

        if (p.getPriority() < current.getPatient().getPriority()) {
            current.setLeft(insertRec(current.getLeft(), p));
        } else if (p.getPriority() > current.getPatient().getPriority()) {
            current.setRight(insertRec(current.getRight(), p));
        } else {
            // Si la prioridad es igual, desempatar con el ID
            if (p.getId() < current.getPatient().getId()) {
                current.setLeft(insertRec(current.getLeft(), p));
            } else {
                current.setRight(insertRec(current.getRight(), p));
            }
        }
        return current;
    }

    // Buscar paciente por prioridad
    public Patient search(int priority) {
        TreeNode n = searchRec(root, priority);
        return (n != null) ? n.getPatient() : null;
    }

    private TreeNode searchRec(TreeNode current, int priority) {
        if (current == null || current.getPatient().getPriority() == priority) return current;

        if (priority < current.getPatient().getPriority()) 
            return searchRec(current.getLeft(), priority);
        else 
            return searchRec(current.getRight(), priority);
    }

    // Eliminar paciente por prioridad
    public void delete(int priority) {
        root = deleteRec(root, priority);
    }

    private TreeNode deleteRec(TreeNode current, int priority) {
        if (current == null) return null;

        if (priority < current.getPatient().getPriority()) {
            current.setLeft(deleteRec(current.getLeft(), priority));
        } else if (priority > current.getPatient().getPriority()) {
            current.setRight(deleteRec(current.getRight(), priority));
        } else {
            // Encontramos el nodo a eliminar
            if (current.getLeft() == null) return current.getRight();
            else if (current.getRight() == null) return current.getLeft();

            // Caso con dos hijos: mínimo del subárbol derecho
            current.setPatient(minValue(current.getRight()).getPatient());
            current.setRight(deleteRec(current.getRight(), current.getPatient().getPriority()));
        }
        return current;
    }

    private TreeNode minValue(TreeNode n) {
        while (n.getLeft() != null) n = n.getLeft();
        return n;
    }

    // Recorrido in-order (pacientes ordenados por prioridad)
    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(TreeNode n) {
        if (n != null) {
            inOrderRec(n.getLeft());
            System.out.println(n.getPatient());
            inOrderRec(n.getRight());
        }
    }

    // Obtener el paciente más urgente (mínima prioridad)
    public Patient getNextPatient() {
        TreeNode n = root;
        if (n == null) return null;
        while (n.getLeft() != null) n = n.getLeft();
        return n.getPatient();
    }
}
