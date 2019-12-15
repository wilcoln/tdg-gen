package utils;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {

    public static List<Position> calculPlusCourtChemin(Position dep, Position arr, HashMap<Position, Integer> cases){
        //initialize the graph base on the Romania map
        List<Position> result = new ArrayList<>();
        HashMap<Position, Node> nodes = new HashMap<>();
        for (Position pos : cases.keySet()) {
            nodes.put(pos, new Node(pos.getX() + "," + pos.getY()));
        }
        //initialize the edges
        for (Position pos : cases.keySet()) {
            nodes.get(pos).adjacencies = new ArrayList<>();
            for(Position adj: pos.getAdjacents()){
                if(estDans(cases, adj) != null){
                    nodes.get(pos).adjacencies.add(new Edge(nodes.get(estDans2(nodes, adj)), cases.get(estDans(cases, adj))));
                }
            }
        }
        //compute paths
        DijkstraAlgo.computePaths(nodes.get(estDans2(nodes, dep)));
        List<Node> path = DijkstraAlgo.getShortestPathTo(nodes.get(estDans2(nodes, arr)));
        for(Node n : path){
            result.add(node2Position(n));
        }
        return result;
    }

    private static Position estDans(HashMap<Position, Integer> cases, Position adj) {
        for(Position pos: cases.keySet())
            if(pos.equals(adj))
                return pos;
        return null;
    }
    private static Position estDans2(HashMap<Position, Node> cases, Position adj) {
        for(Position pos: cases.keySet())
            if(pos.equals(adj))
                return pos;
        return null;
    }

    private static Position node2Position(Node n) {
        String[] strs = n.value.split(",");
        return new Position(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]));
    }
}
