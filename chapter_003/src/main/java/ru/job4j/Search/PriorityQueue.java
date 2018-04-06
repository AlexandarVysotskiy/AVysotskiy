package ru.job4j.Search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставляет в нужную позицию элемент.
     */
    public void put(Task task) {
        int priority = 0;
        if (!tasks.isEmpty()) {
            for (Task index : tasks) {
                if (index.getPriority() > task.getPriority()) {
                    tasks.add(tasks.indexOf(index), task);
                    priority++;
                    break;
                }
            }
        } else {
            tasks.add(priority++, task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}