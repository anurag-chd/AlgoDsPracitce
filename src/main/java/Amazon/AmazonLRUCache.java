package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class AmazonLRUCache {
	public static void main(String[] args){
		int num = 6;

		Integer[] p = {1,2,1,3,1,2};

		int maxCacheSize = 2;
		List<Integer> pages = Arrays.asList(p);
		//pages.addAll(p);
		System.out.println(lruCacheMisses(num, pages, maxCacheSize));


	}

	public static int lruCacheMisses(int num, List<Integer> pages, int maxCacheSize) {
		LinkedHashSet<Integer> cache = new LinkedHashSet<>();
		int cacheMiss = 0;
		for(int i : pages){
			if(!cache.contains(i)){
				cacheMiss++;
				if(cache.size() == maxCacheSize){
					int p = cache.iterator().next();
					cache.remove(p);
				}

			}
			else{
				cache.remove(i);
			}
			cache.add(i);

		}
		return cacheMiss;
	}
}
