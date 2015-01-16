package platformer.coop.quadtree;

import platformer.coop.entities.GameEntity;

public class Node {
    Double x, y;              
    Node NW, NE, SE, SW;   
    GameEntity value;          

    Node(Double x, Double y, GameEntity value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}
