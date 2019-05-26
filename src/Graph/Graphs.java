package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Graphs {

	static class Edge {
		int n;
		int w;

		Edge(int n, int w) {
			this.n = n;
			this.w = w;
		}

	}

	public static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

	static void addEdge(ArrayList<ArrayList<Edge>> graph, int v1, int v2, int w) {

		graph.get(v1).add(new Edge(v2, w));
		graph.get(v2).add(new Edge(v1, w));

	}

//	static void removeEdge(ArrayList<ArrayList<Edge>> graph, int v1, int v2, int w) {
//
//		graph.get(v1).remove();
//		graph.get(v2).remove(v1);
//
//	}

	static void display(ArrayList<ArrayList<Edge>> graph) {
		System.out.println("-----------------------------------------");

		for (int v = 0; v < graph.size(); v++) {

			System.out.print(v + " -> ");

			for (int n = 0; n < graph.get(v).size(); n++) {
				Edge ne = graph.get(v).get(n);
				System.out.print("[" + ne.n + "@" + ne.w + "] ");
			}
			System.out.println();

		}

		System.out.println("-----------------------------------------");
	}

	static boolean hasPath(ArrayList<ArrayList<Edge>> graph, int src, int des, boolean[] visited) {
		if (src == des) {
			return true;
		}

		visited[src] = true;

		for (int i = 0; i < graph.get(src).size(); i++) {
			Edge nbr = graph.get(src).get(i);

			if (visited[nbr.n] == false) {

				if (hasPath(graph, nbr.n, des, visited) == true) {
					return true;
				}

			}
		}

		return false;

	}

	static void printPath(ArrayList<ArrayList<Edge>> graph, int src, int des, boolean[] visited, String psf, int wt) {
		if (src == des) {
			System.out.println(psf + " @ " + wt);
			return;
		}

		visited[src] = true;

		for (int i = 0; i < graph.get(src).size(); i++) {

			Edge nbr = graph.get(src).get(i);

			if (visited[nbr.n] == false) {
				printPath(graph, nbr.n, des, visited, psf + nbr.n + " ", wt + nbr.w);

			}
		}

		visited[src] = false;

	}

	private static String sp;
	private static String lp;
	private static String cp;
	private static String fp;
	private static int spw;
	private static int lpw;
	private static int cpw;
	private static int fpw;

	public static void multisolver(ArrayList<ArrayList<Edge>> graph, boolean[] visited, int s, int d, int cf, int ff) {
		spw = Integer.MAX_VALUE;
		lpw = Integer.MIN_VALUE;
		cpw = Integer.MAX_VALUE;
		fpw = Integer.MIN_VALUE;

		sp = "";
		lp = "";
		cp = "";
		fp = "";

		multisolver1(graph, s, d, visited, cf, ff, s + "", 0);
		System.out.println("Shortest = " + sp + "@" + spw);
		System.out.println("Longest = " + lp + "@" + lpw);
		System.out.println("Ceil = " + cp + "@" + cpw);
		System.out.println("Floor = " + fp + "@" + fpw);

	}

	public static void multisolver1(ArrayList<ArrayList<Edge>> graph, int s, int d, boolean[] visited, int cf, int ff,
			String psf, int wsf) {

		if (s == d) {

			System.out.println(psf + "@" + wsf);
			if (wsf < spw) {
				sp = psf;
				spw = wsf;
			}

			if (wsf > lpw) {
				lp = psf;
				lpw = wsf;
			}

			if (wsf > cf && wsf < cpw) {
				cp = psf;
				cpw = wsf;
			}

			if (wsf < ff && wsf > fpw) {
				fp = psf;
				fpw = wsf;
			}

		}

		visited[s] = true;

		for (int n = 0; n < graph.get(s).size(); n++) {
			Edge ne = graph.get(s).get(n);
			if (visited[ne.n] == false) {
				multisolver1(graph, ne.n, d, visited, cf, ff, psf + ne.n, wsf + ne.w);
			}
		}
		visited[s] = false;

	}

	public static class pair implements Comparable<pair> {
		int data;
		String path;

		@Override
		public int compareTo(pair arg0) {
			// TODO Auto-generated method stub
			return this.data - arg0.data;
		}

	}

	public static PriorityQueue<pair> pq;

	public static void kthLargest(ArrayList<ArrayList<Edge>> graph, boolean[] visited, int s, int d, int k) {
		pq = new PriorityQueue<>();
		multisolver2(graph, visited, s, d, k, s + "", 0);
		while (pq.size() > 0) {
			pair p = pq.remove();
			System.out.println(p.data + " @ " + p.path);
		}
	}

	private static void multisolver2(ArrayList<ArrayList<Edge>> graph2, boolean[] visited, int s, int d, int k,
									String psf, int wsf) {
		
		if(s==d) {
			if(pq.size()<k) {
				pair p=new pair();
				p.data=wsf;
				p.path=psf;
				pq.add(p);
			}else if(pq.peek().data<wsf) {
				pair p=pq.remove();
				p.data=wsf;
				p.path=psf;
				pq.add(p);
			}
		}
		
		visited[s] = true;

		for (int n = 0; n < graph.get(s).size(); n++) {
			Edge ne = graph.get(s).get(n);
			if (visited[ne.n] == false) {
				multisolver2(graph2, visited, ne.n, d, k, psf+ne.n, wsf+ne.w);
			}
		}
		visited[s] = false;
		
	}

//	=================================================Diff Approach===============================================
	 public static class Node {
         int pathLength;
         String path;
     }

     public static void kLargest(ArrayList<ArrayList<Edge>> graph , int src , int dest,boolean[] visited,int pathLength,String path,ArrayList<Node> myList){
        if(src==dest){
            Node temp = new Node();
            temp.pathLength=pathLength;
            temp.path=path;
            myList.add(temp);
            return ;
        }
        if(visited[src])
         return;
        
        visited[src]=true;
        ArrayList<Edge> list = graph.get(src);

        for(int i=0;i<list.size();++i){
            Edge edge = list.get(i);
            kLargest(graph, edge.n, dest,visited , pathLength +edge.w , path+"=>"+edge.n,myList);
        }
        visited[src]=false;
    }


    public static void kLargest(int k , int floor , int ceil){
        ArrayList<Node> list =new ArrayList<Node>();
        kLargest(graph,0,6,new boolean[graph.size()],0,"0",list);

        Comparator<Node> cmp = new Comparator<Node>(){
            public int compare(Node n1 , Node n2){
                return n1.pathLength-n2.pathLength;
            }
        };

        Collections.sort(list,cmp);

        for(Node node:list){
            System.out.println("length:"+node.pathLength + " path:"+node.path);
        }

        System.out.println("Kth-Largest: path: "+ list.get(k-1).path+ " length="+list.get(k-1).pathLength);

        int myceil=0,myFloor=0;
        String myceilPath="null",myfloorPath="null";

        for(Node node:list){
            if(node.pathLength>floor)
                break;
               
                myFloor=node.pathLength;
                myfloorPath=node.path;
            }
            System.out.println();
            System.out.println("FLOOR: path: "+ myfloorPath+ " length="+myFloor);
                System.out.println();

            for(int i=list.size()-1;i>=0;--i){
                Node node = list.get(i);
                if(node.pathLength<ceil)
                    break;
                   
                    myceil=node.pathLength;
                    myceilPath=node.path;
            }
                System.out.println("CEIL: path: "+ myceilPath+ " length="+myceil);

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for (int v = 0; v < 7; v++) {
			graph.add(new ArrayList<>());
		}

		addEdge(graph, 0, 1, 10);
		addEdge(graph, 1, 2, 10);
		addEdge(graph, 2, 3, 10);
		addEdge(graph, 0, 3, 40);
		addEdge(graph, 3, 4, 2);
		addEdge(graph, 4, 5, 3);
		addEdge(graph, 5, 6, 3);
		addEdge(graph, 4, 6, 8);

//		for adding a new vertex we have to add an element in first arraylist
//		graph.add(new ArrayList<>());

//		display(graph);

		boolean[] visited = new boolean[graph.size()];

//		System.out.println(hasPath(graph, 0, 6, visited));

//		printPath(graph, 0, 6, visited, 0+" ",0);

//		multisolver(graph, visited, 0, 6, 38, 40);

//		kthLargest(graph, visited, 0, 6, 3);
		kLargest(3,45, 47);
	}

}
