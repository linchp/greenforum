package cn.greenforum.postandcomment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.pojo.Comment;
import com.jt.common.pojo.Flow;
import com.jt.common.pojo.Post;


public interface PostAndCommentMapper {

	void insertPost(Post post);

	int queryPostsTotalCount();
	int queryCommentsTotalCount();

	List<Post> queryPosts(@Param("start") int start,@Param("rows") Integer rows);

	void insertComment(Comment comment);

	List<Post> queryPostByPostId(String postId);

	List<Comment> queryCommentsByPostId(String postId);

	void insertFlow(Flow flow);

	List<Post> queryPostsByUserId(String userId);

	List<Post> queryPostsFlowByUserId(String userId);

	List<Post> queryPostsCommentByUserId(String userId);

	

}
