package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012346789";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        String url2 = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String bodyText2 = Request.Get(url2).execute().returnContent().asString();

        Gson mapper2 = new Gson();
        Course c = mapper.fromJson(bodyText2, Course.class);

        String url3 = "https://studies.cs.helsinki.fi/ohtustats/stats";
        String statsResponse = Request.Get(url3).execute().returnContent().asString();

        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(statsResponse).getAsJsonObject();

        //System.out.println("Oliot:");
        int th = 0;
        int numSub = 0;
        int num = 0;

        System.out.println("Kurssi: " + c.getName() + " " + c.getTerm());

        System.out.println("Opiskelijanumero: " + studentNr + "\n");
        for (Submission submission : subs) {
            System.out.println("viikko "+submission.getWeek()+ ": tehtyjä tehtäviä yhteensä: "+
                    submission.getExercises().size() + " (maksimi " + c.getMax(num) + ") "
                + ", aikaa kului " +submission.getHours()+ " tuntia, tehdyt tehtävät: " +
                submission.getExercises().toString());

            //System.out.println(submission);
            th += submission.getHours();
            numSub += submission.numExercises();
            num++;
        }

        int pal = 0;
        int teht = 0;
        String test = "";

        for (int z = 0; z < parsittuData.size(); z++)
        {
             int b = z+1;
             JsonElement el = parsittuData.get(Integer.toString(b));
             JsonObject o = el.getAsJsonObject();
             String p = o.get("students").toString();
             String t = o.get("exercise_total").toString();
             pal += Integer.parseInt(p);
             teht += Integer.parseInt(t);
        }


        System.out.println("\nYhteensä: " + numSub + " tehtävää " + th + " tuntia\n");
        System.out.println("kurssilla palautuksia yhteensä " + pal + ", palautettuja tehtäviä " + teht + " kpl");



    }
}
