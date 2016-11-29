package parser.model;

import parser.vo.Vacancy;

import java.util.List;

/**
 * Created by Алексей on 26.11.2016.
 */
public interface Strategy {
    List<Vacancy> getVacancies();
}
