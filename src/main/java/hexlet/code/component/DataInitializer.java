package hexlet.code.component;

import hexlet.code.model.Label;
import hexlet.code.model.TaskStatus;
import hexlet.code.model.User;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.TaskStatusRepository;

import hexlet.code.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final TaskStatusRepository taskStatusRepository;
    private final LabelRepository labelRepository;
    private final UserRepository userRepository;

    private PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var email = "hexlet@example.com";
        var userData = new User();
        userData.setEmail(email);
        userData.setPasswordDigest(encoder.encode("qwerty"));
        userRepository.save(userData);


        makeDefaultStatus("Draft", "draft");
        makeDefaultStatus("To_review", "to_review");
        makeDefaultStatus("To_be_fixed", "to_be_fixed");
        makeDefaultStatus("To_publish", "to_publish");
        makeDefaultStatus("Published", "published");

        makeDefaultLabel("feature");
        makeDefaultLabel("bug");

    }

    private void makeDefaultStatus(String name, String slug) {
        var status = new TaskStatus();
        status.setName(name);
        status.setSlug(slug);
        taskStatusRepository.save(status);
    }

    private void makeDefaultLabel(String name) {
        var label = new Label();
        label.setName(name);
        labelRepository.save(label);
    }
}
