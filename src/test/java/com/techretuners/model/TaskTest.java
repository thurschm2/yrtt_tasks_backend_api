package com.techretuners.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.techreturners.model.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TaskTest {

    @Test
    @DisplayName("Test task description GET")
    public void testTaskDescription() {
        Task t = new Task("abc123","some task description");
        assertEquals("some task description", t.getDescription() , "task description was incorrect");
    }

    @Test
    @DisplayName("CHeck completed is false by default")
    public void testDefaulrCompletedStatus() {
        Task t = new Task("abc123","some description");
        assertFalse(t.isCompleted() ,"task status was not false by default");
    }

    @Test
    @DisplayName("Ceeck Task ID GET")
    public void testTaskId() {
        Task t = new Task("abc123","some description", false);
        assertEquals("abc123", t.getTaskId() , "task ID was incorrect");
    }

}
