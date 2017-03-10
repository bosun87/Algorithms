




public class KdTree {
	
	
	private Node root;
	
	private int N;
	
	private static class Node {
		
		private Point2D p;
		
		private RectHV rect;
		
		private Node lb;
		
		private Node rt; 
		
		// private Node parent;
		
		private boolean flag;
		
		public Node (Point2D p) {
			
			this.p=p;
			
		}
		
	}
	
	public KdTree () {
		
		
		
		this.N=0;
		
		
	}
	
	
	public boolean isEmpty() {
		
		
		return this.N==0;
		
	}
	
	public int size() {
		
		
		return this.N;
		
	}
	
	public boolean contains(Point2D p) {
		
		return contains(root,p);
		
		
		
		
		
		
		
	}
	
	private boolean contains(Node x,Point2D p) {
		
		if(x==null) return false;
		
		if (x.flag==false) {
			
			double cmp = p.x()-x.p.x();
			
			
			if(cmp<0)   return contains(x.lb,p);
			
			else if(cmp>0)  return contains(x.rt,p);
			
			else {
				
				if(p.y()==x.p.y())
					
					return true;
				
				else 
					
					return contains(x.rt,p);
					
					
				
				
			}
			
			
			
			
		}
		
		else {
			
			double cmp = p.y()-x.p.y();
			
			if(cmp<0)   return contains(x.lb,p);
			
			else if(cmp>0) return contains(x.rt,p);
			
			else {
				
				if(p.x()==x.p.x())
					
					return true;
				
				else
					
					return contains(x.rt,p);
				
				
				
			}
			
			
			
			
		}
		
		
		
		
	}
	
	
	public void insert(Point2D p) {
		
		if (!contains(p))  {
			
			this.N=this.N+1;
			
			this.root = insert (this.root,p);
			
			if(this.root.rect==null)
				
				this.root.rect=new RectHV(0,0,1,1);
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
	}
	
	private Node insert(Node x, Point2D p)  {
		
		if(x==null) 
			
			return new Node(p);
		
		
		if(x.flag==false)  {
			
			double cmp = p.x()-x.p.x();
			
			if(cmp<0) {
				
				x.lb = insert(x.lb,p);
				
				
					
			    x.lb.flag = true;
				
				// x.lb.parent = x;
			    
			    if(x.lb.rect==null)
				
				x.lb.rect = new RectHV(x.rect.xmin(),x.rect.ymin(),x.p.x(),x.rect.ymax());
				
			}
			
			
			else {
				
				x.rt = insert(x.rt,p);
				
               
					
				x.rt.flag = true;
                
                // x.rt.parent = x;
				
				if(x.rt.rect==null)
                
                x.rt.rect = new RectHV(x.p.x(),x.rect.ymin(),x.rect.xmax(),x.rect.ymax());
				
				
				
				
				
			}
				
				
			
			
		}
		
		
		else {
			
			double cmp = p.y()-x.p.y();
			
            if(cmp<0) {
				
				x.lb = insert(x.lb,p);
				
				
					
			    x.lb.flag = false;
				
				
				
				// x.lb.parent = x;
			    
			    if(x.lb.rect==null)
				
				x.lb.rect =  new RectHV (x.rect.xmin(),x.rect.ymin(),x.rect.xmax(),x.p.y());
				
			}
            
            
              else {
				
				x.rt = insert(x.rt,p);
				
                
					
				x.rt.flag = false;
				
				
                
               //  x.rt.parent = x;
				
				if(x.rt.rect==null)
                
                x.rt.rect =  new RectHV (x.rect.xmin(),x.p.y(),x.rect.xmax(),x.rect.ymax());
				
				
				
				
				
			}
            
            
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		return x;
		
		
		
		
	
			
			
		
		
		
		
	}
	
	public void draw()  {
		
		Queue<Node> keys = new Queue<Node>();
		
        Queue<Node> queue = new Queue<Node>();
        
        queue.enqueue(this.root);
        
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x);
            queue.enqueue(x.lb);
            queue.enqueue(x.rt);
        }
        
        
        for (Node s : keys)  {
        	
        	
        	StdDraw.setPenColor(StdDraw.BLACK);
        	
        	StdDraw.setPenRadius(.01);
        	
        	s.p.draw();
        	
        	if(s.flag==false)  {
        		
        		StdDraw.setPenColor(StdDraw.RED);
        		
        		StdDraw.setPenRadius();
        		
        		s.p.drawTo(new Point2D(s.p.x(),s.rect.ymin()));
        		
        		s.p.drawTo(new Point2D(s.p.x(),s.rect.ymax()));
        		
        		
        		
        		
        	}
        	
        	
        	else  {
        		
                StdDraw.setPenColor(StdDraw.BLUE);
        		
        		StdDraw.setPenRadius();
        		
        		s.p.drawTo(new Point2D(s.rect.xmin(),s.p.y()));
        		
        		s.p.drawTo(new Point2D(s.rect.xmax(),s.p.y()));
        		
        		
        		
        		
        		
        		
        	}
        	
        	
        	
        	
        	
        	
        	
        }
        
        
        
        
		
		
		
		
		
		
	}
		
		
		
		
		
		
	public Iterable<Point2D> range(RectHV rect) {
		
		
		Queue<Point2D> keys = new Queue<Point2D>();
		
		range(this.root,keys,rect);
		
		return keys;
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	private void range(Node x, Queue<Point2D> queue, RectHV rect) {
		
		
		if(x==null) return;
		
		// if(!x.rect.intersects(rect)) return;
		
		if(rect.contains(x.p))  {
			
			queue.enqueue(x.p);
			
		/*	if(rect.xmin()==x.p.x() || rect.ymin()==x.p.y())  {
				
				
				range(x.rt,queue,rect);
				
				
			} */
			
			
			
			
			range(x.lb,queue,rect);
			range(x.rt,queue,rect);
			
			
			
		}
		
		
		else  {
			
			
			if(x.flag==false) {
				
				if(rect.xmax()<x.p.x())
					
					range(x.lb,queue,rect);
				
				else if(rect.xmin()>=x.p.x())
					
					range(x.rt,queue,rect);
				
				else {
					
					range(x.lb,queue,rect);
					range(x.rt,queue,rect);
					
					
				}
					
				
				
				
				
				
				
			}
			
			
			
			else {
				
				if(rect.ymax()<x.p.y())
					
					range(x.lb,queue,rect);
				
				else if (rect.ymin()>=x.p.y())
					
					range(x.rt,queue,rect);
				
				else {
					
					range(x.lb,queue,rect);
					range(x.rt,queue,rect);
					
					
					
				}
				
				
				
				
				
				
				
				
			}
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
	}
	
	public Point2D nearest(Point2D p) {
		
		if(this.root==null)
			
			return null;
		
		Queue<Point2D> keys = new Queue<Point2D>();
		
		Point2D q = new Point2D(100,100);
		
		keys.enqueue(q);
		
		nearest(this.root,keys,p);
		
		return keys.peek();
		
		
		
		
		
		
		
		
		
		
	}
	
	
	private void nearest(Node x, Queue<Point2D> queue, Point2D p )  {
		
		if(x==null) return;
		
		Point2D t = queue.peek();
		
		if(t.distanceSquaredTo(p)<=x.rect.distanceSquaredTo(p))  return;
		
		if(x.p.distanceSquaredTo(p)<t.distanceSquaredTo(p))  {
			
			queue.dequeue();
			
			queue.enqueue(x.p);
			
			if(x.flag==false) {
				
				if(p.x()<x.p.x()) {
					
					nearest(x.lb,queue,p);
					
					nearest(x.rt,queue,p);
				}
				
				else {
					
					nearest(x.rt,queue,p);
					
					nearest(x.lb,queue,p);
					
					
					
					
					
				}
					
					
				
				
			}
			
			
			else {
				
				
				if(p.y()<x.p.y())  {
					
                    nearest(x.lb,queue,p);
					
					nearest(x.rt,queue,p);
					
					
					
				}
				
				
				else  {
					
                    nearest(x.rt,queue,p);
					
					nearest(x.lb,queue,p);
					
					
					
				}
				
				
				
			}
			
			
			
			
			
			
		}
		
		
		else  {
			
               if(x.flag==false) {
				
				if(p.x()<x.p.x()) {
					
					nearest(x.lb,queue,p);
					
					nearest(x.rt,queue,p);
				}
				
				else {
					
					nearest(x.rt,queue,p);
					
					nearest(x.lb,queue,p);
					
					
					
					
					
				}
					
					
				
				
			}
               
               
               else {
   				
   				
   				if(p.y()<x.p.y())  {
   					
                    nearest(x.lb,queue,p);
   					
   					nearest(x.rt,queue,p);
   					
   					
   					
   				}
   				
   				
   				else  {
   					
                    nearest(x.rt,queue,p);
   					
   					nearest(x.lb,queue,p);
   					
   					
   					
   				}
   				
   				
   				
   			}
               
               
               
               
               
               
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
			
			
		
		
		
		
		
		
		
		
		
		
		
	}
		
		
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		StdDraw.show(0);
		
		KdTree kdtree = new KdTree();
		
		Point2D p1 = new Point2D(0.7, 0.2);
		
		kdtree.insert(p1);
		
		Point2D p2 = new Point2D(0.5, 0.4);
		
		kdtree.insert(p2);
		
        Point2D p3 = new Point2D(0.6, 0.4);
		
		kdtree.insert(p3);
		
		System.out.println(kdtree.size());
		
		System.out.println(kdtree.contains(p1));
		
		System.out.println(kdtree.root.p);
		
		
		
		
        /*Point2D p4 = new Point2D(0.4, 0.7);
		
		kdtree.insert(p4);
		
        Point2D p5 = new Point2D(0.9, 0.6);
		
		kdtree.insert(p5);*/
		
		
		kdtree.draw();
		
		
		StdDraw.show(50);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
