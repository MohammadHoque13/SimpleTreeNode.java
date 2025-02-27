import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
public class JGraphX2 extends JFrame
{

    private static final long serialVersionUID = -2707712944901661771L;
    SimpleTreeNode<String> root = createSimpleTreeNode();

    public void fillGraphFromModel( mxGraph graph)  {
        //   graphUpdate();

        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();

        try
        {
            Object vRoot = graph.insertVertex(parent, null, createSimpleTreeNode().getData(), 100, 00, 80, 30);

            CreateGraphPoints(graph, parent, vRoot, createSimpleTreeNode());
            mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
            layout.setUseBoundingBox(false);
            layout.execute(parent);
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
    }

    public void CreateGraphPoints(mxGraph graph, Object parent, Object vRoot, SimpleTreeNode<String> parentNode)
    {
        for(SimpleTreeNode child : parentNode.getChildren())
        {
            Object meRoot = graph.insertVertex(parent, null, child.getData(), 80, 0, 80, 30);
            graph.insertEdge(parent, null, "", vRoot, meRoot);


            if(child.childCount() > 0)
            {
                // Recursively draw nodes
                CreateGraphPoints(graph, parent, meRoot, child);
            }
        }
    }


    public JGraphX2()
    {
        super("Simple Tree Node");
        mxGraph graph = new mxGraph();
        fillGraphFromModel(graph);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);


    }


    public SimpleTreeNode<String> createSimpleTreeNode2() {
        root = new SimpleTreeNode<>("Physics");

        SimpleTreeNode<String> child1 = new SimpleTreeNode<>("Mechanic");
        child1.addChild("Vector");
        child1.addChild("Angular Motion");
        child1.addChild("W,P & E");



        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("Heat");


        root.addChild(child1);
        root.addChild(child2);
        SimpleTreeNode<String> child3 = new SimpleTreeNode<>("Waves&Vibration");
        
        root.addChild(child3);
        child3.addChild(new SimpleTreeNode<>("Optics"));

        root.addChildren(Arrays.asList(
                new SimpleTreeNode<>("Child4"),
                new SimpleTreeNode<>("Child5"),
                new SimpleTreeNode<>("Child6")



        ));
        for (SimpleTreeNode child: root.getChildren()) {
            root.printChildren(child);
        }
        return root;
    }

    public SimpleTreeNode<String> createSimpleTreeNode() {
        root = new SimpleTreeNode<>("Physics");

        SimpleTreeNode<String> child1 = new SimpleTreeNode<>("Mechanics");
        child1.addChild("Vector");
        child1.addChild("Angular Motion");
        child1.addChild("W,P & E");




        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("Heat");
        root.addChild(child1);
        root.addChild(child2);



        SimpleTreeNode<String> child3 = new SimpleTreeNode<>("Waves&Vibration");
        root.addChild(child3);
        child3.addChild(new SimpleTreeNode<>("S.H. Motion"));
        child3.addChild(new SimpleTreeNode<>("Waves"));

        SimpleTreeNode<String> child4 = new SimpleTreeNode<>("Optics");
        root.addChild(child4);
        child4.addChild(new SimpleTreeNode<>("Reflection"));
        child4.addChild(new SimpleTreeNode<>("Refraction"));



          SimpleTreeNode<String> child5 = new SimpleTreeNode<>("Magnetism");
          root.addChild(child5);

          SimpleTreeNode<String> child6 = new SimpleTreeNode<>("Electricity");
          root.addChild(child6);
        child6.addChild(new SimpleTreeNode<>("Static"));
        child6.addChild(new SimpleTreeNode<>("Current"));

       




        for (SimpleTreeNode child: root.getChildren()) {
            root.printChildren(child);
        }
        return root;
    }


    // Backup not used
    public void graphUpdate() {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try
        {
            //Notice that the parent is the default parent...
            //The hierarchical structure looks great but I cannot collapse/expand the tree.
            Object vDogsRoot = graph.insertVertex(parent, null, "Whale", 80, 0, 80, 30);
            Object v2 = graph.insertVertex(parent, null, "Shar Pei", 0, 0, 80, 30);
            Object v3 = graph.insertVertex(parent, null, "Pug", 0, 0, 80, 30);
            Object v4 = graph.insertVertex(parent, null, "Cocker Spaniel", 0, 0, 80, 30);
            Object v5 = graph.insertVertex(parent, null, "Pit Bull", 0, 0, 80, 30);
            Object v6 = graph.insertVertex(parent, null, "Chihuahua", 0, 0, 80, 30);
            Object v7 = graph.insertVertex(null, null, "GrandPuppy", 0,0,90,30);
            Object v8 = graph.insertVertex(null, null, "GrandPuppy2", 0,0,90,30);
            Object v9 = graph.insertVertex(null, null, "GrandPuppy3", 0,0,90,30);
            Object v10 = graph.insertVertex(null, null, "GrandPuppy3", 0,0,90,30);
            graph.insertEdge(parent, null, "", vDogsRoot, v2);
            graph.insertEdge(parent, null, "", vDogsRoot, v3);
            graph.insertEdge(parent, null, "", vDogsRoot, v4);
            graph.insertEdge(parent, null, "", vDogsRoot, v5);
            graph.insertEdge(parent, null, "", vDogsRoot, v6);
            graph.insertEdge(v6, null, "", v6, v7);
            graph.insertEdge(v6, null, "", v6, v8);
            graph.insertEdge(v6, null, "", v6, v9);

            mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
            layout.setUseBoundingBox(false);

            layout.execute(parent);
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    public static void main(String[] args)
    {
        JGraphX2 frame = new JGraphX2();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 420);
        frame.setVisible(true);
    }

}