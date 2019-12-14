package com.cos.bsymWeb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.bsymWeb.MyUtil.UtillCommon;
import com.cos.bsymWeb.model.BoardReplyCon;
import com.cos.bsymWeb.model.BsymBoard;
import com.cos.bsymWeb.model.Replys;
import com.cos.bsymWeb.model.Rocker;
import com.cos.bsymWeb.model.Users;
import com.cos.bsymWeb.repository.BoardReplyConRepository;
import com.cos.bsymWeb.repository.BsymBoardRepository;
import com.cos.bsymWeb.repository.ReplysRepository;
import com.cos.bsymWeb.repository.RockerRepository;
import com.cos.bsymWeb.repository.UsersRepository;
import com.google.gson.Gson;
import com.cos.bsymWeb.model.imgtest;


@RestController
public class bsymWebController {
	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private BsymBoardRepository bsymBoardRepository;
	@Autowired
	private ReplysRepository replyRepository;
	@Autowired
	private RockerRepository rockerRepository;
	@Autowired
	private BoardReplyConRepository boardReplyConRepository;
	@Autowired
	private Gson gson;
//	@Autowired
//	private AndroidPushNotificationsService androidPushNotificationsService;

	@PostMapping("/test/board/upload")
	public BsymBoard bsymUpdate() {
		Users user = UtillCommon.getUser();
		userRepository.save(user);
//		BsymBoard bsymBoard = BsymBoard.builder()
//				.broadcastName("수미네반찬")
//				.imgAddress("http:akakakakaaaaaaaaaaa")
//				.title("마늘장아찌")
//				.urlAddress("http:aaaaaaaaaaaassssssssssss")
//				.createDate(LocalDate.now())
//				//.uploadDate(LocalDate.now())
//				.readCount(0)
//				.likeCount(1)
//				.shareCount(2)
//				.boardDivision(BoardDivision.BSFOOD)
//				.build();
//		bsymBoardRepository.save(bsymBoard);
//		Rocker rocker = Rocker.builder()
//				.user(user)
//				.build();
//		rockerRepository.save(rocker);
//		BoardReplyCon brc = BoardReplyCon.builder()
//				.bsymBoard(bsymBoard)
//				.build();
//		boardReplyConRepository.save(brc);
//		Replys reply = Replys.builder()
//				.context("이거 진짜 맛있다.")
//				.createDate(LocalDate.now())
//				.brc(brc)
//				.rocker(rocker)
//				.build();
//		replyRepository.save(reply);
		return null;
	}
	
