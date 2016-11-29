package parser.model;

import parser.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алексей on 26.11.2016.
 *     // https://hh.ru/search/vacancy?text=java&area=1002&page=1
 *
 */
public class HHStrategy implements Strategy {
    private String city;
//    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
    private static final String URL_FORMAT = "https://hh.ru/search/vacancy?area=1002&text=java&page=%d";



    @Override
    public List<Vacancy> getVacancies() {

        List<Vacancy> vacancies = new ArrayList<>();
        try {
            int pageNumber = 0;
            Document doc;
            while (true) {
                doc = getDocument(city, pageNumber++);
                if (doc == null) break;
                Elements elements;
                if (pageNumber < 3) elements = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy vacancy-serp__vacancy_premium");
                else elements = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elements.size() == 0) break;
                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setUrl(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
                    vacancy.setTitle(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                    vacancy.setCity(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                    vacancy.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                    vacancy.setSiteName("http://hh.ru");
                    Element salaryElement = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").first();
                    String salary = "";
                    if (salaryElement != null) {
                        salary = salaryElement.text();
                    }
                    vacancy.setSalary(salary);
                    vacancies.add(vacancy);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String s=null;
        return vacancies;
    }

    protected  Document getDocument(String searchString, int page) throws IOException{


//            String url = String.format(URL_FORMAT, searchString, page);
            String url = String.format(URL_FORMAT, page);

            String referrer = "https://hh.ru/search/vacancy?text=java";
            String myUserAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
            Document doc = Jsoup.connect(url).userAgent(myUserAgent).referrer(referrer).get();
            return doc;
    }
}
