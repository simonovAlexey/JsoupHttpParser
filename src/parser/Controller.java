package parser;

import parser.model.Model;

/**
 * Created by Алексей on 26.11.2016.
 */
public class Controller {

    private Model model;

    public Controller(Model model) {
        if( model==null) throw new IllegalArgumentException();
        this.model = model;
    }

    public void onCitySelect(){
        model.selectCity();
    }
}
