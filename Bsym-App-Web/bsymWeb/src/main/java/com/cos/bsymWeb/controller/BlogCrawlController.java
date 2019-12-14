package com.cos.bsymWeb.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.bsymWeb.MyUtil.UtillCommon;
import com.cos.bsymWeb.Service.BlogService;
import com.cos.bsymWeb.model.Blog;
import com.cos.bsymWeb.model.BoardDivision;
import com.cos.bsymWeb.model.BoardReplyCon;
import com.cos.bsymWeb.model.BsymBoard;
import com.cos.bsymWeb.repository.BoardReplyConRepository;
import com.cos.bsymWeb.repository.BsymBoardRepository;
import com.google.gson.Gson;

@Controller
@RequestMapping("/admin")
public class BlogCrawlController {
	@Autowired
	private BsymBoardRepository bsymBoardRepository;
	@Autowired
	private BoardReplyConRepository boardReplyConRepository;
	@Autowired
	private Gson gson;
	@Autowired
	private BlogService blogService;
	
//	public String blogSave() {
//		blogService.
//		return "ok";
//	}
	
	@PostMapping("/blog/Save/{check}")
	public @ResponseBody String blogAllSave(@PathVariable String check){
		String[][] castName= {{"수미네반찬","알토란","만물상","스페인하숙","모두의주방","집밥백선생","요리비결","냉부해","생생정보","윤식당","삼시세끼","커피프렌즈"},{"밥블레스유","골목식당","전참시","수요미식회","생방송투데이","맛있는녀석들","식신로드","생활의달인","외식하는날","3대천왕","미식클럽"}};
		String division;
		int checkDate = 0;
		if(check.equals("All")) {
			checkDate = 1;
		}else if(check.equals("New")) {
			checkDate=Integer.parseInt(LocalDate.now().toString().replace("-", ""));
		}
		for(int j = 0; j<castName.length; j++) {
			if(j==0) {
				division = BoardDivision.BSFOOD.getName();
			}else {
				division = BoardDivision.BSRESTAURANT.getName();
			}
			for (int i = 0; i<castName[j].length; i++) {
				String bloglist = UtillCommon.BlogSearch(castName[j][i]);
				Blog blog = gson.fromJson(bloglist, Blog.class);
				for(int order =0; order<blog.getDisplay(); order++) {
					int postDate = Integer.parseInt(blog.getItems().get(order).getPostdate());
					if(((checkDate>=postDate)&&(postDate>=(checkDate-7)))||checkDate==1) {
					try {
						int lastCount = bsymBoardRepository.findAll().size();
						BsymBoard bsymBoard = UtillCommon.BlogCrawl(blog, castName[j][i], division,checkDate,lastCount,order);
						if(bsymBoard.getImglink()!=null) {
							bsymBoardRepository.save(bsymBoard);
							BoardReplyCon brc = BoardReplyCon.builder()
									.bsymBoard(bsymBoard)
									.build();
							boardReplyConRepository.save(brc);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				}
			}
		}
		return "ok";
	}
}
