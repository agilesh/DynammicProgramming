import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Knapsack {
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of loads");
		int loads = sc.nextInt();
		
		int[] weights = new int[loads];
		for(int i=0;i<loads;i++)
		{
			weights[i] = sc.nextInt();
		}
		
		int[] profits = new int[loads];
		for(int i=0;i<loads;i++)
		{
			profits[i] = sc.nextInt();
		}
		
	System.out.println("Enter the capacity of the container");
	int capacity = sc.nextInt();
	
	double maxvalue = getmaxValue(weights,profits,capacity);
	System.out.println("The maximum profit obtained is "+maxvalue);
		
	}
	
	public static double getmaxValue(int[] profits, int[] weights , int capacity)
	{
		
		Items[] items = new Items[weights.length];
		
		for(int i=0;i<weights.length;i++)
		{
			items[i] = new Items(profits[i] ,weights[i] , i);
		}
		
		Arrays.sort(items , new Comparator<Items>() {
			
			
			public int compare(Items i1 , Items i2)
			{
				return i2.profitPerUnit.compareTo(i1.profitPerUnit);
			}
		});
		
		double totalprofit = 0d;
		for(Items i : items)
		{
			int currentWeight = (int)i.weight;
			int currentProfit = (int)i.profit;
			
			if(capacity - currentWeight >= 0 )
			{
				capacity = capacity - currentWeight;
				totalprofit += currentProfit;
			}
			else
			{
				double fraction = (double)capacity/(double)currentWeight;
				totalprofit += (fraction*currentProfit);
				capacity = (int)(capacity-(currentWeight*fraction));
				break;
			}
		}
		return totalprofit;
	}

}



class Items
{
	Double profitPerUnit;
	double profit;
	double weight;
	double index;
	
     public Items(int profit , int weight , int index)
     {
    	 this.profit = profit;
    	 this.weight = weight;
    	 this.index = index;
    	 profitPerUnit = new Double(weight/profit);
     }
	
}