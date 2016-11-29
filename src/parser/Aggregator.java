package parser;

import parser.model.HHStrategy;
import parser.model.Model;
import parser.model.MoikrugStrategy;
import parser.model.Provider;
import parser.view.HtmlView;

/**
 * Created by Алексей on 26.11.2016.
 */
public class Aggregator {

    public static void main(String[] args) {
        Provider[] pr ={new Provider(new HHStrategy("Минск")), new Provider(new MoikrugStrategy("Минск"))};
        HtmlView view = new HtmlView();
        Model model = new Model(view,pr);
        Controller controller = new Controller(model);

        view.setController(controller);

        view.userCitySelectEmulationMethod();

    }
}
