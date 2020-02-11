package Bugtracker.BugTracker.repository;

import Bugtracker.BugTracker.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
   List<Comment> findByBugId(Long userId);
}
