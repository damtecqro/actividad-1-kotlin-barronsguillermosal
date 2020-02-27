package com.test.tarea1

fun rotate (r:Int, lst:List<Char>):List<Char> {
    val k=r%(lst.size)
    val ans= mutableListOf<Char>()
    if(k>=0){
        for(i in k..(lst.size-1)){
            ans.add(lst.get(i))
        }
        for(i in 0..(k-1)){
            ans.add(lst.get(i))
        }
    }
    else{
        for(i in (lst.size+k)..(lst.size-1)){
            ans.add(lst.get(i))
        }
        for(i in 0..(lst.size+k-1)){
            ans.add(lst.get(i))
        }
    }
    return ans
}

println(rotate(3, "abcdefghijk".toList()))
println(rotate(-2, "abcdefghijk".toList()))
println(rotate(12, "abcdefghijk".toList()))
println(rotate(0, "abcdefghijk".toList()))