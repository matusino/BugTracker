package Bugtracker.BugTracker.service;

import Bugtracker.BugTracker.model.Bug;
import Bugtracker.BugTracker.repository.BugRepository;
import org.apache.tomcat.jni.Time;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class BugService {
    private final BugRepository bugRepository;

    public BugService(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    public Bug saveBug(Bug bug){
        Timestamp currentTimeStamp = new Timestamp(Calendar.getInstance().getTime().getTime());
        return bugRepository.save(bug);
    }
}
