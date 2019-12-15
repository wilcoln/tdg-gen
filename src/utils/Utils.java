package utils;

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
                if(cases.containsKey(adj)){
                    nodes.get(pos).adjacencies.add(new Edge(nodes.get(adj), cases.get(adj)));
                }
            }
        }
        //compute paths
        DijkstraAlgo.computePaths(nodes.get(dep));
        List<Node> path = DijkstraAlgo.getShortestPathTo(nodes.get(arr));
        for(Node n : path){
            result.add(node2Position(n));
        }
        return result;
    }

    private static Position node2Position(Node n) {
        String[] strs = n.value.split(",");
        return new Position(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]));
    }
}
