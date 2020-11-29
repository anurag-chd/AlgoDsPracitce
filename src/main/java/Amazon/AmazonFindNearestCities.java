package Amazon;

import java.util.*;

public class AmazonFindNearestCities {

	public static void main(String[] args){
		int numOfCities = 8;

		String[] cities = {"monterey","m3n","mont","los gatos","los altos","san marino","san mateo","san francisco"};

		int[] xCoordinates = {3250,7000,7000,2000,4050,1000,2000,4050};

		int[] yCoordinates = {2500,19250,3000,10000,2500,2500,10000,3000};

		int numOfQueries = 7;

		String[] queries = {"monterey","mont","los gatos","los altos","san marino","san mateo","san francisco"};

		String[] res = findNearestCities(numOfCities, cities, xCoordinates, yCoordinates, numOfQueries, queries);
		System.out.println(Arrays.toString(res));
	}

	public static String[] findNearestCities(int numOfCities,
											 String[] cities,
											 int[] xCoordinates,
											 int[] yCoordinates,
											 int numOfQueries,
											 String[] queries) {
		String[] res = new String[numOfQueries];
		Map<Integer, TreeSet<CityCord>> xMap = new HashMap<>();
		Map<Integer, TreeSet<CityCord>> yMap = new HashMap<>();
		Map<String, int[]> map = new HashMap<>();

		for(int i = 0; i< numOfCities; i++){
			int x = xCoordinates[i];
			int y = yCoordinates[i];
			String city = cities[i];
			map.put(city, new int[]{x,y});
			TreeSet<CityCord> xL = xMap.getOrDefault(x, new TreeSet<>((a,b)-> Integer.compare(a.dist,b.dist)));
			xL.add(new CityCord(city, y));
			xMap.put(x, xL);

			TreeSet<CityCord> yL = yMap.getOrDefault(y, new TreeSet<>((a,b) -> Integer.compare(a.dist, b.dist)));
			yL.add(new CityCord(city, x));
			yMap.put(y, yL);
		}

		for(int i = 0; i< numOfQueries;i++){
			String q = queries[i];
			int x = map.get(q)[0];
			int y = map.get(q)[1];

			if(xMap.get(x).size() == 1 && yMap.get(y).size() == 1){
				res[i] = null;// "NONE";
			}
			else{
				int minDist = Integer.MAX_VALUE;
				String minCity = "";
				CityCord qx = new CityCord(q,y);
				CityCord qy = new CityCord(q,x);

				CityCord xlower = xMap.get(x).lower(qx);
				if(xlower != null){
					if(minDist > Math.abs(qx.dist - xlower.dist)){
						minDist = Math.abs(qx.dist - xlower.dist);
						minCity = xlower.name;
					}
					else if(minDist ==  Math.abs(qx.dist - xlower.dist)){
						minCity = minCity.compareTo(xlower.name) < 0 ? minCity : xlower.name;
					}
				}

				CityCord xhigher = xMap.get(x).higher(qx);
				if(xhigher != null){
					if(minDist > Math.abs(qx.dist - xhigher.dist)){
						minDist = Math.abs(qx.dist - xhigher.dist);
						minCity = xhigher.name;
					}
					else if(minDist ==  Math.abs(qx.dist - xlower.dist)){
						minCity = minCity.compareTo(xhigher.name) < 0 ? minCity : xhigher.name;
					}
				}

				CityCord ylower = yMap.get(y).lower(qy);
				if(ylower != null){
					if(minDist > Math.abs(qy.dist - ylower.dist)){
						minDist = Math.abs(qy.dist - ylower.dist);
						minCity = ylower.name;
					}
					else if(minDist ==  Math.abs(qy.dist - ylower.dist)){
						minCity = minCity.compareTo(ylower.name) < 0 ? minCity : ylower.name;
					}
				}

				CityCord yhigher = yMap.get(y).higher(qy);
				if(yhigher != null){
					if(minDist > Math.abs(qy.dist - yhigher.dist)){
						minDist = Math.abs(qy.dist - yhigher.dist);
						minCity = yhigher.name;
					}
					else if(minDist ==  Math.abs(qy.dist - yhigher.dist)){
						minCity = minCity.compareTo(yhigher.name) < 0 ? minCity : yhigher.name;
					}
				}

				res[i] = minCity;

			}

			//return res;


		}





		return res;
	}


}
class CityCord {
	String name;
	int dist;
	public CityCord(String name , int dist){
		this.name = name;
		this.dist = dist;
	}
}
