package main;

import java.util.concurrent.atomic.AtomicInteger;
public class sequence_parallel extends LLP{
    private int[] A;
    private int[][] pre;
    private boolean[] fixed;
    public sequence_parallel(int[] input){
        super(input.length);
        this.A=input;
        for(int i=0;i<A.length;i++){
            G[i] = new AtomicInteger(1);
            fixed[i]=false;
        }
    }
    @Override
    public boolean forbidden(int j) {
        if(fixed[j]==false){
            for(int i=0;i<j;i++){
                if(A[i]<A[j]&&fixed[i]==false){
                    return false;
                }
            }
        return true;
        }
        return false;
    }

    @Override
    public void advance(int j) {
        int maxI=0;
        for(int i=0;i<j;i++){
            if(A[i]<A[j]){
                maxI=i;
            }
        }
        G[j].set(G[maxI].incrementAndGet());
        fixed[j]=true;

    }

    public int[] getSolution(){
        int[] temp=new int[A.length];
        for(int i=0;i<temp.length;i++){
            temp[i]=G[i].get();
        }
        return temp;
    }
}