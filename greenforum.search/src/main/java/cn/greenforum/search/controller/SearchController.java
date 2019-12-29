package cn.greenforum.search.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jt.common.pojo.Post;

import cn.greenforum.search.service.EsService;

@RestController
@RequestMapping("search/manage")
public class SearchController {
	@Autowired
	private EsService searcher;
	//搜索功能
	@RequestMapping("query")
	public List<Post> search( @RequestParam("query")String text,Integer page,Integer rows) 
			throws JsonParseException, JsonMappingException, IOException{
		return searcher.search(text,page,rows);
	} 
}
