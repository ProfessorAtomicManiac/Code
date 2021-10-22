package TreeStructure;

import LinkedListStructures.Stack;

import java.util.ArrayList;

// This code was copied from https://github.com/Anksharskarp/Intermediate-And-Advanced-Data-Structures-and-Algorithms-Course/blob/master/src/anksharskarp/dsandalgorithms/coursework/Class15to18/Tree.java
// I didn't follow on and do the code along in class, so I took one of my classmate's code and edited it.
// I mainly wanted to make the classes on my own completely from scratch, so I typically don't have the exact code necessary for the assignment
// I added some edits to the code for the assignment

// A demonstration of the usefulness of the Tree data structure.
public class CompanyRUs {

    TreeNodeComponent root;

    public CompanyRUs(String deptName) {
        root = new TreeNodeComponent(deptName);
    }

    public void buildTree() {
        root.setNumberOfEmployees(35);
        TreeNodeComponent ctnSale = root.addChild("Sales");
        ctnSale.setNumberOfEmployees(15);
        TreeNodeComponent ctnMan = root.addChild("Manufacturing");
        ctnMan.setNumberOfEmployees(6);
        TreeNodeComponent ctnRN = root.addChild("R&D");
        ctnRN.setNumberOfEmployees(20);
        TreeNodeComponent ctnUS = ctnSale.addChild("US");
        ctnUS.setNumberOfEmployees(29);
        TreeNodeComponent ctnInt = ctnSale.addChild("International");
        ctnInt.setNumberOfEmployees(12);
        TreeNodeComponent ctnEurope = ctnInt.addChild("Europe");
        ctnEurope.setNumberOfEmployees(45);
        TreeNodeComponent ctnAsia = ctnInt.addChild("Asia");
        ctnAsia.setNumberOfEmployees(34);
        TreeNodeComponent ctnCanada = ctnInt.addChild("Canada");
        ctnCanada.setNumberOfEmployees(78);
        TreeNodeComponent ctnLap = ctnMan.addChild("Laptops");
        ctnLap.setNumberOfEmployees(34);
        TreeNodeComponent ctnDesk = ctnMan.addChild("Desktops");
        ctnDesk.setNumberOfEmployees(62);
    }

    // Appears to be "preorder" traversal
    public void traversal(TreeNodeComponent ctn) {
        if (ctn == null) return;
        System.out.println(ctn.dn + " has " + ctn.children.size() + " children and " + ctn.numberOfEmployees
                + " employees (or subdivisions).");
        System.out.println(ctn.dn + " has " + getTotalNumberOfEmployees(ctn) + " employees");
        for (int i = 0; i < ctn.children.size(); i++)
            traversal(ctn.children.get(i));
    }

    public int computeSubTreeSize(TreeNodeComponent ctn) {
        if (ctn == null) {
            return 0;
        }
        int subTreeSize = 1;
        System.out.println(ctn.dn + " has " + ctn.children.size() + " children");
        for (int i = 0; i < ctn.children.size(); i++) {
            subTreeSize += computeSubTreeSize(ctn.children.get(i));
        }
        System.out.println(ctn.dn + " has a size of " + subTreeSize);
        return subTreeSize;
    }

    // Recursive version
    public int getTotalNumberOfEmployees(TreeNodeComponent ctn) {
        if (ctn == null) {
            return 0;
        }
        int numberOfEmployees = ctn.numberOfEmployees;
        //System.out.println(ctn.dn + " has " + ctn.children.size() + " children");
        for (int i = 0; i < ctn.children.size(); i++) {
            numberOfEmployees += getTotalNumberOfEmployees(ctn.children.get(i));
        }
        System.out.println(ctn.dn + " has a total of " + numberOfEmployees + " employees.");
        return numberOfEmployees;
    }

    // Non-recursive version (This code is original)
    public int getTotalNumberOfEmployeesIterative(TreeNodeComponent ctn)
    {
        if (ctn == null)
            return 0;
        int numberOfEmployees = 0;

        //System.out.println(ctn.dn + " has " + ctn.children.size() + " children");
        Stack<TreeNodeComponent> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNodeComponent n = s.pop();
            numberOfEmployees += n.numberOfEmployees;
            for (int i = n.children.size() - 1; i >= 0; i--) {
                s.push(n.children.get(i));
            }
        }
        return numberOfEmployees;
    }

    public static void main(String[] args) {
        CompanyRUs test = new CompanyRUs("Computers \"R\" Us");
        System.out.println("Building tree...");
        test.buildTree();
        System.out.println();

        System.out.println("Performing a traversal of the tree.");
        test.traversal(test.root);
        System.out.println();

        System.out.println("Size of the tree: ");
        test.computeSubTreeSize(test.root);
        System.out.println();

        System.out.println("Total number of employees: ");
        test.getTotalNumberOfEmployees(test.root);
        System.out.println();
        System.out.println("Total number of employees (Calculated Iteratively): ");
        System.out.print(test.getTotalNumberOfEmployeesIterative(test.root));
        System.out.println();


    }
}

class TreeNodeComponent {
    String dn;
    int numberOfEmployees;
    ArrayList<TreeNodeComponent> children;

    public TreeNodeComponent(String name) {
        dn = name;
        children = new ArrayList<>();
    }

    public TreeNodeComponent addChild(String childName) {
        TreeNodeComponent ctn = new TreeNodeComponent(childName);
        children.add(ctn);
        return ctn;
    }

    public void setNumberOfEmployees(int ne) {
        numberOfEmployees = ne;
    }
}