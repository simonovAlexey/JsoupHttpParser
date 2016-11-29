package parser.model;

import parser.vo.Vacancy;

import java.util.List;

/**
 * Created by Алексей on 26.11.2016.
 */
public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    public List<Vacancy> getJavaVacancies(){
        return strategy.getVacancies();
    }
}
