package com.suman.crm.service;

import com.suman.crm.model.Activity;
import com.suman.crm.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity updateActivity(Long id, Activity updatedActivity) {
        return activityRepository.findById(id).map(activity -> {
            activity.setType(updatedActivity.getType());
            activity.setSubject(updatedActivity.getSubject());
            activity.setDueDate(updatedActivity.getDueDate());
            activity.setOwner(updatedActivity.getOwner());
            return activityRepository.save(activity);
        }).orElseGet(() -> {
            updatedActivity.setId(id);
            return activityRepository.save(updatedActivity);
        });
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
