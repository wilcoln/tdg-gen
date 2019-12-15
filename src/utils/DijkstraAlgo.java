package utils;

import java.util.*;

public class DijkstraAlgo{
    /* Dijkstra Algorithm
     *
     *
     */
    public static void computePaths(Node source){
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

    public static List<Node> getShortestPathTo(Node target){

        //trace path from target to source
        List<Node> path = new ArrayList<Node>();
        for(Node node = target; node!=null; node = node.parent){
            path.add(node);
        }


        //reverse the order such that it will be from source to target
        Collections.reverse(path);

        return path;
    }



    public static void main(String[] args){

        //initialize the graph base on the Romania map
        HashMap<String, Node> nodes = new HashMap<>();
        nodes.put("1,1", new Node("1,1"));
        nodes.put("1,2", new Node("1,2"));

        nodes.put("2,1", new Node("2,1"));
        nodes.put("2,2", new Node("2,2"));
        nodes.put("2,3", new Node("2,3"));
        nodes.put("2,4", new Node("2,4"));
        nodes.put("2,5", new Node("2,5"));
        nodes.put("3,1", new Node("3,1"));
        nodes.put("3,2", new Node("3,2"));
        nodes.put("3,3", new Node("3,3"));
        nodes.put("3,4", new Node("3,4"));
        nodes.put("3,5", new Node("3,5"));

        /*//initialize the edges
        nodes.get("1,1").adjacencies = new Edge[]{
                new Edge(nodes.get("1,2"),1),
                new Edge(nodes.get("2,1"),0),
        };

        nodes.get("1,2").adjacencies = new Edge[]{
                new Edge(nodes.get("1,1"),1),
        };

        nodes.get("2,1").adjacencies = new Edge[]{
                new Edge(nodes.get("1,1"),0),
                new Edge(nodes.get("3,1"),1)
        };

        nodes.get("3,1").adjacencies = new Edge[]{
                new Edge(nodes.get("2,1"),0),
                new Edge(nodes.get("3,2"),1)
        };

        nodes.get("3,2").adjacencies = new Edge[]{
                new Edge(nodes.get("3,1"),1),
                new Edge(nodes.get("3,3"),1),
        };

        nodes.get("2,3").adjacencies = new Edge[]{
                new Edge(nodes.get("2,4"),1),
                new Edge(nodes.get("3,3"),1)
        };

        nodes.get("3,3").adjacencies = new Edge[]{
                new Edge(nodes.get("3,2"),1),
                new Edge(nodes.get("2,3"),1)
        };

        nodes.get("2,4").adjacencies = new Edge[]{
                new Edge(nodes.get("2,3"),1),
                new Edge(nodes.get("2,5"),0)
        };

        nodes.get("2,5").adjacencies = new Edge[]{
                new Edge(nodes.get("2,4"),1),
                new Edge(nodes.get("3,5"),0)
        };

        nodes.get("3,5").adjacencies = new Edge[]{
                new Edge(nodes.get("2,5"),0)
        };

        //compute paths
        computePaths(nodes.get("1,1"));
        List<Node> path = getShortestPathTo(nodes.get("3,5"));
        System.out.println("Path n11 à n35 : " + path);

        reInit(nodes.values());
        //compute paths
        computePaths(nodes.get("2,1"));
        path = getShortestPathTo(nodes.get("3,5"));
        System.out.println("Path n21 à n35 : " + path);*/

    }

    private static void reInit(Collection<Node> nodes) {
        for (Node n: nodes) {
            n.shortestDistance = Double.POSITIVE_INFINITY;
            n.parent = null;
        }
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
    public final Node target;
    public final double weight;
    public Edge(Node targetNode, double weightVal){
        target = targetNode;
        weight = weightVal;
    }
}