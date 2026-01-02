package com.snack.service;

import com.snack.entity.Comment;
import java.util.List;

public interface CommentService {
    List<Comment> findBySnackId(Integer snackId);
    List<Comment> findAll();
    void add(Comment comment);
    void delete(Integer id);
}
