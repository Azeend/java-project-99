package hexlet.code.util;

import hexlet.code.model.Label;
import hexlet.code.model.Task;
import hexlet.code.model.TaskStatus;
import hexlet.code.model.User;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ModelsGenerator {
    private Model<User> testUser;
    private Model<Task> testTask;
    private Model<TaskStatus> testStatus;
    private Model<Label> testLabel;

    @Autowired
    private Faker faker;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostConstruct
    private void init() {
        testUser = Instancio.of(User.class)
                .ignore(Select.field(User::getId))
                .ignore(Select.field(User::getTasks))
                .supply(Select.field(User::getPasswordDigest), () -> faker.internet().password(3, 10))
                .supply(Select.field(User::getFirstName), () -> faker.name().firstName())
                .supply(Select.field(User::getLastName), () -> faker.name().lastName())
                .supply(Select.field(User::getEmail), () -> faker.internet().emailAddress())
                .toModel();

        testStatus = Instancio.of(TaskStatus.class)
                .ignore(Select.field(TaskStatus::getId))
                .ignore(Select.field(TaskStatus::getTasks))
                .supply(Select.field(TaskStatus::getName), () -> faker.name().name())
                .supply(Select.field(TaskStatus::getSlug), () -> faker.internet().slug())
                .toModel();

        testTask = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .ignore(Select.field(Task::getStatus))
                .ignore(Select.field(Task::getAssignee))
                .ignore(Select.field(Task::getLabels))
                .supply(Select.field(Task::getName), () -> faker.lorem().word())
                .supply(Select.field(Task::getIndex), () -> faker.number().positive())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence())
                .toModel();

        testLabel = Instancio.of(Label.class)
                .ignore(Select.field(Label::getId))
                .ignore(Select.field(Label::getTasks))
                .supply(Select.field(Label::getName), () -> faker.lorem().characters(3, 1000))
                .toModel();
    }
}
