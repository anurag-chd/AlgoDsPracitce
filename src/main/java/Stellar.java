import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Stellar {
	private final String baseUrl = "https://example.com/snippets/";
	private Map<String, Snippet> cache = new ConcurrentHashMap<>();
	Thread cleanup;
	public Stellar (){
		cleanup = new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					while(true){
						cleanCache();
						Thread.sleep(1000);
					}


				}
				catch (Exception e){

				}
			}});
		cleanup.setDaemon(true);
		cleanup.start();
	}
	private void cleanCache(){
		System.out.println("Cleaning");
		Iterator<Map.Entry<String, Snippet> >
				iterator = cache.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, Snippet> entry = iterator.next();
			if (entry.getValue().expiresAt.getTime() < System.currentTimeMillis() ) {
				iterator.remove();
			}
		}
	}

	private int getCacheSize(){
		return cache.size();
	}

	public static void main(String args[]){

		Stellar st = new Stellar();
		System.out.println(st.post("recipe", 3, "1 apple"));
		//System.out.println(st.get("https://example.com/snippets/recipe"));
		//System.out.println(st.postLike("https://example.com/snippets/recipe/like"));
		try {

			 Thread.sleep(6000);
		}
		catch (Exception e){}
		//System.out.println(st.get("https://example.com/snippets/recipe"));
		System.out.println(st.getCacheSize());



	}

	public Response postLike(String url){
		if(url == null || url.length() == 0 || !url.endsWith("/like")){
			new ErrorResponse();
		}
		url = url.substring(0, url.length()-"/like".length());

		if(cache.containsKey(url)){
			Snippet s = cache.get(url);
			s.retrieved();
			s.incrementLikes();
			Response success = new SuccessResponse(s,200, "OK");
			return success;

		}
		else
			return new ErrorResponse();

	}




	public Response post(String name, int expriesIn, String snippet){
		String url = baseUrl + name;
		if(!cache.containsKey(url)){
			Date expiresAt = new Date();
			expiresAt.setTime(System.currentTimeMillis() + expriesIn * 30);
			Snippet s = new Snippet(url,name, expiresAt, snippet);
			cache.put(s.url, s);
		}
		Response success = new SuccessResponse(cache.get(url),201, "Created");
		return success;
	}

	public Response get(String url){
		if(cache.containsKey(url)){
			Snippet s = cache.get(url);
			if(System.currentTimeMillis() > s.expiresAt.getTime()){
				return new ErrorResponse();
			}
			s.retrieved();
			Response success = new SuccessResponse(s,200, "OK");
			return success;

		}
		else
			return new ErrorResponse();
	}

	abstract class Response {
		int status;
	}

	class SuccessResponse extends Response{
		Snippet sp;
		String msg;

		public SuccessResponse(Snippet sp, int status, String msg){
			this.sp = sp;
			this.status = 200;
			this.msg = status + " " +msg;
		}

		@Override
		public String toString() {
			return " # response " + msg + " \n " + sp;
		}


	}

	class ErrorResponse extends Response {
		String msg;

		public ErrorResponse(){
			this.status = 404;
			this.msg = status + " Not Found";
		}

		@Override
		public String toString() {
			return " # response " + msg;
		}
	}

	class Snippet {
		private String url;
		private String name;
		private Date expiresAt;
		private String body;
		private AtomicInteger likes = new AtomicInteger(0);

		public Snippet(String url, String name, Date  expires, String body){
			this.url  = url;
			this.name = name;
			this.expiresAt = expires;
			this.body = body;
		}

		public void retrieved(){
			this.expiresAt.setTime(this.expiresAt.getTime() + 30000);
		}


		public void incrementLikes(){
			this.likes.incrementAndGet();
		}



		@Override
		public String toString() {
			String res =  "{ \n \"url\": " + url + ", \n" +
					"name: " + name +", \n" +
					"expires_at: " + expiresAt + ", \n" +
					"snippet: " + body + " \n " +
					"likes: " + likes.get() + " \n }";
			return res;
		}
	}
}
