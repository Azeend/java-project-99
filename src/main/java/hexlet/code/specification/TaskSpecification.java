package hexlet.code.specification;

import hexlet.code.dto.TaskParamsDto;
import hexlet.code.model.Task;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TaskSpecification {
    public Specification<Task> build(TaskParamsDto params) {
        return withAssigneeId(params.getAssigneeId())
                .and(withLabelId(params.getLabelId()))
                .and(withStatus(params.getStatus()))
                .and(withTitleCont(params.getTitleCont()));
    }

    private Specification<Task> withAssigneeId(Long id) {
        return ((root, query, criteriaBuilder) -> id == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("assignee").get("id"), id));

    }

    private Specification<Task> withTitleCont(String substring) {
        return (root, query, criteriaBuilder) -> substring == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + substring + "%");
    }

    private Specification<Task> withStatus(String status) {
        return (root, query, criteriaBuilder) -> status == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("status").get("slug"), status);
    }

    private Specification<Task> withLabelId(Long id) {
        return (root, query, criteriaBuilder) -> id == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("labels").get("id"), id);
    }
}
