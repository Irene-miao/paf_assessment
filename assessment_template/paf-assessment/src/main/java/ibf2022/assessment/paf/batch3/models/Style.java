package ibf2022.assessment.paf.batch3.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

// DO NOT MODIFY THIS FILE.

public class Style {

    private int styleId;
    private String name;
    private int beerCount;

    public int getStyleId() {
        return styleId;
    }
    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBeerCount() {
        return beerCount;
    }
    public void setBeerCount(int beerCount) {
        this.beerCount = beerCount;
    }

    @Override
    public String toString() {
        return "Style [styleId=" + styleId + ", name=" + name + ", beerCount=" + beerCount + "]";
    }

    public static Style create(SqlRowSet rs){
        Style style = new Style();
        style.setName(rs.getString("style_name"));
        style.setBeerCount(rs.getInt("beer_count"));

        return style;
    }

    public JsonObject toJson(){
        return Json.createObjectBuilder()
        .add("style_name", getName())
        .add("beer_count", getBeerCount())
        .build();
    }
}
