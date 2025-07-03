import java.util.ArrayList;
import java.util.List;

public class Epic extends Task  {
    private List<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name, String description, int id) {
        super(name, description, id, Status.NEW);
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void addSubtaskId( int subtaskId ) {
        if (!subtaskIds.contains(subtaskId)) {
            subtaskIds.add(subtaskId);
        }
    }

    public void removeSubtaskId(int subtaskId) {
        subtaskIds.remove((Integer) subtaskId);
    }

    public void clearSubtaskId(int subtaskId) {
        subtaskIds.clear();
    }

    @Override
    public String toString() {
        return "Эпик {" + System.lineSeparator() +
                "  Название: '" + name + "'," + System.lineSeparator() +
                "  Описание: '" + description + "'," + System.lineSeparator() +
                "  ID: " + id + "," + System.lineSeparator() +
                "  Статус: " + status + "," + System.lineSeparator() +
                "  Подзадачи: " + subtaskIds + System.lineSeparator() +
                '}';
    }

}
