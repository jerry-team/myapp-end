package com.jerry.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jerry.entity.Comment;
import com.jerry.entity.User;
import com.jerry.mapper.CommentMapper;
import com.jerry.mapper.UserMapper;
import com.jerry.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jerry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jerry
 * @since 2022-09-01
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<Comment> listByCommodityId(Integer commodityId) {
        QueryWrapper<Comment> commentQW = new QueryWrapper<>();
        List<Comment> commentList = commentMapper.selectList(commentQW.eq("commodity_id",commodityId));
        List<Comment> commentTree = buildTreeComment(commentList);
        return convert(commentTree,null,0);
    }

    private List<Comment> buildTreeComment(List<Comment> commentList)
    {
        List<Comment> finalComments = new ArrayList<>();
        for(Comment comment:commentList){
            for(Comment reply:commentList){
                if(reply.getParentId().equals(comment.getId())){
                    comment.getReplyList().add(reply);
                }
            }
            if(comment.getParentId() == 0){
                finalComments.add(comment);
            }
        }
        return finalComments;
    }

    private List<Comment> convert(List<Comment> commentTree,String parentName,Integer flag)
    {
        QueryWrapper<User> userQW = new QueryWrapper<>();
        commentTree.forEach(c ->{
//            System.out.println("id:" + c.getId() + "  userId:" + c.getUserId());
            User user = userService.getById(c.getUserId());
//            System.out.println("username: " + user);
            c.setUser_name(user.getNickname());
            if(flag <= 1){
                c.setParent_name(null);
            }
            else {
                c.setParent_name(parentName);
            }
            if(c.getReplyList().size() > 0){
                convert(c.getReplyList(),c.getUser_name(),flag+1);
            }
        });
        return commentTree;
    }
}
