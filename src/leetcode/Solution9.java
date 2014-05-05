package leetcode;

import java.util.ArrayList;
import java.util.HashSet;

public class Solution9 {
    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
     * design an algorithm to find the maximum profit.
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length <= 1)
            return 0;
        int len = prices.length;
        //ma keep the minimum number till now.
        int[] ma = new int[len];
        //mia keep the maximum number from the end to now
        int[] mia = new int[len];
        ma[0] = prices[0];
        for(int i=1;i<len;i++){
            if(prices[i]>ma[i-1])
                ma[i] = ma[i-1];
            else
                ma[i] = prices[i];
        }
        mia[len-1] = prices[len-1];
        for(int i = len-2;i>=0;i--){
            if(prices[i]>mia[i+1])
                mia[i] = prices[i];
            else
                mia[i] = mia[i+1];
        }
        int max=0;
        for(int i=0;i<len;i++){
            max= mia[i]-ma[i]>max? mia[i]-ma[i]:max;
        }
        return max;
    }
    /**
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
     * (ie, buy one and sell one share of the stock multiple times).
     *  However, you may not engage in multiple transactions at the same time 
     *  (ie, you must sell the stock before you buy again).
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices){
        if(prices==null || prices.length <= 1)
            return 0;
        int profit = 0;
        int len = prices.length;
        //the local minimum is marked as -1;the local maximum is marked as 1; otherwise is 0
        int[] mark = new int[len];
        int[] left = new int[len];//mark whether the left is <= or >=
        int[] right = new int[len];//mark whether the right is <= or >=
        // the first number is initialized as 0
        left[0] = 0;
        //get the left array, by one traversal
        int i=1;
        while(i<len){
            if(prices[i-1]<prices[i])
                left[i] = -1;
            else if(prices[i-1]>prices[i])
                left[i] = 1;
            else
                left[i] = left[i-1];
            i++;
        }
        //initialize the last element 
        right[len-1] = 0;
        i = len-2;
        while(i>=0){
            if(prices[i+1]<prices[i])
                right[i] = -1;
            else if(prices[i+1]>prices[i])
                right[i] = 1;
            else
                right[i] = right[i+1];
            i--;
        }
        //get the mark
        i = 0;
        while(i<len){
            if(left[i] == right[i]){
                mark[i] = left[i]*(-1);
            }else {
                if(left[i] == 0){
                    mark[i] = right[i]*(-1);
                }
                else if(right[i] == 0)
                        mark[i] = left[i]*(-1);
                else
                    mark[i] = 0;
            }
            i++;
        }
        int buy=0 ,sell = 0;
        boolean f1 = false, f2 = false;
        for(i=0;i<len;i++){
            if(f1==false && mark[i] == -1)//at the minimum time buy.
            {
                buy = prices[i];
                f1 = true;
            }
            if(f2 == false && f1 == true && mark[i] == 1)//at the maximum time sell, by the condition that we have bought the stock;
            {
                sell = prices[i];
                f2 = true;
            }
            if(f1 && f2)
            {
                profit += (sell - buy); 
                f1 = false;
                f2 = false;
            }
        }
        return profit;
     }
    /**
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices){
        if(prices==null || prices.length <= 1)
            return 0;
        int profit = 0;
        int len = prices.length;
        //the local minimum is marked as -1;the local maximum is marked as 1; otherwise is 0
        int[] mark = new int[len];
        int[] left = new int[len];//mark whether the left is <= or >=
        int[] right = new int[len];//mark whether the right is <= or >=
        // the first number is initialized as 0
        left[0] = 0;
        //get the left array, by one traversal
        int i=1;
        while(i<len){
            if(prices[i-1]<prices[i])
                left[i] = -1;
            else if(prices[i-1]>prices[i])
                left[i] = 1;
            else
                left[i] = left[i-1];
            i++;
        }
        //initialize the last element 
        right[len-1] = 0;
        i = len-2;
        while(i>=0){
            if(prices[i+1]<prices[i])
                right[i] = -1;
            else if(prices[i+1]>prices[i])
                right[i] = 1;
            else
                right[i] = right[i+1];
            i--;
        }
        //get the mark
        i = 0;
        while(i<len){
            if(left[i] == right[i]){
                mark[i] = left[i]*(-1);
            }else {
                if(left[i] == 0){
                    mark[i] = right[i]*(-1);
                }
                else if(right[i] == 0)
                        mark[i] = left[i]*(-1);
                else
                    mark[i] = 0;
            }
            i++;
        }
        for( i=0;i<len-1;i++){
            if(mark[i] == 1){
                int temp1 = oneTransaction(0, i+1, prices);
                int temp2=0;
                if(i+1<len)
                    temp2 = oneTransaction(i+1,len,prices);
                if(temp1+temp2>profit)
                    profit = temp1+temp2;
            }
        }
        int temp = oneTransaction(0,len,prices);
        if(temp>profit)
            profit = temp;
        return profit;
    }
    /**
     * 
     * @param start [
     * @param end 锛�     * @param mark
     * @param prices
     * @return
     */
    private int oneTransaction(int start, int end,  int[] prices){
       if(end-start<=1)
           return 0;
       if(end-start==2)
           return prices[end-1]-prices[start]>0?prices[end-1]-prices[start]:0;
        int len = prices.length;
        int[] lmin = new int[len];
        int[] rmax = new int[len];
        lmin[start] = prices[start];
        int i = start+1;
        while(i<end){
            if(prices[i]>lmin[i-1])
                lmin[i] = lmin[i-1];
            else
                lmin[i] = prices[i];
            i++;
        }
        i = end-1;
        rmax[i] = prices[i];
        i--;
        while(i>=start){
            if(prices[i]>rmax[i+1])
                rmax[i] = prices[i];
            else
                rmax[i] = rmax[i+1];
            i--;
        }
        int max = 0;
        for(i=start;i<end;i++){
            if(rmax[i]-lmin[i]>max)
                max = rmax[i] - lmin[i];
        }
        return max;
    }
    /**
     * Given numRows, generate the first numRows of Pascal's triangle.
     * @param numRows
     * @return
     */
    public ArrayList<ArrayList<Integer>> generate(int numRows){
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(numRows<=0)
            return result;
        ArrayList<Integer> row1 = new ArrayList<Integer>();
        row1.add(1);
        result.add(row1);
        if(numRows == 1)
        {
            return result;
        }
        ArrayList<Integer> row2 = new ArrayList<Integer>();
        row2.add(1);
        row2.add(1);
        result.add(row2);
        if(numRows == 2){
            return result;
        }
        for(int i=3;i<=numRows;i++){
            ArrayList<Integer> ls = new ArrayList<Integer>();
            genOneRow(i,result.get(i-2),ls);
            result.add(ls);
        }
        return result;
    }
    /**
     * 鐢熸垚绗瑀ow鎺掔殑缁撴灉
     * @param row
     * @param last
     * @param curr
     */
    private void genOneRow(int row,ArrayList<Integer> last, ArrayList<Integer> curr){
        curr.add(1);
        for(int i=0;i<last.size()-1;i++){
            curr.add(last.get(i)+last.get(i+1));
        }
        curr.add(1);
    }
    /**
     * Given an index k, return the kth row of the Pascal's triangle.
     * only O(k) extra space
     * @param rowIndex
     * @return
     */
    public ArrayList<Integer> getRow(int rowIndex){
        ArrayList<Integer> r = new ArrayList<Integer>();
        if(rowIndex<0)
            return r;
        r.add(1);
        if(rowIndex == 0)
            return r;
        for(int i=1;i<rowIndex;i++){
            //It's very important here!! handle the big number that's out of range
            if(r.get(i-1)>Math.pow(2, 31)/(rowIndex-i+1))
             {
                int t = gcd(r.get(i-1),i);
                int temp = r.get(i-1)/t * (rowIndex-i+1)*t/i;
                System.out.println(temp);
                r.add(temp);
                continue;
             }
                
            int temp = r.get(i-1)*(rowIndex-i+1)/i;
            r.add(temp);
        }
        r.add(1);
        return r;
    }
    /**
     * 姹傛渶澶у叕绾︽暟锛屼负浜嗗鐞嗗ぇ鏁存暟
     * @param a
     * @param b
     * @return
     */
    private int gcd(int a, int b){
        while(a%b!=0){
            int t = b;
            b = a%b;
            a = t;
        }
        return b;
    }
    /**
     * Given a triangle, find the minimum path sum from top to bottom. 
     * Each step you may move to adjacent numbers on the row below.
     * @param triangle
     * @return
     */
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle){
        if(triangle==null || triangle.size()<=0)
            return 0;
        if(triangle.size() == 1)
            return triangle.get(0).get(0);
        ArrayList<Integer> last = triangle.get(0);
        ArrayList<Integer> curr = new ArrayList<Integer>();
        //through Dynamic Process 
        for(int i=1;i<triangle.size();i++){
            curr.add(last.get(0)+triangle.get(i).get(0));
            for(int j=1;j<i;j++){
                //get the smaller adjacent one on the last row 
                curr.add((last.get(j-1)<last.get(j)?last.get(j-1):last.get(j))+triangle.get(i).get(j));
            }
            curr.add(last.get(i-1)+triangle.get(i).get(i));
            last.clear();
            last.addAll(curr);
            curr.clear();
        }
        
        int min = Integer.MAX_VALUE;
        for(int i=0;i<last.size();i++){
            if(last.get(i)<min)
                min = last.get(i);
        }
        return min;
    }
    public static void main(String[] args){
        int [] a = {1,2,4};
        Solution9 ht = new Solution9();
       // System.out.println(ht.maxProfit3(a));
        ArrayList<Integer> r1 = new ArrayList<Integer>();
        r1.add(-1);
        ArrayList<Integer> r2 = new ArrayList<Integer>();
        r2.add(2);
        r2.add(3);
        ArrayList<Integer> r3 = new ArrayList<Integer>();
        r3.add(1);
        r3.add(-1);
        r3.add(-3);
        ArrayList<ArrayList<Integer>> r = new ArrayList<ArrayList<Integer>>();
        r.add(r1);
        r.add(r2);
        r.add(r3);
        //ht.getRow(30);
        ht.minimumTotal(r);
    }
}
