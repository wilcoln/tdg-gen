package utils;

import java.util.*;

/**
 * Cette classe implémente différentes fonction utilitaires utilisées ici et là dans le projet
 * @author Wilfried L. Bounsi
 */

public class Utils {

    /**
     * Calcul le plus court chemin entre {@code dep } {@code arr } dans un graphe défini par {@code cases}
     *  à l'aide de l'algorithme de Dijstra
     * @param dep
     * @param arr
     * @param cases
     * @return une liste de positions correspondant au plus court chemin
     */
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


// Cette classe implémente l'algorithme de Dijstra pour le calcul du plus court chemin dans dans un graphe
class DijkstraAlgo{
    static void computePaths(Node source){
        source.shortestDistance=0;

        //implement a priority queue
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue.add(source);

        while(!queue.isEmpty()){
            Node u = queue.poll();

			/*visit the adjacencies, starting from
			the nearest node(smallest shortestDistance)*/

            for(Edge e: u.adjacencies){

                Node v = e.target;
                double weight = e.weight;

                //relax(u,v,weight)
                double distanceFromU = u.shortestDistance+weight;
                if(distanceFromU<v.shortestDistance){

					/*remove v from queue for updating
					the shortestDistance value*/
                    queue.remove(v);
                    v.shortestDistance = distanceFromU;
                    v.parent = u;
                    queue.add(v);

                }
            }
        }
    }

    static List<Node> getShortestPathTo(Node target){

        //trace path from target to source
        List<Node> path = new ArrayList<Node>();
        for(Node node = target; node!=null; node = node.parent){
            path.add(node);
        }


        //reverse the order such that it will be from source to target
        Collections.reverse(path);

        return path;
    }

}

//define Node
class Node implements Comparable<Node>{

    public final String value;
    public List<Edge> adjacencies;
    public double shortestDistance = Double.POSITIVE_INFINITY;
    public Node parent;

    public Node(String val){
        value = val;
    }

    public String toString(){
        return value;
    }

    public int compareTo(Node other){
        return Double.compare(shortestDistance, other.shortestDistance);
    }

}

//define Edge
class Edge{
    final Node target;
    final double weight;
    Edge(Node targetNode, double weightVal){
        target = targetNode;
        weight = weightVal;
    }
}