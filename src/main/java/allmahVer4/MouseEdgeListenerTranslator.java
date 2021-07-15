package allmahVer4;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class MouseEdgeListenerTranslator<V,E> extends MouseAdapter {

    private VisualizationViewer<V,E> vv;
    private GraphEdgeMouseListener<E> geml;

    public MouseEdgeListenerTranslator(GraphEdgeMouseListener<E> geml, VisualizationViewer<V,E> vv){
        this.geml = geml;
        this.vv = vv;
    }

    private E getEdge(Point2D point){
        Point2D p = point;
        GraphElementAccessor<V,E> pickSupport = vv.getPickSupport();
        Layout<V,E> layout = vv.getGraphLayout();
        E e = null;
        if(pickSupport != null){
            e = pickSupport.getEdge(layout, p.getX(), p.getY());
        }
        return e;
    }

    public void mouseClicked(MouseEvent me){
        E e = getEdge(me.getPoint());
        if(e != null){
            geml.graphClicked(e, me);
        }
    }

    public void mousePressed(MouseEvent me){
        E e = getEdge(me.getPoint());
        if(e != null){
            geml.graphPressed(e, me);
        }
    }

    public void mouseReleased(MouseEvent me){
        E e = getEdge(me.getPoint());
        if(e != null){
            geml.graphReleased(e, me);
        }
    }

} 