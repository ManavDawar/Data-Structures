package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Graph {

	static class Edge {
		int nbr;
		int wt;

		public Edge(int nbr, int wt) {
			this.nbr = nbr;
			this.wt = wt;
		}
	}

	public static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

	public static void addEdge(ArrayList<ArrayList<Edge>> graph, int v1, int v2, int wt) {
		graph.get(v1).add(new Edge(v2, wt));
		graph.get(v2).add(new Edge(v1, wt));
	}

	public static void display(ArrayList<ArrayList<Edge>> graph) {
		System.out.println("-----------------------------------------");
		for (int v = 0; v < graph.size(); v++) {
			System.out.print(v + "->");

			for (int n = 0; n < graph.get(v).size(); n++) {
				Edge ne = graph.get(v).get(n);
				System.out.print("[" + ne.nbr + "@" + ne.wt + "] ");
			}
			System.out.println();

		}

		System.out.println("-----------------------------------------");

	}

	static boolean haspath(ArrayList<ArrayList<Edge>> graph, int src, int des, boolean[] visited) {
		if (src == des) {
			return true;
		}
		visited[src] = true;

		for (int n = 0; n < graph.get(src).size(); n++) {
			Edge ne = graph.get(src).get(n);
			if (visited[ne.nbr] == false) {
				if (haspath(graph, ne.nbr, des, visited)) {
					return true;
				}
			}
		}
		return false;

	}

	static void printallpaths(ArrayList<ArrayList<Edge>> graph, int src, int des, boolean[] visited, String psf,
			int wsf) {
		if (src == des) {
			psf += des;
			System.out.println(psf + "@" + wsf);
			return;
		}

		visited[src] = true;

		for (int n = 0; n < graph.get(src).size(); n++) {
			Edge ne = graph.get(src).get(n);
			if (visited[ne.nbr] == false) {
				printallpaths(graph, ne.nbr, des, visited, psf + src, wsf + ne.wt);
			}
		}
		visited[src] = false;
	}

	private static int spw;
	private static int lpw;
	private static int cpw;
	private static int fpw;
	private static String sp;
	private static String lp;
	private static String cp;
	private static String fp;

	public static void multisolver(ArrayList<ArrayList<Edge>> graph, int s, int d, boolean[] visited, int cf, int ff) {
		spw = Integer.MAX_VALUE;
		lpw = Integer.MIN_VALUE;
		cpw = Integer.MAX_VALUE;
		fpw = Integer.MIN_VALUE;

		multisolver(graph, s, d, visited, cf, ff, s + "", 0);
		System.out.println("spw=" + sp + "@" + spw);
		System.out.println("lpw=" + lp + "@" + lpw);
		System.out.println("cpw=" + cp + "@" + cpw);
		System.out.println("fpw=" + fp + "@" + fpw);

	}

	private static void multisolver(ArrayList<ArrayList<Edge>> graph, int s, int d, boolean[] visited, int cf, int ff,
			String psf, int wsf) {

		if (s == d) {

			if (wsf < spw) {
				spw = wsf;
				sp = psf;
			}

			if (wsf > lpw) {
				lpw = wsf;
				lp = psf;
			}

			if (wsf > cf && wsf < cpw) {
				cpw = wsf;
				cp = psf;
			}

			if (wsf < ff && wsf > fpw) {
				fpw = wsf;
				fp = psf;
			}
			return;

		}

		visited[s] = true;

		for (int n = 0; n < graph.get(s).size(); n++) {
			Edge ne = graph.get(s).get(n);
			if (visited[ne.nbr] == false) {
				multisolver(graph, ne.nbr, d, visited, cf, ff, psf + ne.nbr, wsf + ne.wt);
			}
		}

		visited[s] = false;

	}

	static void HamiltonianCycle(ArrayList<ArrayList<Edge>> graph, int src, boolean[] visited) {
		ArrayList<Integer> psf = new ArrayList<>();
//		withcout using this arraylist we could have passed a original source as paramter and have a parameter as String psf 

		HamiltonianCycle(graph, src, visited, psf);
	}

	private static void HamiltonianCycle(ArrayList<ArrayList<Edge>> graph, int src, boolean[] visited,
			ArrayList<Integer> psf) {

		if (psf.size() == graph.size() - 1) {

			for (int i = 0; i < psf.size(); i++) {
				System.out.print(psf.get(i) + " ");
			}
			System.out.print(src + " ");

			int first = psf.get(0);
			int last = src;
			boolean cycle = false;
			for (int i = 0; i < graph.get(first).size(); i++) {
				Edge ne = graph.get(first).get(i);
				if (ne.nbr == last) {
					cycle = true;
				}
			}

			if (cycle) {
				System.out.println("*");
			} else {
				System.out.println(".");
			}

		}

		visited[src] = true;

		for (int n = 0; n < graph.get(src).size(); n++) {
			Edge ne = graph.get(src).get(n);
			if (visited[ne.nbr] == false) {
				psf.add(src);
				HamiltonianCycle(graph, ne.nbr, visited, psf);
				psf.remove(psf.size() - 1);
			}

		}

		visited[src] = false;
	}

	static int counter = 0;

	static void KnightsTour(int[][] chess, int row, int col, int move) {
		// TODO Auto-generated method stub
		if (move == chess.length * chess.length - 1) {
			++counter;
			chess[row][col] = move;
			System.out.println("------------" + counter + "-------------");
			for (int i = 0; i < chess.length; ++i) {
				for (int j = 0; j < chess.length; ++j) {
					System.out.print(chess[i][j] + "\t ");
				}
				System.out.println();
			}
			chess[row][col] = -1;
			return;
		}

		chess[row][col] = move;

		if (isKvalid(chess, row - 2, col + 1)) {
			KnightsTour(chess, row - 2, col + 1, move + 1);
		}
		if (isKvalid(chess, row - 1, col + 2)) {
			KnightsTour(chess, row - 1, col + 2, move + 1);
		}
		if (isKvalid(chess, row + 1, col + 2)) {
			KnightsTour(chess, row + 1, col + 2, move + 1);
		}
		if (isKvalid(chess, row + 2, col + 1)) {
			KnightsTour(chess, row + 2, col + 1, move + 1);
		}
		if (isKvalid(chess, row + 2, col - 1)) {
			KnightsTour(chess, row + 2, col - 1, move + 1);
		}
		if (isKvalid(chess, row + 1, col - 2)) {
			KnightsTour(chess, row + 1, col - 2, move + 1);
		}
		if (isKvalid(chess, row - 1, col - 2)) {
			KnightsTour(chess, row - 1, col - 2, move + 1);
		}
		if (isKvalid(chess, row - 2, col - 1)) {
			KnightsTour(chess, row - 2, col - 1, move + 1);
		}

		chess[row][col] = -1;

	}

	static boolean isKvalid(int[][] chess, int row, int col) {

		if (row < 0 || row >= chess.length || col < 0 || col >= chess[0].length) {
			return false;
		} else if (chess[row][col] != -1) {
			return false;
		} else {
			return true;
		}

	}

	static boolean bfs(int s, int d) {
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		boolean[] visited = new boolean[graph.size()];
		while (q.size() > 0) {
			Integer rem = q.remove();

			if (visited[rem] == true) {
				continue;
			} else {
				visited[rem] = true;
			}
			if (rem == d) {
				return true;
			}
			for (int n = 0; n < graph.get(rem).size(); n++) {
				Integer e = graph.get(rem).get(n).nbr;
				if (visited[e] == false) {
					q.add(e);
				}
			}

		}

		return false;
	}

	static ArrayList<String> gcc() {
		ArrayList<String> comps = new ArrayList<>();
		boolean[] visited = new boolean[graph.size()];

		for (int i = 0; i < graph.size(); i++) {
			if (visited[i] == false) {
				String comp = gccomponents(visited, i);
				comps.add(comp);
			}
		}
		return comps;
	}

	private static String gccomponents(boolean[] visited, int i) {
		String comp = "";
		Queue<Integer> q = new LinkedList<>();
		q.add(i);
		while (q.size() > 0) {
			Integer rem = q.remove();
			if (visited[rem] == true) {
				continue;
			}
			visited[rem] = true;
			comp += rem;

			for (int n = 0; n < graph.get(rem).size(); n++) {
				Integer ne = graph.get(rem).get(n).nbr;
				if (visited[ne] == false) {
					q.add(ne);
				}
			}
		}
		return comp;
	}

	static ArrayList<Integer> gccint(ArrayList<ArrayList<Edge>> graph) {

		ArrayList<Integer> list = new ArrayList<>();

		boolean[] visited = new boolean[graph.size()];

		for (int v = 0; v < graph.size(); v++) {
			if (visited[v] == false) {
				Integer comp = getconnectedcomponentint(graph, visited, v);
				list.add(comp);
			}
		}
		return list;
	}

	private static Integer getconnectedcomponentint(ArrayList<ArrayList<Edge>> graph, boolean[] visited, int v) {

		Integer comp = 0;

		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(v);

		while (queue.size() > 0) {
			int rem = queue.removeFirst();

			if (visited[rem] == true) {
				continue;
			} else {
				visited[rem] = true;
			}

			comp++;

			for (int n = 0; n < graph.get(rem).size(); n++) {
				Edge ne = graph.get(rem).get(n);
				if (visited[ne.nbr] == false) {
					queue.add(ne.nbr);
				}
			}

		}

		return comp;
	}

	public static void astro(int[] arr1, int[] arr2, int n) {

		ArrayList<ArrayList<Edge>> graph1 = new ArrayList<>();
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph1.add(new ArrayList<>());
		}

		for (int i = 0; i < arr1.length; i++) {
			addEdge(graph1, arr1[i], arr2[i], 1);
		}
		list = gccint(graph1);
		int ans = 0;
		System.out.println(list);

		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				ans += list.get(i) * list.get(j);
			}
		}
		System.out.println(ans);

	}

	public static int noofisland(int[][] arr) {
		int count = 0;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 1) {
					count++;
					funcall(arr, i, j);
				}
			}
		}

		return count;
	}

	private static void funcall(int[][] arr, int i, int j) {

		if (i >= arr.length || i < 0 || j >= arr[0].length || j < 0 || arr[i][j] != 1) {
			return;
		}
		arr[i][j] = 0;
		funcall(arr, i + 1, j);
		funcall(arr, i - 1, j);
		funcall(arr, i, j + 1);
		funcall(arr, i, j - 1);

	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for (int i = 0; i < 7; i++) {
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
//		addEdge(graph, 2, 5, 5);

//		display(graph);
		boolean[] visited = new boolean[graph.size()];
//		System.out.println(haspath(graph, 1, 3, visited));

//		printallpaths(graph, 0, 6, visited, "", 0);
//		multisolver(graph, 0, 6, visited, 38, 40);
//		HamiltonianCycle(graph, 0, visited);

//		int[][] arr = new int[5][5];
//		for (int i = 0; i < arr.length; i++) {
//			Arrays.fill(arr[i], -1);
//		}
//		KnightsTour(arr, 1, 1, 0);

//		System.out.println(bfs(0, 6));
		
		System.out.println(gcc());
		
	}

}
