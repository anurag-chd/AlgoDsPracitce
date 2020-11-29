package Amazon;

import java.util.PriorityQueue;

public class FiveStarSellers {
	public static void main(String[] args){
		int num = 3;
		int[][] productRatings = {{4,4},{1,2},{3,6}};

		int threshold = 77;

		System.out.println(getMinSteps(productRatings, threshold));

	}

	public static int getMinSteps(int[][] productRatings, int threshold ){
		PriorityQueue<ProdRatings> q = new PriorityQueue<>((a, b) -> Float.compare((float)(b.numerator+1)/(float)(b.denom +1) - (float)(b.numerator)/(float)(b.denom),
				(float) (a.numerator + 1)/ (float) (a.denom +1) - (float) (a.numerator)/ (float) (a.denom)));
		int count = 0;
		float per = 0.0f;
		threshold = threshold * productRatings.length;

		for(int[] rating : productRatings){
			per = per + (float) rating[0]/(float)rating[1];
			if(per != 1.0f)
				q.add(new ProdRatings(rating[0], rating[1]));
		}
		per *= 100;
		while(per < threshold){
			ProdRatings pr = q.poll();
			float oldRatio = pr.ratio;
			pr.updateRatio();
			float newRatio = pr.ratio;
			per += ((newRatio - oldRatio)*100) ;
			count++;
			q.add(pr);

		}
		return count;
	}
}

class ProdRatings {
	int numerator, denom;
	float ratio;

	public ProdRatings(int numerator, int denom){
		this.numerator = numerator;
		this.denom = denom;
		this.ratio = (float)numerator/(float)denom;
	}

	public void  updateRatio(){
		this.numerator++;
		this.denom++;
		this.ratio = (float)numerator/(float)denom;

	}
}
