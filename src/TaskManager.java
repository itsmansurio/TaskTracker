import java.util.*;

public class TaskManager {
        private int nextId = 1;

        private final Map<Integer, Task> tasks = new HashMap<>();
        private final Map<Integer, Epic> epics = new HashMap<>();
        private final Map<Integer, Subtask> subtasks = new HashMap<>();

        public int generateId() {
            return nextId++;
        }

        public void addTask(Task task) {
            tasks.put(task.getId(), task);
        }

        public void addEpic(Epic epic) {
            epics.put(epic.getId(), epic);
        }

        public void addSubtask(Subtask subtask) {
            subtasks.put(subtask.getId(), subtask);

            // Добавляем id подзадачи в epic
            Epic epic = epics.get(subtask.getEpicId());
            if ( epic != null ) {
                epic.addSubtaskId(subtask.getId());
                updateEpicStatus(epic);
            }
        }

        private void updateEpicStatus(Epic epic) {
            List<Integer> subtaskIds = epic.getSubtaskIds();

            if(subtaskIds.isEmpty()) {
                epic.setStatus(Status.NEW);
                return;
            }

            boolean allNew = true;
            boolean allDone = true;

            for ( int id : subtaskIds ) {
                Subtask subtask = subtasks.get(id);
                if (subtask == null) continue;

                if (subtask.getStatus() != Status.NEW ) {
                    allNew = false;
                }
                if (subtask.getStatus() != Status.DONE) {
                    allDone = false;
                }
            }

            if (allDone) {
                epic.setStatus(Status.DONE);
            } else if (allNew) {
                epic.setStatus(Status.NEW);
            } else {
                epic.setStatus(Status.IN_PROGRESS);
            }
        }

        public void updateSubtask(Subtask subtask) {
                if (subtasks.containsKey(subtask.getId())) {
                    subtasks.put(subtask.getId(), subtask);
                    Epic epic = epics.get(subtask.getEpicId());
                    if (epic != null) {
                        updateEpicStatus(epic); // пересчёт статуса, но НЕ добавляем ID
                    }
                }
        }


        public List<Task> getAllTasks() {
            return new ArrayList<>(tasks.values());
        }

        public List<Epic> getAllEpics() {
            return new ArrayList<>(epics.values());
        }

        public List<Subtask> getAllSubtasks() {
            return new ArrayList<>(subtasks.values());
        }

        public List<Subtask> getSubtasksOfEpic(int epicId) {
            List<Subtask> result = new ArrayList<>();
            Epic epic = epics.get(epicId);
            if (epic != null ) {
                for ( int subtaskId : epic.getSubtaskIds() ) {
                    Subtask subtask = subtasks.get(subtaskId);
                    if (subtask != null) {
                        result.add(subtask);
                    }
                }
            }
            return result;
        }

        public void deleteTaskById(int id) {
            tasks.remove(id);
        }

        public void deleteEpicById(int id) {
            Epic epic = epics.remove(id);
            if (epic != null) {
                for (int subtaskId : epic.getSubtaskIds()) {
                    subtasks.remove(subtaskId);
                }
            }
        }

        public void deleteSubtaskById(int id) {
            Subtask subtask = subtasks.remove(id);
            if (subtask != null) {
                Epic epic = epics.get(subtask.getEpicId());
                if (epic != null) {
                    epic.removeSubtaskId(id);
                    updateEpicStatus(epic);
                }
            }
        }

}
