package JPA.demo.dto;

import lombok.Getter;
import lombok.Setter;

public class PostDTO {
    public int getIndex_number() {
        return index_number;
    }

    public void setIndex_number(int index_number) {
        this.index_number = index_number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaintext() {
        return maintext;
    }

    public void setMaintext(String maintext) {
        this.maintext = maintext;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    int index_number;
    String title;
    String maintext;
    String writer;
}
