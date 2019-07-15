package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ExpenseService.ExpenseService.getExpenseCodeByProjectTypeAndName;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        String projectName = "projectName";
        Project project = new Project(ProjectType.INTERNAL, projectName);

        // when
        ExpenseType projectType = getExpenseCodeByProjectTypeAndName(project);

        // then
        Assertions.assertEquals(projectType, ExpenseType.INTERNAL_PROJECT_EXPENSE);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        String projectName = "Project A";
        Project project = new Project(ProjectType.EXTERNAL, projectName);

        // when
        ExpenseType projectType = getExpenseCodeByProjectTypeAndName(project);

        // then
        Assertions.assertEquals(projectType, ExpenseType.EXPENSE_TYPE_A);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        String projectName = "Project B";
        Project project = new Project(ProjectType.EXTERNAL, projectName);

        // when
        ExpenseType projectType = getExpenseCodeByProjectTypeAndName(project);

        // then
        Assertions.assertEquals(projectType, ExpenseType.EXPENSE_TYPE_B);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        String projectName = "Project C";
        Project project = new Project(ProjectType.EXTERNAL, projectName);

        // when
        ExpenseType projectType = getExpenseCodeByProjectTypeAndName(project);

        // then
        Assertions.assertEquals(projectType, ExpenseType.OTHER_EXPENSE);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() throws UnexpectedProjectTypeException {
        // given
        String projectName = "projectName";

        // when
        Project project = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE, projectName);
//        ExpenseType projectType = getExpenseCodeByProjectTypeAndName(project);

        // then
//        Assertions.assertThrows(new UnexpectedProjectTypeException("You enter invalid project type").getClass(),
//                () ->ExpenseService.getExpenseCodeByProjectTypeAndName(project));
        Assertions.assertThrows(UnexpectedProjectTypeException.class,
                () ->ExpenseService.getExpenseCodeByProjectTypeAndName(project));
    }
}