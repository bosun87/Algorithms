



public class Board {
	
	private int N;  //dimension
	
	private int [][] tiles;   // block array
	
	private int zero1,zero2;  // the location of 0
	
	private int manhattan1;
	
	
	
	
	
	public Board(int[][] blocks)  {
		
        int result=0;
		
		int temp;
		
		int row,column; 
		
		
	    
		
		
		this.N= blocks.length;
		
		
		
		tiles = new int [N][N];
		
		for(int i=0;i<N;i++) {
			
			for(int j=0;j<N;j++) {
				
				
				
				if(blocks[i][j]==0) {
					
					this.zero1 = i;
					this.zero2 = j;
					
				}
				
				tiles[i][j] = blocks[i][j];
				
				
				
                if(tiles[i][j]==0)
					
					continue;
				
				
				temp= tiles[i][j];
				
				column = temp%N==0?N-1:temp%N-1;
				
				row = (temp-column)/N;
				
				result = result + Math.abs(row-i) + Math.abs(column-j);
				
                
				
				
				
				
				
				
				
				
			}
		
		
		
		
	}
		
		
		
		
		this.manhattan1 = result;
		
		
		
		
		
		
		
				
				
		
		
		
		
		
		
		
		
	}
	
	
	public int dimension()  {
		
		
		return N;
	}
	
	
	public int hamming() {
		
		int result=0;
		
		
		
		
		
		for(int i=0;i<N;i++)
			
			for(int j=0;j<N;j++)  {
				
				
				
				if(tiles[i][j]==0)
					
					continue ;
				
				
				
				if(tiles[i][j]!=i*N+j+1)
					
					result++;
					
				
				
			}
		
		return result;
		
		
		
		
		
	}
	
	
	public int manhattan() {
		
		
		
		
				
			return this.manhattan1;	
				
				
				
				
				
				
				
				
				
				
				
				
			
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean isGoal()  {
		
		if(hamming()==0)
			
			return true;
		
		else
			
			return false;
		
		
		
		
		
		
		
	}
	
	
	
	
	public Board twin() {
		
		int [][] aux;
		
		aux = new int [N][N];
		
		for(int i=0;i<N;i++) 
			
			for(int j=0;j<N;j++)
				
				aux[i][j]= tiles[i][j];
		
		if(zero1+1<=N-1) {
			
			int temp;
			
			temp = aux[zero1+1][0];
			
			aux[zero1+1][0] = aux[zero1+1][1];
			
			aux[zero1+1][1]= temp;
			
			
			
			
			
		}
		
		else {
			
			int temp;
			
			temp = aux[zero1-1][0];
			
			aux[zero1-1][0]= aux[zero1-1][1];
			
			aux[zero1-1][1] =  temp;
			
			
		}
		
		
		return new Board(aux);
			
			
			
	}
	
	
	
	public boolean equals(Object y) {
		
		
		if(y==this) return true;
		
		if(y==null) return false;
		
		if(y.getClass() != this.getClass()) return false;
		
		Board that = (Board) y;
		
		if(this.N!=that.N)
			
			return false;
		
		for(int i =0; i<N; i++) 
			
			for(int j=0;j<N;j++) 
				
				if(this.tiles[i][j]!=that.tiles[i][j])
					
					return false;
		
		
		return true;
				
				
		
		
		
		
		
		
	}
	
	
	public Iterable<Board> neighbors() {    // return all neighboring board
		
		
		Queue<Board> queue = new Queue<Board>(); 
		
		int [][] aux;
		
		aux = new int [N][N];
		
		for(int i=0;i<N;i++) 
			
			for(int j=0;j<N;j++)
				
				aux[i][j]= tiles[i][j];
		
		int temp;
		
		
		
		
		if(zero2-1<=N-1&&zero2-1>=0) {
			
			
			
			
			
			
			temp = aux[zero1][zero2-1];
			
			aux[zero1][zero2-1] = aux[zero1][zero2];
			
			aux[zero1][zero2] = temp;
			
			queue.enqueue(new Board(aux));
			
            temp = aux[zero1][zero2-1];
			
			aux[zero1][zero2-1] = aux[zero1][zero2];
			
			aux[zero1][zero2] = temp;
			
			
			
			
		}
		
		
		if(zero2+1<=N-1&&zero2+1>=0)  {
			
			
            
			
			
			
            temp = aux[zero1][zero2+1];
			
			aux[zero1][zero2+1] = aux[zero1][zero2];
			
			aux[zero1][zero2] = temp;
			
			
			queue.enqueue(new Board(aux));
			
            temp = aux[zero1][zero2+1];
			
			aux[zero1][zero2+1] = aux[zero1][zero2];
			
			aux[zero1][zero2] = temp;
			
			
			
			
		}	
		
		
		if(zero1-1<=N-1&&zero1-1>=0)  {
			
			
            
			
			
		
			
            temp = aux[zero1-1][zero2];
			
			aux[zero1-1][zero2] = aux[zero1][zero2];
			
			aux[zero1][zero2] = temp;
			
			queue.enqueue(new Board(aux));
			
            temp = aux[zero1-1][zero2];
			
			aux[zero1-1][zero2] = aux[zero1][zero2];
			
			aux[zero1][zero2] = temp;
			
			
			
			
			
		}
		
		
		if(zero1+1<=N-1&&zero1+1>=0)  {
			
			
            
			
            
			
            temp = aux[zero1+1][zero2];
			
			aux[zero1+1][zero2] = aux[zero1][zero2];
			
			aux[zero1][zero2] = temp;
			
			queue.enqueue(new Board(aux));
			
            temp = aux[zero1+1][zero2];
			
			aux[zero1+1][zero2] = aux[zero1][zero2];
			
			aux[zero1][zero2] = temp;
			
			
			
			
			
		}
		
		
		return queue;
			
			
			
	}		
			
			
		
			
			
			
		
		
		
		
		
		
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		
	
	
	
	public String toString() {
		
		
		
	    
		StringBuilder s = new StringBuilder();
	    s.append(N + "\n");
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            s.append(String.format("%2d ", tiles[i][j]));
	        }
	        s.append("\n");
	    }
	    return s.toString();
		
		
		
		
	}
	
	
	
		
			
			
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
