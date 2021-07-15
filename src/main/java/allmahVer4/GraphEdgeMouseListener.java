package allmahVer4;

import java.awt.event.MouseEvent; 

public interface GraphEdgeMouseListener<E> {

    void graphClicked(E e, MouseEvent me);
    void graphPressed(E e, MouseEvent me);
    void graphReleased(E e, MouseEvent me);

}
