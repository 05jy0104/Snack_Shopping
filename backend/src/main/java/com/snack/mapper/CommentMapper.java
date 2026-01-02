package com.snack.mapper;

import com.snack.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> findBySnackId(Integer snackId);
    List<Comment> findAll();
    void insert(Comment comment);
    void delete(Integer id);
}
