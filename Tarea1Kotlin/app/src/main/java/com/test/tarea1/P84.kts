package com.test.tarea1

fun minSpanningTree(graph: String): String {
    var ans = "["
    var adjlist = mutableMapOf<String, MutableList<Pair<String,Int> > >()
    var raw_graph = graph.substring(1,graph.lastIndex)
    var list_edges = raw_graph.split(",")
    var visited_edges = mutableMapOf<String,Boolean>()
    var startedge = ""
    for(edge in list_edges){
        var info_edge=edge.split("/")
        var verts = info_edge.get(0)
        var weight = info_edge.get(1).toInt()
        var listverts = verts.split("-")
        var vert1 = listverts.get(0)
        var vert2 = listverts.get(1)
        if(!visited_edges.containsKey(vert1)){
            visited_edges.set(vert1,false)
        }
        if(!adjlist.containsKey(vert1)){
            adjlist.put(vert1, mutableListOf<Pair<String,Int> >())
        }
        adjlist[vert1]!!.add(Pair(vert2,weight))
        if(!visited_edges.containsKey(vert2)){
            visited_edges.set(vert2,false)
        }
        if(!adjlist.containsKey(vert2)){
            adjlist.put(vert2, mutableListOf<Pair<String,Int> >())
        }
        adjlist[vert2]!!.add(Pair(vert1,weight))
        startedge=vert2
    }
    var out_edges= mutableListOf<Pair<Int, Pair<String,String>>>()
    for(i in adjlist[startedge]!!){
        out_edges.add(Pair(i.second, Pair(startedge,i.first)))
    }
    visited_edges[startedge]=true
    out_edges.sortBy { it.first }
    while(!out_edges.isEmpty()){
        var iter_edge = out_edges.first()
        if(!visited_edges[iter_edge.second.second]!!) {
            if(startedge==""){
                ans+=","
            }
            visited_edges[iter_edge.second.second]=true
            ans+=(iter_edge.second.first+"-"+iter_edge.second.second+"/"+iter_edge.first)
            startedge=""
            var new_node = iter_edge.second.second
            out_edges.remove(iter_edge)
            for(i in adjlist[new_node]!!){
                out_edges.add(Pair(i.second, Pair(new_node,i.first)))
            }
        }
        else{
            out_edges.remove(iter_edge)
        }
        out_edges.sortBy { it.first }
    }
    ans+="]"
    return ans
}

fun verifyWeight(graph: String,correct: Int): Boolean {
    var raw_graph = graph.substring(1,graph.lastIndex)
    var list_edges = raw_graph.split(",")
    var ans = 0
    for(edge in list_edges){
        var info_edge=edge.split("/")
        var weight = info_edge.get(1).toInt()
        ans+=weight
    }
    if(correct==ans){
        return true
    }
    return false
}

println(verifyWeight(minSpanningTree("[a-b/5,a-d/3,b-c/2,b-e/4,c-e/6,d-e/7,d-f/4,d-g/3,e-h/5,f-g/4,g-h/1]"),22))
println(verifyWeight(minSpanningTree("[a-b/5,b-c/7,a-c/20]"),12))
println(verifyWeight(minSpanningTree("[a-b/5,b-c/7,a-d/2,d-e/4,c-f/16,b-g/10]"),44))

/*
minSpanningTree(graph):
	ans=Graph()
	visited[graph.nodes.size]
	start=graph.nodes[0]
	visited[graph.nodes[0]]=true
	out_edges=[]
	for edge in adjlist[graph.nodes[0]]
		out_edges.append(edge)
	endfor
	sort(out_edges)
	while(not out_edges.empty())
		edge=out_edges[0]
		if(not visited[edge.nodes[1]])
			visited[edge.nodes[1]]=true
			Graph.addNode(edge.nodes[1])
			Graph.addEdge(edge)
			for edge1 in adjlist[edge.node[1]]
				out_edges.append(edge1)
			endfor
		endif
		out_edges.remove(edge)
		sort(out_edges)
	endwhile
	return ans
end
 */