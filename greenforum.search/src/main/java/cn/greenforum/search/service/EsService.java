package cn.greenforum.search.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jt.common.pojo.Post;
import com.jt.common.utils.MapperUtil;

@Service
public class EsService {
	@Autowired
	private TransportClient client;
	public List<Post> search(String text, Integer page, Integer rows) 
			throws JsonParseException, JsonMappingException, IOException {
		//使用transportclient封装query对象查询
		MultiMatchQueryBuilder query = 
			QueryBuilders.multiMatchQuery(text, "postTitle","postContent");
		//prepare获取request对象
		SearchRequestBuilder request = client.prepareSearch("greenforum");
		//query,start,rows
		SearchResponse response = request.setQuery(query).setFrom((page-1)*rows)
		.setSize(rows).get();
		//response中有需要的查询结果
		List<Post> pList=new ArrayList<Post>();
		//获取hits 循环解析到pList
		SearchHit[] hits = response.getHits().getHits();
		for (SearchHit hit : hits) {
			String pJson=hit.getSourceAsString();
			Post p=MapperUtil.MP.readValue(pJson, Post.class);
			pList.add(p);
		}
		return pList;
	}

}
