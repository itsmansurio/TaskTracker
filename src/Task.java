public class Task {
    protected String name;
    protected String description;
    protected int id;
    protected Status status;

    public Task(String name, String description, int id, Status status) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Задача {" + System.lineSeparator() +
                "  Название: '" + name + '\'' + System.lineSeparator() +
                "  Описание: '" + description + '\''  + System.lineSeparator() +
                "  ID: " + id + System.lineSeparator() +
                "  Статус: " + status + System.lineSeparator() +
                '}';
    }

}