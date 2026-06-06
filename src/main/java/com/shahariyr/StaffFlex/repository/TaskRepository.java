package com.shahariyr.StaffFlex.repository;

import com.shahariyr.StaffFlex.entity.Employee;
import com.shahariyr.StaffFlex.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignee(Employee assignee);
}
