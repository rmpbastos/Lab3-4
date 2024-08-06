package javat;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
@Service
public class Catdao {

    JdbcTemplate template;

    public Catdao(JdbcTemplate template) {
        this.template = template;
    }

    public void setTemplate(JdbcTemplate template) {

        this.template = template;

    }

    public List<Category> display() throws ClassNotFoundException, SQLException {
        return template.query("SELECT * FROM category", (rs, row) -> {

            Category c = new Category();
            c.setCatcode(rs.getString(1));
            c.setCatdesc(rs.getString(2));
            return c;
        });
    }

    public int insertData(final Category category) {
        return template.update("INSERT INTO category VALUES(?,?)", category.getCatcode(), category.getCatdesc());
    }

    public int deleteData(String cat) {
        return template.update("DELETE FROM category WHERE catcode = ?", cat);
    }

    public int EditData(final Category category, String cat) {
        return template.update("UPDATE category SET catcode=?, catdesc = ? WHERE catcode = ?", category.getCatcode(), category.getCatdesc(), cat);
    }

    public List<Map<String, Object>> getcat(String cat) {
        return template.queryForList("SELECT * FROM category WHERE catcode = ?", cat);
    }

    public List<Map<String, Object>> getitem(String cat) {
        return template.queryForList("SELECT * FROM items WHERE catcode = ?", cat);
    }

}
