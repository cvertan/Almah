package allmahVer4;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.jsoup.*;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.graph.Graph;

public class MyListGT2Layout<GraphGT2Node, OperatorLink> extends AbstractLayout<GraphGT2Node,OperatorLink> {

	private double radius;
	private List<GraphGT2Node> vertex_ordered_list;
	
    protected LoadingCache<GraphGT2Node, CircleVertexData> circleVertexDatas =
    	CacheBuilder.newBuilder().build(new CacheLoader<GraphGT2Node, CircleVertexData>() {
	    	public CircleVertexData load(GraphGT2Node vertex) {
	    		return new CircleVertexData();
	    	}
    });

	public MyListGT2Layout(Graph<GraphGT2Node,OperatorLink> g) {
		super(g);
	}

	/**
	 * @return the radius of the circle.
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Sets the radius of the circle.  Must be called before {@code initialize()} is called.
	 * @param radius the radius of the circle
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Sets the order of the vertices in the layout according to the ordering
	 * specified by {@code comparator}.
	 * @param comparator the comparator to use to order the vertices
	 */
	public void setVertexOrder(Comparator<PhonemNode> comparator)
	{
	    if (vertex_ordered_list == null)
	        vertex_ordered_list = new ArrayList<GraphGT2Node>(getGraph().getVertices());
	   // Collections.sort(vertex_ordered_list, comparator);
	}

    /**
     * Sets the order of the vertices in the layout according to the ordering
     * of {@code vertex_list}.
     * @param vertex_list a list specifying the ordering of the vertices
     */
	public void setVertexOrder(List<GraphGT2Node> vertex_list)
	{
	    if (!vertex_list.containsAll(getGraph().getVertices())) 
	        throw new IllegalArgumentException("Supplied list must include " +
	        		"all vertices of the graph");
	    this.vertex_ordered_list = vertex_list;
	}
	
	public void reset() {
		initialize();
	}

	public void initialize() 
	{
		Dimension d = getSize();
		
		if (d != null) 
		{
		    if (vertex_ordered_list == null) 
		        setVertexOrder(new ArrayList<GraphGT2Node>(getGraph().getVertices()));

			double height = d.getHeight();
			double width = d.getWidth();

			if (radius <= 0) {
				int summe=0;
				for (GraphGT2Node v : vertex_ordered_list)
				{
				summe= summe+(Jsoup.parse(v.toString()).text().length()*20);
				}
				radius=(width-summe)/vertex_ordered_list.size();
				if (radius<=0) radius=5;
			}

			int i = 0;
			double summe=0;
			for (GraphGT2Node v : vertex_ordered_list)
			{
				Point2D coord = apply(v);
           
				//double angle = (2 * Math.PI * i) / vertex_ordered_list.size();
                double restLength=summe;
                if(i>0) restLength=summe+radius;
				/*coord.setLocation(Math.cos(angle) * radius + width / 2,
						Math.sin(angle) * radius + height / 2);*/
                		coord.setLocation(restLength, 0.45*height);
                summe=restLength+(Jsoup.parse(v.toString()).text().length()*20);
				CircleVertexData data = getCircleData(v);
				data.setAngle(restLength);
				i++;
			}
		}
	}

	protected CircleVertexData getCircleData(GraphGT2Node v) {
		return circleVertexDatas.getUnchecked(v);
	}

	protected static class CircleVertexData {
		private double angle;

		protected double getAngle() {
			return angle;
		}

		protected void setAngle(double angle) {
			this.angle = angle;
		}

		@Override
		public String toString() {
			return "CircleVertexData: angle=" + angle;
		}
	}
}

