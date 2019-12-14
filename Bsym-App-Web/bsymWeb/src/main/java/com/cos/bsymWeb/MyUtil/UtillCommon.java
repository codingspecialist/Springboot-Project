package com.cos.bsymWeb.MyUtil;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;

import com.cos.bsymWeb.model.BoardDivision;
import com.cos.bsymWeb.model.BoardReplyCon;
import com.cos.bsymWeb.model.BsymBoard;
import com.cos.bsymWeb.model.Replys;
import com.cos.bsymWeb.model.Users;
import com.cos.bsymWeb.model.Blog;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class UtillCommon {
//	@Autowired
//	private static BroadCastRepository broadCastRepository;

	public static Users getUser() {
		Users user = Users.builder()
				.username("admin@com")
				.name("운영자")
				.password("123")
				.address("회사")
				.phone("000-1111-2222")
				.gender("남")
				.createDate(LocalDate.now())
				.updataDate(LocalDate.now())
				.build();
		return user;
	}
	public static String BlogSearch(String blogName) {
		
		// 네이버 검색 API예제는 블로그를 비롯 전문자료까지 호출방법이 동일하므로 blog검색만 대표로 예제를 올렸습니다.
		// 네이버 검색 API 예제 - blog 검색
		String clientId = "f_74Q9mRqMegGoOl3yMn";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "JHVLN7Zny0";// 애플리케이션 클라이언트 시크릿값";
		try {
			String text = URLEncoder.encode(blogName, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/blog.json?query=" + text+"&display=100"; // json 결과
			// String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
			// // xml 결과
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();		
			return response.toString();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public static BsymBoard BlogCrawl(Blog blog, String broadcastName, String division, int checkDate,int lastCount, int order) throws Exception{
		BsymBoard bsymBoard = blog.getItems().get(order);
		String endUrl="";
		String imglink="";
		String url = bsymBoard.getLink();
			if(url.split("/")[2].equals("blog.naver.com")) {
				Document doc = Jsoup.connect(url).get();
				endUrl = "https://blog.naver.com"+doc.select("#mainFrame").get(0).attr("src");
				Document endDoc = Jsoup.connect(endUrl).get();
				String postCheck = endDoc.select("#post_1").get(0).attr("data-post-editor-version");
				//System.out.println(postCheck);
				if(postCheck.equals("2")) {
					imglink = endDoc.select("#postViewArea p img").get(0).attr("src");
				}else if(postCheck.equals("4")) {
					imglink = endDoc.select(".se-image-resource").get(0).attr("data-lazy-src");
				}
				if(imglink.equals("")) {
					return null;
				}
				
				bsymBoard = BsymBoard.builder()
						.title(bsymBoard.getTitle())
						.link(bsymBoard.getLink())
						.description(bsymBoard.getDescription())
						.postdate(bsymBoard.getPostdate())
						.broadcastName(broadcastName)
						.imglink(imglink)
						.imgPath(imgFile(imglink, lastCount))
						.createDate(LocalDate.now())
						.division(division)
						.build();
			}
		return bsymBoard;
	}
	public static String imgFile(String imglink, int lastCount) {
		try {
			URL imgurl = new URL(imglink);
			BufferedImage image = ImageIO.read(imgurl);
			String filePath = getResourcePath()+"/"+(lastCount+1)+".jpg";
			File file = new File(filePath);
			ImageIO.write(image, "jpg", file);
			
			return filePath;
			//Files.write(filePath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getResourcePath() {
		return "E://spring_instagrem//bsymWeb//src//main//resources//static//image//sumnail";
	}
	
	public static void script(String msg, HttpServletResponse response) {
		try {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('"+msg+"')");
			script.println("history.back()");
			script.println("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
  //---------------------------------아래는 테스트로만 쓴 함수 -------------------
	public static BsymBoard BlogCrawling(String broadCast, int start) {
		String url = "https://search.naver.com/search.naver?where=post&sm=tab_jum&query=" + broadCast + "&start="
				+ start;
		try {
			Document doc = Jsoup.connect(url).get();
			BsymBoard bsymBoard = BsymBoard.builder().broadcastName(broadCast)
					.title(doc.select(".sh_blog_top dl dt").get(0).text())
					.createDate(LocalDate.now())
					//.postdate(doc.select(".txt_inline").get(0).text())
					.boardDivision(BoardDivision.BSFOOD).build();

			return bsymBoard;
		} catch (Exception e) {

		}
		return null;
	}


}
