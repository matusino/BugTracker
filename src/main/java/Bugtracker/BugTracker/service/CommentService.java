package Bugtracker.BugTracker.service;

import Bugtracker.BugTracker.model.Comment;
import Bugtracker.BugTracker.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment saveComment(Comment comment){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        comment.setDateOfSubmit(timestamp);
        return commentRepository.save(comment);
    }

    public List<Comment> listOfComments(Long userId){
       return commentRepository.findByBugId(userId);
    }

}
