package GitHub;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Помыть машину", "Использовать пену", taskManager.generateId(), Status.NEW);
        taskManager.addTask(task1);

        Epic epic1 = new Epic("Организовать праздник", "Мамин ДР", taskManager.generateId());
        taskManager.addEpic(epic1);

        Subtask sub1 = new Subtask("Купить торт", "Шоколадный", taskManager.generateId(), Status.NEW, epic1.getId());
        Subtask sub2 = new Subtask("Позвать гостей", "Позвонить", taskManager.generateId(), Status.NEW, epic1.getId());
        taskManager.addSubtask(sub1);
        taskManager.addSubtask(sub2);

        System.out.println("--- Эпики ---");
        for (Epic epic : taskManager.getAllEpics()) {
            System.out.println(epic);
        }

        System.out.println("--- Подзадачи ---");
        for (Subtask subtask : taskManager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("--- Задачи ---");
        for (Task task : taskManager.getAllTasks()) {
            System.out.println(task);
        }

        sub1.setStatus(Status.DONE);
        taskManager.updateSubtask(sub1);
        for ( Epic epic : taskManager.getAllEpics() ) {
            System.out.println("\nПосле обновления подзадачи: \n" + epic);
        }

        for ( Subtask subtask : taskManager.getSubtasksOfEpic(epic1.getId()) ) {
            System.out.println("\nПодзадачи эпика: \n" + subtask);
        }

    }
}