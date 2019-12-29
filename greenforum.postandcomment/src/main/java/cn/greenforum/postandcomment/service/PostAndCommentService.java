package cn.greenforum.postandcomment.service;

import java.util.List;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.pojo.Comment;
import com.jt.common.pojo.Flow;
import com.jt.common.pojo.Post;
import com.jt.common.utils.MapperUtil;
import com.jt.common.vo.EasyUIResult;

import cn.greenforum.postandcomment.mapper.PostAndCommentMapper;

@Service
public class PostAndCommentService {
	@Autowired
	private PostAndCommentMapper postAndCommentMapper;
	public void savePost(Post post) {
		postAndCommentMapper.insertPost(post);
	}

	public EasyUIResult queryPostsByPage(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		//准备EasyUIResult对象
		EasyUIResult result=new EasyUIResult();
		//封装总数 select count(product_id) from t_product
		int total=postAndCommentMapper.queryPostsTotalCount();
		result.setTotal(total);//total是js计算显示页数
		//total%rows==0? allPages=total/rows:(total/rows)+1
		//查询的页数page rows和sql语句执行的条件封装有关
		//传递2个值,一个start起始下标 rows查询条数 start=(page-1)*rows
		int start=(page-1)*rows;
		List<Post> pList=postAndCommentMapper.queryPosts(start,rows);
		for (Post post : pList) {
			post.setPostPassword("1"); 
		}
		result.setRows(pList);
		return result;
	}

	public void saveComment(Comment comment) {
		// TODO Auto-generated method stub
		postAndCommentMapper.insertComment(comment); 
	}

	public EasyUIResult queryPostByPostId(String postId,String postPsw) {
		//准备EasyUIResult对象
		EasyUIResult result=new EasyUIResult();
		result.setTotal(1);//total是js计算显示页数
		List<Post> pList = postAndCommentMapper.queryPostByPostId(postId);

		for (Post post : pList) {
			System.out.println(post.getPostPassword());
//			if (!post.getPostPassword().isEmpty() && postPsw!= null ) {
//				if (postPsw.equals(post.getPostPassword())) {
//					result.setRows(pList); 
//				}
//			} 
			if (post.getPostPassword().isEmpty() || (postPsw != null && post.getPostPassword().equals(postPsw))) {
				post.setPostPassword("1");
				result.setRows(pList); 
			}
		}
		return result;
	}

	public EasyUIResult queryCommentsByPostId(String postId) {
		// TODO Auto-generated method stub
		//准备EasyUIResult对象
		EasyUIResult result=new EasyUIResult();
		//封装总数 
//		int total=postAndCommentMapper.queryCommentsTotalCount();

		List<Comment> pList=postAndCommentMapper.queryCommentsByPostId(postId);
		result.setTotal(pList.size());//total是js计算显示页数
		result.setRows(pList);
		return result;
	}

	@Autowired
	private TransportClient client;
	public void createIndex(Post post) throws Exception {
		client.admin().indices().prepareCreate("greenforum").get();
		String json = MapperUtil.MP.writeValueAsString(post);
		IndexRequestBuilder request = client.prepareIndex("greenforum", "post", post.getPostId());
		//添加request参数 json
		request.setSource(json).get(); 
//		}
	}

	public void saveFlow(Flow flow) {
		// TODO Auto-generated method stub
		postAndCommentMapper.insertFlow(flow); 
	}

	public EasyUIResult queryPostsByName(Integer page, Integer rows, String pageName, String userId) {
		// TODO Auto-generated method stub
		//准备EasyUIResult对象
		EasyUIResult result=new EasyUIResult();
		List<Post> pList = null;
		if ("posts".equals(pageName)) {
			pList=postAndCommentMapper.queryPostsByUserId(userId); 
		}else if("flows".equals(pageName)){ 
			pList=postAndCommentMapper.queryPostsFlowByUserId(userId);
		}else if("comments".equals(pageName)){
			pList=postAndCommentMapper.queryPostsCommentByUserId(userId);
		} 
		//封装总数 
		result.setTotal(pList.size());//total是js计算显示页数 
		result.setRows(pList);
		return result; 
	}
}
