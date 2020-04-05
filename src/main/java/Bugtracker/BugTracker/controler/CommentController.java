package Bugtracker.BugTracker.controler;

import Bugtracker.BugTracker.model.Bug;
import Bugtracker.BugTracker.model.Comment;
import Bugtracker.BugTracker.service.BugService;
import Bugtracker.BugTracker.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final BugService bugService;

    public CommentController(CommentService commentService, BugService bugService) {
        this.commentService = commentService;
        this.bugService = bugService;
    }

    @RequestMapping(value = "/addComment/{bugId}")
    public String viewComment(Model model, @PathVariable Long bugId){
        Bug bug = bugService.findByBugId(bugId);
        model.addAttribute("comment", new Comment());
        model.addAttribute("bug", bug);

        List<Comment> comments = commentService.listOfComments(bugId);
        model.addAttribute("comments", comments);
        return "bug/addcommenttobug";
    }

    @RequestMapping(value = "/add-comment-to-bug/{bugId}", method = RequestMethod.POST)
    public String addComment (@Valid @ModelAttribute Comment comment, BindingResult bindingResult, @PathVariable Long bugId){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            for(ObjectError error : errors) {
                System.out.println("This is the error: " +error);
            }
            return "error";
        }
        Bug bugFromDB = bugService.findByBugId(bugId);

        comment.setBug(bugFromDB);
        commentService.saveComment(comment);

        return "redirect:/addComment/{bugId}";
    }



}
