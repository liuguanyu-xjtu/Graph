publiv class WeightedGraph<V> implements  AbstractGraph<V>{
	java.util.Vector<V> vertexs=null;
	Integer[][] edges=null;
	Integer[][] distances=null;
	
	public WeightedGraph(Integer[][] edges,V[] vertexs){
		this.vertexs=new java.util.Vector<V>();
		for(int i=0;i<vertexs.length;i++){
			this.vertexs.add(vertexs[i]);
		}
		this.edges=edges;distances=new Integer[this.getSize()][this.getSize()];
		for(int i=0;i<this.getSize();i++){
			this.makeDistance(i);
			
		}
		
	}
	public void makeDistance(int index){
		distances[index][index]=0;
//		java.util.Vector<Record<Integer>> close=new java.util.Vector<Record<Integer>>(),open=new java.util.Vector<Record<Integer>>();
		Integer[] close=new Integer[this.getSize()],open=new Integer[this.getSize()];
		for(int i=0;i<this.getSize();i++){
			close[i]=null;
			open[i]=null;
		}
		close[index]=0;
		int p=index;
		while(true){
			V temp=this.getVertex(p);
			java.util.Vector<V> neighbors=this.getNeighbor(temp);
			for(int i=0;i<neighbors.size();i++){
				int q=this.indexOf(neighbors.get(i));//邻居
				int l=close[p]+this.edges[p][q];
				if(open[q]==null||open[q]>l){
					if(close[q]==null)
					open[q]=l;
				}
				
			}boolean br=true;int yy=-1,mm=-1;
			for(int i=0;i<this.getSize();i++){
				if(open[i]!=null){
					if(yy==-1||yy>open[i]){
						yy=open[i];
						mm=i;
					}
					br=false;
				}
			}
			if(br){
				break;
			}
			p=mm;
			close[p]=open[p];
			open[p]=null;
		}
		for(int i=0;i<this.getSize();i++){
			this.distances[index][i]=close[i];
		}
		
	}
	public java.util.Vector<String> route(int start,int end){
		java.util.Vector<String> results=new java.util.Vector<String>();
		//BFS
		java.util.Vector<V> neighbors=this.getNeighbor(this.getVertex(start));
		for(int i=0;i<neighbors.size();i++){
			int p=this.indexOf(neighbors.get(i));
			if(p==end&&this.distances[p][start]==this.distances[start][end]){
				String u=Integer.toString(start)+Integer.toString(p);
				results.add(u);
			}else{
				if(p!=end&&this.distances[start][p]+this.distances[p][end]==this.distances[start][end]){
					java.util.Vector<String> t=route(p,end);
					t=cross(Integer.toString(start),t);
					results.addAll(t);
				}
			}
		}
		return results;
	}
	public java.util.Vector<String> cross(String a,java.util.Vector<String> b){
		java.util.Vector<String> results=new java.util.Vector<String>();
		
			for(int j=0;j<b.size();j++){
				String u=a+b.get(j);
				results.add(u);
			}
		
		return results;
	}
	
	@Override
	public int getSize() {
		return this.vertexs.size();
	}

	@Override
	public java.util.Vector<V> getVertexs() {
		// TODO Auto-generated method stub
		return  this.vertexs;
	}

	@Override
	public int indexOf(V a) {
		// TODO Auto-generated method stub
		return this.vertexs.indexOf(a);
	}

	@Override
	public java.util.Vector<V> getNeighbor(V a) {
		java.util.Vector<V> neighbors = new java.util.Vector<V>();
		int index=this.indexOf(a);
		for(int i=0;i<index;i++){
			if(this.edges[i][index]!=null){
				neighbors.add(this.vertexs.get(i));
				
			}
		}
		for(int j=index+1;j<this.getSize();j++){
			if(this.edges[index][j]!=null){
				neighbors.add(this.vertexs.get(j));
				
			}
		}
		return neighbors;
	}

	@Override
	public int getDegree(V a) {
		// TODO Auto-generated method stub
		return this.getNeighbor(a).size();
	}

	@Override
	public Integer[][] getAdjacencyMatrix() {
		// TODO Auto-generated method stub
		return this.edges;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public V getVertex(int y) {
		// TODO Auto-generated method stub
		return this.vertexs.get(y);
	}
	
}

//class Record{
//	public Record(int a,int l){
//		this.a=a;
//		this.l=l;
//	}
//	int a;
//	int l;
//}

interface AbstractGraph<V>{
	public int getSize();//顶点个数
	public java.util.Vector<V> getVertexs();
	public int indexOf(V a);
	public java.util.Vector<V> getNeighbor(V a);
	public int getDegree(V a);
	public Integer[][] getAdjacencyMatrix();
	public void print();
	public V getVertex(int y);
	
}