	@GetMapping("test/board/list")
	public Optional<Rocker> rocker() {
		return rockerRepository.findById(1);
	}
	@GetMapping("test/board/bfind")
	public Optional<BoardReplyCon> bsymFind() {
		return boardReplyConRepository.findById(2);//bsymBoardRepository.findById(3);
	}
	@GetMapping("test/board/rfind")
	public List<Replys> replyFind() {
		return replyRepository.findAll();
	}
	@GetMapping("test/board/bslist")
	public List<BsymBoard> bsNameFind(){//String broadcastName) {
		List<BsymBoard> list = bsymBoardRepository.findAll();//.findByBroadcastName("수미네반찬");
		return list;
	}
	@GetMapping("test/board/bslistpage")
	public Page<BsymBoard> bspage(@PageableDefault(size = 5) Pageable pageable){//String broadcastName) {
		Page<BsymBoard> list = bsymBoardRepository.findAll(pageable);//.findByBroadcastName("수미네반찬");
		return list;
	}
	@GetMapping("test/page")
	public String testPage() {
		return "index";
	}
	@GetMapping("/test/img")
	public List<imgtest> imgtest() {
		List<imgtest> img = new ArrayList<imgtest>();
		imgtest test = new imgtest();
		test.setImglink("http://192.168.0.4:8000/image/sumnail/1.jpg");
		img.add(test);
		return img;
	}
//	@PostMapping("test/board/blogSave")
//	public String blogSave() throws UnsupportedEncodingException {
//		String[][] castName= {{"수미네반찬","알토란","만물상","스페인하숙","모두의주방","집밥백선생","요리비결","냉부해","생생정보","윤식당","삼시세끼","커피프렌즈"},{"밥블레스유","골목식당","전참시","수유미식회","생방송투데이","맛있는녀석들","식신로드","생활의달인","외식하는날","3대천왕","미식클럽"}};
//		for (int i = 0; i<castName[0].length; i++) {
//			String bloglist = UtillCommon.BlogSearch(castName[0][i]);
//			Blog blog = gson.fromJson(bloglist, Blog.class);
//			List<BsymBoard> list = UtillCommon.BlogCrawl(blog, castName[0][i], BoardDivision.BSFOOD.getName(),0);
//			bsymBoardRepository.saveAll(list);
//		}
//		return "ok";
//	}
	@GetMapping("test/blog")
	public String blog() {
		//UtillCommon.BlogSearch("수미네반찬");
		//String checkDate = LocalDate.now().toString().replace("-", "");
		return UtillCommon.BlogSearch("수미네반찬");//checkDate;
	}
//	@GetMapping("test/board/blogRS")
//	public List<BsymBoard> blogRSearch() throws UnsupportedEncodingException {
//		String[] resCastName= {"밥블레스유","골목식당","전참시","수유미식회","생방송투데이","맛있는녀석들","식신로드","생활의달인","외식하는날","3대천왕","미식클럽"};
//		String bloglist = UtillCommon.BlogSearch(resCastName[0]);
//		//System.out.println(bloglist);
//		Blog blog = gson.fromJson(bloglist, Blog.class);
//		//System.out.println(blog.getLastBuildDate());
//		List<BsymBoard> list = UtillCommon.BlogCrawl(blog, resCastName[0], BoardDivision.BSRESTAURANT.getName(),0);
//		bsymBoardRepository.saveAll(list);
//		return list;
//	}
	@PostMapping("test/board/blcrawl")
	public @ResponseBody void blogCrawl() {
		bsymBoardRepository.save(UtillCommon.BlogCrawling("수미네반찬", 1));
	}
	@PostMapping("test/board/bccrawl")
	public void broadcastCrawl() {
				//for(int i = 0; i<broadCastName.length; i++) {
		//UtillCommon.broadCastCrawling(broadCastName[17]);
		//	broadCastRepository.save());
		//}
	}
//	@GetMapping("test/board/bsrlist")
//	public List<Replys> boardreplyFind() {
//		BsymBoard bb;
//		
//		return replyRepository.findByBsymBoard(BsymBoard bb);
//	}
//	@RequestMapping(value = "/sendmessage", method = RequestMethod.POST, produces = {"application/json"})
//	public @ResponseBody ResponseEntity<String> send(@RequestBody Map<String, Object> paramInfo) throws JSONException{
//		Map<String, Object> retVal = new HashMap<String, Object>();
//		
//		//System.out.println("token: " + paramInfo.get("token"));
//		//System.out.println("message: " + paramInfo.get("message"));
//		
//		//FCM 메시지 전송//
//		JSONObject body = new JSONObject();
//		
//		//DB에 저장된 여러개의 토큰(수신자)을 가져와서 설정할 수 있다.//
//		List<String> tokenlist = new ArrayList<String>();
//		//DB과정 생략(직접 대입)//
//		tokenlist.add("token value 1");
//		tokenlist.add("token value 2");
//		tokenlist.add("token value 3");
//		
//		JSONArray array = new JSONArray();
//		
//		for(int i=0; i<tokenlist.size(); i++) {
//			array.put(tokenlist.get(i));
//		}
//		
//		body.put("registration_ids", array); //여러개의 메시지일 경우 registration_ids, 단일 메세지는 to사용//
// 
//		JSONObject notification = new JSONObject();
//		notification.put("title", "FCM Test App");
//		notification.put("body", paramInfo.get("message"));
//		
//		body.put("notification", notification);
//		
//		System.out.println(body.toString());
//		
//		HttpEntity<String> request = new HttpEntity<>(body.toString());
//		 
//		CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
//		CompletableFuture.allOf(pushNotification).join();
// 
//		try {
//			String firebaseResponse = pushNotification.get();
//			
//			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		}
// 
//		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
//	}
	
}
