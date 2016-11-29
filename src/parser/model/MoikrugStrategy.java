package parser.model;

import parser.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private String city;
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";

    public MoikrugStrategy(String city) {
        this.city = city;
    }

    @Override
    public List<Vacancy> getVacancies() {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;
        try {
            while (true) {
                Document document = getDocument(this.city, page++);
                if (document == null) break;
                Elements searchAttribute = document.getElementsByClass("job");
                if (searchAttribute.isEmpty()) break;
                for (Element element : searchAttribute) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setSiteName(document.title());
                    vacancy.setSalary(element.getElementsByClass("salary").first()
                            .getElementsByAttributeValue("title", "Зарплата").text());
                    vacancy.setTitle(element.getElementsByClass("info").first().getElementsByAttribute("title").text());
                    vacancy.setUrl("https://moikrug.ru" + element.getElementsByClass("title").first()
                            .getElementsByTag("a").attr("href"));
                    vacancy.setCity(element.getElementsByClass("location").text());
                    vacancy.setCompanyName(element.getElementsByClass("company_name").first().getElementsByTag("a").text());
                    vacancies.add(vacancy);
                }
            }
        }
        catch (IOException ignore) {}
        return vacancies;
    }
    protected Document getDocument(String city, int page) throws IOException {
        Document document = null;
        try {
            document = Jsoup.connect(String.format(URL_FORMAT, page, city))
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
                    .referrer("http://javarush.ru/").get();
        }
        catch (IOException ignore) {}
        return document;
    }
}
