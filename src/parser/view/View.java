package parser.view;

import parser.Controller;
import parser.vo.Vacancy;

import java.util.List;

/**
 * Created by Алексей on 26.11.2016.
 */
public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
