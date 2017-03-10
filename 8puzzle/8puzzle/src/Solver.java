
public class Solver {
	
	
	private int minmove;
	
	
	private boolean original=false;
	
	private boolean copy=false;
	
	
	private Node goal;
	
	private class Node implements Comparable<Node>  {
		
		
		private int move ;  // moves made so far
		
		private Node previous ; // previous Node
		
		private Board board;
		
		
		
		
		
		public Node (Board board)  {
			
			this.board = board;
			
			
			
		}
		
		
		public int compareTo(Node that)  {
			
			int priority1 = this.board.manhattan()+this.move;
			
			int priority2 = that.board.manhattan()+that.move;
			
			if(priority1<priority2)
				
				return -1;
			
			else if (priority1>priority2)
				
				return 1;
			
			
			else 
				
				return 0;
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
	}
	
	
	
	public Solver(Board initial) {
		
		
		Node root1 = new Node(initial) ;   // root1 of game tree
		
		Node root2 = new Node(initial.twin());   // root2 of game tree
		
		MinPQ<Node> pq1 = new MinPQ<Node>();
		
		MinPQ<Node> pq2 = new MinPQ<Node>();
		
		
		pq1.insert(root1);
		
		pq2.insert(root2);
		
		while(true) {
			
			
			
			
			
			
			
			Node out1 = pq1.delMin();
			
			
			
			
			
			if(out1.board.isGoal())  {
				
				original = true;
				
				goal = out1;
				
				break;
				
				
				
			}
			
			Node out2 = pq2.delMin();
			
			
			if(out2.board.isGoal())  {
				
				copy = true;
				
				break;
				
				
				
			}
			
			
			for(Board s: out1.board.neighbors())  {  // all the neighbors
				
				
				if(out1.previous==null) {
					
					Node inserted1 = new Node(s);
					
					inserted1.previous = out1;
					
					inserted1.move = out1.move +1;
					
					pq1.insert(inserted1);
					
					
				}
					
					
					
				else {
					
					if(s.equals(out1.previous.board))
						
						continue;
					
					
                    Node inserted1 = new Node(s);
					
					inserted1.previous = out1;
					
					inserted1.move = out1.move +1;
					
					pq1.insert(inserted1);
					
					
					
					
					
					
					
					
					
					
					
				}
				
				
				
				
				
				
				
			}
			
			
                for(Board s: out2.board.neighbors())  {  // all the neighbors
				
				
				if(out2.previous==null) {
					
					Node inserted2 = new Node(s);
					
					inserted2.previous = out2;
					
					inserted2.move = out2.move +1;
					
					pq2.insert(inserted2);
					
					
				}
					
					
					
				else {
					
					if(s.equals(out2.previous.board))
						
						continue;
					
					
                    Node inserted2 = new Node(s);
					
					inserted2.previous = out2;
					
					inserted2.move = out2.move +1;
					
					pq2.insert(inserted2);
					
					
					
					
					
					
					
					
					
					
					
				}
				
				
				
				
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
	}
	
	
	public boolean isSolvable() {
		
		if(original==true)
			
			return true;
		
		else 
			
			return false;
		
		
		
		
		
	}
	
	
	public int moves() {
		
		if(!isSolvable())
			
			return -1;
		
		
		else 
			
			return goal.move;
		
		
		
		
	}
	
	
	
	public Iterable<Board> solution() {
		
		
		if(!isSolvable())
			
			return null;
		
		else  {
			
			
			Stack<Board> s = new Stack<Board>();
			
			Node temp = goal;
			
			  while(temp!=null)
		        {
		                s.push(temp.board);
		                temp = temp.previous;
		        }
			  
			  
			  
			  return s;
			
			
			
			
			
		}
			
			
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String filename = args[0];
		
		In in = new In(filename);
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);

	    // solve the puzzle
	    Solver solver = new Solver(initial);

	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
		
		
		
		
		
		
		

	}

}
