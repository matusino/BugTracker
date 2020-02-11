package Bugtracker.BugTracker.service;

import Bugtracker.BugTracker.model.Bug;
import Bugtracker.BugTracker.repository.BugRepository;
import org.apache.tomcat.jni.Time;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BugService {
    private final BugRepository bugRepository;

    public BugService(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    public Bug saveBug(Bug bug){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        bug.setCreation(timestamp);
        return bugRepository.save(bug);
    }

    public Bug completeBug(Bug bug){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        bug.setCompletion(timestamp);
        return bugRepository.save(bug);
    }

    public List<Bug> findByProjectId(Long id){
        return bugRepository.findByProjectId(id);
    }

    public List<Bug> findAllBugs(){
        List<Bug> list = new ArrayList<>();
        bugRepository.findAll().forEach(list::add);
        return list;
    }

    public Bug findByBugId(Long id){
        return bugRepository.findById(id).orElse(null);
    }
}
