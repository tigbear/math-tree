package com.geneology;

import org.jsoup.*;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by tanya on 1/30/17.
 */
public class MathematicianParser {

    public MathematicianParser() {

    }

    public Mathematician parse(String fileName) {
        Mathematician mathematician = null;
        int id = 0;
        String name = "";
        String almaMater = "";
        int year = 0;
        String flag = "";
        try {
            String text = Files.toString(new File(fileName), Charsets.UTF_8);
            Document doc = Jsoup.parse(text);
            name = getName(doc);
            id = getId(doc);
            almaMater = getAlmaMater(doc);
            year = getYear(doc);
            flag = getFlag(doc);
            mathematician = new Mathematician(id, name, almaMater, year, flag);
            LinkedList<Student> students = getStudents(doc);
            mathematician.setStudents(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mathematician;
    }

    private LinkedList<Student> getStudents(Document doc) {
        LinkedList<Student> students = new LinkedList<Student>();
        Elements children = null;
        try {
            Elements table = doc.getElementsByTag("table");
            if (table.size() != 0) {
                children = table.get(0).children().get(0).children();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (children == null) {
            return students;
        }
        for (int index=1; index<children.size(); index++) {
            String studentName = "";
            int id = 0;
            int year = 0;
            int studentCount = 0;
            String school = "";
            Element row = children.get(index);
            Elements child = row.children();
            try {
                Element name = child.get(0);
                if (name.tag().getName().equals("td")) {
                    studentName = name.child(0).text();
                    String idStr = name.child(0).attributes().asList().get(0).getValue();
                    id = Integer.parseInt(idStr.split("id=")[1]);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                school = child.get(1).text();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                year = Integer.parseInt(child.get(2).text());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                studentCount = Integer.parseInt(child.get(3).text());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Student student = new Student(id, studentName, school, year, studentCount);
            students.add(student);
        }
        Collections.sort(students);
        return students;
    }

    private String getName(Document doc) {
        String name = "";
        Elements h2 = doc.getElementsByTag("h2");
        if(h2.size() == 1) {
            Element el = h2.get(0);
            name = el.text().trim();
        }
        return name;
    }

    private int getId(Document doc) {
        int id = 0;
        Elements as = doc.getElementsByTag("a");
        try {
            for (Element a : as) {
                if (a.text().equals("update form")) {
                   //href="submit-data.php?id=31332&amp;edit=0">update form</a>
                    String href = a.attr("href");
                    //id.php?id=31332&fChrono=1
                    String[] split = href.split("id=");
                    split = split[1].split("&");
                    id = Integer.parseInt(split[0]);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    private String getAlmaMater(Document doc) {
        String almaMater = "";
        //<span style="color:#006633; margin-left: 0.5em">The Johns Hopkins University</span>
        Elements al = doc.getElementsByTag("span");
        for (Element el : al) {
            String styleValue = el.attr("style").replace(" ","").replace("\n","");
            //color:#006633; margin-left: 0.5em
            if (styleValue.equals("color:#006633;margin-left:0.5em")) {
                almaMater = el.text().trim();
            }
        }
        return almaMater;
    }

    private int getYear(Document doc) {
        int year = 0;
        Elements elements = doc.getElementsByTag("span");
        for (Element element : elements) {
            String styleValue = element.attr("style").replace(" ","").replace("\n","");
            if (styleValue.equals("margin-right:0.5em")) {
                String[] split = element.text().split(" ");
                try {
                    year = Integer.parseInt(split[split.length - 1]);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return year;
    }

    private String getFlag(Document doc) {
        //<img src="img/flags/UnitedStates.gif" alt="UnitedStates" title="UnitedStates">
        String flag = "";
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            if(element.hasAttr("title")) {
                flag = element.attr("title");
            }
        }
        return flag;
    }
}
