package com.snack.service.impl;

import com.snack.entity.Comment;
import com.snack.mapper.CommentMapper;
import com.snack.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> findBySnackId(Integer snackId) {
        return commentMapper.findBySnackId(snackId);
    }

    @Override
    public List<Comment> findAll() {
        return commentMapper.findAll();
    }

    @Override
    public void add(Comment comment) {
        comment.setCreateTime(new Date());
        commentMapper.insert(comment);
    }

    @Override
    public void delete(Integer id) {
        commentMapper.delete(id);
    }
}
