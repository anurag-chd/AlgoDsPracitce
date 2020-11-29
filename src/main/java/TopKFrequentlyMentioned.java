import java.util.*;

public class TopKFrequentlyMentioned {
	public static void main(String[] args){
		int k = 2;
		String[] keywords = {"anacell", "betacellular", "cetracular", "deltacellular", "eurocell"};
		String[] reviews = {
				"I love anacell Best services; Best services provided by anacell",
				"betacellular has great services",
				"deltacellular provides much better services than betacellular",
				"cetracular is worse than ",
				"Betacellular is better than deltacellular."
		};

		String[] res = getTopKFrequent(keywords, reviews, k);
		System.out.println(Arrays.toString(res));
	}

	public static String[] getTopKFrequent(String[] keywords, String[] reviews, int k){
		Set<String> words = new HashSet<>();
		for(String str : keywords){
			words.add(str);
		}
		Map<String, Integer> map = new HashMap<>();
		for(String s : reviews){
			String[] arr = s.split(" ");
			for(String word : arr){
				word = word.toLowerCase();
				if(words.contains(word)){
					map.put(word, map.getOrDefault(word,0) + 1);
				}
			}
		}

		PriorityQueue<WordCount> q = new PriorityQueue<>();
		for(String w : map.keySet()){
			WordCount  wc = new WordCount(w, map.get(w));
			if(q.size() < k){
				q.add(wc);
			}
			else if(q.peek().count < wc.count){
				q.poll();
				q.add(wc);
			}
			else if(q.peek().count == wc.count && wc.word.compareTo(q.peek().word ) < 0){
				q.poll();
				q.add(wc);
			}
		}
		List<String> l = new ArrayList<>(10);


		String[] res = new String[q.size()];
		int index = res.length-1;
		while(!q.isEmpty()){
			res[index--] = q.poll().word;
		}
		return res;


	}


}

class WordCount implements Comparable {
	String word;
	int count;

	public WordCount(String word, int count){
		this.word = word;
		this.count = count;
	}


	@Override
	public int compareTo(Object o) {
		WordCount obj = (WordCount)o;
		if(this.count != obj.count)
			return Integer.compare(this.count, obj.count);
		return obj.word.compareTo(this.word);
	}
}

