package parser.model;

import parser.view.View;
import parser.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алексей on 26.11.2016.
 */
public class Model {
    private Provider[] providers;
    private View view;

    public Model(View view, Provider[] providers) {
        if (providers == null || providers.length==0) throw new IllegalArgumentException();
        this.providers = providers;
        if ( view==null) throw new IllegalArgumentException();
        this.view = view;
    }
    public void selectCity(){
        List<Vacancy> vl = new ArrayList<>();
        for (Provider pr:providers){
            vl.addAll(pr.getJavaVacancies());
        }
        view.update(vl);
    }

}
