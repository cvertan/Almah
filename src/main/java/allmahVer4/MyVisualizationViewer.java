package allmahVer4;
import java.awt.Dimension;
import edu.uci.ics.jung.visualization.VisualizationModel;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class MyVisualizationViewer<V,E> extends VisualizationViewer<V,E> {

    public MyVisualizationViewer(VisualizationModel<V,E> visualizationModel, Dimension size){
         super(visualizationModel, size);
     }

     public void addGraphEdgeMouseListener(GraphEdgeMouseListener<E> geml){
         addMouseListener(new MouseEdgeListenerTranslator<V,E>(geml, this));
    }

}
