public class Subtask extends Task {
    private int epicId;

    public Subtask(String name, String description, int id, Status status, int epicId) {
        super(name, description, id, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "Подзадача {" + System.lineSeparator() +
                "  Название: '" + name + "'," + System.lineSeparator() +
                "  Описание: '" + description + "'," + System.lineSeparator() +
                "  ID: " + id + "," + System.lineSeparator() +
                "  Статус: " + status + "," + System.lineSeparator() +
                "  Epic ID: " + epicId + System.lineSeparator() +
                '}';
    }

}
