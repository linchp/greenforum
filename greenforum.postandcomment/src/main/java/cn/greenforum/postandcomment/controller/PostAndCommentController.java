package cn.greenforum.postandcomment.controller;

import org.elasticsearch.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.common.pojo.Comment;
import com.jt.common.pojo.Flow;
import com.jt.common.pojo.Post;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;

import cn.greenforum.postandcomment.service.PostAndCommentService;

@RestController
@RequestMapping("postandcomment/manage")
public class PostAndCommentController {
	@Autowired
	private PostAndCommentService postAndCommentService;
	//发帖表单提交
	@RequestMapping("savePost")
	public SysResult savePost(Post post){
		try{
			postAndCommentService.savePost(post);//业务层封装数据完整
			//向ES添加索引
			try{
				postAndCommentService.createIndex(post);
				return SysResult.ok();
			}catch (ResourceAlreadyExistsException e) {
				// TODO: handle exception
				e.printStackTrace();
				return SysResult.ok();
			}catch(Exception e){
				e.printStackTrace();
				return SysResult.build(201, "新增索引失败", null);
			}
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, "注册失败", null);
		}
	}
	//评论表单提交
	@RequestMapping("saveComment")
	public SysResult saveComment(Comment comment){
		try{
			postAndCommentService.saveComment(comment);//业务层封装数据完整
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, "注册失败", null);
		}
	}
	//跟随表单提交
	@RequestMapping("saveFlow")
	public SysResult saveFlow(Flow flow){
		try{
			postAndCommentService.saveFlow(flow);//业务层封装数据完整
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, "跟随失败", null);
		}
	}
	@RequestMapping("pageManage")
	public EasyUIResult queryPostsByPage(Integer page,Integer rows){
		//page 表示页数,rows表示一页条数
		return postAndCommentService.queryPostsByPage(page,rows);
	}
	@RequestMapping("pagePost")
	public EasyUIResult queryPostByPostId(String postId,String postPsw){
		//page 表示页数,rows表示一页条数
		return postAndCommentService.queryPostByPostId(postId, postPsw);
	}
	@RequestMapping("pageComment")
	public EasyUIResult queryCommentsByPostId(String postId){
		//page 表示页数,rows表示一页条数
		return postAndCommentService.queryCommentsByPostId(postId);
	}
	@RequestMapping("LoadUserPostsPage")
	public EasyUIResult queryPostsByName(Integer page,Integer rows,String pageName,String userId){
		//page 表示页数,rows表示一页条数
		return postAndCommentService.queryPostsByName(page,rows,pageName,userId);
	}
//	@RequestMapping("deletePost")
//	public EasyUIResult queryCommentsByPostId(String postId){
//		//page 表示页数,rows表示一页条数
//		return postAndCommentService.queryCommentsByPostId(postId);
//	}
}
