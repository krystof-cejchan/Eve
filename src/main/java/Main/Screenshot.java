package Main;

import java.io.IOException;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Screenshot {
	
	public void getScreenshot(MessageReceivedEvent event, String url) throws IOException, InterruptedException{
	/*	HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://web-capture.p.rapidapi.com/image?url=http%3A%2F%2F"+url+"&width=1920&height=1080"))
				.header("x-rapidapi-key", "35cee2c907msh1f86fc154e4074fp156abcjsnadcb7d5a5f6a")
				.header("x-rapidapi-host", "web-capture.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		//System.out.println(response.body());
		
		System.out.println(response.body());*/
		
		//BufferedImage i = new BufferedImage(response.body());
		 
		
		
		
		/*OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
			.url("https://web-capture.p.rapidapi.com/image?url=http%3A%2F%2F"+url+"&width=1920&height=1080")
			.get()
			.addHeader("x-rapidapi-host", "web-capture.p.rapidapi.com")
			.addHeader("x-rapidapi-key", "ea77a952f3mshc61b5188857cf19p1b4ec0jsn6c6ce8f24fe4")
			.build();

		Response response = client.newCall(request).execute();
		System.out.print(response);*/
	/*	try {
			System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
			WebDriver driver =new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://"+url);
			
			TakesScreenshot ts =(TakesScreenshot)driver;
			File fs= ts.getScreenshotAs(OutputType.FILE);
			event.getMessage().reply(fs, null).queue();
			
		} catch (Exception e) {
			event.getMessage().reply(e.toString()).queue();
		}*/
		
		
		
		
		
		
		
		
	}

}
